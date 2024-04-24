package com.air.comment.domain.dto.request;

public record CommentRequest(
        Integer id,
        String userName,
        double starAvg,
        String comment
) {
}
