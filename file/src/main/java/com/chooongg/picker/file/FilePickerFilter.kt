package com.chooongg.picker.file

import com.chooongg.picker.file.FilePicker.Config.mode
import java.io.File
import java.io.FileFilter

class FilePickerFilter(
    private val supportedTypes: Array<out String>,
    private val excludedTypes: Array<out String>,
    private val excludedDotStart: Boolean
) : FileFilter {
    override fun accept(pathname: File?): Boolean {
        if (pathname == null) return false
        if (pathname.isDirectory && !pathname.isHidden) return true
        if (pathname.name.isEmpty()) return false
        if (excludedDotStart && pathname.name[0] == '.') return false
        val fileFormat = pathname.name.split('.').let {
            if (it.size > 1) it.last() else ""
        }

//        // 忽略压缩文件
//        if (pathname.name.endsWith("zip", true) || pathname.name.endsWith(
//                "rar", true
//            ) || pathname.name.endsWith("7z", true)
//        ) {
//            return false
//        }
        if () return if (types.isNotEmpty()) {
            var isEqualType = false
            types.forEach {
                if ((pathname.name.endsWith(it.lowercase()) || pathname.name.endsWith(it.uppercase())) && !pathname.isHidden) {
                    isEqualType = true
                }
            }
            if (isEqualType) mode else !mode
        } else true
    }
}