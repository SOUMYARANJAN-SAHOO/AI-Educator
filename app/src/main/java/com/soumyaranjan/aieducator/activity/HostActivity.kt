package com.soumyaranjan.aieducator.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.soumyaranjan.aieducator.MainViewModel
import com.soumyaranjan.aieducator.R
import com.soumyaranjan.aieducator.Repository
import com.soumyaranjan.aieducator.chatbot.view.ChatBotActivity
import com.soumyaranjan.aieducator.databinding.ActivityHostBinding
import com.soumyaranjan.aieducator.fragment.CommunityFragment
import com.soumyaranjan.aieducator.fragment.CourseFragment
import com.soumyaranjan.aieducator.fragment.HomeFragment
import com.soumyaranjan.aieducator.fragment.ProfileFragment

class HostActivity : AppCompatActivity() {

    lateinit var binding: ActivityHostBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView<ActivityHostBinding>(this,R.layout.activity_host)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



        val viewModel = ViewModelProvider(this, object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(Repository(this@HostActivity)) as T
            }
        }).get(MainViewModel::class.java)

        changeFragment(HomeFragment(viewModel))

        binding.fab.setOnClickListener {
            startActivity(Intent(this, ChatBotActivity::class.java))
        }

        binding.bottomNavigation.setOnItemSelectedListener {

            when(it.itemId){
                R.id.home -> changeFragment(HomeFragment(viewModel))
                R.id.course -> changeFragment(CourseFragment(viewModel))
                R.id.community -> changeFragment(CommunityFragment())
                R.id.profile -> changeFragment(ProfileFragment())
            }

            return@setOnItemSelectedListener true
        }
        
    }

    fun changeFragment(frag:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, frag)
            .commit()
    }
}