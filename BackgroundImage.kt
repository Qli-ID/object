package com.mr.view

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

/**
 * ��������
 *
 * @author mingrisoft
 */
class BackgroundImage {
    @JvmField
    var image // ����ͼƬ
            : BufferedImage
    private var image1: BufferedImage? = null
    private var image2: BufferedImage? = null // ����������ͼƬ
    private val g // ����ͼƬ�Ļ�ͼ����
            : Graphics2D
    var x1: Int
    var x2 // ��������ͼƬ������
            : Int

    init {
        try {
            image1 = ImageIO.read(File("image/����.png"))
            image2 = ImageIO.read(File("image/����.png"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        // ��ͼƬ���ÿ�800��300�Ĳ�ɫͼƬ
        image = BufferedImage(800, 300, BufferedImage.TYPE_INT_RGB)
        g = image.createGraphics() // ��ȡ��ͼƬ��ͼ����
        x1 = 0 // ��һ��ͼƬ��ʼ����Ϊ0
        x2 = 800 // �ڶ���ͼƬ��ʼ������Ϊ800
        g.drawImage(image1, x1, 0, null)
    }

    /**
     * ����
     */
    fun roll() {
        x1 -= SPEED // ��һ��ͼƬ����
        x2 -= SPEED // �ڶ���ͼƬ����
        if (x1 <= -800) { // �����һ��ͼƬ�Ƴ���Ļ
            x1 = 800 // �ص���Ļ�Ҳ�
        }
        if (x2 <= -800) { // ����ڶ���ͼƬ�Ƴ���Ļ
            x2 = 800 // �ص���Ļ�Ҳ�
        }
        g.drawImage(image1, x1, 0, null) // ����ͼƬ�л�������ͼƬ
        g.drawImage(image2, x2, 0, null)
    }

    companion object {
        const val SPEED = 4 // �����ٶ�
    }
}