package com.chooongg.picker.file

import java.io.File
import java.io.FileFilter

class FilePickerFilter(
) : FileFilter {
    override fun accept(file: File?): Boolean {
        if (file == null) return false
        // 忽略隐藏文件
        if (FilePicker.Config.excludeHidden && file.isHidden) return false
        else if (file.isDirectory) return true
        // 忽略空文件名
        if (file.name.isEmpty()) return false
        // 忽略点开头文件
        if (FilePicker.Config.excludeDotStartFiles && file.name.first() == '.') return false
        // 文件格式
        val fileFormat = file.name.split('.').let {
            if (it.size > 1) it.last().lowercase() else null
        }
        // 忽略压缩格式文件
        if (FilePicker.Config.excludeCompressFiles) {
            return !arrayOf(
                "zip",
                "rar",
                "7z",
                "tar",
                "gz",
                "apz",
                "ar",
                "bz",
                "car",
                "dar",
                "cpgz",
                "f",
                "ha",
                "hbc",
                "hbc2",
                "hbe",
                "hpk",
                "hyp"
            ).contains(fileFormat)
        }
        // 排除指定文件格式
        if (FilePicker.Config.excludeFileFormats.contains(fileFormat)) return false
        // 支持指定文件格式
        if (FilePicker.Config.supportFileFormats.contains(fileFormat)) return true
        // 不筛选
        return true
    }
}