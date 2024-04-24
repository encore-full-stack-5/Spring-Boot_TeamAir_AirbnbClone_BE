package com.air.wishlist.domain.dto.response;

import com.air.wishlist.domain.dto.WishlistDto;
import com.air.wishlist.domain.entity.Favorite;

public record UserWishlistResponse(
        Integer id, String name
) {
    public static UserWishlistResponse from(WishlistDto wishlistDto) {

    }
}
