package com.mr.modle

import com.mr.service.FreshThread
import com.mr.service.Sound
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

/**
 * ������
 *
 * @author mingrisoft
 */
class Dinosaur {
    @JvmField
    var image: BufferedImage? = null // ��ͼƬ
    private var image1: BufferedImage? = null
    private var image2: BufferedImage? = null
    private var image3: BufferedImage? = null // �ܲ�ͼƬ
    @JvmField
    var x = 50
    @JvmField
    var y // ����
            : Int
    private var jumpValue = 0 // ��Ծ��������
    private var jumpState = false // ��Ծ״̬
    private var stepTimer = 0 // ̤����ʱ��
    private val JUMP_HIGHT = 100 // �������߶�
    private val LOWEST_Y = 120 // ����������
    private val FREASH = FreshThread.FREASH // ˢ��ʱ��

    init {
        y = LOWEST_Y
        try {
            image1 = ImageIO.read(File("image/����1.png"))
            image2 = ImageIO.read(File("image/����2.png"))
            image3 = ImageIO.read(File("image/����3.png"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * �ƶ�
     */
    fun move() {
        step() // ����̤��
        if (jumpState) { // ���������Ծ
            if (y >= LOWEST_Y) { // �����������ڵ�����͵�
                jumpValue = -4 // ������Ϊ��ֵ
            }
            if (y <= LOWEST_Y - JUMP_HIGHT) { // ���������ߵ�
                jumpValue = 4 // ������Ϊ��ֵ
            }
            y += jumpValue // �����귢���仯
            if (y >= LOWEST_Y) { // ����ٴ����
                jumpState = false // ֹͣ��Ծ
            }
        }
    }

    /**
     * ��Ծ
     */
    fun jump() {
        if (!jumpState) { // ���û������Ծ״̬
            Sound.jump() // ������Ծ��Ч
        }
        jumpState = true // ������Ծ״̬
    }

    /**
     * ̤��
     */
    private fun step() {
        // ÿ��250���룬����һ��ͼƬ����Ϊ����3ͼƬ�����Գ���3ȡ�࣬����չʾ������
        val tmp = stepTimer / 250 % 3
        image = when (tmp) {
            1 -> image1
            2 -> image2
            else -> image3
        }
        stepTimer += FREASH // ��ʱ������
    }

    val footBounds: Rectangle
        /**
         * �㲿�߽�����
         *
         * @return
         */
        get() = Rectangle(x + 30, y + 59, 29, 18)
    val headBounds: Rectangle
        /**
         * ͷ���߽�����
         *
         * @return
         */
        get() = Rectangle(x + 66, y + 25, 32, 22)
}