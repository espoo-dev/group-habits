@file:Suppress("LongMethod", "FunctionNaming", "FunctionParameterNaming")

package com.group.so.presentation.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.group.so.data.ItemType
import com.group.so.data.entities.model.Customer
import com.group.so.presentation.ui.category.CategoryListScreen
import com.group.so.presentation.ui.category.CategoryViewModel
import com.group.so.presentation.ui.customer.AddScreenCustomer
import com.group.so.presentation.ui.customer.CustomerScreen
import com.group.so.presentation.ui.customer.CustomerViewModel
import com.group.so.presentation.ui.customer.DetailsCustomerScreen
import com.group.so.presentation.ui.home.HomeScreen
import com.group.so.presentation.ui.login.LoginScreen
import com.group.so.presentation.ui.login.LoginViewModel
import com.group.so.presentation.ui.service.ServiceScreen
import com.group.so.presentation.ui.service.ServiceViewModel
import org.koin.androidx.compose.koinViewModel

@ExperimentalMaterialApi
@OptIn(ExperimentalComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun ScreenMain() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {
        composable(Routes.Login.route) {
            val loginViewModel = koinViewModel<LoginViewModel>()
            Surface(color = MaterialTheme.colors.primary, modifier = Modifier.fillMaxSize()) {
                LoginScreen(
                    navController = navController,
                    loginViewModel,
                )
            }
        }
        composable(Routes.Category.route) {
            val categoryViewModel = koinViewModel<CategoryViewModel>()
            val categoriesListUiState by categoryViewModel.categoryState.collectAsState()

            CategoryListScreen(
                categoryViewModel,
                categoryListState = categoriesListUiState,
                onNewCategoryClick = {
                },
                onCategoryClick = {
                },
                onDeleteCategory = { categoryViewModel.deleteCategory(it.id) }
            ) { categoryViewModel.fetchLatestCategories() }
        }
        composable(Routes.Home.route) {
            HomeScreen(navController)
        }

        composable(Routes.Customer.route) {
            val customerViewModel = koinViewModel<CustomerViewModel>()
            val customersListUiState by customerViewModel.customerListState.collectAsState()

            CustomerScreen(
                customerViewModel = customerViewModel,
                customerListState = customersListUiState,
                onNewCustomerClick = {
                    navController.navigate(Routes.NewCostumer.route)
                },
                onCustomerClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "customer",
                        value = it
                    )
                    // navController.navigate(Routes.EditCostumer.route + "/${it.id}")
                    navController.navigate(Routes.EditCostumer.route)
                },
                onDeleteCustomer = { customerViewModel.deleteCustomer(it.id) }
            ) { customerViewModel.fetchLatestCustomers() }
        }
        composable(Routes.NewCostumer.route) {
            val customerViewModel = koinViewModel<CustomerViewModel>()
            AddScreenCustomer(
                navController,
                customerViewModel,
            )
        }
        composable(
            Routes.EditCostumer.route,

        ) {
            val customer =
                navController.previousBackStackEntry?.savedStateHandle?.get<Customer>("customer")
            val customerViewModel = koinViewModel<CustomerViewModel>()
            DetailsCustomerScreen(
                navController,
                customer,
                customerViewModel
            )
        }

        composable(Routes.Service.route) {
            val serviceViewModel = koinViewModel<ServiceViewModel>()
            val servicesListUiState by serviceViewModel.itemListState.collectAsState()

            ServiceScreen(
                serviceViewModel = serviceViewModel,
                serviceListState = servicesListUiState,
                onNewServiceClick = {
                },
                onServiceClick = {
                },
                onDeleteService = { }
            ) { serviceViewModel.getAllItemsByItemType(ItemType.SERVICE) }
        }

//        composable(
//            Routes.EditCostumer.route + "/{id}",
//            arguments = listOf(navArgument(name = "id") { type = NavType.IntType })
//        ) {
//            val customerViewModel = koinViewModel<CustomerViewModel>()
//            val customersListUiState by customerViewModel.customerListState.collectAsState()
//
//            DetailsCustomerScreen(navController,customersListUiState ,it.arguments?.getInt("id"),customerViewModel)
//        }
    }
}
