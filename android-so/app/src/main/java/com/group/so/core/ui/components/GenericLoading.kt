@file:Suppress("LongMethod", "FunctionParameterNaming", "FunctionNaming", "LongParameterList")

package com.group.so.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.group.so.R
import com.group.so.core.ui.components.animations.Loader
import com.group.so.ui.theme.SOTheme

@Composable
fun GenericLoading(
    message: String? = null
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier.size(100.dp)) {
            Loader()
        }
        //CircularProgressIndicator()
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = message ?: stringResource(id = R.string.msg_loading))
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewGenericLoading() {
    SOTheme {
        GenericLoading()
    }
}
