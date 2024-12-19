package com.example.demo.domain.reservation.repository;

import com.example.demo.domain.reservation.entity.Reservation;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.demo.domain.reservation.entity.QReservation.reservation;

@RequiredArgsConstructor
public class ReservationRepositoryQueryImpl implements ReservationRepositoryQuery {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Reservation> searchReservationsWithDynamicQuery(Long userId, Long itemId) {
        BooleanBuilder builder = new BooleanBuilder();

        if (userId != null) {
            builder.and(reservation.user.id.eq(userId));
        }

        if (itemId != null) {
            builder.and(reservation.item.id.eq(itemId));
        }

        return jpaQueryFactory
                .select(reservation)
                .from(reservation)
                .join(reservation.user).fetchJoin()
                .join(reservation.item).fetchJoin()
                .where(builder)
                .fetch();
    }
}
