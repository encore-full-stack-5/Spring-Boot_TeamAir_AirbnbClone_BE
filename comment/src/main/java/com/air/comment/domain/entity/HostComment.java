package com.air.comment.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "HostComment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "ROOM_ID")
    private Integer roomId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "COMMENT_CONTENT")
    private String commentContent;
    @Column(name = "COMMENT_STAR")
    private Integer commentStar;
    @Column(name = "COMMENT_CREATED_AT")
    private LocalDate commentCreatedAt;

    @OneToOne
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    public void setHostcomment(String newHostComment) {
    }
}
