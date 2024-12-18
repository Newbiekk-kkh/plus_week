package com.example.demo.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {
    @Test
    @DisplayName("RawPassword가 Encode 했을때 반환된 EncodedPassword와 다른지 확인")
    void rawPasswordEqualsEncodedPassword() {
        // given
        String rawPassword = "abcd";

        // when
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        // then
        System.out.println(encodedPassword);
        assertFalse(rawPassword.equals(encodedPassword));
    }

    @Test
    @DisplayName("RawPassword 와 EncodedPassword가 match 되는지 확인")
    void matchesRawPasswordAndEncodedPassword() {
        // given
        String rawPassword = "abcd";

        // when
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        // then
        assertTrue(PasswordEncoder.matches(rawPassword, encodedPassword));
    }

    @Test
    @DisplayName("똑같은 rawPassword를 각각 인코딩한 비밀번호가 다른지 확인")
    void mismatchWhenRawPasswordEncodeDifferentPasswordEncoder() {
        // given
        String rawPassword = "abcd";

        // when
        String encodedPassword1 = PasswordEncoder.encode(rawPassword);
        String encodedPassword2 = PasswordEncoder.encode(rawPassword);

        // then
        System.out.println(encodedPassword1);
        System.out.println(encodedPassword2);
        assertFalse(encodedPassword1.equals(encodedPassword2));
    }

    @Test
    @DisplayName("rawPassword가 아닌 다른 패스워드를 입력했을때 match 메서드가 false를 반환하는지 확인")
    void mismatchAnotherPasswordAndEncodedPassword() {
        // given
        String rawPassword = "abcd";
        String anotherPassword = "efgh";

        // when
        String encodedPassword = PasswordEncoder.encode(rawPassword);

        // then
        assertFalse(PasswordEncoder.matches(anotherPassword, encodedPassword));
    }
}