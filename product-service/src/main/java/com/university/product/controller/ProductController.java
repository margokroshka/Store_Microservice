package com.university.product.controller;

import com.university.product.entity.ProductRequest;
import com.university.product.entity.ProductResponse;
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
import com.university.product.services.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    @Operation(
            summary = "Get all products",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of products",
                            content = @Content(schema = @Schema(implementation = ProductResponse.class)))
            }
    )
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok().body(productService.getAllProduct());
    }


    @Operation(
            summary = "Get product by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "product found",
                            content = @Content(schema = @Schema(implementation = ProductResponse.class))),
                    @ApiResponse(responseCode = "404", description = "product not found")
            }
    )
    @GetMapping("/getId/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(productService.getProductByID(id));
    }

    @Operation(
            summary = "Get product by Name",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of products",
                            content = @Content(schema = @Schema(implementation = ProductResponse.class)))
            }
    )
    @GetMapping("/getName/{name}")
    public ResponseEntity<List<ProductResponse>> getProductByName(@PathVariable String name) {
        return ResponseEntity.ok().body(productService.getAllProductsByName(name));
    }



    @Operation(
            summary = "Create a product",
            description = "Create a new user product.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "product successfully created",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class),
                            mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest body) {
        try {
            return ResponseEntity.ok().body(productService.createProduct(body));
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @Operation(
            summary = "Update product by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "product updated",
                            content = @Content(schema = @Schema(implementation = ProductResponse.class))),
                    @ApiResponse(responseCode = "404", description = "product not found")
            }
    )
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest body, @PathVariable Integer id) throws Exception {
        return ResponseEntity.ok().body(productService.updateProductByID(id, body));
    }


    @Operation(
            summary = "Delete product",
            responses = {
                    @ApiResponse(responseCode = "200", description = "product deleted"),
                    @ApiResponse(responseCode = "404", description = "product not found")

            }
    )
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable Integer id) throws Exception {
        productService.deleteById(id);
    }
}
