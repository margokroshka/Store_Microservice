package com.university.product.services;

import com.university.product.entity.ProductRequest;
import com.university.product.entity.ProductResponse;


import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProduct();

    ProductResponse getProductByID(Integer id) throws Exception;

    ProductResponse updateProductByID(Integer id, ProductRequest body) throws Exception;

    void deleteById(Integer id) throws Exception;

    List<ProductResponse> getAllProductsByName(String name);
}
