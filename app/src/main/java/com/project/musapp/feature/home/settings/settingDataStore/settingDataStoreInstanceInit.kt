package com.project.musapp.feature.home.settings.settingDataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

val Context.settingDataStore : DataStore<Preferences> by preferencesDataStore("MusApp settings datastore")