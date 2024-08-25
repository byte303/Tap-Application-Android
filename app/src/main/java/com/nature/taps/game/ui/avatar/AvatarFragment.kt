package com.nature.taps.game.ui.avatar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.nature.taps.game.databinding.FragmentAvatarBinding
import com.nature.taps.game.ui.mining.MiningViewModel

class AvatarFragment : Fragment() {

    private val viewModel : MiningViewModel by viewModels()

    private var _binding: FragmentAvatarBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAvatarBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val viewModel = ViewModelProvider(requireActivity())[MiningViewModel::class.java]
        binding.vm = viewModel
        binding.lifecycleOwner = this


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}