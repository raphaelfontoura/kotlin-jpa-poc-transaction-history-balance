package com.github.raphaelfontoura.jpatest.usecase

import com.github.raphaelfontoura.jpatest.domain.Balance
import com.github.raphaelfontoura.jpatest.domain.BalanceType
import com.github.raphaelfontoura.jpatest.domain.StatusEnum
import com.github.raphaelfontoura.jpatest.repository.BalanceRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class BalanceService(
    private val balanceRepository: BalanceRepository
) {

    fun getBalance(clientId: UUID): Balance {
        var balance = balanceRepository.findByClientId(clientId)
        if (balance == null) {
            balance = Balance(
                clientId = clientId
            )
            createBalanceTypes(balance)
            balanceRepository.saveAndFlush(balance)
        }
        return balance
    }

    private fun createBalanceTypes(balance: Balance) {
        StatusEnum.entries.forEach {
            val balanceType = BalanceType(
                balance = balance,
                statusEnum = it
            )
            balance.balanceTypes.add(balanceType)
        }
    }

}
