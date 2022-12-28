package com.mr.modle

import com.mr.view.BackgroundImage
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.*
import javax.imageio.ImageIO

/**
 * �ϰ���
 *
 * @author mingrisoft
 */
class Obstacle {
    @JvmField
    var x: Int
    @JvmField
    var y // ��������
            : Int
    @JvmField
    var image: BufferedImage? = null
    private var stone: BufferedImage? = null // ʯͷͼƬ
    private var cacti: BufferedImage? = null // ������ͼƬ
    private val speed // �ƶ��ٶ�
            : Int

    init {
        try {
            stone = ImageIO.read(File("image/ʯͷ.png"))
            cacti = ImageIO.read(File("image/������.png"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val r = Random() // �����������
        image = if (r.nextInt(2) == 0) { // ��0��1��ȡһֵ����Ϊ0
            cacti // ����������ͼƬ
        } else {
            stone // ����ʯͷͼƬ
        }
        x = 800 // ��ʼ������
        y = 200 - image!!.height // ������
        speed = BackgroundImage.SPEED // �ƶ��ٶ��뱳��ͬ��
    }

    /**
     * �ƶ�
     */
    fun move() {
        x -= speed // ������ݼ�
    }

    val bounds: Rectangle
        /**
         * ��ȡ�߽�
         *
         * @return
         */
        get() = if (image === cacti) { // ���ʹ��������ͼƬ
            // ���������Ƶı߽�
            Rectangle(x + 7, y, 15, image!!.height)
        } else Rectangle(x + 5, y + 4, 23, 21)

    // ����ʯͷ�ı߽�
    val isLive: Boolean
        /**
         * �Ƿ���
         *
         * @return
         */
        get() =// ����Ƴ�����Ϸ����
            if (x <= -image!!.width) {
                false // ����
            } else true
    // ���
}