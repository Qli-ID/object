package com.mr.view

import com.mr.service.ScoreRecorder.scores
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.awt.GridLayout
import java.awt.event.ActionListener
import javax.swing.*

/**
 * �ɼ��Ի���
 *
 * @author mingrisoft
 */
class ScoreDialog(frame: JFrame) : JDialog(frame, true) {
    /**
     * ���췽��
     *
     * @param frame
     * ������
     */
    init {
        val scores = scores // ��ȡ��ǰǰ�����ɼ�
        val scoreP = JPanel(GridLayout(4, 1)) // �ɼ���壬4��1��
        scoreP.background = Color.WHITE // ��ɫ����
        val title = JLabel("�÷����а�", JLabel.CENTER) // �����ǩ������
        title.font = Font("����", Font.BOLD, 20) // ��������
        title.foreground = Color.RED // ��ɫ����
        val first = JLabel("��һ����" + scores[2], JLabel.CENTER) // ��һ����ǩ
        val second = JLabel("�ڶ�����" + scores[1], JLabel.CENTER) // �ڶ�����ǩ
        val third = JLabel("��������" + scores[0], JLabel.CENTER) // ��������ǩ
        val restart = JButton("���¿�ʼ") // ���¿�ʼ��ť
        restart.addActionListener(ActionListener
        // ��ť����¼�����
        {
            // �����ʱ
            dispose() // ���ٶԻ���
        })
        scoreP.add(title) // �ɼ������ӱ�ǩ
        scoreP.add(first)
        scoreP.add(second)
        scoreP.add(third)
        val c = contentPane // ��ȡ������
        c.layout = BorderLayout() // ʹ�ñ߽粼��
        c.add(scoreP, BorderLayout.CENTER) // �ɼ������м�
        c.add(restart, BorderLayout.SOUTH) // ��ť�ŵײ�
        setTitle("��Ϸ����") // �Ի������
        val width: Int
        val height: Int // �Ի�����
        height = 200
        width = height // �Ի����߾�Ϊ200
        // ����������о���λ�õĺ�����
        val x = frame.x + (frame.width - width) / 2
        // ����������о���λ�õ�������
        val y = frame.y + (frame.height - height) / 2
        setBounds(x, y, width, height) // ��������Ϳ��
        isVisible = true // ��ʾ�Ի���
    }
}