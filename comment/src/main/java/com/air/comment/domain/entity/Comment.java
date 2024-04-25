package com.air.comment.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="COMMENT_ID")
    private Integer commentId;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "ROOM_ID")
    private Integer roomId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "COMMENT_STAR")
    private double starAvg;
    @Column(name="COMMENT_CONTENT")
    private String comment;
    @Column(name="COMMENT_CREATED_AT", columnDefinition = "time with time zone DEFAULT now()")
    private LocalDate commentCreatedAt;

    @OneToOne(mappedBy = "comment")
    private HostComment hostComment;

    public void setComment(String newComment) {
    }
}
