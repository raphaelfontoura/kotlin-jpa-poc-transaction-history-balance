package com.github.raphaelfontoura.jpatest.api.request

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.raphaelfontoura.jpatest.domain.StatusEnum
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TransactionInput(
    val clientId: UUID,
    val amount: Long = 0
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TransactionStatusInput(
    val status: StatusEnum
)
