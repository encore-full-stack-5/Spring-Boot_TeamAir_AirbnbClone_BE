package com.air.comment.domain.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CommentRequest(
        Integer commentId,

        Integer userId,

        Integer roomId,

        String userName,

        double starAvg,

        String comment,

        LocalDate commentCreatedAt
) {
}
