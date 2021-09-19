package com.laughing.tetris.ui;

import javax.sound.sampled.*;
import java.io.InputStream;

/**
 * @author : laughing
 * @create : 2021-09-19 1:14
 * @description : 背景音乐
 */
public class Music {
    private static final String PATH = "music/ANorth.wav";
    private static Clip clip = null;

    static {
        try {
            clip = AudioSystem.getClip();
            InputStream is = Music.class.getClassLoader().getResourceAsStream(PATH);
            AudioInputStream ais = AudioSystem.getAudioInputStream(is);
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放音乐
     */
    public static void playBGM() {
        // 设置音量
        FloatControl floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        floatControl.setValue(-10.0f);
        // 循环播放
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    /**
     * 停止播放
     */
    public static void stopBGM() {
        clip.stop();
    }
}
