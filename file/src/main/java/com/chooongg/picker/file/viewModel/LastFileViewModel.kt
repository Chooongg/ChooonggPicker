package com.chooongg.picker.file.viewModel

import android.content.Context
import android.provider.MediaStore.Images.Media
import androidx.lifecycle.ViewModel
import com.chooongg.ktx.withMain
import java.io.File
import java.util.LinkedList
import java.util.Queue

class LastFileViewModel(
    private val supportedTypes: Array<out String>, private val excludedTypes: Array<out String>
) : ViewModel() {


    fun getLastFiles(context: Context) {
        context.contentResolver.query(Media.EXTERNAL_CONTENT_URI, null, null, null, null)?.use {
            Media.EXTERNAL_CONTENT_URI
        }
    }

    /**
     * 查找文件夹下的所有文件
     */
    suspend fun findCurrentFolderAllFile(paths: List<String>) {
        val fileList: ArrayList<File> = ArrayList()
        val files: Queue<File> = LinkedList()
        files.addAll(List(paths.size) { File(paths[it]) })
        while (!files.isEmpty()) {
            val file = files.remove()
            if (file.isDirectory) {
                val listFiles = file.listFiles(
                    PickerFileFilter(
                        FilePickerConstant.filterTypes, FilePickerConstant.filterTypeMode
                    )
                )
                if (!listFiles.isNullOrEmpty()) files.addAll(listFiles.toList())
            } else if (file.exists()) {
                fileList.add(file)
            }
        }
        val sortList = fileList.sortedWith(FilePickerUtils.SortByTime())
        val returnList = FileItem.getPickerFileList(sortList)
        withMain {
            onFindFileListListener?.onFindFileList("", returnList)
        }
    }
}