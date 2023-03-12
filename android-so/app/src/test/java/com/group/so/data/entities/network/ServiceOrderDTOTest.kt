package com.group.so.data.entities.network

import com.group.so.data.entities.model.Category
import com.group.so.data.entities.model.SalesUnit
import com.group.so.data.entities.model.ServiceOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ServiceOrderDTOTest {

    private val serviceOrderDTOItem = ServiceOrderDTOItem(
        id = 1,
        creationDate = "",
        conclusionDate = "",
        customer = CustomerDTO(
            1,
            name = "customer",
            phone = "",
            documentNumber = "123456",
            stateInscription = "",
            customeType = "person"
        ),
        discount = 0.0,
        extraInfo = "",
        items = listOf(
            ItemDTO(
                id = 1,
                name = "product",
                extraInfo = "",
                salePrice = 0.0,
                purchasePrice = 0.0,
                itemType = "product",
                category = Category(id = 1, name = "Category 1"),
                saleUnit = SalesUnit(id = 1, name = "Sales Unit test")
            )
        ),
        status = "Aguardando"

    )

    @Test
    fun `should correctly convert to model entity`() {
        val serviceOrderDTOItem: ServiceOrder = serviceOrderDTOItem.toModel()

        assert(serviceOrderDTOItem.id == serviceOrderDTOItem.id)
    }
}
