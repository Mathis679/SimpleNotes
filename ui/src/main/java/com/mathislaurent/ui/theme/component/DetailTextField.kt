package com.mathislaurent.ui.theme.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class,
    ExperimentalLayoutApi::class
)
@Composable
fun DetailTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    maxLines: Int = Int.MAX_VALUE,
    textStyle: TextStyle,
    placeholder: String? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val isFocused = interactionSource.collectIsFocusedAsState().value
    val scrollState = rememberScrollState()
    val imeVisible = WindowInsets.isImeVisible

    LaunchedEffect(imeVisible) {
        if (isFocused && imeVisible) {
            // TODO find another fix (get cursor position rect and bringittoview with cursor rect ?)
            scrollState.animateScrollTo(250)
        }
    }

    BasicTextField(
        modifier = modifier
            .verticalScroll(scrollState)
            .bringIntoViewRequester(bringIntoViewRequester),
        value = value,
        onValueChange = onValueChange,
        maxLines = maxLines,
        textStyle = textStyle,
        interactionSource = interactionSource,
        keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
        decorationBox = @Composable { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                placeholder = {
                    if (value.isEmpty() && placeholder != null) {
                        Text(
                            text = placeholder,
                            style = textStyle,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                },
                label = null,
                leadingIcon = null,
                trailingIcon = null,
                prefix = null,
                suffix = null,
                supportingText = null,
                shape = TextFieldDefaults.shape,
                singleLine = false,
                enabled = true,
                isError = false,
                interactionSource = interactionSource,
                colors = TextFieldDefaults.colors().copy(
                    disabledContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    errorContainerColor = Color.Transparent
                )
            )
        }
    )
}