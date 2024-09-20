package com.kropsz.market.domain.model

import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "tb_nfe")
data class NFE(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val value: Double,

    @ElementCollection
    val products: List<String> = listOf(),

    val date: LocalDateTime,

    val store: Int,

    val paymentMethod: Int
)
