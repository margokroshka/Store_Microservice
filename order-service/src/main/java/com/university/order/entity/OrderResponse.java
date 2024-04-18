package com.university.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String customerId;
    private String product_name;
    private Boolean paymentStatus = Boolean.FALSE;
    private String paymentDetails;
}
