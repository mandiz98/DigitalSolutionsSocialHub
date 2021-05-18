package com.example.digital_solutions_social_hub.ui.authentication

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amazonaws.mobile.client.*
import com.bumptech.glide.Glide
import com.example.digital_solutions_social_hub.MainActivity
import com.example.digital_solutions_social_hub.databinding.ActivityAuthenticationBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {

    private val TAG = AuthenticationActivity::class.java.simpleName
    private lateinit var binding: ActivityAuthenticationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Glide.with(this)
            .load("https://web.dev-back-office-tool.dss.husqvarnagroup.net/static/media/login.fa3a63b8.jpg")
            .into(binding.imgBg)

        AWSMobileClient.getInstance()
            .initialize(applicationContext, object : Callback<UserStateDetails?> {
                override fun onResult(userStateDetails: UserStateDetails?) {
                    Log.i(TAG, userStateDetails?.userState.toString())
                    when (userStateDetails?.userState) {
                        UserState.SIGNED_IN -> {
                            val i = Intent(this@AuthenticationActivity, MainActivity::class.java)
                            startActivity(i)
                        }
                        UserState.SIGNED_OUT -> showSignIn()
                        else -> {
                            AWSMobileClient.getInstance().signOut()
                            showSignIn()
                        }
                    }
                }

                override fun onError(e: Exception) {
                    Log.e(TAG, e.toString())
                }

            })
    }

    private fun showSignIn() {
        try {
            AWSMobileClient.getInstance().showSignIn(
                this,
                SignInUIOptions.builder().nextActivity(MainActivity::class.java).build()
            )
        } catch (e: Exception) {
            Log.e(TAG, e.toString())
        }
    }


//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding.btnLogIn.setOnClickListener {
//            try {
//                val result = Amplify.Auth.signInWithWebUI()
//                Log.e("AuthenticationActivity", "onCreate: $result")
//            } catch (e: AuthException) {
//                Log.e("AuthenticationActivity", "exception: $e")
//            }
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
//            Amplify.Auth.handleWebUISignInResponse(data)
//        }
//    }
}