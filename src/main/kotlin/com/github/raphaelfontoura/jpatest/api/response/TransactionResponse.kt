package com.github.raphaelfontoura.jpatest.api.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.raphaelfontoura.jpatest.domain.Balance
import com.github.raphaelfontoura.jpatest.domain.StatusEnum
import com.github.raphaelfontoura.jpatest.domain.Transaction
import com.github.raphaelfontoura.jpatest.domain.TransactionHistory
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TransactionResponse(
    val transactionId: UUID,
    val clientId: UUID,
    val amount: Long,
    val status: StatusEnum,
    val history: List<TransactionHistoryResponse> = emptyList()
) {
    companion object {
        fun of(transaction: Transaction): TransactionResponse {
            return TransactionResponse(
                transactionId = transaction.id!!,
                clientId = transaction.clientId,
                amount = transaction.amount,
                status = transaction.getStatus(),
                history = transaction.history.map { TransactionHistoryResponse.of(it) }
            )
        }
    }
}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BalanceResponse(
    val balanceId: UUID,
    val balanceTypes: List<BalanceTypeResponse> = emptyList(),
) {
    companion object {
        fun of(balance: Balance): BalanceResponse {
            return BalanceResponse(
                balanceId = balance.id!!,
                balanceTypes = balance.balanceTypes.map { BalanceTypeResponse.of(it) },
            )
        }
    }
}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TransactionHistoryResponse(
    val historyId: UUID,
    val origemId: UUID? = null,
    val origemStatus: StatusEnum? = null,
    val destinoId: UUID,
    val destinoStatus: StatusEnum,
    val amount: Long
) {
    companion object{
        fun of(transactionHistory: TransactionHistory) = TransactionHistoryResponse(
            historyId = transactionHistory.id!!,
            origemId = transactionHistory.origem?.id,
            origemStatus = transactionHistory.origem?.statusEnum,
            destinoId = transactionHistory.destiny.id!!,
            destinoStatus = transactionHistory.destiny.statusEnum,
            amount = transactionHistory.amount
        )
    }
}
