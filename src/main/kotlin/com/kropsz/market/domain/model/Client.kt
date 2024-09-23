package com.kropsz.market.domain.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "tb_client")
data class Client(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    val name: String,
    var password: String,
    val email: String,
    val phone: String,
    val address: String,

    @Column(name = "cpf", unique = true)
    val cpf: String,

    var points: Int,

    @OneToMany(mappedBy = "client")
    var rewards: List<Reward>
)
