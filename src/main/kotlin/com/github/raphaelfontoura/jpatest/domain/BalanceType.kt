package com.github.raphaelfontoura.jpatest.domain

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "balance_types")
class BalanceType(
    @Id
    val id: UUID? = UUID.randomUUID(),
    @ManyToOne
    val balance: Balance,
    @Enumerated(EnumType.STRING)
    val statusEnum: StatusEnum,
    var amount: Long = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BalanceType

        if (id != other.id) return false
        if (balance != other.balance) return false
        if (statusEnum != other.statusEnum) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + balance.hashCode()
        result = 31 * result + statusEnum.hashCode()
        return result
    }
}
