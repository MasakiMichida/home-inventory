package io.github.homeinventory.dto;

public record ItemResponseDto(
        int id,
        String name,
        int quantity,
        java.time.OffsetDateTime createdAt,
        java.time.OffsetDateTime updatedAt) {
}
