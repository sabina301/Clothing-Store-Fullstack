package com.myclothingstore.backend.service;

import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.model.DTO.ChangeProductDTO;

public interface ProductService {
    void addProductInCategoryService(ProductEntity productEntity);
    ProductEntity changeProductService(Long id, ChangeProductDTO productDTO) throws Exception;
    void deleteProductService(Long id) throws Exception;

}
