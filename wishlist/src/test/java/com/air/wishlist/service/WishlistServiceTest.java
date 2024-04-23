package com.air.wishlist.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class WishlistServiceTest {

    @Value("${spring.datasource.wishlistName}")
    public String wishlistName;

    @Test
    void addRoomToWishlist() {
    }

    @Test
    void addWishlist() {
        Assertions.assertEquals("add", wishlistName);
    }
}