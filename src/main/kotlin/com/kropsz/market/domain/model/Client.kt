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

    @Column(name = "cpf", unique = true)
    val cpf: String,

    var points: Int,

    @OneToMany(mappedBy = "client")
    var rewards: MutableList<Reward> = mutableListOf(),

    @ElementCollection
    @CollectionTable(name = "tb_points_history",
        joinColumns = [JoinColumn(name = "client_id")])
    var pointsHistory: MutableList<PointsHistory> = mutableListOf()

) {
    fun addPoints(points: Int) {
        this.points += points
    }

    fun removePoints(points: Int) {
        this.points -= points
    }

    fun addReward(reward: Reward) {
        rewards.add(reward)
    }

    fun addPointsHistory(history: PointsHistory) {
        pointsHistory.add(history)
    }
}
