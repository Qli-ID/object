package com.mr.view

import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

/**
 * 滚动背景
 *
 * @author mingrisoft
 */
class BackgroundImage {
    @JvmField
    var image // 背景图片
            : BufferedImage
    private var image1: BufferedImage? = null
    private var image2: BufferedImage? = null // 滚动的两个图片
    private val g // 背景图片的绘图对象
            : Graphics2D
    var x1: Int
    var x2 // 两个滚动图片的坐标
            : Int

    init {
        try {
            image1 = ImageIO.read(File("image/背景.png"))
            image2 = ImageIO.read(File("image/背景.png"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        // 主图片采用宽800高300的彩色图片
        image = BufferedImage(800, 300, BufferedImage.TYPE_INT_RGB)
        g = image.createGraphics() // 获取主图片绘图对象
        x1 = 0 // 第一幅图片初始坐标为0
        x2 = 800 // 第二幅图片初始横坐标为800
        g.drawImage(image1, x1, 0, null)
    }

    /**
     * 滚动
     */
    fun roll() {
        x1 -= SPEED // 第一幅图片左移
        x2 -= SPEED // 第二幅图片左移
        if (x1 <= -800) { // 如果第一幅图片移出屏幕
            x1 = 800 // 回到屏幕右侧
        }
        if (x2 <= -800) { // 如果第二幅图片移出屏幕
            x2 = 800 // 回到屏幕右侧
        }
        g.drawImage(image1, x1, 0, null) // 在主图片中绘制两幅图片
        g.drawImage(image2, x2, 0, null)
    }

    companion object {
        const val SPEED = 4 // 滚动速度
    }
}