package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.CategoryEntity;
import com.myclothingstore.backend.entity.ProductEntity;

import java.util.List;

public interface CategoryService {
    CategoryEntity addCategoryService(CategoryEntity categoryEntity) throws Exception;
    List<CategoryEntity> showAllCategoriesService() throws Exception;
    List<ProductEntity> showProductsInCategoryService(Integer categoryId);
}
