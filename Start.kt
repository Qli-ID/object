package com.mr.main

import com.mr.view.MainFrame

/**
 * ?????
 *
 * @author mingrisoft
 */
object Start {
    @JvmStatic
    fun main(args: Array<String>) {
        val frame = MainFrame() // ??????????
        frame.isVisible = true // ?????????
    }
}