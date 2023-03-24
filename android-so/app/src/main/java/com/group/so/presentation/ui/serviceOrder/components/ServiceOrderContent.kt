@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.presentation.ui.serviceOrder.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.group.so.R
import com.group.so.core.presentation.components.fields.ErrorField
import com.group.so.core.presentation.components.validations.TextState
import com.group.so.ui.theme.AccentColor
import com.group.so.ui.theme.SecondaryColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceOrderContent(scrollBehavior: TopAppBarScrollBehavior) {
    println(scrollBehavior)
    val nameTextState = remember {
        TextState()
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        androidx.compose.material.OutlinedTextField(
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

        androidx.compose.material.OutlinedTextField(
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
        androidx.compose.material.OutlinedTextField(
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
        Spacer(modifier = Modifier.size(30.dp))
        Divider(
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth() // fill the max height
                .width(1.dp)
        )
        Spacer(modifier = Modifier.size(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            androidx.compose.material.IconButton(
                onClick = {},
                modifier = Modifier.background(SecondaryColor, RoundedCornerShape(8.dp))
            ) {
                Text("2")
            }

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(50.dp) // fill the max height
                    .width(1.dp)
            )
            Text("Produto(s)")
            Text("R$ 200,00")
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            androidx.compose.material.IconButton(
                onClick = {},
                modifier = Modifier.background(AccentColor, RoundedCornerShape(8.dp))
            ) {
                Text("1")
            }

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .height(50.dp) // fill the max height
                    .width(1.dp)
            )
            Text("Serviço(s)")
            Text("R$ 100,00")
        }
        Spacer(modifier = Modifier.size(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Row() {
                Image(
                    painter = painterResource(id = R.drawable.ic_circle_secondary),
                    contentDescription = ""
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_circle_accent),
                    contentDescription = ""
                )
            }

            Text("3 itens")
            Text("R$ 300,00")
        }
    }
}
