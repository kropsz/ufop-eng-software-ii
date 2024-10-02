package com.kropsz.market.web.dto

import com.kropsz.market.domain.model.Product
import java.util.UUID

data class ProductDto(
    val products: MutableList<UUID> = mutableListOf()
)
