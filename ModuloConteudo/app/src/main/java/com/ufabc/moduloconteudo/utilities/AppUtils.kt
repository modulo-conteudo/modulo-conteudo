package com.ufabc.moduloconteudo.utilities

import android.util.Log
import java.util.*

class AppUtils {
    companion object {
        fun biweekly(d: Int, m: Int, y: Int): Int {
            val currDate = Calendar.getInstance()
            currDate.set(y, m, d)
            val q1FirstDay = Calendar.getInstance()
            q1FirstDay.set(2020, 2, 10)

            val week = (currDate.timeInMillis - q1FirstDay.timeInMillis)/(7*24*60*60*1000)
            Log.d("debugPrint", "week = {$week}")
            return if (week%2L == 0L) 1 else 2
        }
    }
}
