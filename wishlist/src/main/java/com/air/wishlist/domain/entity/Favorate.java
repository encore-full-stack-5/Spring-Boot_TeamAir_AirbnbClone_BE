package com.air.wishlist.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Favorates")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favorate {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FAVORATE_ID")
    private Integer id;
    @Column(name="room_id")
    private Integer roomId;
    // 룸 머지되면 JoinColumn 처리 해야됨

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="wishlist_id")
    private Wishlist wishlist;

}
