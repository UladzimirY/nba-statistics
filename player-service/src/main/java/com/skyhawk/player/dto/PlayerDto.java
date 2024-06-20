package com.skyhawk.player.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlayerDto (
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Long id,
        String name,
        Long teamId
) {
}
