package com.shlyankin.imagesearcher.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.shlyankin.imagesearcher.databinding.ActivityMainBinding
import com.shlyankin.imagesearcher.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainFragment by lazy { MainFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        supportFragmentManager.commit {
            replace(
                binding.rootContainer.id,
                mainFragment,
                mainFragment.javaClass.name
            )
        }
    }
}