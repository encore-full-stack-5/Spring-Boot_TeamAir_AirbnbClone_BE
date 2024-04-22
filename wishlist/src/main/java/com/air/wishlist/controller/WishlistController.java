package com.air.wishlist.controller;
// 숙소 추가, 숙소 삭제, 저장, 위시리스트 생성, 위시리스트 삭제,
import com.air.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping

}
