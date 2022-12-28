package com.mr.service

import com.mr.view.GamePanel
import com.mr.view.MainFrame
import com.mr.view.ScoreDialog

/**
 * 刷新帧线程
 *
 * @author mingrisoft
 */
class FreshThread( // 游戏面板
    var p: GamePanel
) : Thread() {
    override fun run() {
        while (!p.isFinish) { // 如果游戏未结束
            p.repaint() // 重绘游戏面板
            try {
                sleep(FREASH.toLong()) // 按照刷新时间休眠
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        var c = p.parent // 获取面板父容器
        while (c !is MainFrame) { // 如果父容器不是主窗体类
            c = c.parent // 继续获取父容器的父容器
        }
        val frame = c // 将容器强制转换为主窗体类
        ScoreDialog(frame) // 弹出得分记录对话框
        frame.restart() // 主窗体重载开始游戏
    }

    companion object {
        const val FREASH = 20 // 刷新时间
    }
}