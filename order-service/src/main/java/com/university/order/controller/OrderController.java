package com.university.order.controller;

import com.university.order.entity.OrderRequest;
import com.university.order.entity.OrderResponse;
import com.university.order.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    @Operation(
            summary = "Get all orders",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of orders",
                            content = @Content(schema = @Schema(implementation = OrderResponse.class)))
            }
    )
    @GetMapping("/getAll")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }


    @Operation(
            summary = "Get order by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "order found",
                            content = @Content(schema = @Schema(implementation = OrderResponse.class))),
                    @ApiResponse(responseCode = "404", description = "product not found")
            }
    )
    @GetMapping("/getId/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }

    @Operation(
            summary = "Get order by CustomerID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of orders",
                            content = @Content(schema = @Schema(implementation = OrderResponse.class)))
            }
    )
    @GetMapping("/getCustomerId/{customerId}")
    public ResponseEntity<List<OrderResponse>> getAllOrdersByCustomerId(@PathVariable Integer customerId) {
        return ResponseEntity.ok().body(orderService.getAllOrdersByCustomerId(customerId));
    }



    @Operation(
            summary = "Create a order",
            description = "Create a new user order.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "order successfully created",
                    content = @Content(schema = @Schema(implementation = OrderResponse.class),
                            mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest body) {
        try {
            return ResponseEntity.ok().body(orderService.createOrder(body));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @Operation(
            summary = "Update order by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "order updated",
                            content = @Content(schema = @Schema(implementation = OrderResponse.class))),
                    @ApiResponse(responseCode = "404", description = "order not found")
            }
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderResponse> updateOrder(@RequestBody OrderRequest body, @PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(orderService.updateOrderById(id, body));
    }


    @Operation(
            summary = "Delete order",
            responses = {
                    @ApiResponse(responseCode = "200", description = "order deleted"),
                    @ApiResponse(responseCode = "404", description = "order not found")

            }
    )
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrder(@PathVariable Integer id) throws Exception {
        orderService.deleteById(id);
    }
}
