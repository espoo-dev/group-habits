package com.group.so.core.ui.components

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import kotlin.math.absoluteValue

class MaskVisualTransformation(private val mask: String) : VisualTransformation {

    private val specialSymbolsIndices = mask.indices.filter { mask[it] != '#' }

    override fun filter(text: AnnotatedString): TransformedText {
        var out = ""
        var maskIndex = 0
        text.forEach { char ->
            while (specialSymbolsIndices.contains(maskIndex)) {
                out += mask[maskIndex]
                maskIndex++
            }
            out += char
            maskIndex++
        }
        return TransformedText(AnnotatedString(out), offsetTranslator())
    }

    private fun offsetTranslator() = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            val offsetValue = offset.absoluteValue
            if (offsetValue == 0) return 0
            var numberOfHashtags = 0
            val masked = mask.takeWhile {
                if (it == '#') numberOfHashtags++
                numberOfHashtags < offsetValue
            }
            return masked.length + 1
        }

        override fun transformedToOriginal(offset: Int): Int {
            return mask.take(offset.absoluteValue).count { it == '#' }
        }
    }
}

// @Composable
// fun CustomTextField() {
//    var text by remember { mutableStateOf("") }
//    OutlinedTextField(
//        value = text,
//        onValueChange = { it ->
//            if (it.length <= INPUT_LENGTH) {
//                text = it.filter { it.isDigit() }
//            }
//        },
//        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//        visualTransformation = MaskVisualTransformation(MASK)
//    )
// }
//
// object NumberDefaults {
//    const val MASK = "#####-###"
//    const val INPUT_LENGTH = 8 // Equals to "#####-###".count { it == '#' }
// }
