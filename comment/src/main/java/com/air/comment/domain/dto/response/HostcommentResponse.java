package com.air.comment.domain.dto.response;

import java.time.LocalDate;

public record HostcommentResponse(
        Integer userId,

        Integer roomId,

        String userName,

        String commentContent,

        Integer commentStar,

        LocalDate commentCreatedAt
) {
}
