package com.github.raphaelfontoura.jpatest.domain

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "transactions")
class Transaction(
    @Id
    val id: UUID? = UUID.randomUUID(),
    val clientId: UUID,
    val amount: Long = 0,
    @Enumerated(EnumType.STRING)
    private var status: StatusEnum,
    @ManyToOne(optional = true, cascade = [CascadeType.ALL])
    var balance: Balance? = null,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER )
    val history: MutableList<TransactionHistory> = mutableListOf()
) {
    init {
        println("Transaction $id inicializada")
        if (status == StatusEnum.PROCESSING) {
            this.balance?.notifyUpdate(null, this.status, this)
            val balanceType = getDestinyBalanceType(this.status)
            balanceType?.let { inputHistory(null, it) }
        }
    }

    fun setStatus(newStatus: StatusEnum) {
        this.balance?.notifyUpdate(this.status, newStatus, this)
        val destinoBalanceType = getDestinyBalanceType(newStatus)
        if (destinoBalanceType != null) {
            inputHistory(balance?.getBalanceType(this.status), destinoBalanceType)
        }
        this.status = newStatus
    }

    private fun getDestinyBalanceType(status: StatusEnum): BalanceType? {
        return balance?.getBalanceType(status)
    }

    fun inputHistory(origem: BalanceType?, destino: BalanceType) {
        val transactionHistory = TransactionHistory(
            id = UUID.randomUUID(),
            transaction = this,
            amount = this.amount,
            origem = origem,
            destiny = destino
        )
        history.add(transactionHistory)
    }

    fun getStatus(): StatusEnum {
        return this.status
    }
}