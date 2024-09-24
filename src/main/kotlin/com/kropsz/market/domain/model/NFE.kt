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
    @MapKeyColumn(name = "product_value")
    @Column(name = "product_name")
    val products: Map<Double, String> = mapOf(),

    val date: LocalDateTime,

    val store: Int,

    var isUsed: Boolean,

    @Enumerated(EnumType.STRING)
    val paymentMethod: PaymentMethod
){
    enum class PaymentMethod {
        CREDIT_CARD,
        DEBIT_CARD,
        CASH
    }
}
