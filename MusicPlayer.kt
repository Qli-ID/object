package com.mr.service

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import javax.sound.sampled.*

/**
 * ���ֲ�����
 *
 * @author mingrisoft
 */
class MusicPlayer @JvmOverloads constructor(
    filepath: String, // �Ƿ�ѭ������
    var circulate: Boolean = false
) : Runnable {
    var soundFile // �����ļ�
            : File
    var thread: Thread? = null // ���߳�
    /**
     * ���췽��
     *
     * @param filepath
     * �����ļ���������
     * @param circulate
     * �Ƿ�ѭ������
     * @throws FileNotFoundException
     */
    /**
     * ���췽����Ĭ�ϲ�ѭ������
     *
     * @param filepath
     * �����ļ���������
     * @throws FileNotFoundException
     */
    init {
        soundFile = File(filepath)
        if (!soundFile.exists()) { // ����ļ�������
            throw FileNotFoundException(filepath + "δ�ҵ�")
        }
    }

    /**
     * ����
     */
    fun play() {
        thread = Thread(this) // �����̶߳���
        thread!!.start() // �����߳�
    }

    /**
     * ֹͣ����
     */
    fun stop() {
        thread!!.stop() // ǿ�ƹر��߳�
    }

    /**
     * ��д�߳�ִ�з���
     */
    override fun run() {
        val auBuffer = ByteArray(1024 * 128) // ����128k������
        do {
            var audioInputStream: AudioInputStream? = null // ������Ƶ����������
            var auline: SourceDataLine? = null // ��Ƶ��Դ������
            try {
                // �������ļ��л�ȡ��Ƶ������
                audioInputStream = AudioSystem.getAudioInputStream(soundFile)
                val format = audioInputStream.format // ��ȡ��Ƶ��ʽ
                // ����Դ���������ͺ�ָ����Ƶ��ʽ���������ж���
                val info = DataLine.Info(
                    SourceDataLine::class.java,
                    format
                )
                // ������Ƶϵͳ������ָ�� Line.Info �����е�����ƥ����У���ת��ΪԴ�����ж���
                auline = AudioSystem.getLine(info) as SourceDataLine
                auline.open(format) // ����ָ����ʽ��Դ������
                auline!!.start() // Դ�����п�����д�
                var byteCount = 0 // ��¼��Ƶ�������������ֽ���
                while (byteCount != -1) { // �����Ƶ�������ж�ȡ���ֽ�����Ϊ-1
                    // ����Ƶ�������ж���128K������
                    byteCount = audioInputStream.read(
                        auBuffer, 0,
                        auBuffer.size
                    )
                    if (byteCount >= 0) { // ���������Ч����
                        auline.write(auBuffer, 0, byteCount) // ����Ч����д����������
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: UnsupportedAudioFileException) {
                e.printStackTrace()
            } catch (e: LineUnavailableException) {
                e.printStackTrace()
            } finally {
                auline!!.drain() // ���������
                auline.close() // �ر�������
            }
        } while (circulate) // ����ѭ����־�ж��Ƿ�ѭ������
    }
}