package com.test_project.TestApp.Service;

import com.test_project.TestApp.Exceptions.ProductMCNotFoundException;
import com.test_project.TestApp.Models.MaterialComponent;
import com.test_project.TestApp.Models.Product;
import com.test_project.TestApp.Models.ProductMC;
import com.test_project.TestApp.Repositories.ProductMCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductMCService {
    private final ProductMCRepository productMCRepository;
    @Autowired
    public ProductMCService(ProductMCRepository productMCRepository){
        this.productMCRepository = productMCRepository;
    }
    @Transactional
    public ProductMC addProductMC(Product product, MaterialComponent MC, int quantityMC){
        ProductMC productMC = new ProductMC(quantityMC);
        productMC.setProduct(product);
        productMC.setMC(MC);
        return productMCRepository.save(productMC);
    }
    @Transactional()
    public ProductMC getProductMC(Long id){
        return productMCRepository.findById(id).orElseThrow(() -> new ProductMCNotFoundException(id));
    }

    @Transactional
    public List<ProductMC> getAllProducts(){
        return productMCRepository.findAll();
    }

    @Transactional
    public ProductMC updateProductMC(Long id, int quantityMC){
        ProductMC productMC = getProductMC(id);
        productMC.setQuantityMC(quantityMC);
        return productMCRepository.save(productMC);
    }

    @Transactional
    public ProductMC deleteProductMC(Long id){
        ProductMC productMC = getProductMC(id);
        productMCRepository.delete(productMC);
        return productMC;
    }
    @Transactional
    public void deleteAllProductMC(){
        productMCRepository.deleteAll();
    }
}
