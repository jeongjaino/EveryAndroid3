package com.plcoding.testingcourse.shopping.domain

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

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

    // ParameterizedTest
    // 매개변수로 들어오는 값을 설정할 수 있다.
    @ParameterizedTest
    @ValueSource(
        ints = [1, 2, 3, 4, 5]
    )
    fun `여러 개의 상품을 1 ~ 5 번 더했을 때, 매번 총 가격이 일치해야 한다`(quantity: Int){
        // GIVEN
        val product = Product(
            id = 1,
            name = "아이스크림",
            price = 40.0
        )
        shoppingCart.addProduct(product, quantity)

        // ACTION
        val totalCost = shoppingCart.getTotalCost()

        // ASSERTION
        assertThat(totalCost).isEqualTo(40.0 * quantity)
    }

    // 매개변수가 2개 이상일 때, Csv타입으로 설정할 수 있다.
    @ParameterizedTest
    @CsvSource(
        "4,20.0",
        "3,15.0",
        "6,30.0"
    )
    fun `여러 개의 상품을 quantity번 더했을 때, 매번 총 가격이 expectedPriceSum과 일치해야 한다`(
        quantity: Int,
        expectedPriceSum: Double
    ){
        // GIVEN
        val product = Product(
            id = 1,
            name = "아이스크림",
            price = 5.0
        )
        shoppingCart.addProduct(product, quantity)

        // ACTION
        val totalCost = shoppingCart.getTotalCost()

        // ASSERTION
        assertThat(totalCost).isEqualTo(expectedPriceSum)
    }

    // Private Function Test
    // Private 함수를 Public으로 열고 @OpenForTesting을 하는 것은 권장하지 않는 방법이다.
    // 다른 Public 함수를 통해 간접적으로 Private 함수를 검증하는 것이 올바른 방법이다.
    // 이와 유사하게 Private 프로퍼티를 검증하는 방법 역시 Test 케이스에서 새롭게 프로퍼티를 만들어 검증하는 것이 올바르다.

    @Test
    fun `존재하지 않는 상품인 경우, isInvalidProduct 함수는 Invalid를 반환해야 한다`(){
        // GIVEN
        val product = Product(
            id = 1231,
            name = "아이스크림",
            price = 5.0
        )
        shoppingCart.addProduct(product, 5)

        // ACTION
        val totalCost = shoppingCart.getTotalCost()

        // ASSERTION
        assertThat(totalCost).isEqualTo(0.0)
    }
}