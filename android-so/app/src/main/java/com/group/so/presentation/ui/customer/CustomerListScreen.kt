@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.customer

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.group.so.R
import com.group.so.core.State
import com.group.so.core.ui.components.AsyncData
import com.group.so.core.ui.components.CustomTopAppBar
import com.group.so.core.ui.components.GenericError
import com.group.so.core.ui.components.SearchAppBarState
import com.group.so.core.ui.components.SharedViewModel
import com.group.so.data.entities.model.Customer
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

const val ANIMATION_TWEEN_300 = 300
const val ANIMATION_TWEEN_100 = 100

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
@ExperimentalComposeApi
fun CustomerScreen(
    customerListState: State<List<Customer>>,
    onNewCustomerClick: () -> Unit,
    onCustomerClick: (Customer) -> Unit,
    onDeleteCustomer: (Customer) -> Unit,
    reloadCustomers: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val sharedViewModel = koinViewModel<SharedViewModel>()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState

    Scaffold(scaffoldState = scaffoldState, topBar = {
        CustomTopAppBar(
            titleToolbar = stringResource(id = R.string.title_toolbar_customers),
            sharedViewModel = sharedViewModel,
            searchAppBarState = searchAppBarState,
            searchTextState = searchTextState,
            onSubmitSearch = {
            }
        )
    }, floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text(text = stringResource(R.string.title_fab_new_customer)) },
                onClick = {
                    onNewCustomerClick
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = stringResource(
                            id = R.string.cd_new_category
                        )
                    )
                }
            )
        }) {

        Box(Modifier.padding(it)) {
            AsyncData(resultState = customerListState, errorContent = {
                GenericError(
                    onDismissAction = reloadCustomers
                )
            }) { customersList ->
                customersList?.let {
                    SwipeRefresh(
                        state = rememberSwipeRefreshState(customerListState is State.Loading),
                        onRefresh = reloadCustomers,
                    ) {
                        if (it.isEmpty()) {
                            EmptyList()
                        } else {
                            LazyColumn {
                                items(customersList, key = { customer -> customer.id }) { item ->
                                    CustomerItem(
                                        customer = item,
                                        onCustomerClick = onCustomerClick,
                                        onDeleteCustomer = onDeleteCustomer,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CustomerItem(
    customer: Customer,
    onCustomerClick: (Customer) -> Unit,
    onDeleteCustomer: (Customer) -> Unit,
) {

    val scope = rememberCoroutineScope()
    // Animation to slide out the component
    val translateXAnim = remember(customer.id) {
        Animatable(0f)
    }
    var markedAsDeleted by remember(customer.id) {
        mutableStateOf(false)
    }
    BoxWithConstraints(
        modifier = Modifier
            .graphicsLayer {
                translationX = translateXAnim.value
            }
            .animateContentSize(
                animationSpec = tween(ANIMATION_TWEEN_100, easing = LinearEasing),
                finishedListener = { _, _ ->
                    if (markedAsDeleted) {
                        onDeleteCustomer(customer)
                    }
                }
            )
            .then(
                if (markedAsDeleted) Modifier.height(height = 0.dp)
                else Modifier.wrapContentSize()
            )
    ) {
        CustomerItemContent(
            customer = customer,
            onCustomerClick = onCustomerClick,
            onDeleteCustomer = {
                scope.launch {
                    translateXAnim.animateTo(
                        targetValue = constraints.maxWidth.toFloat(),
                        animationSpec = tween(ANIMATION_TWEEN_300)
                    )
                    markedAsDeleted = true
                }
            }
        )
    }
}

@Composable
fun EmptyList() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(id = R.string.msg_empty_customer_list))
    }
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CustomerItemContent(
    customer: Customer,
    onCustomerClick: (Customer) -> Unit,
    onDeleteCustomer: (Customer) -> Unit,
) {

    Card(
        modifier = Modifier
            .padding(all = dimensionResource(id = R.dimen.customer_card_padding_all_dimen))
            .shadow(
                ambientColor = Color.Blue,
                elevation = dimensionResource(id = R.dimen.customer_elevation_shadow_card_dimen)
            ),
        elevation = dimensionResource(id = R.dimen.customer_elevation_card_dimen),
        onClick = {
            onCustomerClick
        }
    ) {
        Row(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.customer_content_item_dimen))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CustomerBuildInfo(
                customer = customer,
                modifier = Modifier.weight(1f)
            )
            DeleteButton {
                onDeleteCustomer
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalMaterialApi
@Composable
private fun CustomerCustomType() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {

        AssistChip(
            onClick = { /* Do something! */ },
            label = { Text("Pessoa Juridica") },
            leadingIcon = {
                Icon(
                    Icons.Filled.Person,
                    contentDescription = "",
                    Modifier.size(AssistChipDefaults.IconSize)
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CustomerBuildInfo(
    customer: Customer,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {

        Text(
            text = customer.name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.customer_name_dimen))
        )

        Text(
            text = "Telefone: ${customer.phone}",
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.customer_phone_dimen))
        )

        CustomerCustomType()
    }
}

@Composable
private fun DeleteButton(onDeleteCustomer: (Customer) -> Unit) {
    IconButton(
        onClick = {
            onDeleteCustomer
        }
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .size(size = 30.dp),
            imageVector = Icons.Outlined.Delete,
            tint = MaterialTheme.colors.primary,
            contentDescription = null
        )
    }
}
