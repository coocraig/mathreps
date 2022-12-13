package com.example.mathreps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mathreps.data.Attempt
import com.example.mathreps.databinding.FragmentPregameBinding
import com.example.mathreps.databinding.FragmentProblemBinding

//This class will be responsible for pushing the data onto the database and thereby checking if the answer is wrong or not
//This class will have access to both viewmodels (hopefully this works)
class Problem : Fragment() {
    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    // to share the ViewModel across fragments.
//    private val viewModel: MathRepsViewModel by activityViewModels{
//        MathRepsViewModel.MathRepsViewModelFactory(
//            (activity?.application as MathRepsApplication).database.itemDao()
//        )
//    }
//    lateinit var attempt: Attempt


    private var _binding: FragmentProblemBinding? = null

    private val binding get() = _binding!!

    //Creates a reference to the second viewmodel
    private val sharedViewModel: MathViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProblemBinding.inflate(inflater, container, false)
        sharedViewModel.setNumbers(sharedViewModel.range.value)
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = sharedViewModel
            problem = this@Problem
        }
        binding.submitAnswerButton.setOnClickListener{
            onToResults()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onToResults()
    {
        var input = binding.answer.text.toString()
        sharedViewModel.setAnswer(input)
        sharedViewModel.setResultText()


        findNavController().navigate(R.id.action_problem_to_rating)
    }


}