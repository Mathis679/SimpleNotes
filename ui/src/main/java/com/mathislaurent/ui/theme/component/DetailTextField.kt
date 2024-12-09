package com.mathislaurent.ui.theme.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
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
    BasicTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        maxLines = maxLines,
        textStyle = textStyle,
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