package com.learn.movie.support.dialog

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.learn.movie.R

class DialogSuccess(context: Context): BottomSheetDialog(context){

    init {
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        setContentView(R.layout.dialog_success)
    }

    fun showDialog() {
        super.show()
    }

}