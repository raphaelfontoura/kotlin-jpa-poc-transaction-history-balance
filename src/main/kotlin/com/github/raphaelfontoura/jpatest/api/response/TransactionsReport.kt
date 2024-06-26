package com.github.raphaelfontoura.jpatest.api.response

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.raphaelfontoura.jpatest.domain.BalanceType
import com.github.raphaelfontoura.jpatest.domain.StatusEnum
import com.github.raphaelfontoura.jpatest.domain.Transaction
import java.util.*

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TransactionsReport(
    val transactions: List<TransactionResponse> = emptyList()
) {
    companion object {
        fun of(transactions: List<Transaction>): TransactionsReport {
            return TransactionsReport(
                transactions = transactions.map { TransactionResponse.of(it) }
            )
        }
    }
}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class TransactionsBalanceReport(
    val transactions: List<TransactionResponse> = emptyList(),
    val balance: BalanceResponse? = null
) {
    companion object {
        fun of(transactions: List<Transaction>): TransactionsBalanceReport {
            if (transactions.isNotEmpty())
             return TransactionsBalanceReport(
                transactions = transactions.map { TransactionResponse.of(it) },
                balance = BalanceResponse.of(transactions.first().balance!!)
            )
            return TransactionsBalanceReport()
        }
    }
}

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class BalanceTypeResponse(
    val balanceTypeId: UUID,
    val status: StatusEnum,
    val amount: Long,
) {
    companion object {
        fun of(balanceType: BalanceType): BalanceTypeResponse {
            return BalanceTypeResponse(
                balanceTypeId = balanceType.id!!,
                status = balanceType.statusEnum,
                amount = balanceType.amount
            )
        }
    }
}