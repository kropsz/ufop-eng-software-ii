package com.kropsz.market.domain.repository

import com.kropsz.market.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ProductRepository: JpaRepository<Product, UUID> {


    @Query("select p from Product p")
    override fun findAll(): List<Product>
}