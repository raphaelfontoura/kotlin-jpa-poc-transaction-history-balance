package com.github.raphaelfontoura.jpatest.repository

import com.github.raphaelfontoura.jpatest.domain.Balance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BalanceRepository : JpaRepository<Balance, UUID> {
    fun findByClientId(clientId: UUID): Balance?
}