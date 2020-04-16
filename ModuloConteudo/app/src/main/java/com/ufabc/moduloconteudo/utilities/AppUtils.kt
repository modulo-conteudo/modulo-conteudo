package com.ufabc.moduloconteudo.utilities

import java.util.*

class AppUtils {
    companion object {
        fun biweekly(d: Int, m: Int, y: Int): Int {
            val currDate = Calendar.getInstance()
            currDate.set(y, m, d)

            val q1FirstDay = Calendar.getInstance()
            q1FirstDay.set(2020, 1, 9)

            val week = ((currDate.timeInMillis - q1FirstDay.timeInMillis)/(7*24*60*60*1000))+1
            return if (week%2L == 0L) 2 else 1
        }
    }
}
