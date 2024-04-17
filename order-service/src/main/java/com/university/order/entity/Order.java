package com.university.order.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.antlr.v4.runtime.misc.NotNull;

@Getter
@Setter
@ToString
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="order")
public class Order {
    @Id
    private String id;
    @Column(name="custome_id")
    private String customerId;

    @Column(name="payment_status")
    private Boolean paymentStatus = Boolean.FALSE;

    @NotNull
    @Column(name="payment_details")
    private String paymentDetails;
    @NotEmpty
    @Column(name="product_name")
    private String product_name;
}
