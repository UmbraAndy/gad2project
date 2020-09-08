package com.example.gads2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_submission.*


class SubmissionFragment : Fragment(R.layout.fragment_submission) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitScreenBackButtton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}