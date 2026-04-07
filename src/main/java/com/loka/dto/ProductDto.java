package com.loka.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDto {

    @Data
    public static class CreateProductRequest {
        @NotBlank(message = "Product name is required")
        private String name;

        private String description;

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be greater than 0")
        private BigDecimal price;

        private BigDecimal originalPrice;
        private String category;
        private String imageUrl;

        @Min(value = 0, message = "Stock quantity cannot be negative")
        private Integer stockQuantity = 0;

        private Boolean featured = false;
    }

    @Data
    public static class ProductResponse {
        private Long id;
        private String name;
        private String description;
        private BigDecimal price;
        private BigDecimal originalPrice;
        private String category;
        private String imageUrl;
        private Integer stockQuantity;
        private Boolean featured;
        private LocalDateTime createdAt;
    }
}
