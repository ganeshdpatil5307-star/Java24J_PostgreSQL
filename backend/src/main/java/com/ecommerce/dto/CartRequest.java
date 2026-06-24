package com.ecommerce.dto;

public class CartRequest {

    private Long productId;

    public CartRequest() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
