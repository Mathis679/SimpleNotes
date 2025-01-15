package com.mathislaurent.ui.widget

import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.state.PreferencesGlanceStateDefinition
import com.mathislaurent.core.common.ACTION_RECEIVER_UPDATE
import com.mathislaurent.domain.usecase.GetLastNoteUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SimpleNotesWidgetReceiver: GlanceAppWidgetReceiver() {

    companion object {
        val lastNotePref = stringPreferencesKey("lastNote")
    }

    override val glanceAppWidget: GlanceAppWidget = SimpleNotesWidget()

    private val coroutineScope = MainScope()

    @Inject
    lateinit var getLastNoteUseCase: GetLastNoteUseCase

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        refresh(context)
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)
        if (intent.action == ACTION_RECEIVER_UPDATE) {
            refresh(context)
        }
    }

    private fun refresh(context: Context) {
        coroutineScope.launch {
            val lastNote = getLastNoteUseCase()
            val glanceId = GlanceAppWidgetManager(context).getGlanceIds(SimpleNotesWidget::class.java).firstOrNull()

            glanceId?.let {
                updateAppWidgetState(context, PreferencesGlanceStateDefinition, it) { prefs ->
                    prefs.toMutablePreferences().apply {
                        this[lastNotePref] = lastNote?.title ?: "No note"
                    }
                }
                glanceAppWidget.update(context, it)
            }
        }

    }
}