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
            return FilePickerBuilder(context, null)
        }

        fun with(targetView: View): FilePickerBuilder {
            return FilePickerBuilder(targetView.context, targetView)
        }
    }

    open class Configuration internal constructor() {

        @StyleRes
        var theme: Int = 0

        @FilePickerMode
        var mode: Int = MODE_PAGE

        @StringRes
        var chooseButtonText: Int = 0

        var supportFileFormats: Array<out String> = emptyArray()

        var excludeCompressFiles: Boolean = false
        var excludeDotStartFiles: Boolean = false
        var excludeFileFormats: Array<out String> = emptyArray()
        var excludeHidden: Boolean = true
    }

    internal object Config : Configuration() {

        @IntRange(from = 1)
        var maxCount: Int = 1

        var selectedFiles: List<File>? = null

        var listener: ((MutableList<File>) -> Unit)? = null

        fun reset() {
            theme = FilePickerGlobalConfig.theme
            mode = FilePickerGlobalConfig.mode
            maxCount = 1
            chooseButtonText = FilePickerGlobalConfig.chooseButtonText
            supportFileFormats = FilePickerGlobalConfig.supportFileFormats
            excludeCompressFiles = FilePickerGlobalConfig.excludeCompressFiles
            excludeDotStartFiles = FilePickerGlobalConfig.excludeDotStartFiles
            excludeFileFormats = FilePickerGlobalConfig.excludeFileFormats
            selectedFiles = null
            listener = null
        }
    }

    class FilePickerBuilder internal constructor(
        private val context: Context, private val targetView: View?
    ) {

        init {
            Config.reset()
        }

        fun theme(@StyleRes theme: Int) = apply {
            Config.theme = theme
        }

        fun pickerMode(@FilePickerMode mode: Int) = apply {
            Config.mode = mode
        }

        fun maxCount(@IntRange(from = 1) maxCount: Int) = apply {
            Config.maxCount = maxCount
        }

        fun supportFileFormats(vararg types: String) = apply {
            Config.supportFileFormats = types
        }

        fun excludeCompressFiles(boolean: Boolean) = apply {
            Config.excludeCompressFiles = boolean
        }

        fun excludeDotStartFiles(boolean: Boolean) = apply {
            Config.excludeDotStartFiles = boolean
        }

        fun excludeFileFormats(vararg types: String) = apply {
            Config.excludeFileFormats = types
        }

        fun excludeHidden(boolean: Boolean) = apply {
            Config.excludeHidden = boolean
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