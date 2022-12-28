package com.mr.view

import com.mr.modle.Dinosaur
import com.mr.modle.Obstacle
import com.mr.service.FreshThread
import com.mr.service.ScoreRecorder.addNewScore
import com.mr.service.Sound.hit
import java.awt.Color
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.awt.image.BufferedImage
import javax.swing.JPanel

/**
 * ��Ϸ���
 *
 * @author mingrisoft
 */
class GamePanel : JPanel(), KeyListener {
    private val image // ��ͼƬ
            : BufferedImage
    private val background // ����ͼƬ
            : BackgroundImage
    private val golden // ����
            : Dinosaur
    private val g2 // ��ͼƬ��ͼ����
            : Graphics2D
    private var addObstacleTimer = 0 // ����ϰ���ʱ��

    /**
     * ��Ϸ�Ƿ����
     *
     * @return
     */
    var isFinish = false // ��Ϸ������־
        private set
    private val list: MutableList<Obstacle> = ArrayList() // �ϰ�����
    private val FREASH = FreshThread.FREASH // ˢ��ʱ��
    var score = 0 // �÷�
    var scoreTimer = 0 // ������ʱ��

    init {
        // ��ͼƬ���ÿ�800��300�Ĳ�ɫͼƬ
        image = BufferedImage(800, 300, BufferedImage.TYPE_INT_BGR)
        g2 = image.createGraphics() // ��ȡ��ͼƬ��ͼ����
        background = BackgroundImage() // ��ʼ����������
        golden = Dinosaur() // ��ʼ��С����
        list.add(Obstacle()) // ��ӵ�һ���ϰ�
        val t = FreshThread(this) // ˢ��֡�߳�
        t.start() // �����߳�
    }

    /**
     * ������ͼƬ
     */
    private fun paintImage() {
        background.roll() // ����ͼƬ��ʼ����
        golden.move() // ������ʼ�ƶ�
        g2.drawImage(background.image, 0, 0, this) // ���ƹ�������
        if (addObstacleTimer == 1300) { // ÿ��1300����
            if (Math.random() * 100 > 40) { // 60%���ʳ����ϰ�
                list.add(Obstacle())
            }
            addObstacleTimer = 0 // ���¼�ʱ
        }
        var i = 0
        while (i < list.size) {
            // �����ϰ�����
            val o = list[i] // ��ȡ�ϰ�����
            if (o.isLive) { // �������Ч�ϰ�
                o.move() // �ϰ��ƶ�
                g2.drawImage(o.image, o.x, o.y, this) // �����ϰ�
                // �������ͷ�������ϰ�
                if (o.bounds.intersects(golden.footBounds)
                    || o.bounds.intersects(golden.headBounds)
                ) {
                    hit() // ����ײ������
                    gameOver() // ��Ϸ����
                }
            } else { // ���������Ч�ϰ�
                list.removeAt(i) // ɾ�����ϰ�
                i-- // ѭ������ǰ��
            }
            i++
        }
        g2.drawImage(golden.image, golden.x, golden.y, this) // ���ƿ���
        if (scoreTimer >= 500) { // ÿ��500����
            score += 10 // ��ʮ��
            scoreTimer = 0 // ���¼�ʱ
        }
        g2.color = Color.BLACK // ʹ�ú�ɫ
        g2.font = Font("����", Font.BOLD, 24) // ��������
        g2.drawString(String.format("%06d", score), 700, 30) // ���Ʒ���
        addObstacleTimer += FREASH // �ϰ���ʱ������
        scoreTimer += FREASH // ������ʱ������
    }

    /**
     * ��д�����������
     */
    override fun paint(g: Graphics) {
        paintImage() // ������ͼƬ����
        g.drawImage(image, 0, 0, this)
    }

    /**
     * ʹ��Ϸ����
     */
    fun gameOver() {
        addNewScore(score) // ��¼��ǰ����
        isFinish = true
    }

    /**
     * ʵ�ְ��¼��̰�������
     */
    override fun keyPressed(e: KeyEvent) {
        val code = e.keyCode // ��ȡ���µİ���ֵ
        if (code == KeyEvent.VK_SPACE) { // ����ǿո�
            golden.jump() // ������Ծ
        }
    }

    override fun keyReleased(e: KeyEvent) {}
    override fun keyTyped(e: KeyEvent) {}
}