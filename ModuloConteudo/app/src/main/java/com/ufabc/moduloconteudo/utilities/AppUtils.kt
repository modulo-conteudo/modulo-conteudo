package com.ufabc.moduloconteudo.utilities

fun biweekly(d: Int, m: Int, y: Int): Int {
    // first days of quarters (counting from 01/01/2020)
    val q1_first_day_offset = 41
    val q2_first_day_offset = 153
    val q3_first_day_offset = 265

    val m_days = arrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    var day_offset = d
    for (i in 0..(m-2)) day_offset += m_days[i]


    // first days of quarters
    if (day_offset >= q1_first_day_offset && day_offset < q2_first_day_offset) {
        day_offset -= q1_first_day_offset

    } else if (day_offset >= q2_first_day_offset && day_offset < q3_first_day_offset) {
        day_offset -= q2_first_day_offset

    } else if (day_offset >= q3_first_day_offset) {
        day_offset -= q3_first_day_offset
    }

    val week_offset = day_offset / 7

    if (week_offset % 2 == 0) {
        return 1
    } else {
        return 2
    }
}
