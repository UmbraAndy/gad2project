package com.example.gads2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_submission.*


class SubmissionFragment : Fragment(R.layout.fragment_submission) {

val submissionViewModel:SubmissionViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        submitScreenBackButtton.setOnClickListener {
            findNavController().popBackStack()
        }

        projectSubmitButton.setOnClickListener {
            submissionViewModel.submitForm("aaaa@gmail.com","aaa","bbb","www.contra.com").observe(viewLifecycleOwner,{ response ->
                if (response.isSuccessful){
                    Log.d("XXXXXX","FORM_SUBMIT: OK")
                }
                else{
                    Log.d("XXXXXX","FORM_SUBMIT: FAILED")
                }
            })
        }
    }
}