package com.kropsz.market.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "tb_product")
data class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val name: String,

    val description: String,

    @Column(name = "price_in_points")
    val priceInPoints: Int,

    var stock: Int,

    @Column(name = "image_url")
    val imageUrl: String? = null,

    @JsonIgnore
    @ManyToMany(mappedBy = "products")
    val rewards: List<Reward> = listOf()
)
