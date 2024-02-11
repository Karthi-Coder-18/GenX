package com.accenture.genx.user_account

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.accenture.genx.R
import com.accenture.genx.databinding.ActivityUserAccountDetailsBinding
import com.accenture.genx.user_account.database.viewmodel.DataStoreViewModel
import com.accenture.genx.user_account.database.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class UserAccountDetails : AppCompatActivity() {

    private lateinit var binding: ActivityUserAccountDetailsBinding

    private val userViewModel: UserViewModel by viewModels()
    private val dataStoreViewModel: DataStoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserAccountDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getUserDetails()

        handleClicks()

    }

    private fun handleClicks(){

        binding.btnDeleteAccount.setOnClickListener {

            //clear record from room database
            userViewModel.doDeleteSingleUserRecord()

            //remove the datastorage key
            dataStoreViewModel.setSavedKey(false)

            //go to main activity
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)

        }

    }

    private fun getUserDetails(){

        this.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){

                userViewModel.doGetUserDetails()
                userViewModel.userDetails.collect { users->

                    for (user in users){
                        //set data into view
                        binding.tvEmail.text = user.email
                     /*   binding.txtAge.text = user.age
                        binding.txtNumber.text = user.number*/
                    }

                }
            }
        }

    }
}