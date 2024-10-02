package com.kropsz.market.builder.impl

import com.kropsz.market.builder.Builder
import com.kropsz.market.domain.model.Client
import com.kropsz.market.domain.model.Product
import com.kropsz.market.domain.model.Reward
import java.time.LocalDate

class RewardBuilder : Builder<Reward> {
    private var client: Client? = null
    private var products: List<Product>? = null
    private var rewardDate: LocalDate = LocalDate.now()
    private var status: Reward.Status = Reward.Status.PENDING

    fun client(client: Client) = apply { this.client = client }
    fun products(products: List<Product>) = apply { this.products = products }
    fun rewardDate(rewardDate: LocalDate) = apply { this.rewardDate = rewardDate }
    fun status(status: Reward.Status) = apply { this.status = status }

    override fun build(): Reward {
        return Reward(
            client = client!!,
            products = products!!,
            rewardDate = rewardDate,
            status = status
        )
    }
}