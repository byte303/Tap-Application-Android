package com.nature.taps.game.ui.improve

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.nature.taps.game.R
import com.nature.taps.game.databinding.FragmentImproveBinding
import com.nature.taps.game.databinding.FragmentMiningBinding
import com.nature.taps.game.ui.mining.MiningViewModel

class FragmentImprove : Fragment() {

    private var _binding: FragmentImproveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(requireActivity())[MiningViewModel::class.java]

        _binding = FragmentImproveBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.vm = viewModel
        binding.lifecycleOwner = this

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}