package com.air.comment.domain.dto;

public record CommentDto(
        Integer id,
        String userName,
        double starAvg,
        String comment
        ) {

}
