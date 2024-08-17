package com.soumyaranjan.aieducator.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import com.soumyaranjan.aieducator.MainViewModel
import com.soumyaranjan.aieducator.R
import com.soumyaranjan.aieducator.adapter.HomeRecyclerAdapter
import com.soumyaranjan.aieducator.databinding.FragmentCourseBinding
import com.soumyaranjan.aieducator.model.Video

class CourseFragment(private val mainViewModel: MainViewModel) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentCourseBinding.inflate(inflater, container, false)

        val context = requireContext()
        binding.coursesRecycler.adapter = HomeRecyclerAdapter(mainViewModel.courcesList, context)

        binding.searchCourses.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query != null){
                    val filteredList = mainViewModel.courcesList.filter { course ->
                        course.title.contains(query, ignoreCase = true) or course.topics.any { topic ->
                            topic.title.contains(query, ignoreCase = true)
                        }
                    }
                    binding.coursesRecycler.adapter = HomeRecyclerAdapter(filteredList, context)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null) {
                    val filteredList = mainViewModel.courcesList.filter { course ->
                        course.title.contains(newText, ignoreCase = true) or course.topics.any { topic ->
                            topic.title.contains(newText, ignoreCase = true)
                        }
                    }
                    binding.coursesRecycler.adapter = HomeRecyclerAdapter(filteredList,context)
                }
                return true
            }
        })






        return binding.root
    }
}