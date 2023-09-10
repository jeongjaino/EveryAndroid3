package com.ivy.core.domain.action.exchange

import com.ivy.data.CurrencyCode
import com.ivy.data.ExchangeRatesMap
import com.ivy.data.exchange.ExchangeProvider
import com.ivy.exchange.RemoteExchangeProvider

class RemoteExchangeProviderFake: RemoteExchangeProvider {

    private val ratesMap = mapOf(
        "WON" to mapOf(
            "USD" to 1300.0,
            "EURO" to 1200.0,
            "YEN" to -100.0
        ),
        "USD" to mapOf(
            "WON" to 100.0,
            "EURO" to 200.0,
            "YEN" to 400.0
        ),
    )

    override suspend fun fetchExchangeRates(baseCurrency: CurrencyCode): RemoteExchangeProvider.Result {
        return RemoteExchangeProvider.Result(
            ratesMap = ratesMap[baseCurrency] as ExchangeRatesMap,
            provider = ExchangeProvider.Fawazahmed0
        )
    }
}