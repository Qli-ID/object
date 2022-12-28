package com.mr.service

import com.mr.view.GamePanel
import com.mr.view.MainFrame
import com.mr.view.ScoreDialog

/**
 * ˢ��֡�߳�
 *
 * @author mingrisoft
 */
class FreshThread( // ��Ϸ���
    var p: GamePanel
) : Thread() {
    override fun run() {
        while (!p.isFinish) { // �����Ϸδ����
            p.repaint() // �ػ���Ϸ���
            try {
                sleep(FREASH.toLong()) // ����ˢ��ʱ������
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        var c = p.parent // ��ȡ��常����
        while (c !is MainFrame) { // ���������������������
            c = c.parent // ������ȡ�������ĸ�����
        }
        val frame = c // ������ǿ��ת��Ϊ��������
        ScoreDialog(frame) // �����÷ּ�¼�Ի���
        frame.restart() // ���������ؿ�ʼ��Ϸ
    }

    companion object {
        const val FREASH = 20 // ˢ��ʱ��
    }
}