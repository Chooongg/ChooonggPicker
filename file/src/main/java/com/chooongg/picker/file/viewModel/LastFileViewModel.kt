package com.chooongg.picker.file.viewModel

import android.content.Context
import android.net.Uri
import android.os.FileUtils
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import java.io.File

class LastFileViewModel : ViewModel() {

    fun getLastFiles(context: Context) {
        context.contentResolver.query(FileProvider.getUriForFile(), null, null, null, null)?.use {

        }
    }
}