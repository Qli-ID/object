package com.mr.service

import java.io.*
import java.util.*

/**
 * ������¼��
 *
 * @author mingrisoft
 */
object ScoreRecorder {
    private const val SCOREFILE = "data/soure" // �÷ּ�¼�ļ�

    /**
     * ��ȡ����
     *
     * @return
     */
    @JvmStatic
    var scores = IntArray(3) // ��ǰ�÷����ǰ����
        private set

    /**
     * ������ʼ��
     */
    @JvmStatic
    fun init() {
        val f = File(SCOREFILE) // ������¼�ļ�
        if (!f.exists()) { // ����ļ�������
            try {
                f.createNewFile() // �������ļ�
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return  // ֹͣ����
        }
        var fis: FileInputStream? = null
        var isr: InputStreamReader? = null
        var br: BufferedReader? = null
        try {
            fis = FileInputStream(f) // �ļ��ֽ�������
            isr = InputStreamReader(fis) // �ֽ���ת�ַ���
            br = BufferedReader(isr) // �����ַ���
            val value = br.readLine() // ��ȡһ��
            if (!(value == null || "" == value)) { // �����Ϊ��ֵ
                val vs = value.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray() // �ָ��ַ���
                if (vs.size < 3) { // ����ָ���С��3
                    Arrays.fill(scores, 0) // �������0
                } else {
                    for (i in 0..2) {
                        // ����¼�ļ��е�ֵ������ǰ��������
                        scores[i] = vs[i].toInt()
                    }
                }
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally { // ���ιر���
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
     * �������
     */
    @JvmStatic
    fun saveScore() {
        // ƴ�ӵ÷�����
        val value = scores[0].toString() + "," + scores[1] + "," + scores[2]
        var fos: FileOutputStream? = null
        var osw: OutputStreamWriter? = null
        var bw: BufferedWriter? = null
        try {
            fos = FileOutputStream(SCOREFILE) // �ļ��ֽ������
            osw = OutputStreamWriter(fos) // �ֽ���ת�ַ���
            bw = BufferedWriter(osw) // �����ַ���
            bw.write(value) // д��ƴ�Ӻ���ַ���
            bw.flush() // �ַ���ˢ��
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally { // ���ιر���
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
     * ��ӷ������������ӵķ��������а�����ߣ���Ὣ�·����������а�
     *
     * @param score
     * �·���
     */
    @JvmStatic
    fun addNewScore(score: Int) {
        // �ڵ÷����������ϴ���һ������Ϊ4����ʱ����
        val tmp = Arrays.copyOf(scores, 4)
        tmp[3] = score // ���·�����ֵ�����ĸ�Ԫ��
        Arrays.sort(tmp) // ��ʱ���齵������
        scores = Arrays.copyOfRange(tmp, 1, 4) // ��������Ԫ�ظ�ֵ���÷�����
    }
}