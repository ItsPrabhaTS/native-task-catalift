package com.example.catalift.ui

import android.animation.ObjectAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.catalift.R
import com.example.catalift.databinding.FragmentInterestBinding
import com.google.android.material.chip.Chip
import com.google.android.material.progressindicator.LinearProgressIndicator

class InterestsFragment : Fragment() {

    private var _binding: FragmentInterestBinding? = null
    private val binding get() = _binding!!

    private val interests = listOf(
        "Reading", "Writing", "Traveling", "Photography", "Painting", "Drawing", "Cooking", "Baking",
        "Gardening", "Dancing", "Music", "Singing", "Playing Instruments", "Hiking", "Cycling", "Running",
        "Swimming", "Yoga", "Meditation", "Fitness", "Gaming", "Coding", "Blogging", "Vlogging",
        "Watching Movies", "Theater", "Volunteering", "Crafting", "DIY Projects", "Collecting",
        "Learning Languages", "Chess", "Board Games", "Podcasts", "Fashion", "Makeup", "Technology",
        "Robotics", "Martial Arts", "Sports", "Camping", "Fishing", "Skateboarding", "Travel Blogging",
        "Public Speaking", "Investing", "Photography Editing", "Pet Care", "Astro Photography", "Animation",
        "Science", "Art", "Health", "Travel", "Education", "Books", "Finance"
    )

    private val selectedInterests = mutableSetOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInterestBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Animate progress bar from 100 to 50
        binding.progressBar.progress = 100
        animateProgressBar(binding.progressBar, 50)

        // Load all chips
        updateChips(interests)

        // Search filter for chips
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val filteredList = interests.filter {
                    it.contains(s.toString(), ignoreCase = true)
                }
                updateChips(filteredList)
            }
        })

        // Continue button action
        binding.btnContinue.setOnClickListener {
            val selected = getSelectedInterests()

            if (selected.isNotEmpty()) {
                Toast.makeText(requireContext(), "Selected: $selected", Toast.LENGTH_SHORT).show()
                view.findNavController().navigate(R.id.action_interestsFragment_to_professionFragment)
            } else {
                Toast.makeText(requireContext(), "Please select at least one interest", Toast.LENGTH_SHORT).show()
            }
        }

        // Back button action (uncomment if needed)
        // binding.btnBack.setOnClickListener {
        //     findNavController().popBackStack()
        // }
    }

    private fun updateChips(filteredInterests: List<String>) {
        binding.chipGroup.removeAllViews()

        val chipBgColor = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.catalift_primary),
                ContextCompat.getColor(requireContext(), R.color.white)
            )
        )

        val chipTextColor = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.white),
                ContextCompat.getColor(requireContext(), R.color.catalift_primary)
            )
        )

        val strokeColor = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                ContextCompat.getColor(requireContext(), R.color.white),
                ContextCompat.getColor(requireContext(), R.color.catalift_primary)
            )
        )

        for (interest in filteredInterests) {
            val chip = Chip(ContextThemeWrapper(requireContext(), R.style.Widget_Custom_Chip), null, 0).apply {
                text = interest
                isCheckable = true
                isClickable = true
                isCheckedIconVisible = false
                chipBackgroundColor = chipBgColor
                setTextColor(chipTextColor)
                chipStrokeColor = strokeColor
                chipStrokeWidth = 2f
                isChecked = selectedInterests.contains(interest)
                jumpDrawablesToCurrentState()
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        selectedInterests.add(interest)
                    } else {
                        selectedInterests.remove(interest)
                    }
                }
            }
            binding.chipGroup.addView(chip)
        }
    }


    private fun getSelectedInterests(): List<String> {
        return selectedInterests.toList()
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
