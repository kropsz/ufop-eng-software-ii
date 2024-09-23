package com.kropsz.market.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.time.LocalDateTime
import java.util.*

@Embeddable
data class PointsHistory(

    @Column(name = "points_added")
    val pointsAdded: Int,

    @Column(name = "nfe_id")
    val nfeId: UUID,

    @Column(name = "date")
    val date: LocalDateTime = LocalDateTime.now()
)