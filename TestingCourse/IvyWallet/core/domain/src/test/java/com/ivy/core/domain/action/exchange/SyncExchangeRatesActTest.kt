package com.ivy.core.domain.action.exchange

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import com.ivy.core.persistence.dao.exchange.ExchangeRateDao
import com.ivy.exchange.RemoteExchangeProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class SyncExchangeRatesActTest{

    private lateinit var remoteExchangeProvider: RemoteExchangeProvider
    private lateinit var exchangeRateDao: ExchangeRateDao
    private lateinit var syncExchangeRatesAct: SyncExchangeRatesAct

    @BeforeEach
    fun setUp() {
        remoteExchangeProvider = RemoteExchangeProviderFake()
        exchangeRateDao = ExchangeRateDaoFake()
        syncExchangeRatesAct = SyncExchangeRatesAct(
            remoteExchangeProvider,
            exchangeRateDao
        )
    }

    @Test
    fun `음수로 된 환율은 저장 시 값이 무시되어야 한다`() = runBlocking {
        syncExchangeRatesAct("WON") // >

        val wonRates = exchangeRateDao
            .findAllByBaseCurrency("WON")
            .first{ it.isNotEmpty() }
        val yenRates = wonRates.find { it.currency == "YEN" }

        assertThat(yenRates).isNull()
    }

    @Test
    fun `유효한 값은 저장되어야 한다`() = runBlocking<Unit>{
        syncExchangeRatesAct("WON") // >

        val wonRates = exchangeRateDao
            .findAllByBaseCurrency("WON")
            .first{ it.isNotEmpty() }
        val usdRates = wonRates.find { it.currency == "USD" }
        val euroRates = wonRates.find { it.currency == "EURO" }

        assertThat(usdRates).isNotNull()
        assertThat(euroRates).isNotNull()
    }
}