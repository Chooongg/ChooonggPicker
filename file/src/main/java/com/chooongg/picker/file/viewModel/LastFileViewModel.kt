package com.chooongg.picker.file.viewModel

import android.content.Context
import android.net.Uri
import android.os.FileUtils
import android.provider.DocumentsContract
import android.provider.DocumentsProvider
import android.provider.MediaStore.Images.Media
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import java.io.File

class LastFileViewModel : ViewModel() {

    fun getLastFiles(context: Context) {
        context.contentResolver.query(Media.EXTERNAL_CONTENT_URI, null, null, null, null)?.use {
            Media.EXTERNAL_CONTENT_URI
        }
    }
}