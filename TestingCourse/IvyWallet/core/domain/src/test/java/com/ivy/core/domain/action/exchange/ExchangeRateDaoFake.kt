package com.ivy.core.domain.action.exchange

import com.ivy.core.persistence.dao.exchange.ExchangeRateDao
import com.ivy.core.persistence.entity.exchange.ExchangeRateEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class ExchangeRateDaoFake: ExchangeRateDao {

    private val rates: MutableStateFlow<List<ExchangeRateEntity>> = MutableStateFlow(emptyList())

    override suspend fun save(values: List<ExchangeRateEntity>) {
        rates.value = values
    }

    override fun findAllByBaseCurrency(baseCurrency: String): Flow<List<ExchangeRateEntity>> {
        return rates.map { list ->
            list.filter{ it.baseCurrency == baseCurrency }
        }
    }
}