package com.mr.service

import java.io.FileNotFoundException

/**
 * ��Ч��
 *
 * @author mingrisoft
 */
object Sound {
    const val DIR = "music/" // �����ļ���
    const val BACKGROUD = "background.wav" // ��������
    const val JUMP = "jump.wav" // ��Ծ��Ч
    const val HIT = "hit.wav" // ײ����Ч

    /**
     * ������Ծ��Ч
     */
    fun jump() {
        play(DIR + JUMP, false) // ����һ����Ծ��Ч
    }

    /**
     * ����ײ����Ч
     */
    @JvmStatic
    fun hit() {
        play(DIR + HIT, false) // ����һ��ײ����Ч
    }

    /**
     * ���ű�������
     */
    @JvmStatic
    fun backgroud() {
        play(DIR + BACKGROUD, true) // ѭ�����ű�������
    }

    /**
     * ����
     *
     * @param file
     * �����ļ���������
     * @param circulate
     * �Ƿ�ѭ������
     */
    private fun play(file: String, circulate: Boolean) {
        try {
            // ����������
            val player = MusicPlayer(file, circulate)
            player.play() // ��������ʼ����
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }
}