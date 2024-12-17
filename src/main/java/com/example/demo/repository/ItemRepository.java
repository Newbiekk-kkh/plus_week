package com.example.demo.repository;

import com.example.demo.entity.Item;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    default Item findByIdOrElseThrow(Long itemId) {
        return findById(itemId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 ID의 상품을 찾을 수 없습니다.") );
    }
}
