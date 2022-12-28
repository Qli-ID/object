package com.mr.modle

import com.mr.service.FreshThread
import com.mr.service.Sound
import java.awt.Rectangle
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO

/**
 * 恐龙类
 *
 * @author mingrisoft
 */
class Dinosaur {
    @JvmField
    var image: BufferedImage? = null // 主图片
    private var image1: BufferedImage? = null
    private var image2: BufferedImage? = null
    private var image3: BufferedImage? = null // 跑步图片
    @JvmField
    var x = 50
    @JvmField
    var y // 坐标
            : Int
    private var jumpValue = 0 // 跳跃的增变量
    private var jumpState = false // 跳跃状态
    private var stepTimer = 0 // 踏步计时器
    private val JUMP_HIGHT = 100 // 跳起最大高度
    private val LOWEST_Y = 120 // 落地最低坐标
    private val FREASH = FreshThread.FREASH // 刷新时间

    init {
        y = LOWEST_Y
        try {
            image1 = ImageIO.read(File("image/恐龙1.png"))
            image2 = ImageIO.read(File("image/恐龙2.png"))
            image3 = ImageIO.read(File("image/恐龙3.png"))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 移动
     */
    fun move() {
        step() // 不断踏步
        if (jumpState) { // 如果正在跳跃
            if (y >= LOWEST_Y) { // 如果纵坐标大于等于最低点
                jumpValue = -4 // 增变量为负值
            }
            if (y <= LOWEST_Y - JUMP_HIGHT) { // 如果跳过最高点
                jumpValue = 4 // 增变量为正值
            }
            y += jumpValue // 纵坐标发生变化
            if (y >= LOWEST_Y) { // 如果再次落地
                jumpState = false // 停止跳跃
            }
        }
    }

    /**
     * 跳跃
     */
    fun jump() {
        if (!jumpState) { // 如果没处于跳跃状态
            Sound.jump() // 播放跳跃音效
        }
        jumpState = true // 处于跳跃状态
    }

    /**
     * 踏步
     */
    private fun step() {
        // 每过250毫秒，更换一张图片。因为共有3图片，所以除以3取余，轮流展示这三张
        val tmp = stepTimer / 250 % 3
        image = when (tmp) {
            1 -> image1
            2 -> image2
            else -> image3
        }
        stepTimer += FREASH // 计时器递增
    }

    val footBounds: Rectangle
        /**
         * 足部边界区域
         *
         * @return
         */
        get() = Rectangle(x + 30, y + 59, 29, 18)
    val headBounds: Rectangle
        /**
         * 头部边界区域
         *
         * @return
         */
        get() = Rectangle(x + 66, y + 25, 32, 22)
}