package com.example.mathreps

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mathreps.data.Attempt
import com.example.mathreps.databinding.FragmentRatingBinding
import com.example.mathreps.databinding.FragmentResultsBinding


class Rating : Fragment() {
        private val viewModel: MathRepsViewModel by activityViewModels{
            MathRepsViewModel.MathRepsViewModelFactory(
                (activity?.application as MathRepsApplication).database.attemptDao()
            )
        }



    lateinit var attempt: Attempt

    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = viewModel
            rating = this@Rating
        }
        binding.toResults.setOnClickListener{
            //addNewAttempt()---------------------------------------------------------------------------
            //Needs to be implemented
            onToResults()
        }

    }



    private fun addNewAttempt(){
        if(binding.notDiffButton.isChecked){
            viewModel.addNewAttempt("not difficult")
        } else if (binding.somDiffButton.isChecked) {
            viewModel.addNewAttempt("somewhat difficult")
        } else if (binding.diffButton.isChecked) {
            viewModel.addNewAttempt("difficult")
        } else {
            viewModel.addNewAttempt("very difficult")
        }
    }

    private fun onToResults()
    {
        findNavController().navigate(R.id.action_rating_to_results)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }




}