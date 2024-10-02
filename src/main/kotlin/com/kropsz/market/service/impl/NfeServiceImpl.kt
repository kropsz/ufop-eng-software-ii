package com.kropsz.market.service.impl

import com.kropsz.market.builder.impl.PointsHistoryBuilder
import com.kropsz.market.builder.impl.RewardBuilder
import com.kropsz.market.domain.model.Product
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
            nfe.isUsed = true
            nfeRepository.save(nfe)
            return "Points added successfully"
        }

        return "NFE is not valid or is used"

    }

    override fun exchangePointsForProducts(clientId: UUID, productsId: MutableList<UUID>): Boolean {
        val client = clientRepository.findById(clientId)
            .orElseThrow { throw EntityNotFoundException("Client not found") }
        println(client.points)
        val products = mutableListOf<Product>()

        val totalPoints = productsId.stream()
            .map { productId ->
                val product =
                    productRepository.findById(productId).orElseThrow { EntityNotFoundException("Product not found") }
                products.add(product)
                product
            }
            .mapToInt { product -> product.priceInPoints }
            .sum()

        if (client.points >= totalPoints) {
            val reward = RewardBuilder()
                .client(client)
                .products(products)
                .rewardDate(LocalDate.now())
                .status(Reward.Status.PENDING)
                .build()

            rewardRepository.save(reward)

            products.stream().forEach { product ->
                product.stock--
                productRepository.save(product)
            }

            client.points -= totalPoints
            println(client.points)

            client.addReward(reward)
            clientRepository.save(client)
            println(client.points)

            return true
        }

        return false
    }

    override fun verifyNfe(nfeId: UUID): Boolean {
        val nfe = nfeRepository.findById(nfeId)
            .orElseThrow { throw EntityNotFoundException("NFE not found") }

        return nfe.date.isAfter(LocalDateTime.now().minusDays(7)) && !nfe.isUsed
    }

    private fun calculatePoints(value: Double): Int {
        return (value / 10).toInt()
    }
}