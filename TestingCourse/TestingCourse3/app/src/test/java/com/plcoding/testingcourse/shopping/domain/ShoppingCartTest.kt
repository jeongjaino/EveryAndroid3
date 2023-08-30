package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test

internal class ShoppingCartTest{

    private lateinit var shoppingCart: ShoppingCart

    @BeforeEach
    fun setUp() {
        shoppingCart = ShoppingCart()
    }

    // Naming Rule : Doing_ExpectResult
    fun addShoppingCart_totalPriceSumCorrect(){

    }

    // 백틱을 이용해 자연어로 사용해도 된다. -> 테스트는 가독성이 중요해, 함수명이 긴 것보다 잘 읽히는 것이 중요하다.
    @Test
    fun `여러 개의 상품을 더했을 때, 총 가격이 일치해야 한다`(){
        // GIVEN
        val product = Product(
            id = 1,
            name = "아이스크림",
            price = 40.0
        )

        shoppingCart.addProduct(product, 4)

        // ACTION
        val totalCost = shoppingCart.getTotalCost()

        // ASSERTION
        assertThat(totalCost).isEqualTo(160.0)
    }

    @Test
    fun `음수 갯수의 상품을 추가했을 때, 예외를 발생해야 한다`(){
        // GIVEN
        val product = Product(
            id = 1,
            name = "마카롱",
            price = 4.0
        )

        // ASSERTION && ACTION
        assertFailure {
            shoppingCart.addProduct(product, -4)
        }
    }

    // RepeatedTest는 Flaky Test에서 사용된다.
    // Flaky Test는 테스트 코드가 실행될 때 때로는 통과하고 때로는 실패하는 테스트를 가리킨다.
    // 문제에 대한 원인을 찾기 힘들 때, 해당 RepeatedTest를 통해 해결할 수 있다.
    @RepeatedTest(100)
    fun `음수 갯수의 상품을 100번 추가했을 때, 모두 예외를 발생해야 한다`(){
        // GIVEN
        val product = Product(
            id = 1,
            name = "마카롱",
            price = 4.0
        )

        // ASSERTION && ACTION
        assertFailure {
            shoppingCart.addProduct(product, -4)
        }
    }
}