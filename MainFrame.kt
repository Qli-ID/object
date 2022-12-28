package com.mr.view

import com.mr.service.ScoreRecorder.init
import com.mr.service.ScoreRecorder.saveScore
import com.mr.service.Sound.backgroud
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import javax.swing.JFrame

/**
 * 主窗体
 * @author mingrisoft
 */
class MainFrame : JFrame() {
    init {
        restart() // 开始
        setBounds(340, 150, 821, 260) // 设置横纵坐标和宽高
        title = "奔跑吧！小恐龙！" // 标题
        backgroud() // 播放背景音乐
        init() // 读取得分记录
        addListener() // 添加监听
        defaultCloseOperation = EXIT_ON_CLOSE // 关闭窗体则停止程序
    }

    /**
     * 重新开始
     */
    fun restart() {
        val c = contentPane // 获取主容器对象
        c.removeAll() // 删除容器中所有组件
        val panel = GamePanel() // 创建新的游戏面板
        c.add(panel)
        addKeyListener(panel) // 添加键盘事件
        c.validate() // 容器重新验证所有组件
    }

    /**
     * 添加监听
     */
    private fun addListener() {
        addWindowListener(object : WindowAdapter() {
            // 添加窗体监听
            override fun windowClosing(e: WindowEvent) { // 窗体关闭前
                saveScore() // 保存比分
            }
        })
    }
}