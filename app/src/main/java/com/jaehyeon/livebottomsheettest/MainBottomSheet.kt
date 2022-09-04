package com.jaehyeon.livebottomsheettest

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jaehyeon.livebottomsheettest.databinding.BottomsheetMainBinding

/**
 * Created by Jaehyeon on 2022/09/05.
 */
class MainBottomSheet: BottomSheetDialogFragment() {

    private val binding by lazy {
        BottomsheetMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainBottomSheetViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        resources.getStringArray(R.array.test).also {
            ArrayAdapter(requireContext(), R.layout.item_list, it).also { adapter ->
                binding.spinner.apply {
                    this.adapter = adapter
                    this.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {

                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            when(position) {
                                0 -> {
                                    viewModel.setDayTypeValue(DayType.Day15)
                                }

                                1 -> {
                                    viewModel.setDayTypeValue(DayType.Day30)
                                }

                                2 -> {
                                    viewModel.setDayTypeValue(DayType.Day50)
                                }

                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {}
                    }
                }
            }
        }

        with(binding) {
            btnTypeA.setOnClickListener {
                viewModel.setButtonState(0)
            }

            btnTypeB.setOnClickListener {
                viewModel.setButtonState(1)
            }

            btnTypeC.setOnClickListener {
                viewModel.setButtonState(2)
            }

            binding.btnDone.setOnClickListener {
                //todo Data 저장
                dismiss()
            }
        }

        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.typeAButtonState.observe(viewLifecycleOwner) {
            if(it)
                binding.btnTypeA.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_android_24)
            else
                binding.btnTypeA.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_android_24_white)
        }

        viewModel.typeBButtonState.observe(viewLifecycleOwner) {
            if(it)
                binding.btnTypeB.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_bedtime_24)
            else
                binding.btnTypeB.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_bedtime_24_white)
        }

        viewModel.typeCButtonState.observe(viewLifecycleOwner) {
            if(it)
                binding.btnTypeC.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_dark_mode_24)
            else
                binding.btnTypeC.background = ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_dark_mode_white)
        }

        viewModel.dayTypeValue.observe(viewLifecycleOwner) {

        }

        viewModel.lifeType.observe(viewLifecycleOwner) {
            if (it == LifeType.None) {
                binding.tvResult.visibility = View.INVISIBLE
                binding.btnDone.isEnabled = false
            }
            else {
                binding.btnDone.isEnabled = true
                binding.tvResult.visibility = View.VISIBLE
                binding.tvResult.text = "설정된 Life는 ${it.life} 개 입니다."
            }
        }
    }
}