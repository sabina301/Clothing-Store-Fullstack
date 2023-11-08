package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.repository.CategoryRepository;
import com.myclothingstore.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UniversalService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<CategoryEntity> showAllCategoriesService() throws Exception{
        try{
            return categoryRepository.findAll();
        } catch (Exception err){
            throw new Exception("Ошибкаа");
        }
    }

    public List<ProductEntity> showProductsInCategoryService(Integer categoryId){
        return productRepository.findByCategoryId(categoryId);
    }
}
