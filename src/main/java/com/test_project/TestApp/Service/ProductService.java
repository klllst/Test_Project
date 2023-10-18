package com.test_project.TestApp.Service;

import com.test_project.TestApp.Exceptions.ProductNotFoundException;
import com.test_project.TestApp.Models.Product;
import com.test_project.TestApp.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Transactional
    public Product addProduct(String name){
        Product product = new Product(name);
        return productRepository.save(product);
    }
    @Transactional()
    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    @Transactional
    public Product updateProduct(Long id, String name){
        Product product = getProduct(id);
        product.setName(name);
        return productRepository.save(product);
    }

    @Transactional
    public Product deleteProduct(Long id){
        Product product = getProduct(id);
        productRepository.delete(product);
        return product;
    }
    @Transactional
    public void deleteAllProducts(){
        productRepository.deleteAll();
    }
}
