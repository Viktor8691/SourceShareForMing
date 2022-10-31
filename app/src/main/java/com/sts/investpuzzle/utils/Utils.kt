package com.sts.investpuzzle.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import com.sts.investpuzzle.R
import java.util.Calendar

class Utils {
    companion object {

        fun customDialog(context: Context, viewBinding: ViewBinding) : Dialog{
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(viewBinding.root)
            dialog.getWindow()?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

            return dialog
        }

        fun getMaxYearOfBirthday() : Int{
            return Calendar.getInstance().get(Calendar.YEAR)
        }

        fun intToStringTime(sec : Int) : String {
            val min = sec / 60
            val seconds = sec - min * 60
            return "$min:$seconds"
        }
    }
}