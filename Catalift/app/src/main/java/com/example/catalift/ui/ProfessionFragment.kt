package com.example.catalift.ui

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.catalift.R
import com.example.catalift.databinding.FragmentProfessionBinding
import com.google.android.material.progressindicator.LinearProgressIndicator

class ProfessionFragment : Fragment() {

    private var _binding: FragmentProfessionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfessionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Animate progress bar from 50 to 100
        binding.progressBar.progress = 50
        animateProgressBar(binding.progressBar, 100)

        // Define options for dropdowns
        val professions = listOf(
            "Artificial Intelligence", "Cybersecurity Specialist",
            "Data Scientist", "Software Developer", "Cloud Computing"
        )

        val companies = listOf("Apple", "Google", "Microsoft", "Amazon")

        // Set up profession spinner
        binding.professionSpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            professions
        )

        // Set up company spinner
        binding.companySpinner.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            companies
        )

        // Handle continue button click
        binding.btnContinue.setOnClickListener {
            val profession = binding.professionSpinner.selectedItem.toString()
            val company = binding.companySpinner.selectedItem.toString()

            Toast.makeText(
                requireContext(),
                "You chose to be a $profession at $company",
                Toast.LENGTH_SHORT
            ).show()

            // You can add navigation here if needed
            // view.findNavController().navigate(R.id.action_professionFragment_to_nextFragment)
        }

        // Handle back button click
        binding.btnBack.setOnClickListener {
            view.findNavController().navigate(R.id.action_professionFragment_to_interestsFragment)
        }
    }

    private fun animateProgressBar(progressBar: LinearProgressIndicator, toProgress: Int) {
        val animation = ObjectAnimator.ofInt(progressBar, "progress", progressBar.progress, toProgress)
        animation.duration = 600
        animation.interpolator = AccelerateDecelerateInterpolator()
        animation.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
