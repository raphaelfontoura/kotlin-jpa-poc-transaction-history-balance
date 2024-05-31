package com.github.raphaelfontoura.jpatest.api

import com.github.raphaelfontoura.jpatest.api.request.TransactionInput
import com.github.raphaelfontoura.jpatest.api.request.TransactionStatusInput
import com.github.raphaelfontoura.jpatest.api.response.TransactionResponse
import com.github.raphaelfontoura.jpatest.api.response.TransactionsBalanceReport
import com.github.raphaelfontoura.jpatest.api.response.TransactionsReport
import com.github.raphaelfontoura.jpatest.usecase.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("transaction")
class TransactionController(
    private val transactionService: TransactionService
) {

    @PostMapping
    fun createTransaction(@RequestBody transaction: TransactionInput): ResponseEntity<TransactionResponse> {
        val createTransaction = transactionService.createTransaction(transaction)
        return ResponseEntity.ok(createTransaction)
    }

    @GetMapping("{id}")
    fun getTransaction(@PathVariable id: UUID): ResponseEntity<TransactionResponse> {
        return ResponseEntity.ok(transactionService.getById(id))
    }

    @PatchMapping("{id}")
    fun changeStatusTransaction(@PathVariable id: UUID, @RequestBody transaction: TransactionStatusInput): ResponseEntity<TransactionResponse> {
        val result = transactionService.updateTransaction(id, transaction)
        return ResponseEntity.ok(result)
    }

    @GetMapping("client/{clientId}")
    fun getAllTransactionsByClient(@PathVariable clientId: UUID): ResponseEntity<TransactionsBalanceReport> {
        return ResponseEntity.ok(transactionService.getAllByClient(clientId))
    }

    @GetMapping
    fun getAllTransactions(): ResponseEntity<TransactionsReport> {
        return ResponseEntity.ok(transactionService.getAll())
    }

}