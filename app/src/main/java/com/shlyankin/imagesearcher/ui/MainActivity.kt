package com.shlyankin.imagesearcher.ui

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.shlyankin.imagesearcher.databinding.ActivityMainBinding
import com.shlyankin.imagesearcher.ui.main.MainFragment
import com.shlyankin.imagesearcher.utils.getNonGrantedPermissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private companion object {
        const val PERMISSIONS_CODE = 1703
    }

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainFragment by lazy { MainFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initViews()
        checkPermissions()
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

    private fun checkPermissions() {
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val nonGrantedPermissions = getNonGrantedPermissions(*permissions)
        if (nonGrantedPermissions.isNotEmpty()) {
            requestPermissions(nonGrantedPermissions, PERMISSIONS_CODE)
        }
    }
}