package com.chooongg.picker.file

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.annotation.IntDef
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import java.io.File

class FilePicker {

    @IntDef(MODE_PAGE, MODE_BOTTOM_SHEET)
    annotation class FilePickerMode

    companion object {
        const val MODE_PAGE = 0
        const val MODE_BOTTOM_SHEET = 1

        fun with(context: Context): FilePickerBuilder {
            Config.reset()
            return FilePickerBuilder(context, null)
        }

        fun with(targetView: View): FilePickerBuilder {
            return FilePickerBuilder(targetView.context, targetView)
        }
    }

    internal object Config {

        @StyleRes
        var theme: Int = 0

        @FilePickerMode
        var mode: Int = MODE_PAGE

        @IntRange(from = 1)
        var maxCount: Int = 1

        @StringRes
        var chooseButtonText: Int = 0
        var supportedTypes: List<String>? = null
        var excludedTypes: List<String>? = null
        var selectedFiles: List<File>? = null
        var listener: ((MutableList<File>) -> Unit)? = null

        fun reset() {
            theme = 0
            mode = MODE_PAGE
            maxCount = 1
            chooseButtonText = 0
            supportedTypes = null
            excludedTypes = null
            selectedFiles = null
            listener = null
        }
    }

    class FilePickerBuilder internal constructor(
        private val context: Context, private val targetView: View?
    ) {

        fun theme(@StyleRes theme: Int) = apply {
            Config.theme = theme
        }

        fun pickerMode(@FilePickerMode mode: Int) = apply {
            Config.mode = mode
        }

        fun maxCount(@IntRange(from = 1) maxCount: Int) = apply {
            Config.maxCount = maxCount
        }

        fun supportedTypes(vararg types: String) = apply {
            Config.supportedTypes = mutableListOf(*types)
        }

        fun excludedTypes(vararg types: String) = apply {
            Config.excludedTypes = mutableListOf(*types)
        }

        fun selectedFiles(files: List<File>) = apply {
            Config.selectedFiles = files
        }

        fun chooseButtonText(@StringRes id: Int) = apply {
            Config.chooseButtonText = id
        }

        fun start(listener: (MutableList<File>) -> Unit) {
            Config.listener = listener
            context.startActivity(Intent(context, FilePickerActivity::class.java))
        }
    }
}