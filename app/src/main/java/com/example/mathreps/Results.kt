package com.example.mathreps

import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mathreps.databinding.FragmentProblemBinding
import com.example.mathreps.databinding.FragmentResultsBinding


class Results : Fragment() {
    private var _binding: FragmentResultsBinding? = null

    private val binding get() = _binding!!

    //Creates a reference to the second viewmodel
    private val sharedViewModel: MathViewModel by activityViewModels()

    lateinit var victorySound : MediaPlayer
    lateinit var lossSound : MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        victorySound = MediaPlayer.create(activity, R.raw.tadaa)
        lossSound = MediaPlayer.create(activity, R.raw.youlose)
        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val root = binding.root
        return root
    }
    private var nuum = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = sharedViewModel
            results = this@Results
        }
        binding.resultsText.setOnClickListener{
//            if(sharedViewModel.answer.value?.toInt()==sharedViewModel.actualAnswer.value?.toInt() && sharedViewModel.audio.value == true)
            if(sharedViewModel.audio.value == true) {
                if(sharedViewModel.resultText.value.toString().equals("Correct!"))
                {
                    victorySound.start()
                }
                else{
                    lossSound.start()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun toHome(){
        findNavController().navigate(R.id.action_results_to_pregame)
    }

    fun anotherRep(){
        findNavController().navigate(R.id.action_results_to_problem)
    }

}