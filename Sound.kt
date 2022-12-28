package com.mr.service

import java.io.FileNotFoundException

/**
 * 音效类
 *
 * @author mingrisoft
 */
object Sound {
    const val DIR = "music/" // 音乐文件夹
    const val BACKGROUD = "background.wav" // 背景音乐
    const val JUMP = "jump.wav" // 跳跃音效
    const val HIT = "hit.wav" // 撞击音效

    /**
     * 播放跳跃音效
     */
    fun jump() {
        play(DIR + JUMP, false) // 播放一次跳跃音效
    }

    /**
     * 播放撞击音效
     */
    @JvmStatic
    fun hit() {
        play(DIR + HIT, false) // 播放一次撞击音效
    }

    /**
     * 播放背景音乐
     */
    @JvmStatic
    fun backgroud() {
        play(DIR + BACKGROUD, true) // 循环播放背景音乐
    }

    /**
     * 播放
     *
     * @param file
     * 音乐文件完整名称
     * @param circulate
     * 是否循环播放
     */
    private fun play(file: String, circulate: Boolean) {
        try {
            // 创建播放器
            val player = MusicPlayer(file, circulate)
            player.play() // 播放器开始播放
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }
}