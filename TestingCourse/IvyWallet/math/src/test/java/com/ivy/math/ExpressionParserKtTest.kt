package com.ivy.math

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.ivy.parser.Parser
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class ExpressionParserTest{

    private lateinit var parser: Parser<TreeNode>

    @BeforeEach
    fun setUp(){
        parser = expressionParser()
    }

    @Test
    fun `문자열 표현식의 값과 결과가 일치해야 한다`(){
        // GIVEN
        val result = parser("2+(3*6)").first()

        // ACTION
        val actual = result.value.eval()

        // ASSERTION
        assertThat(actual).isEqualTo(20.0)
    }

    @ParameterizedTest
    @CsvSource(
        "2+(3*6),20.0",
        "9.000000,9.0",
        "32/32+1,2.0",
        "(32-2)/6.0,5.0"
    )
    fun `입력받은 표현식의 결과와 값이 일치해야 한다`(
        expression: String,
        expected: Double
    ){
        // GIVEN
        val result = parser(expression).first()

        // ACTION
        val actual = result.value.eval()

        // ASSERTION
        assertThat(actual).isEqualTo(expected)
    }
}
