package com.kropsz.market.builder.impl

import com.kropsz.market.builder.Builder
import com.kropsz.market.domain.model.PointsHistory
import java.time.LocalDateTime
import java.util.*

class PointsHistoryBuilder : Builder<PointsHistory> {
    private var pointsAdded: Int = 0
    private var nfeId: UUID? = null
    private var date: LocalDateTime = LocalDateTime.now()

    fun pointsAdded(pointsAdded: Int) = apply { this.pointsAdded = pointsAdded }
    fun nfeId(nfeId: UUID) = apply { this.nfeId = nfeId }
    fun date(date: LocalDateTime) = apply { this.date = date }

    override fun build(): PointsHistory {
        return PointsHistory(
            pointsAdded = pointsAdded,
            nfeId = nfeId!!,
            date = date
        )
    }
}