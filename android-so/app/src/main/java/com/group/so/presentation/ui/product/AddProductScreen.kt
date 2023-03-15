@file:Suppress(
    "ComplexCondition",
    "CyclomaticComplexMethod",
    "LongMethod",
    "FunctionParameterNaming",
    "FunctionNaming",
    "LongParameterList",
    "MaxLineLength"
)

package com.group.so.presentation.ui.product

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.ZERO
import com.group.so.core.ui.components.AsyncData
import com.group.so.core.ui.components.buttons.PrimaryButton
import com.group.so.core.ui.components.fields.ErrorField
import com.group.so.core.ui.components.fields.MoneyField
import com.group.so.core.ui.components.generic.GenericError
import com.group.so.core.ui.components.generic.GenericLoading
import com.group.so.core.ui.components.toolbars.custom.TopBarWhite
import com.group.so.core.ui.components.validations.TextState
import com.group.so.data.entities.model.Category
import com.group.so.presentation.ui.Routes
import com.group.so.presentation.ui.product.components.AutoCompleteCategory

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddProductScreen(
    navController: NavController,
    productViewModel: ProductViewModel,
    categoriesListState: State<List<Category>>,

) {
    val scaffoldState = rememberScaffoldState()

    val nameTextState = remember {
        TextState()
    }
    val extraInfoTextState = remember {
        TextState()
    }
    val salePriceTextState = remember { TextState() }

    val purchasePriceTextState = remember { TextState() }

    var category by remember { mutableStateOf(0) }

    var totalProfitLabel by remember { mutableStateOf("") }

    val viewState = productViewModel.registerProductState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewState.value) {
        if (viewState.value is State.Error) {
            Toast.makeText(
                context, (viewState.value as State.Error).error.message, Toast.LENGTH_SHORT
            ).show()
        }
        if (viewState.value is State.Success) {
            Toast.makeText(
                context, R.string.txt_register_success_product, Toast.LENGTH_SHORT
            ).show()
            navController.navigate(Routes.Product.route) {
                popUpTo(Routes.Product.route) { inclusive = true }
            }
        }
    }

    LaunchedEffect(Unit, block = {
        productViewModel.fetchLatestCategories()
    })

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBarWhite(
                stringResource(id = R.string.title_toolbar_add_new_product),
                navController,
                onActionClicked = {
                    if (nameTextState.isValid() &&
                        purchasePriceTextState.isValid() && salePriceTextState.isValid() && category != 0
                    ) {
                        productViewModel.register(
                            name = nameTextState.text,
                            extraInfo = extraInfoTextState.text,
                            salePrice = salePriceTextState.text.toDouble(),
                            purchasePrice = purchasePriceTextState.text.toDouble(),
                            categoryId = category,
                            salesUnitId = 1
                        )
                    }
                }
            )
        },
    ) {
        if (viewState.value is State.Loading) {
            GenericLoading()
        } else {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedTextField(
                    value = nameTextState.text,
                    onValueChange = {
                        nameTextState.text = it
                        nameTextState.validate()
                    },
                    label = { Text(stringResource(R.string.lbl_field_name_product)) },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                nameTextState.error?.let { ErrorField(it) }

                Box(Modifier.padding(it)) {
                    AsyncData(resultState = categoriesListState, errorContent = {
                        GenericError(onDismissAction = {})
                    }) { categoriesList ->
                        categoriesList?.let { categories ->
                            category = categories.first().id
                            if (categories.isEmpty()) {
                                PrimaryButton(
                                    text = stringResource(R.string.title_button_register_category_in_product),
                                    onClick = {
                                        navController.navigate(Routes.Category.route) {
                                            popUpTo(Routes.Product.route) { inclusive = true }
                                        }
                                    },
                                    enabled = nameTextState.isValid() && salePriceTextState.isValid() && purchasePriceTextState.isValid(),
                                    isLoading = viewState.value is State.Loading,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 0.dp)
                                        .padding(top = 16.dp),
                                    backgroundColor = MaterialTheme.colors.primary,
                                    contentColor = Color.White,
                                    contentPadding = PaddingValues(vertical = 14.dp),
                                )
                            } else {
                                AutoCompleteCategory(
                                    categories = categoriesList,
                                    itemSelected = { itemSelected ->
                                        category = itemSelected.id
                                    },
                                )
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = extraInfoTextState.text,
                    onValueChange = {
                        extraInfoTextState.text = it
                        extraInfoTextState.validate()
                    },
                    label = { Text(stringResource(R.string.lbl_field_extra_info_product)) },
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                )
                extraInfoTextState.error?.let { ErrorField(it) }

                MoneyField(
                    labelText = stringResource(R.string.lbl_field_purchase_price_product),
                    textColor = MaterialTheme.colors.primary,
                    valueText = purchasePriceTextState.text,
                    error = purchasePriceTextState.error,
                    iconColor = MaterialTheme.colors.primary,
                    iconClear = purchasePriceTextState.text.isNotBlank(),
                    clearText = {
                        purchasePriceTextState.text = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    purchasePriceTextState.text = it
                    purchasePriceTextState.validate()

                    if (salePriceTextState.text.isNotBlank() && purchasePriceTextState.text.isNotBlank()) {
                        totalProfitLabel = productViewModel.calculateProfitProduct(
                            salePrice = salePriceTextState.text.toDouble(),
                            purchasePrice = purchasePriceTextState.text.toDouble()
                        )
                    }
                }

                MoneyField(
                    labelText = stringResource(R.string.lbl_field_price_product),
                    textColor = MaterialTheme.colors.primary,
                    valueText = salePriceTextState.text,
                    error = salePriceTextState.error,
                    iconColor = MaterialTheme.colors.primary,
                    iconClear = salePriceTextState.text.isNotBlank(),
                    clearText = {
                        salePriceTextState.text = ""
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    salePriceTextState.text = it
                    salePriceTextState.validate()
                    if (salePriceTextState.text.isNotBlank() && purchasePriceTextState.text.isNotBlank()) {
                        totalProfitLabel = productViewModel.calculateProfitProduct(
                            salePrice = salePriceTextState.text.toDouble(),
                            purchasePrice = purchasePriceTextState.text.toDouble()
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        color = if (productViewModel.totalProfit.value <= ZERO)
                            MaterialTheme.colors.error else MaterialTheme.colors.primary,
                        text = totalProfitLabel
                    )
                }

                PrimaryButton(
                    text = stringResource(R.string.title_button_register_product),
                    onClick = {
                        productViewModel.register(
                            name = nameTextState.text,
                            extraInfo = extraInfoTextState.text,
                            salePrice = salePriceTextState.text.toDouble(),
                            purchasePrice = purchasePriceTextState.text.toDouble(),
                            categoryId = category,
                            salesUnitId = 1
                        )
                    },
                    enabled = nameTextState.isValid() && salePriceTextState.isValid() && purchasePriceTextState.isValid(),
                    isLoading = viewState.value is State.Loading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 0.dp)
                        .padding(top = 16.dp),
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = Color.White,
                    contentPadding = PaddingValues(vertical = 14.dp),
                )
            }
        }
    }
}
