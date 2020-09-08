package com.example.gads2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_splash_screen.*

class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            splashScreen.setOnClickListener {
                findNavController().navigate(R.id.action_splashScreenFragment_to_leaderBoardMainFragment)
            }
    }
}