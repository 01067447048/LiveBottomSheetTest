package com.jaehyeon.livebottomsheettest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jaehyeon.livebottomsheettest.databinding.FragmentMainBinding

/**
 * Created by Jaehyeon on 2022/09/05.
 */
class MainFragment: Fragment() {

    private val binding: FragmentMainBinding by lazy {
        FragmentMainBinding.inflate(layoutInflater)
    }

    private val mainBottomSheet = MainBottomSheet()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding.button.setOnClickListener {
            MainBottomSheet().also {
                childFragmentManager.beginTransaction().add(it, "TAG").commitNowAllowingStateLoss()
            }
        }

        return binding.root
    }
}