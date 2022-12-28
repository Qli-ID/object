package com.mr.service

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import javax.sound.sampled.*

/**
 * 音乐播放器
 *
 * @author mingrisoft
 */
class MusicPlayer @JvmOverloads constructor(
    filepath: String, // 是否循环播放
    var circulate: Boolean = false
) : Runnable {
    var soundFile // 音乐文件
            : File
    var thread: Thread? = null // 父线程
    /**
     * 构造方法
     *
     * @param filepath
     * 音乐文件完整名称
     * @param circulate
     * 是否循环播放
     * @throws FileNotFoundException
     */
    /**
     * 构造方法，默认不循环播放
     *
     * @param filepath
     * 音乐文件完整名称
     * @throws FileNotFoundException
     */
    init {
        soundFile = File(filepath)
        if (!soundFile.exists()) { // 如果文件不存在
            throw FileNotFoundException(filepath + "未找到")
        }
    }

    /**
     * 播放
     */
    fun play() {
        thread = Thread(this) // 创建线程对象
        thread!!.start() // 开启线程
    }

    /**
     * 停止播放
     */
    fun stop() {
        thread!!.stop() // 强制关闭线程
    }

    /**
     * 重写线程执行方法
     */
    override fun run() {
        val auBuffer = ByteArray(1024 * 128) // 创建128k缓冲区
        do {
            var audioInputStream: AudioInputStream? = null // 创建音频输入流对象
            var auline: SourceDataLine? = null // 混频器源数据行
            try {
                // 从音乐文件中获取音频输入流
                audioInputStream = AudioSystem.getAudioInputStream(soundFile)
                val format = audioInputStream.format // 获取音频格式
                // 按照源数据行类型和指定音频格式创建数据行对象
                val info = DataLine.Info(
                    SourceDataLine::class.java,
                    format
                )
                // 利用音频系统类获得与指定 Line.Info 对象中的描述匹配的行，并转换为源数据行对象
                auline = AudioSystem.getLine(info) as SourceDataLine
                auline.open(format) // 按照指定格式打开源数据行
                auline!!.start() // 源数据行开启读写活动
                var byteCount = 0 // 记录音频输入流读出的字节数
                while (byteCount != -1) { // 如果音频输入流中读取的字节数不为-1
                    // 从音频数据流中读出128K的数据
                    byteCount = audioInputStream.read(
                        auBuffer, 0,
                        auBuffer.size
                    )
                    if (byteCount >= 0) { // 如果读出有效数据
                        auline.write(auBuffer, 0, byteCount) // 将有效数据写入数据行中
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: UnsupportedAudioFileException) {
                e.printStackTrace()
            } catch (e: LineUnavailableException) {
                e.printStackTrace()
            } finally {
                auline!!.drain() // 清空数据行
                auline.close() // 关闭数据行
            }
        } while (circulate) // 根据循环标志判断是否循环播放
    }
}