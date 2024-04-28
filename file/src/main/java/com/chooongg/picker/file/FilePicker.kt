package com.chooongg.picker.file

import android.content.Context
import androidx.annotation.IntDef
import androidx.annotation.IntRange
import androidx.annotation.StyleRes
import java.io.File

object FilePicker {

    const val MODE_PAGE = 0
    const val MODE_BOTTOM_SHEET = 1

    @IntDef(MODE_PAGE, MODE_BOTTOM_SHEET)
    annotation class FilePickerMode

    fun with(context: Context): FilePickerBuilder {
        Config.reset()
        return FilePickerBuilder(context)
    }

    internal object Config {

        @StyleRes
        var theme: Int = 0

        @FilePickerMode
        var mode: Int = MODE_PAGE

        @IntRange(from = 1)
        var maxCount: Int = 1
        var supportedTypes: List<String>? = null
        var excludedTypes: List<String>? = null
        var selectedFiles: List<File>? = null

        fun reset() {
            theme = 0
            mode = MODE_PAGE
            maxCount = 1
            supportedTypes = null
            excludedTypes = null
            selectedFiles = null
        }
    }

    class FilePickerBuilder internal constructor(val context: Context) {

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
    }
}