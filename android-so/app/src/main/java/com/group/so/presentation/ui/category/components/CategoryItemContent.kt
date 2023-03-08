@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.category.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.group.so.core.ui.components.DialogDelete
import com.group.so.data.entities.model.Category
import com.group.so.presentation.ui.category.CategoryEditScreen
import com.group.so.presentation.ui.category.CategoryViewModel
import com.group.so.ui.theme.Poppins
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun CategoryItemContent(
    categoryViewModel: CategoryViewModel,
    category: Category,
    onCategoryClick: (Category) -> Unit,
    onDeleteCategory: (Category) -> Unit,
) {

    val coroutineScope = rememberCoroutineScope()
    // val revealState = rememberRevealState()

    var openDialogDelete by remember {
        mutableStateOf(false) // Initially dialog is closed
    }

    var openDialogEdit by remember {
        mutableStateOf(false) // Initially dialog is closed
    }

    DialogDelete(showDialog = openDialogDelete, onDismiss = {
        openDialogDelete = false
    }, onDeleteSuccess = {
            coroutineScope.launch {
                onDeleteCategory(category)
                // revealState.snapTo(RevealValue.Default)
            }
        })

    CategoryEditScreen(
        category,
        categoryViewModel = categoryViewModel,
        showDialog = openDialogEdit
    ) { openDialogEdit = false }

    Column() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(
                        bounded = true,
                        radius = 250.dp,
                        color = MaterialTheme.colors.primary
                    ),
                    onClick = {
                        openDialogEdit = true
                        onCategoryClick(category)
                    }
                )
        ) {
            Text(
                text = "${category.name}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontFamily = Poppins,
                modifier = Modifier
                    .padding(10.dp).weight(1f),

                textAlign = TextAlign.Start,
                softWrap = true,
                color = Color.Black,
            )
            IconButton(
                onClick = {
                    openDialogDelete = true
                }
            ) {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .size(30.dp),
                    imageVector = Icons.Outlined.Delete,
                    tint = MaterialTheme.colors.primary,
                    contentDescription = null
                )
            }
        }
        Divider()
    }
}
