package com.university.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class OrderRequest {
    private String customerId;
    private String product_name;
    private Boolean paymentStatus = Boolean.FALSE;
    private String paymentDetails;
}
