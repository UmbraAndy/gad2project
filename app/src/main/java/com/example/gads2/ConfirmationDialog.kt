package com.example.gads2

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.dialog_confirm.view.*

class ConfirmationDialog(): DialogFragment() {

    var action: (() ->Unit)? = null
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = MaterialAlertDialogBuilder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            val customView =  inflater.inflate(R.layout.dialog_confirm, null)
            val cancelButton = customView.cancelButton //findViewById<ImageButton>(R.id.cancelButton)
            cancelButton.setOnClickListener {
                dismiss()
            }
            val yesButton = customView.yesButton
            yesButton.setOnClickListener {
                Log.d("XXXXX","YES CLICKED")
                action?.invoke()
                dismiss()
            }
            builder.setView(customView)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}