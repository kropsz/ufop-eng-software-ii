package com.kropsz.market.builder.impl

import com.kropsz.market.builder.Builder
import com.kropsz.market.domain.model.Client
import com.kropsz.market.domain.model.Product
import com.kropsz.market.domain.model.Reward
import java.time.LocalDate

class RewardBuilder : Builder<Reward> {
    private var client: Client? = null
    private var product: Product? = null
    private var rewardDate: LocalDate = LocalDate.now()
    private var status: Reward.Status = Reward.Status.PENDING

    fun client(client: Client) = apply { this.client = client }
    fun product(product: Product) = apply { this.product = product }
    fun rewardDate(rewardDate: LocalDate) = apply { this.rewardDate = rewardDate }
    fun status(status: Reward.Status) = apply { this.status = status }

    override fun build(): Reward {
        return Reward(
            client = client!!,
            product = product!!,
            rewardDate = rewardDate,
            status = status
        )
    }
}