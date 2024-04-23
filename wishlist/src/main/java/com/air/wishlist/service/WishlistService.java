package com.air.wishlist.service;

import com.air.wishlist.domain.dto.WishlistDto;
import com.air.wishlist.domain.dto.request.FavoriteRequest;
import com.air.wishlist.domain.dto.request.WishlistRequest;
import com.air.wishlist.domain.entity.Favorite;
import com.air.wishlist.domain.entity.Wishlist;
import com.air.wishlist.domain.repository.FavoriteRepository;
import com.air.wishlist.domain.repository.WishlistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final FavoriteRepository favoriteRepository;
    private final WishlistRepository wishlistRepository;


    public WishlistDto addRoomToWishlist(FavoriteRequest request) {
        Wishlist wishlist = Wishlist.builder()
                .id(request.wishlistId())
                .build();
        Favorite favorite = Favorite.builder()
                .roomId(request.roomId())
                .roomName(request.roomName())
                .roomType(request.roomType())
                .starAVG(request.starAvg())
                .commentCount(request.commentCount())
                .cityName(request.cityName())
                .wishlist(wishlist)
                .build();
        Favorite save = favoriteRepository.save(favorite);
        return new WishlistDto(save.getId(), save.getRoomName());
    }

    public void deleteWishlist(int id) {
        wishlistRepository.deleteById(id);
    }

    public void addWishlist(WishlistRequest request){
        Wishlist wishlist = Wishlist.builder()
                .name(request.wishlistName())
                .id(request.userId())
                .build();
        wishlistRepository.save(wishlist);

//        Favorite favorite = Favorite.builder()
//                .roomId(request.roomId())
//                .roomName(request.roomName())
//                .roomType(request.roomType())
//                .starAVG(request.starAvg())
//                .commentCount(request.commentCount())
//                .cityName(request.cityName())
//                .wishlist(wishlist)
//                .build();
//        Favorite save = favoriteRepository.save(favorite);
//
    }
}
