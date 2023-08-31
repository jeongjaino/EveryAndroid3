package com.ivy.core.domain.algorithm.calc

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isGreaterThan
import com.ivy.core.persistence.algorithm.calc.CalcTrn
import com.ivy.data.transaction.TransactionType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.Instant


class RawStatsTest{
    private lateinit var calcTrnList: List<CalcTrn>

    @BeforeEach
    fun setUp() {
        calcTrnList = listOf(
            CalcTrn(
                amount = 20.0,
                currency = "Euro",
                type = TransactionType.Expense,
                time = Instant.now()
            ),
            CalcTrn(
                amount = 5.0,
                currency = "Yen",
                type = TransactionType.Income,
                time = Instant.now()
            ),
            CalcTrn(
                amount = 50.0,
                currency = "Dollar",
                type = TransactionType.Income,
                time = Instant.now()
            ),
            CalcTrn(
                amount = 50.0,
                currency = "Dollar",
                type = TransactionType.Income,
                time = Instant.now()
            ),
            CalcTrn(
                amount = 10.0,
                currency = "Won",
                type = TransactionType.Expense,
                time = Instant.now()
            ),
            CalcTrn(
                amount = 20.0,
                currency = "Won",
                type = TransactionType.Expense,
                time = Instant.now()
            )
        )
    }

    @Test
    fun `리스트 아이템의 지출 횟수와 expensesCount 가 정확해야 한다`(){
        val rawStats = rawStats(calcTrnList)

        assertThat(rawStats.expensesCount).isEqualTo(3)
    }

    @Test
    fun `리스트 아이템의 소득 횟수와 incomeCount 가 정확해야 한다`(){
        val rawStats = rawStats(calcTrnList)

        assertThat(rawStats.incomesCount).isEqualTo(3)
    }

    @Test
    fun `아이템의 시각이 이전 시각보다 최근인 경우, 해당 시각으로 newestTrnTime을 수정 해야 한다`(){
        val rawStats = rawStats(calcTrnList)

        assertThat(rawStats.newestTrnTime).isGreaterThan(Instant.MIN)
    }

    @Test
    fun `처음 소비한 화폐인 경우, 해당 화폐의 총 소비 값과 처음 소비한 값이 같아야 한다`(){
        val rawStats = rawStats(calcTrnList)

        assertThat(rawStats.expenses["Euro"]).isEqualTo(20.0)
    }

    @Test
    fun `처음 소득한 화폐인 경우, 해당 화폐의 총 소득 값과 처음 소득한 값이 같아야 한다`(){
        val rawStats = rawStats(calcTrnList)

        assertThat(rawStats.incomes["Yen"]).isEqualTo(5.0)
    }

    @Test
    fun `소비 아이템이 여러 개인 경우, income의 값은 소비 아이템의 amount의 합이여야 한다`(){
        val rawStats = rawStats(calcTrnList)

        assertThat(rawStats.incomes["Dollar"]).isEqualTo(100.0)
    }

    @Test
    fun `소득 아이템이 여러 개인 경우, outcome의 값은 소득 아이템의 amount의 합이여야 한다`(){
        val rawStats = rawStats(calcTrnList)

        assertThat(rawStats.expenses["Won"]).isEqualTo(30.0)
    }
}