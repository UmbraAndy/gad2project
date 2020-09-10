package com.example.gads2

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_submit_message.view.*

class SubmitMessageDialog : DialogFragment() {
    var success = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = MaterialAlertDialogBuilder(it)
            val inflater = requireActivity().layoutInflater
            val customView = inflater.inflate(R.layout.dialog_submit_message, null)
            if (success) {
                customView.successImageView.setImageResource(R.drawable.ic_baseline_check_circle_24)
                customView.messageTxt.text = "Submission successful"
            } else {
                customView.successImageView.setImageResource(R.drawable.ic_baseline_warning_24)
                customView.messageTxt.text = "Submission not successful"
            }
            builder.setView(customView)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}