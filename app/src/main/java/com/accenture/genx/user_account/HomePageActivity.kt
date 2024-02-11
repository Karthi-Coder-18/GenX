package com.accenture.genx.user_account

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.accenture.genx.R
import com.accenture.genx.databinding.ActivityHomePageBinding

class HomePageActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityHomePageBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_home_page)
        setupIds()
    }

    private fun setupIds() {
        binding.apply {
            ivHomePage.setOnClickListener{

            }
            ivUserAccountDetails.setOnClickListener {


            }
        }
    }

    override fun onClick(v: View?) {

    }







}