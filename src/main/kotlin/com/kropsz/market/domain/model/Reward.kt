package com.kropsz.market.domain.model

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "tb_reward")
data class Reward(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @ManyToOne
    @JoinColumn(name = "client_id")
    val client: Client,

    @ManyToMany
    @JoinTable(
        name = "reward_product",
        joinColumns = [JoinColumn(name = "reward_id")],
        inverseJoinColumns = [JoinColumn(name = "product_id")]
    )
    val products: List<Product> = listOf(),

    val rewardDate: LocalDate,

    @Enumerated(EnumType.STRING)
    val status: Status
) {
    enum class Status {
        PENDING,
        USED,
        EXPIRED
    }
}
