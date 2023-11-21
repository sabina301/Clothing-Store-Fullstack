package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.entity.ProductSizeEntity;
import com.myclothingstore.backend.model.DTO.ChangeProductDTO;

import java.util.Set;

public interface ProductService {
    void addProductInCategoryService(ProductEntity productEntity);
    ProductEntity changeProductService(Long id, ChangeProductDTO productDTO) throws Exception;
    void deleteProductService(Long id) throws Exception;


}
