@file:Suppress("LongMethod", "FunctionNaming", "FunctionParameterNaming")

package com.group.so.core.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.group.so.data.ItemType
import com.group.so.data.entities.model.Customer
import com.group.so.data.entities.model.Item
import com.group.so.presentation.ui.category.CategoryListScreen
import com.group.so.presentation.ui.category.CategoryViewModel
import com.group.so.presentation.ui.customer.AddScreenCustomer
import com.group.so.presentation.ui.customer.CustomerScreen
import com.group.so.presentation.ui.customer.CustomerViewModel
import com.group.so.presentation.ui.customer.DetailsCustomerScreen
import com.group.so.presentation.ui.forgotPassword.ForgotPasswordScreen
import com.group.so.presentation.ui.home.HomeScreen
import com.group.so.presentation.ui.login.LoginScreen
import com.group.so.presentation.ui.login.LoginViewModel
import com.group.so.presentation.ui.product.AddProductScreen
import com.group.so.presentation.ui.product.DetailsProductScreen
import com.group.so.presentation.ui.product.ProductScreen
import com.group.so.presentation.ui.product.ProductViewModel
import com.group.so.presentation.ui.service.AddScreenService
import com.group.so.presentation.ui.service.DetailsServiceScreen
import com.group.so.presentation.ui.service.ServiceScreen
import com.group.so.presentation.ui.service.ServiceViewModel
import com.group.so.presentation.ui.serviceOrder.AddScreenOrderService
import com.group.so.presentation.ui.serviceOrder.OrderChooseItemsScreen
import com.group.so.presentation.ui.serviceOrder.ServiceOrderScreen
import com.group.so.presentation.ui.serviceOrder.ServiceOrderViewModel
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

        composable(Routes.ForgotPassword.route) {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                ForgotPasswordScreen()
            }
        }

        composable(Routes.Category.route) {
            val categoryViewModel = koinViewModel<CategoryViewModel>()
            val categoriesListUiState by categoryViewModel.categoryState.collectAsStateWithLifecycle()

            CategoryListScreen(
                navController,
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
            val customersListUiState by customerViewModel.customerListState.collectAsStateWithLifecycle()

            CustomerScreen(
                navController = navController,
                customerViewModel = customerViewModel,
                customerListState = customersListUiState,
                onNewCustomerClick = {
                    navController.navigate(Routes.NewCustomer.route)
                },
                onCustomerClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "customer",
                        value = it
                    )
                    // navController.navigate(Routes.EditCostumer.route + "/${it.id}")
                    navController.navigate(Routes.EditCustomer.route)
                },
                onDeleteCustomer = { customerViewModel.deleteCustomer(it.id) }
            ) { customerViewModel.fetchLatestCustomers() }
        }
        composable(Routes.NewCustomer.route) {
            val customerViewModel = koinViewModel<CustomerViewModel>()
            AddScreenCustomer(
                navController,
                customerViewModel,
            )
        }
        composable(
            Routes.EditCustomer.route,

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
            val servicesListUiState by serviceViewModel.itemListState.collectAsStateWithLifecycle()

            ServiceScreen(
                navController = navController,
                serviceViewModel = serviceViewModel,
                serviceListState = servicesListUiState,
                onNewServiceClick = {
                    navController.navigate(Routes.NewService.route)
                },
                onServiceClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "service",
                        value = it
                    )

                    navController.navigate(Routes.EditService.route)
                },
                onDeleteService = {
                    serviceViewModel.deleteItem(it.id)
                }
            ) { serviceViewModel.getAllItemsByItemType(ItemType.SERVICE) }
        }

        composable(Routes.NewService.route) {
            val serviceViewModel = koinViewModel<ServiceViewModel>()
            AddScreenService(
                navController,
                serviceViewModel,
            )
        }
        composable(
            Routes.EditService.route,

        ) {
            val service =
                navController.previousBackStackEntry?.savedStateHandle?.get<Item>("service")
            val serviceViewModel = koinViewModel<ServiceViewModel>()
            DetailsServiceScreen(
                navController,
                service,
                serviceViewModel
            )
        }

        composable(Routes.Product.route) {
            val productViewModel = koinViewModel<ProductViewModel>()
            val productListUiState by productViewModel.productListState.collectAsStateWithLifecycle()

            ProductScreen(
                navController = navController,
                productViewModel = productViewModel,
                productListState = productListUiState,
                onNewProductClick = {
                    navController.navigate(Routes.NewProduct.route)
                },
                onProductClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set(
                        key = "product",
                        value = it
                    )

                    navController.navigate(Routes.EditProduct.route)
                },
                onDeleteProduct = {
                    productViewModel.deleteProduct(it.id)
                }
            ) { productViewModel.getAllProducts() }
        }
        composable(Routes.NewProduct.route) {
            val productViewModel = koinViewModel<ProductViewModel>()
            val categoriesListState by productViewModel.categoriesListState.collectAsStateWithLifecycle()
            AddProductScreen(
                navController,
                productViewModel,
                categoriesListState
            )
        }

        composable(
            Routes.EditProduct.route,

        ) {
            val product =
                navController.previousBackStackEntry?.savedStateHandle?.get<Item>("product")
            val productViewModel = koinViewModel<ProductViewModel>()
            val categoriesListState by productViewModel.categoriesListState.collectAsStateWithLifecycle()
            DetailsProductScreen(
                navController,
                product,
                productViewModel,
                categoriesListState
            )
        }
        composable(Routes.ServiceOrder.route) {

            ServiceOrderScreen(
                navController = navController,
                onNewServiceClick = {
                    navController.navigate(Routes.NewServiceOrder.route)
                },
            )
        }

        composable(Routes.NewServiceOrder.route) {
            val serviceOrderViewModel = koinViewModel<ServiceOrderViewModel>()
            AddScreenOrderService(
                navController = navController,
                serviceOrderViewModel = serviceOrderViewModel
            )
        }

        composable(
            Routes.OrderChooseProducts.route + "/{itemType}",
            arguments = listOf(navArgument(name = "itemType") { type = NavType.StringType })
        ) {
            val serviceOrderViewModel = koinViewModel<ServiceOrderViewModel>()
            val productListUiState by serviceOrderViewModel.itemsListState.collectAsStateWithLifecycle()
            val itemType = it.arguments?.getString("itemType")
            OrderChooseItemsScreen(
                navController = navController,
                viewModel = serviceOrderViewModel,
                itemType,
                productListUiState
            )
        }
    }
}
