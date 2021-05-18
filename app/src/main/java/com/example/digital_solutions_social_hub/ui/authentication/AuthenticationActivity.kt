package com.example.digital_solutions_social_hub.ui.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import com.bumptech.glide.Glide
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

        Amplify.Auth.fetchAuthSession(
            { Log.i(TAG, "Auth session = $it") },
            { Log.e(TAG, "failed to fetch auth session: $it") }
        )

        binding.btnLogIn.setOnClickListener {
            Amplify.Auth.signInWithWebUI(
                this,
                { Log.i("AuthenticationActivity", "sign in ok= $it ") },
                { Log.e("AuthenticationActivity", "sign in failed", it) }
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AWSCognitoAuthPlugin.WEB_UI_SIGN_IN_ACTIVITY_CODE) {
            Amplify.Auth.handleWebUISignInResponse(data)
        }
    }
}