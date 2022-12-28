package com.mr.modle

import com.mr.view.BackgroundImage
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import java.util.*
import javax.imageio.ImageIO

/**
 * 障碍类
 *
 * @author mingrisoft
 */
class Obstacle {
    @JvmField
    var x: Int
    @JvmField
    var y // 横纵坐标
            : Int
    @JvmField
    var image: BufferedImage? = null
    private var stone: BufferedImage? = null // 石头图片
    private var cacti: BufferedImage? = null // 仙人掌图片
    private val speed // 移动速度
            : Int

    init {
        try {
            stone = ImageIO.read(File("image/石头.png"))
            cacti = ImageIO.read(File("image/仙人掌.png"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val r = Random() // 创建随机对象
        image = if (r.nextInt(2) == 0) { // 从0和1中取一值，若为0
            cacti // 采用仙人掌图片
        } else {
            stone // 采用石头图片
        }
        x = 800 // 初始恒做包
        y = 200 - image!!.height // 纵坐标
        speed = BackgroundImage.SPEED // 移动速度与背景同步
    }

    /**
     * 移动
     */
    fun move() {
        x -= speed // 横坐标递减
    }

    val bounds: Rectangle
        /**
         * 获取边界
         *
         * @return
         */
        get() = if (image === cacti) { // 如果使用仙人掌图片
            // 返回仙人掌的边界
            Rectangle(x + 7, y, 15, image!!.height)
        } else Rectangle(x + 5, y + 4, 23, 21)

    // 返回石头的边界
    val isLive: Boolean
        /**
         * 是否存活
         *
         * @return
         */
        get() =// 如果移出了游戏界面
            if (x <= -image!!.width) {
                false // 消亡
            } else true
    // 存活
}