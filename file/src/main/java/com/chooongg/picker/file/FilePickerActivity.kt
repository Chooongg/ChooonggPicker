package com.chooongg.picker.file

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chooongg.picker.file.databinding.ChooonggActivityFilePickerBinding

class FilePickerActivity : AppCompatActivity() {

    private val binding by lazy { ChooonggActivityFilePickerBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}