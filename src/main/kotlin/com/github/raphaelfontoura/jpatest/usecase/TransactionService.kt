package com.github.raphaelfontoura.jpatest.usecase

import com.github.raphaelfontoura.jpatest.api.request.TransactionInput
import com.github.raphaelfontoura.jpatest.api.request.TransactionStatusInput
import com.github.raphaelfontoura.jpatest.api.response.TransactionResponse
import com.github.raphaelfontoura.jpatest.api.response.TransactionsReport
import com.github.raphaelfontoura.jpatest.domain.StatusEnum
import com.github.raphaelfontoura.jpatest.domain.Transaction
import com.github.raphaelfontoura.jpatest.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val balanceService: BalanceService
) {

    fun createTransaction(transactionInput: TransactionInput): TransactionResponse {
        val transaction = Transaction(
            amount = transactionInput.amount,
            clientId = transactionInput.clientId,
            status = StatusEnum.PROCESSING,
            balance = balanceService.getBalance(transactionInput.clientId)
        )
        val result = transactionRepository.save(transaction)
        return TransactionResponse.of(result)
    }

    fun getById(id: UUID): TransactionResponse {
        val transaction = transactionRepository.findById(id).orElseThrow()
        return TransactionResponse.of(transaction)
    }

    fun updateTransaction(id: UUID, transactionInput: TransactionStatusInput): TransactionResponse? {
        val transaction = transactionRepository.findById(id).orElseThrow()
        transaction.setStatus(transactionInput.status)
        transactionRepository.save(transaction)
        return TransactionResponse.of(transaction)
    }

    fun getAll(): TransactionsReport {
        val transactions = transactionRepository.findAll()
        return TransactionsReport.of(transactions)
    }
}