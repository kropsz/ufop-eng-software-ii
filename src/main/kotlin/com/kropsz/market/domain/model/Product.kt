package com.kropsz.market.domain.model

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
    val priceInPoints: Double,

    val stock: Int,

    @OneToMany(mappedBy = "product")
    val rewards: List<Reward> = listOf()
)
