package com.air.room.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ROOMS")
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rooms_room_id_seq")
    @SequenceGenerator(name = "rooms_room_id_seq", sequenceName = "rooms_room_id_seq", allocationSize = 1)
    @Column(name = "ROOM_id", nullable = false)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

//    @Column(name = "user_nickname", nullable = false)
//    private String userNickname;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "ROOM_name", nullable = false)
    private String name;

    @Column(name = "ROOM_desc", nullable = false)
    private String desc;

    @Column(name = "ROOM_type", nullable = false)
    private Integer type;

    @Column(name = "ROOM_max_people", nullable = false)
    private Integer maxPeople;

    @Column(name = "ROOM_reserve_option", nullable = false)
    private Integer reserveOption;

    @Column(name = "ROOM_bedroom_num", nullable = false)
    private Integer bedroomNum;

    @Column(name = "ROOM_bed_num", nullable = false)
    private Integer bedNum;

    @Column(name = "ROOM_bathroom_num", nullable = false)
    private Integer bathroomNum;

    @Column(name = "ROOM_price", nullable = false)
    private Integer price;

    @Column(name = "ROOM_cleaning_price", nullable = false)
    private Integer cleaningPrice;

    @Column(name = "ROOM_check_in_time", nullable = false)
    private Time checkInTime;

    @Column(name = "ROOM_check_out_time", nullable = false)
    private Time checkOutTime;

    @Column(name = "ROOM_using_rule", nullable = false)
    private String usingRule;

    @Column(name = "ROOM_reserve_start_at")
    private LocalDate reserveStartAt;

    @Column(name = "ROOM_reserve_end_at", nullable = false)
    private LocalDate reserveEndAt;

    @OneToMany(mappedBy = "room")
    private List<RoomAccessibility> roomAccessibility;
    @OneToMany(mappedBy = "room")
    private List<RoomAmenity> roomAmenities;
    @OneToMany(mappedBy = "room")
    private List<RoomUniqueAmenity> roomUniqueAmenities;

    @OneToMany(mappedBy = "room")
    private List<RoomLocation> roomLocation;

    @OneToMany(mappedBy = "room")
    private List<SafetySupply> safetySupply;
}
