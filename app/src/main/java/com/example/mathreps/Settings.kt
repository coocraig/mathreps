package com.example.mathreps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mathreps.databinding.FragmentPregameBinding
import com.example.mathreps.databinding.FragmentSettingsBinding


class Settings : Fragment() {
    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
//    private val viewModel: MathRepsViewModel by activityViewModels {
//        MathRepsViewModelFactory(
//            (activity?.application as MathRepsApplication).database
//                .itemDao()
//        )
//    }

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    private val sharedViewModel: MathViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = this.viewModel
            settings = this@Settings
        }

        binding.submitButton.setOnClickListener{
            sharedViewModel.setAudio(binding.toggle.isChecked)
            //findNavController().navigate(R.id.action_settings_to_pregame)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun toStats()
    {
        findNavController().navigate(R.id.action_settings_to_statisticsOV)
    }

    fun toPregame()
    {
        findNavController().navigate(R.id.action_settings_to_pregame)
    }
}