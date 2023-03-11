package com.group.so.data.entities.network

import com.group.so.data.entities.model.SalesUnit
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SalesUnitDTOTest {

    private val salesUnitDTO = SalesUnitDTO(
        id = 1,
        name = "Sales unit test"
    )

    @Test
    fun `should correctly convert to model entity`() {
        val salesUnit: SalesUnit = salesUnitDTO.toModel()

        assert(salesUnit.name == salesUnitDTO.name)
    }
}
