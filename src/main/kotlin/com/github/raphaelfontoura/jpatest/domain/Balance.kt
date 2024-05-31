package com.github.raphaelfontoura.jpatest.domain

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "balances")
class Balance(
    @Id
    val id: UUID? = UUID.randomUUID(),
    @Column(unique = true)
    val clientId: UUID,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val balanceTypes: MutableSet<BalanceType> = mutableSetOf()
) {

    fun getBalanceType(statusEnum: StatusEnum): BalanceType? {
        return balanceTypes.firstOrNull { it.statusEnum == statusEnum }
    }

    fun notifyUpdate(status: StatusEnum?, newStatus: StatusEnum, transaction: Transaction) {
        val balanceOrigin = status?.let { getBalanceType(it) }
        val balanceDestiny = getBalanceType(newStatus)
        val amount = transaction.amount
        balanceOrigin?.let {
            val newAmount = it.amount - amount
            it.amount = newAmount
        }
        balanceDestiny?.let {
            val newAmount = it.amount + amount
            it.amount = newAmount
        }
    }

}
