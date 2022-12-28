package com.mr.view

import com.mr.service.ScoreRecorder.scores
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Font
import java.awt.GridLayout
import java.awt.event.ActionListener
import javax.swing.*

/**
 * 成绩对话框
 *
 * @author mingrisoft
 */
class ScoreDialog(frame: JFrame) : JDialog(frame, true) {
    /**
     * 构造方法
     *
     * @param frame
     * 父窗体
     */
    init {
        val scores = scores // 获取当前前三名成绩
        val scoreP = JPanel(GridLayout(4, 1)) // 成绩面板，4行1列
        scoreP.background = Color.WHITE // 白色背景
        val title = JLabel("得分排行榜", JLabel.CENTER) // 标题标签，居中
        title.font = Font("黑体", Font.BOLD, 20) // 设置字体
        title.foreground = Color.RED // 红色体字
        val first = JLabel("第一名：" + scores[2], JLabel.CENTER) // 第一名标签
        val second = JLabel("第二名：" + scores[1], JLabel.CENTER) // 第二名标签
        val third = JLabel("第三名：" + scores[0], JLabel.CENTER) // 第三名标签
        val restart = JButton("重新开始") // 重新开始按钮
        restart.addActionListener(ActionListener
        // 按钮添加事件监听
        {
            // 当点击时
            dispose() // 销毁对话框
        })
        scoreP.add(title) // 成绩面板添加标签
        scoreP.add(first)
        scoreP.add(second)
        scoreP.add(third)
        val c = contentPane // 获取主容器
        c.layout = BorderLayout() // 使用边界布局
        c.add(scoreP, BorderLayout.CENTER) // 成绩面板放中间
        c.add(restart, BorderLayout.SOUTH) // 按钮放底部
        setTitle("游戏结束") // 对话框标题
        val width: Int
        val height: Int // 对话框宽高
        height = 200
        width = height // 对话框宽高均为200
        // 获得主窗体中居中位置的横坐标
        val x = frame.x + (frame.width - width) / 2
        // 获得主窗体中居中位置的纵坐标
        val y = frame.y + (frame.height - height) / 2
        setBounds(x, y, width, height) // 设置坐标和宽高
        isVisible = true // 显示对话框
    }
}