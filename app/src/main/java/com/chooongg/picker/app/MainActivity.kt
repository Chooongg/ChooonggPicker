package com.chooongg.picker.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chooongg.picker.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.appBarLayout.addLiftOnScrollListener { _, backgroundColor ->
            window.statusBarColor = backgroundColor
        }
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
//        findViewById<TabLayout>(R.id.tabLayout).apply {
//            for (i in 0..4) {
//                val tab = newTab().setText("asdfasdf")
//                    .setIcon(com.chooongg.picker.core.R.drawable.chooongg_ic_end)
//                if (i == 4) {
//                    tab.select()
//                }
//                addTab(tab)
//            }
//            val childView = getChildAt(0) as ViewGroup
//            childView.children.forEachIndexed { index, view ->
//                showToast("$index")
//                when (index) {
//                    0 -> view.updatePaddingRelative(end = dp2px(4f))
//                    childView.childCount - 1 -> view.updatePaddingRelative(start = dp2px(4f))
//                    else -> view.updatePaddingRelative(start = dp2px(4f), end = dp2px(4f))
//                }
//            }
//        }
    }
}