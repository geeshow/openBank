package com.ken207.openbank.service;

import com.ken207.openbank.domain.Product;
import com.ken207.openbank.dto.ProductDto;
import com.ken207.openbank.exception.BizRuntimeException;
import com.ken207.openbank.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long createProduct(ProductDto.Create createProductDto) {

        Product existedProduct = productRepository.findByProductCode(createProductDto.getProductCode());

        if ( existedProduct != null ) {
            throw new BizRuntimeException("이미 등록된 상품 코드 입니다. 상품코드:"+createProductDto.getProductCode());
        }

        Product product = Product.createProduct(createProductDto);
        Product newProduct = productRepository.save(product);

        return newProduct.getId();
    }
}
