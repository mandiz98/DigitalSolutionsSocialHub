package com.example.digital_solutions_social_hub

import android.app.Application
import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.core.Amplify
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DSSHApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Log.i("DSSHApplication", "Initialized Amplify")
        } catch (error: AmplifyException){
            Log.e("DSSHApplication", "Could not initialize amplify",error)
        }
    }
}