package com.university.product.services;

import com.university.product.entity.Product;
import com.university.product.entity.ProductRequest;
import com.university.product.entity.ProductResponse;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.university.product.repository.ProductRepository;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        Product saveProduct = productRepository.save(product);
        return modelMapper.map(saveProduct, ProductResponse.class);
    }

    @Override
    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, ProductResponse.class))
                .toList();
    }

    @Override
    public ProductResponse getProductByID(Integer id) throws Exception {
        Product product = getProductByIdFromDB(id);
        return modelMapper.map(product, ProductResponse.class);
    }

    @Override
    public ProductResponse updateProductByID(Integer id, ProductRequest body) throws Exception {
        return productRepository.findById(id)
                .map(book -> {
                    Product mapProduct = modelMapper.map(body, Product.class);
                    mapProduct.setId(book.getId());
                    productRepository.save(mapProduct);
                    return modelMapper.map(productRepository.save(mapProduct), ProductResponse.class);
                })
                .orElseThrow(() ->
                        new Exception(String.format("Product with ID not Found ", id))
                );
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        Product product = getProductByIdFromDB(id);
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getAllProductsByName(String name) {
        return productRepository.findProductByName(name)
                .stream()
                .map(book -> modelMapper.map(book, ProductResponse.class))
                .toList();
    }

    private Product getProductByIdFromDB(Integer id) throws Exception {
        return productRepository.findById(id).orElseThrow(() ->
                new Exception(
                        String.format("Product wth id not found", id)
                ));
    }
}
