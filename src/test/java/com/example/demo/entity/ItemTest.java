package com.example.demo.entity;

import com.example.demo.domain.item.entity.Item;
import com.example.demo.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Mock
    private User manager;

    @Mock
    private User owner;

    @Test
    @DisplayName("status 의 nullable = false 가 제대로 동작하는지 확인")
    void StatusNotNullable() {
        // given
        Item item = new Item("야구방망이", "단단함", manager, owner);

        // when, then
        assertThrows(IllegalArgumentException.class, () -> item.setStatus(null));
    }

}