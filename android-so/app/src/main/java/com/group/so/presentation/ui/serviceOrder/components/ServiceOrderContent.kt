@file:Suppress(
    "MaxLineLength",
    "LongMethod",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList"
)

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.group.so.core.State
import com.group.so.data.entities.model.Customer
import com.group.so.presentation.ui.serviceOrder.ServiceOrderViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun ServiceOrderContent(
    serviceOrderViewModel: ServiceOrderViewModel,
    customerListState: State<List<Customer>>,
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController
) {
    println(scrollBehavior)
    LaunchedEffect(Unit, block = {
        serviceOrderViewModel.fetchLatestCustomers()
    })

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ServiceOrderFormContent(customerListState, serviceOrderViewModel, navController)
        Spacer(modifier = Modifier.size(30.dp))
        SummaryServiceOrder(serviceOrderViewModel)
        Spacer(modifier = Modifier.size(30.dp))
        FooterContent(serviceOrderViewModel)
    }
}
