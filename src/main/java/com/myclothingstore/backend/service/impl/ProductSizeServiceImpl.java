package com.myclothingstore.backend.service.impl;

import com.myclothingstore.backend.entity.ProductEntity;
import com.myclothingstore.backend.entity.ProductSizeEntity;
import com.myclothingstore.backend.repository.ProductRepository;
import com.myclothingstore.backend.repository.ProductSizeRepository;
import com.myclothingstore.backend.service.ProductSizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductSizeServiceImpl implements ProductSizeService {

    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public ProductSizeRepository productSizeRepository;

    public String addSizeService(Long productId, ProductSizeEntity productSizeEntity) {
        ProductEntity productEntity = productRepository.findById(productId).orElseThrow(()->new RuntimeException("Продукт не найден"));

        productEntity.addSize(productSizeEntity);
        productRepository.save(productEntity);
        productSizeRepository.save(productSizeEntity);

        return "Размер добавлен";
    }

    public Set<ProductSizeEntity> showProductService(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(()->new RuntimeException("Нет продукта с таким id"));
        Set<ProductSizeEntity> sizes = productEntity.getSizes();
        return sizes;
    }
}
