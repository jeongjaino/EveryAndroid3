package com.ivy.core.domain.algorithm.calc

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.ivy.core.persistence.algorithm.calc.CalcTrn
import com.ivy.data.transaction.TransactionType
import org.junit.jupiter.api.Test
import java.time.Instant

// PL이 정의한 솔루션
class RawStatsSolutionTest{

    @Test
    fun `transcation으로부터 RawStats 초기화는 정확해야 한다`(){
        val tenSecondsAgo = Instant.now().minusSeconds(10)
        val fiveSecondsAgo = Instant.now().minusSeconds(5)
        val oneSecondAgo = Instant.now().minusSeconds(1)
        val stats = listOf(
            CalcTrn(
                amount = 20.0,
                currency = "Euro",
                type = TransactionType.Expense,
                time = tenSecondsAgo
            ),
            CalcTrn(
                amount = 10.0,
                currency = "Dollar",
                type = TransactionType.Income,
                time = fiveSecondsAgo
            ),
            CalcTrn(
                amount = 20.0,
                currency = "Euro",
                type = TransactionType.Expense,
                time = oneSecondAgo
            ),
        )

        val rawStats = rawStats(stats)

        assertThat(rawStats.expensesCount).isEqualTo(2)
        assertThat(rawStats.incomesCount).isEqualTo(1)

        assertThat(rawStats.newestTrnTime).isEqualTo(oneSecondAgo)

        assertThat(rawStats.incomes).isEqualTo(mapOf("Dollar" to 10.0))
        assertThat(rawStats.expenses).isEqualTo(mapOf("Euro" to 40.0))
    }
}