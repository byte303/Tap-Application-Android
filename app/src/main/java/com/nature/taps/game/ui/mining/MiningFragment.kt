package com.nature.taps.game.ui.mining

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.nature.taps.game.R
import com.nature.taps.game.databinding.FragmentMiningBinding

class MiningFragment : Fragment() {

    private var _binding: FragmentMiningBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(requireActivity())[MiningViewModel::class.java]

        _binding = FragmentMiningBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.vm = viewModel
        binding.lifecycleOwner = this

        binding.btnEnergy.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_dialogFragmentEnergy)
        }
        binding.btnTap.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_dashboard_to_dialogFragmentBoost)
        }
        setFragmentResultListener("RESULT") { key, bundle ->
            viewModel.onUpdateCoin()
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}