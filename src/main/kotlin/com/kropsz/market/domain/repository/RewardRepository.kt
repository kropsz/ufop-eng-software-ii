package com.kropsz.market.domain.repository

import com.kropsz.market.domain.model.Reward
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface RewardRepository: JpaRepository<Reward, UUID> {
}