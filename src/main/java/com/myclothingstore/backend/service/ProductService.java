package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.model.DTO.ChangeProductDTO;
import com.myclothingstore.backend.repository.CategoryRepository;
import com.myclothingstore.backend.repository.ProductRepository;
import com.myclothingstore.backend.repository.RoleRepository;
import com.myclothingstore.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addProductInCategoryService(ProductEntity productEntity) {
        CategoryEntity categoryEntity = categoryRepository.findById(productEntity.getCategoryId()).orElseThrow(()->new RuntimeException("Нет категории с этим id"));
        categoryEntity.addProduct(productEntity);
        productEntity.setCategoryEntity(categoryEntity);
        productRepository.save(productEntity);
    }

    public ProductEntity changeProductService(Long id, ChangeProductDTO productDTO) throws Exception{
        try {
            ProductEntity productEntity = productRepository.findById(id).get();
            if (productDTO.getProductIcon()!="") {productEntity.setProductIcon(productDTO.getProductIcon());}
            if (productDTO.getProductName()!="") {productEntity.setProductName(productDTO.getProductName());}
            if (productDTO.getProductDescription()!="") {productEntity.setProductDescription(productDTO.getProductDescription());}
            if (productDTO.getProductPrice()>0) {productEntity.setProductPrice(productDTO.getProductPrice());}
            if (productDTO.getProductStatus()!="") {productEntity.setProductStatus(productDTO.getProductStatus());}
            return productRepository.save(productEntity);
        } catch (Exception err){
            throw new Exception("Ошибка2");
        }

    }

    public void deleteProductService(Long id) throws Exception{
        try {

            ProductEntity productEntity = productRepository.findById(id).orElseThrow(() ->
                    new Exception("Нет такого id"));

            CategoryEntity category = productEntity.getCategoryEntity();

            category.removeProduct(productEntity);
            productEntity.setCategoryEntity(null);

            productRepository.save(productEntity);

        } catch (Exception err) {
            throw new Exception("Ошибка при удалении продукта");
        }
    }
}
