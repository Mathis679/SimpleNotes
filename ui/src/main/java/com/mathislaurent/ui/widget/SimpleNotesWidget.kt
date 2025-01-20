package com.mathislaurent.ui.widget

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.currentState
import androidx.glance.state.GlanceStateDefinition
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.mathislaurent.ui.theme.DefaultCardColor

class SimpleNotesWidget: GlanceAppWidget() {

    override val stateDefinition: GlanceStateDefinition<*> = PreferencesGlanceStateDefinition

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val prefs = currentState<Preferences>()
            val lastTitle = prefs[SimpleNotesWidgetReceiver.lastNoteTitlePref]
            val lastContent = prefs[SimpleNotesWidgetReceiver.lastNoteContentPref]
            val lastColor = prefs[SimpleNotesWidgetReceiver.lastNoteColorPref]
            SimpleNotesWidgetContent(
                title = lastTitle ?: "No pref",
                content = lastContent ?: "No pref",
                color = lastColor ?: DefaultCardColor.PURPLE.color
            )
        }
    }
}