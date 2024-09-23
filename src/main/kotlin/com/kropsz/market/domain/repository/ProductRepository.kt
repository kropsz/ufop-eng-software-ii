package com.kropsz.market.domain.repository

import com.kropsz.market.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ProductRepository: JpaRepository<Product, UUID> {
}