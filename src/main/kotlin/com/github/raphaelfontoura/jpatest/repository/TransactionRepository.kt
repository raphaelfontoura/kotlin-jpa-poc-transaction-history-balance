package com.github.raphaelfontoura.jpatest.repository

import com.github.raphaelfontoura.jpatest.domain.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TransactionRepository: JpaRepository<Transaction, UUID> {
}