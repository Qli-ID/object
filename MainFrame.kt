package com.mr.view

import com.mr.service.ScoreRecorder.init
import com.mr.service.ScoreRecorder.saveScore
import com.mr.service.Sound.backgroud
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame

/**
 * ������
 * @author mingrisoft
 */
class MainFrame : JFrame() {
    init {
        restart() // ��ʼ
        setBounds(340, 150, 821, 260) // ���ú�������Ϳ��
        title = "���ܰɣ�С������" // ����
        backgroud() // ���ű�������
        init() // ��ȡ�÷ּ�¼
        addListener() // ��Ӽ���
        defaultCloseOperation = EXIT_ON_CLOSE // �رմ�����ֹͣ����
    }

    /**
     * ���¿�ʼ
     */
    fun restart() {
        val c = contentPane // ��ȡ����������
        c.removeAll() // ɾ���������������
        val panel = GamePanel() // �����µ���Ϸ���
        c.add(panel)
        addKeyListener(panel) // ��Ӽ����¼�
        c.validate() // ����������֤�������
    }

    /**
     * ��Ӽ���
     */
    private fun addListener() {
        addWindowListener(object : WindowAdapter() {
            // ��Ӵ������
            override fun windowClosing(e: WindowEvent) { // ����ر�ǰ
                saveScore() // ����ȷ�
            }
        })
    }
}