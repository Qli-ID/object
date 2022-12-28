package com.mr.service

import java.io.*
import java.util.*

/**
 * 分数记录器
 *
 * @author mingrisoft
 */
object ScoreRecorder {
    private const val SCOREFILE = "data/soure" // 得分记录文件

    /**
     * 获取分数
     *
     * @return
     */
    @JvmStatic
    var scores = IntArray(3) // 当前得分最高前三名
        private set

    /**
     * 分数初始化
     */
    @JvmStatic
    fun init() {
        val f = File(SCOREFILE) // 创建记录文件
        if (!f.exists()) { // 如果文件不存在
            try {
                f.createNewFile() // 创建新文件
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return  // 停止方法
        }
        var fis: FileInputStream? = null
        var isr: InputStreamReader? = null
        var br: BufferedReader? = null
        try {
            fis = FileInputStream(f) // 文件字节输入流
            isr = InputStreamReader(fis) // 字节流转字符流
            br = BufferedReader(isr) // 缓冲字符流
            val value = br.readLine() // 读取一行
            if (!(value == null || "" == value)) { // 如果不为空值
                val vs = value.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() // 分割字符串
                if (vs.size < 3) { // 如果分割结果小于3
                    Arrays.fill(scores, 0) // 数组填充0
                } else {
                    for (i in 0..2) {
                        // 将记录文件中的值赋给当前分数数组
                        scores[i] = vs[i].toInt()
                    }
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally { // 依次关闭流
            try {
                br!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                isr!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                fis!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 保存分数
     */
    @JvmStatic
    fun saveScore() {
        // 拼接得分数组
        val value = scores[0].toString() + "," + scores[1] + "," + scores[2]
        var fos: FileOutputStream? = null
        var osw: OutputStreamWriter? = null
        var bw: BufferedWriter? = null
        try {
            fos = FileOutputStream(SCOREFILE) // 文件字节输出流
            osw = OutputStreamWriter(fos) // 字节流转字符流
            bw = BufferedWriter(osw) // 缓冲字符流
            bw.write(value) // 写入拼接后的字符串
            bw.flush() // 字符流刷新
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally { // 依次关闭流
            try {
                bw!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                osw!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            try {
                fos!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 添加分数。如果新添加的分数比排行榜分数高，则会将新分数记入排行榜。
     *
     * @param score
     * 新分数
     */
    @JvmStatic
    fun addNewScore(score: Int) {
        // 在得分组数基础上创建一个长度为4的临时数组
        val tmp = Arrays.copyOf(scores, 4)
        tmp[3] = score // 将新分数赋值给第四个元素
        Arrays.sort(tmp) // 临时数组降序排列
        scores = Arrays.copyOfRange(tmp, 1, 4) // 将后三个元素赋值给得分数组
    }
}