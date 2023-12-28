package com.myclothingstore.backend.service.impl;

import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.entity.ProductSizeEntity;
import com.myclothingstore.backend.exception.ProductNotFoundException;
import com.myclothingstore.backend.model.DTO.ChangeProductDTO;
import com.myclothingstore.backend.repository.CategoryRepository;
import com.myclothingstore.backend.repository.ProductRepository;
import com.myclothingstore.backend.repository.RoleRepository;
import com.myclothingstore.backend.repository.UserRepository;
import com.myclothingstore.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addProductInCategoryService(ProductEntity productEntity, Integer id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id).orElseThrow(()->new RuntimeException("Нет категории с этим id"));
        categoryEntity.addProduct(productEntity);
        productEntity.setCategoryEntity(categoryEntity);
        productRepository.save(productEntity);
    }

    public ProductEntity changeProductService(Long id, ChangeProductDTO productDTO){
        try {
            ProductEntity productEntity = productRepository.findById(id).orElseThrow(()->{
                return new ProductNotFoundException("Товар не найден");
            });
            if (productDTO.getProductIcon()!="") {productEntity.setProductIcon(productDTO.getProductIcon());}
            if (productDTO.getProductName()!="") {productEntity.setProductName(productDTO.getProductName());}
            if (productDTO.getProductDescription()!="") {productEntity.setProductDescription(productDTO.getProductDescription());}
            if (productDTO.getProductPrice()>0) {productEntity.setProductPrice(productDTO.getProductPrice());}
            return productRepository.save(productEntity);
        } catch (RuntimeException e){
            throw new RuntimeException("Ошибка изменений данных о товаре");
        }

    }

    public void deleteProductService(Long id){
        try {

            ProductEntity productEntity = productRepository.findById(id).orElseThrow(() ->{
                return new ProductNotFoundException("Товар не найден");
            });

            CategoryEntity category = productEntity.getCategoryEntity();

            category.removeProduct(productEntity);
            productEntity.setCategoryEntity(null);

            productRepository.save(productEntity);

        } catch (RuntimeException e) {
            throw new RuntimeException("Ошибка при удалении товара");
        }
    }


}
