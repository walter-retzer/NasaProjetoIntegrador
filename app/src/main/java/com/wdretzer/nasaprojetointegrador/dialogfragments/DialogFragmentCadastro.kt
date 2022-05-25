package com.wdretzer.nasaprojetointegrador.dialogfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.wdretzer.nasaprojetointegrador.R

class DialogFragmentCadastro : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog_cadastro, container, false)
    }

    companion object {
        const val TAG = "DialogSignOut"
    }
}