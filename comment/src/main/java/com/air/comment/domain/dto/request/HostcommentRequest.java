package com.air.comment.domain.dto.request;

import java.time.LocalDate;

public record HostcommentRequest(

        Integer roomId,

        String commentContent,

        Integer commentStar

) {
}
