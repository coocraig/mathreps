package com.example.mathreps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mathreps.databinding.FragmentPregameBinding
import com.example.mathreps.databinding.FragmentSettingsBinding
import com.example.mathreps.databinding.FragmentStatisticsBinding


class Statistics : Fragment() {
    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
    private val dataViewModel: MathRepsViewModel by activityViewModels {
        MathRepsViewModel.MathRepsViewModelFactory(
            (activity?.application as MathRepsApplication).database
                .attemptDao()
        )
    }

    private var _binding: FragmentStatisticsBinding? = null

    private val binding get() = _binding!!

    //private val viewModel: MathViewModel by activityViewModels()

    private val sharedViewModel: MathViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = sharedViewModel
            //dViewModel = dataViewModel
            statistics = this@Statistics
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun toSettings()
    {
        findNavController().navigate(R.id.action_statisticsOV_to_settings)
    }

    fun toPregame()
    {
        findNavController().navigate(R.id.action_statisticsOV_to_pregame)
    }
}