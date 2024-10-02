package com.kropsz.market.web.controller

import com.kropsz.market.domain.model.Product
import com.kropsz.market.domain.repository.ProductRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(val productRepository: ProductRepository) {

    @GetMapping("/products")
    fun getProducts(): List<Product> {
        val products = productRepository.findAll()

        return products
    }

}