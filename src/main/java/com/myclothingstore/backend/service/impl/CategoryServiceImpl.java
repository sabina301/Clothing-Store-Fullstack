package com.myclothingstore.backend.service.impl;


import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.repository.CategoryRepository;
import com.myclothingstore.backend.repository.ProductRepository;
import com.myclothingstore.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public CategoryEntity addCategoryService(CategoryEntity categoryEntity) throws Exception{
        if (categoryRepository.findByName(categoryEntity.getName()).isEmpty()){
            return categoryRepository.save(categoryEntity);
        } else {
            throw new Exception("Такое имя для категории уже существует");
        }
    }

    public List<CategoryEntity> showAllCategoriesService() throws Exception{
        try{
            return categoryRepository.findAll();
        } catch (Exception err){
            throw new Exception("Ошибка");
        }
    }

    public List<ProductEntity> showProductsInCategoryService(Integer categoryId){
        return productRepository.findByCategoryEntity(categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("Не существует категории в таким id")));
    }
}
