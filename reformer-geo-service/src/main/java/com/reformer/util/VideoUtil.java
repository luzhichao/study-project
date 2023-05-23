package com.reformer.util;

import com.coremedia.iso.IsoFile;
import com.googlecode.mp4parser.DataSource;
import com.googlecode.mp4parser.FileDataSourceImpl;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.io.IOException;

/**
 * 描述:
 * 作者: LiuCheng
 * 时间: 2023/5/23 10:26
 */
public class VideoUtil {

    /**
     * 得到语音或视频文件时长,单位秒
     * @return
     * @throws IOException
     */
    public static long getDuration(File file) throws IOException {
        String format = getVideoFormat(file);
        long result = 0;
        if ("wav".equals(format)) {
            result = VideoUtil.getWavDuration(file).intValue();
        } else if ("mp3".equals(format)) {
            result = VideoUtil.getMp3Duration(file).intValue();
        } else if ("m4a".equals(format)) {
            result = VideoUtil.getMp4Duration(file);
        } else if ("mov".equals(format)) {
            result = VideoUtil.getMp4Duration(file);
        } else if ("mp4".equals(format)) {
            result = VideoUtil.getMp4Duration(file);
        }

        return result;
    }


    /**
     * 获取视频文件的播放长度(mp4、mov格式)
     * @return 单位为毫秒
     */
    private static long getMp4Duration(File file) throws IOException {
        DataSource dataSource = new FileDataSourceImpl(file);
        IsoFile isoFile = new IsoFile(dataSource);
        long lengthInSeconds =
                isoFile.getMovieBox().getMovieHeaderBox().getDuration() /
                        isoFile.getMovieBox().getMovieHeaderBox().getTimescale();
        return lengthInSeconds;
    }


    /**
     * 得到文件格式
     * @return
     */
    private static String getVideoFormat(File file) {
        return file.getPath().toLowerCase().substring(file.getPath().toLowerCase().lastIndexOf(".") + 1);
    }

    /**
     * 获取语音文件播放时长(秒) 支持wav 格式
     * @return
     */
    private static Float getWavDuration(File file) {
        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = audioInputStream.getFormat();
            long audioFileLength = file.length();
            int frameSize = format.getFrameSize();
            float frameRate = format.getFrameRate();
            float durationInSeconds = (audioFileLength / (frameSize * frameRate));
            return durationInSeconds;

        } catch (Exception e) {
            e.printStackTrace();
            return 0f;
        }

    }

    /**
     * 获取mp3语音文件播放时长(秒) mp3
     * @return
     */
    private static Float getMp3Duration(File file) {

        try {
            MP3File f = (MP3File) AudioFileIO.read(file);
            MP3AudioHeader audioHeader = (MP3AudioHeader) f.getAudioHeader();
            return Float.parseFloat(audioHeader.getTrackLength() + "");
        } catch (Exception e) {
            e.printStackTrace();
            return 0f;
        }
    }


}
