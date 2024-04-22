package com.air.wishlist.domain.repository;

import com.air.wishlist.domain.entity.Favorate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavorateRepository
        extends JpaRepository<Favorate, Integer> {
}
