package com.example.demo.domain.reservation.service;

import com.example.demo.domain.item.entity.Item;
import com.example.demo.domain.rentalLog.entity.RentalLog;
import com.example.demo.domain.reservation.dto.ReservationResponseDto;
import com.example.demo.domain.reservation.enums.ReservationStatus;
import com.example.demo.domain.reservation.entity.Reservation;
import com.example.demo.domain.reservation.repository.ReservationRepository;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.item.repository.ItemRepository;
import com.example.demo.domain.user.repository.UserRepository;
import com.example.demo.domain.rentalLog.service.RentalLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.demo.domain.reservation.enums.ReservationStatus.PENDING;


@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final RentalLogService rentalLogService;

    public ReservationService(ReservationRepository reservationRepository,
                              ItemRepository itemRepository,
                              UserRepository userRepository,
                              RentalLogService rentalLogService) {
        this.reservationRepository = reservationRepository;
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.rentalLogService = rentalLogService;
    }

    // TODO: 1. 트랜잭션 이해
    @Transactional
    public void createReservation(Long itemId, Long userId, LocalDateTime startAt, LocalDateTime endAt) {
        // 쉽게 데이터를 생성하려면 아래 유효성검사 주석 처리
//        List<Reservation> haveReservations = reservationRepository.findConflictingReservations(itemId, startAt, endAt);
//        if(!haveReservations.isEmpty()) {
//            throw new ReservationConflictException("해당 물건은 이미 그 시간에 예약이 있습니다.");
//        }

        Item item = itemRepository.findByIdOrElseThrow(itemId);
        User user = userRepository.findByIdOrElseThrow(userId);
        Reservation reservation = new Reservation(item, user, PENDING, startAt, endAt);
        Reservation savedReservation = reservationRepository.save(reservation);

        RentalLog rentalLog = new RentalLog(savedReservation, "로그 메세지", "CREATE");
        rentalLogService.save(rentalLog);
    }

    // TODO: 3. N+1 문제
    @Transactional(readOnly = true)
    public List<ReservationResponseDto> getReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return reservations.stream().map(reservation -> {
            User user = reservation.getUser();
            Item item = reservation.getItem();

            return new ReservationResponseDto(
                    reservation.getId(),
                    user.getNickname(),
                    item.getName(),
                    reservation.getStartAt(),
                    reservation.getEndAt()
            );
        }).toList();
    }

    // TODO: 5. QueryDSL 검색 개선
    public List<ReservationResponseDto> searchAndConvertReservations(Long userId, Long itemId) {

        List<Reservation> reservations = searchReservations(userId, itemId);

        return convertToDto(reservations);
    }

    public List<Reservation> searchReservations(Long userId, Long itemId) {
        return reservationRepository.searchReservationsWithDynamicQuery(userId, itemId);
    }

    private List<ReservationResponseDto> convertToDto(List<Reservation> reservations) {
        return reservations.stream()
                .map(reservation -> new ReservationResponseDto(
                        reservation.getId(),
                        reservation.getUser().getNickname(),
                        reservation.getItem().getName(),
                        reservation.getStartAt(),
                        reservation.getEndAt()
                ))
                .toList();
    }

    // TODO: 7. 리팩토링
    @Transactional
    public ReservationResponseDto updateReservationStatus(Long reservationId, ReservationStatus status) {
        Reservation reservation = reservationRepository.findByIdOrElseThrow(reservationId);

        if (ReservationStatus.APPROVED.equals(status)) {
            if (!ReservationStatus.PENDING.equals(reservation.getStatus())) {
                throw new IllegalArgumentException("PENDING 상태만 APPROVED로 변경 가능합니다.");
            }
        }

        if (ReservationStatus.CANCELED.equals(status)) {
            if (ReservationStatus.EXPIRED.equals(reservation.getStatus())) {
                throw new IllegalArgumentException("EXPIRED 상태인 예약은 취소할 수 없습니다.");
            }
        }

        if (ReservationStatus.EXPIRED.equals(status)) {
            if (!ReservationStatus.PENDING.equals(reservation.getStatus())) {
                throw new IllegalArgumentException("PENDING 상태만 EXPIRED로 변경 가능합니다.");
            }
        }

        reservation.updateStatus(status);

        return new ReservationResponseDto(reservation.getId(), reservation.getUser().getNickname(), reservation.getItem().getName(), reservation.getStartAt(), reservation.getEndAt());
    }
}
