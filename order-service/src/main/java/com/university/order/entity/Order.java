package com.university.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="order")
public class Order {
    @Id
    private String id;

    @Column(name="custome_id")
    private String customerId;

    @NotEmpty
    @Column(name="product_name")
    private String product_name;

    @Column(name="payment_status")
    private Boolean paymentStatus = Boolean.FALSE;

    @NotNull
    @Column(name="payment_details")
    private String paymentDetails;

}
