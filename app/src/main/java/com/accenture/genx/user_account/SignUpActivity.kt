package com.accenture.genx.user_account

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.accenture.genx.R
import com.accenture.genx.databinding.ActivitySignUpBinding
import com.accenture.genx.user_account.database.User
import com.accenture.genx.user_account.database.viewmodel.DataStoreViewModel
import com.accenture.genx.user_account.database.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var binding: ActivitySignUpBinding
    private val dataStoreViewModel: DataStoreViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_sign_up)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //onClick(binding.btnSignUp)
        // setupIds()

        checkIfUserHasSavedDetails()
        makeButtonNotClickableAtFirst()


    }


    /* private fun setupIds() {
         binding.apply {
             etEmailAddress.setOnClickListener(this@SignUpActivity)
             etPassword.setOnClickListener(this@SignUpActivity)
             btnSignUp.setOnClickListener {
                 val intent = Intent(this@SignUpActivity, HomePageActivity::class.java)
                 startActivity(intent)
             }
         }

     }*/

    override fun onClick(v: View?) {
    }

    private fun checkIfUserHasSavedDetails() {
        dataStoreViewModel.savedKey.observe(this) {
            if (it == true) {
                //saved go to the next activity
                val intent = Intent(this, UserAccountDetails::class.java)
                startActivity(intent)
            }
            //user hasn't saved details, show the form
            else {
                initViews()
            }
        }
    }

    private fun initViews() {
         handleClick()
    }

    /**
     * Make button unclickable to avoid empty entries into room
     */
    private fun makeButtonNotClickableAtFirst() {
        //Make button UnClickable for the first time
        binding.btnSignUp.isEnabled = false
        binding.btnSignUp.background = ContextCompat.getDrawable(
            this,
            R.drawable.btn_opaque
        )

        //make the button clickable after detecting changes in input field
        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                val email = binding.etEmailAddress.text.toString()
                val password = binding.etPassword.text.toString()

                if (email.isEmpty() || password.isEmpty()) {
                    binding.btnSignUp.isEnabled = false
                    binding.btnSignUp.background = ContextCompat.getDrawable(
                        this@SignUpActivity,
                        R.drawable.btn_opaque
                    )
                } else {
                    binding.btnSignUp.isEnabled = true
                    binding.btnSignUp.background = ContextCompat.getDrawable(
                        this@SignUpActivity,
                        R.drawable.btn_round
                    )
                }
            }

            override fun afterTextChanged(s: Editable) {

            }

        }

        binding.etEmailAddress.addTextChangedListener(watcher)
        binding.etPassword.addTextChangedListener(watcher)
    }


    private fun handleClick() {

        //on click of button save
        binding.btnSignUp.setOnClickListener {

            //get details entered
            val email = binding.etEmailAddress.text.toString()
            val password = binding.etPassword.text.toString()


            val user = User(email = email, password = password)

            //save the details to room database
            userViewModel.insertUserDetails(user)

            userViewModel.response.observe(this) {

                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()

                //success, save key so on next visit user goes to details screen
                dataStoreViewModel.setSavedKey(true)

                //show toast message
                Toast.makeText(
                    this,
                    applicationContext.getString(R.string.record_saved),
                    Toast.LENGTH_LONG
                ).show()

                //go to next activity
                val intent = Intent(this, UserAccountDetails::class.java)
                startActivity(intent)

            }

        }

    }

}

