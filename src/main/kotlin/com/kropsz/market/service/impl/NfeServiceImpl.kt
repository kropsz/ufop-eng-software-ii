package com.kropsz.market.service.impl

import com.kropsz.market.builder.impl.PointsHistoryBuilder
import com.kropsz.market.builder.impl.RewardBuilder
import com.kropsz.market.domain.model.Reward
import com.kropsz.market.domain.repository.ClientRepository
import com.kropsz.market.domain.repository.NfeRepository
import com.kropsz.market.domain.repository.ProductRepository
import com.kropsz.market.domain.repository.RewardRepository
import com.kropsz.market.service.NfeService
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Service
class NfeServiceImpl(
    val clientRepository: ClientRepository,
    val nfeRepository: NfeRepository,
    val productRepository: ProductRepository,
    val rewardRepository: RewardRepository
) : NfeService {

    override fun processNfe(clientID: UUID, nfeId: UUID): String {
        val client = clientRepository.findById(clientID)
            .orElseThrow { throw EntityNotFoundException("Client not found") }

        val nfe = nfeRepository.findById(nfeId)
            .orElseThrow { throw EntityNotFoundException("NFE not found") }

        if (verifyNfe(nfeId)) {
            client.points += calculatePoints(nfe.value)

            val pointsHistory = PointsHistoryBuilder()
                .pointsAdded(calculatePoints(nfe.value))
                .nfeId(nfeId)
                .date(LocalDateTime.now())
                .build()

            client.addPointsHistory(pointsHistory)
            clientRepository.save(client)

            return "Points added successfully"
        }

        return "NFE is not valid"

    }

    override fun exchangePointsForProducts(clientId: UUID, productId: UUID): Boolean {
        val client = clientRepository.findById(clientId)
            .orElseThrow { throw EntityNotFoundException("Client not found") }
        val product = productRepository.findById(productId)
            .orElseThrow { throw EntityNotFoundException("Product not found") }

        if (client.points >= product.priceInPoints && product.stock > 0) {
            client.points -= product.priceInPoints
            val reward = RewardBuilder()
                .client(client)
                .product(product)
                .rewardDate(LocalDate.now())
                .status(Reward.Status.PENDING)
                .build()

            rewardRepository.save(reward)
            client.addReward(reward)
            clientRepository.save(client)
            return true
        }

        return false
    }

    override fun verifyNfe(nfeId: UUID): Boolean {
        val nfe = nfeRepository.findById(nfeId)
            .orElseThrow { throw EntityNotFoundException("NFE not found") }

        nfe.isValid = nfe.date.isAfter(LocalDateTime.now().minusDays(7))
        nfeRepository.save(nfe)
        return nfe.isValid
    }

    private fun calculatePoints(value: Double): Int {
        return (value / 10).toInt()
    }
}