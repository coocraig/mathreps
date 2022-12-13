package com.example.mathreps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mathreps.databinding.FragmentPregameBinding


class Pregame : Fragment() {


    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
    private val viewModel: MathRepsViewModel by activityViewModels {
        MathRepsViewModelFactory(
            (activity?.application as MathRepsApplication).database
                .itemDao()
        )
    }

    private var _binding: FragmentPregameBinding? = null

    private val binding get() = _binding!!

    //private val sharedViewModel: MathRepsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPregameBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this.viewModel
            pregame = this@Pregame
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun toSettings()
    {
        findNavController().navigate(R.id.action_pregame_to_settings)
    }

    fun toStats()
    {
        findNavController().navigate(R.id.action_pregame_to_statisticsOV)
    }
}