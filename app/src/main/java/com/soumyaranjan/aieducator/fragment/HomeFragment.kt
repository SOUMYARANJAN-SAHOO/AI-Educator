package com.soumyaranjan.aieducator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soumyaranjan.aieducator.MainViewModel
import com.soumyaranjan.aieducator.R
import com.soumyaranjan.aieducator.Repository
import com.soumyaranjan.aieducator.adapter.HomeRecyclerAdapter
import com.soumyaranjan.aieducator.databinding.FragmentHomeBinding


class HomeFragment(private val viewModel: MainViewModel): Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)


        val context = requireContext()


        binding.homeRecycler.adapter = HomeRecyclerAdapter(viewModel.courcesList, context)

        return binding.root
    }
}
