package com.github.raphaelfontoura.jpatest.domain

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "transaction_histories")
class TransactionHistory(
    @Id
    val id: UUID? = UUID.randomUUID(),
    @ManyToOne
    val transaction: Transaction,
    @ManyToOne(optional = true)
    val origem: BalanceType? = null,
    @ManyToOne
    val destiny: BalanceType,
    var amount: Long,
    @CreatedDate
    val createdDate: LocalDateTime = LocalDateTime.now()
) {

}
