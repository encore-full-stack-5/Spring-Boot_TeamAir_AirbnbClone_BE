package com.air.room.config;

import io.jsonwebtoken.Claims;

public record TokenInfo(
        Integer id,
        String name
) {
    public static TokenInfo fromClaims(Claims claims) {
        Integer id = claims.get("id", Integer.class);
        String name = claims.get("name", String.class);
        return new TokenInfo(id, name);
    }
}
