package com.mathislaurent.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.mathislaurent.ui.MainActivity
import com.mathislaurent.ui.theme.onPrimaryContainerLight

@Composable
fun SimpleNotesWidgetContent(
    title: String,
    content: String,
    color: Long
) {
    Column(
        modifier = GlanceModifier
            .fillMaxSize()
            .clickable(onClick = actionStartActivity<MainActivity>())
            .background(Color(color))
            .padding(16.dp),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            style = TextStyle(
                color = ColorProvider(onPrimaryContainerLight),
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(24f, TextUnitType.Sp)
            )
        )
        Text(
            modifier = GlanceModifier.padding(top = 16.dp),
            text = content,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = TextUnit(16f, TextUnitType.Sp)
            )
        )
    }
}