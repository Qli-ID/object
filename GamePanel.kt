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
 * 游戏面板
 *
 * @author mingrisoft
 */
class GamePanel : JPanel(), KeyListener {
    private val image // 主图片
            : BufferedImage
    private val background // 背景图片
            : BackgroundImage
    private val golden // 恐龙
            : Dinosaur
    private val g2 // 主图片绘图对象
            : Graphics2D
    private var addObstacleTimer = 0 // 添加障碍计时器

    /**
     * 游戏是否结束
     *
     * @return
     */
    var isFinish = false // 游戏结束标志
        private set
    private val list: MutableList<Obstacle> = ArrayList() // 障碍集合
    private val FREASH = FreshThread.FREASH // 刷新时间
    var score = 0 // 得分
    var scoreTimer = 0 // 分数计时器

    init {
        // 主图片采用宽800高300的彩色图片
        image = BufferedImage(800, 300, BufferedImage.TYPE_INT_BGR)
        g2 = image.createGraphics() // 获取主图片绘图对象
        background = BackgroundImage() // 初始化滚动背景
        golden = Dinosaur() // 初始化小恐龙
        list.add(Obstacle()) // 添加第一个障碍
        val t = FreshThread(this) // 刷新帧线程
        t.start() // 启动线程
    }

    /**
     * 绘制主图片
     */
    private fun paintImage() {
        background.roll() // 背景图片开始滚动
        golden.move() // 恐龙开始移动
        g2.drawImage(background.image, 0, 0, this) // 绘制滚动背景
        if (addObstacleTimer == 1300) { // 每过1300毫秒
            if (Math.random() * 100 > 40) { // 60%概率出现障碍
                list.add(Obstacle())
            }
            addObstacleTimer = 0 // 重新计时
        }
        var i = 0
        while (i < list.size) {
            // 遍历障碍集合
            val o = list[i] // 获取障碍对象
            if (o.isLive) { // 如果是有效障碍
                o.move() // 障碍移动
                g2.drawImage(o.image, o.x, o.y, this) // 绘制障碍
                // 如果恐龙头脚碰到障碍
                if (o.bounds.intersects(golden.footBounds)
                    || o.bounds.intersects(golden.headBounds)
                ) {
                    hit() // 播放撞击声音
                    gameOver() // 游戏结束
                }
            } else { // 如果不是有效障碍
                list.removeAt(i) // 删除此障碍
                i-- // 循环变量前移
            }
            i++
        }
        g2.drawImage(golden.image, golden.x, golden.y, this) // 绘制恐龙
        if (scoreTimer >= 500) { // 每过500毫秒
            score += 10 // 加十分
            scoreTimer = 0 // 重新计时
        }
        g2.color = Color.BLACK // 使用黑色
        g2.font = Font("黑体", Font.BOLD, 24) // 设置字体
        g2.drawString(String.format("%06d", score), 700, 30) // 绘制分数
        addObstacleTimer += FREASH // 障碍计时器递增
        scoreTimer += FREASH // 分数计时器递增
    }

    /**
     * 重写绘制组件方法
     */
    override fun paint(g: Graphics) {
        paintImage() // 绘制主图片内容
        g.drawImage(image, 0, 0, this)
    }

    /**
     * 使游戏结束
     */
    fun gameOver() {
        addNewScore(score) // 记录当前分数
        isFinish = true
    }

    /**
     * 实现按下键盘按键方法
     */
    override fun keyPressed(e: KeyEvent) {
        val code = e.keyCode // 获取按下的按键值
        if (code == KeyEvent.VK_SPACE) { // 如果是空格
            golden.jump() // 恐龙跳跃
        }
    }

    override fun keyReleased(e: KeyEvent) {}
    override fun keyTyped(e: KeyEvent) {}
}