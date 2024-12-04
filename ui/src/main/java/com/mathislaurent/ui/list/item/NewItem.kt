package com.mathislaurent.ui.list.item

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NewItem(
    onClick: () -> Unit
) {
    Card(onClick = { onClick() }) {
        Text(text = "New")
    }
}