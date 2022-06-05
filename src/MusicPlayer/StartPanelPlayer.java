package MusicPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Title:        StartPanelPlayer.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description Music player.
 **/
public class StartPanelPlayer {
    protected String path = "src/resources/music/rambling_pleat.wav";
    protected AudioFormat format = null;
    protected AudioInputStream aistream = null;
    protected float sampleRate = 0;
    protected float framelength = 0;
    protected DataLine.Info datalineinfo = null;
    protected SourceDataLine dataline = null;
    protected int played = 0;
    protected boolean _set = false;
    protected int length = 0;
    protected byte[] bytes = new byte[512];

    public StartPanelPlayer() {
        this.set(path);
    }

    public boolean set(String p)  //设置播放路径
    {
        if (_set)  //如果已经被初始化了（缓冲区已经有音频准备播放了）
        {
            boolean stop_playing_return = stop_playing();  //关闭当前缓冲区
            if (!stop_playing_return)  //如果失败
            {
                return false;
            }
        }
        path = p;  //更新音频路径
        try {
            //初始化音频
            aistream = AudioSystem.getAudioInputStream(new File(path));
            format = aistream.getFormat();
            sampleRate = format.getSampleRate();
            framelength = aistream.getFrameLength();
            datalineinfo = new DataLine.Info(SourceDataLine.class, format);
            dataline = (SourceDataLine) AudioSystem.getLine(datalineinfo);
        } catch (LineUnavailableException err) {
            System.out.println("LineUnavailableException");
            return false;
        } catch (UnsupportedAudioFileException err) {
            System.out.println("UnsupportedAudioFileException");
            return false;
        } catch (IOException err) {
            System.out.println("IOException");
            err.printStackTrace();
            return false;
        }
        try {
            //打开缓冲区
            bytes = new byte[512];
            length = 0;
            dataline.open(format);
            dataline.start();
            played = 0;
        } catch (Exception err) {
            System.out.println("Error");
            err.printStackTrace();
            stop_playing();  //关闭当前缓冲区
            return false;
        } catch (Error err) {
            System.out.println("Error: can not play the audio");
            err.printStackTrace();
            stop_playing();  //关闭当前缓冲区
            return false;
        }
        _set = true;  //初始化成功
        return true;
    }

    /*
     * playFrom返回值
     * -1：异常
     * 0：音频结束
     * 1：正常
     */
    public int playFrom(int t)  //从某处开始播放（需要重新设置缓冲区）
    {
        if (!_set)  //如果没有设置过缓冲区
        {
            return -1;
        }
        //重新设置缓冲区
        boolean stop_playing_return = stop_playing();  //关闭当前缓冲区
        if (!stop_playing_return) {
            return -1;
        }
        boolean set_return = set(path);  //初始化音频并打开缓冲区
        if (!set_return) {
            return -1;
        }
        boolean audio_ended = false;  //音频是否结束
        try {
            while (played < t)  //在播放到t之前
            {
                length = aistream.read(bytes);
                played++;
                if (length <= 0)  //如果音频播放结束了就退出循环
                {
                    audio_ended = true;
                    break;
                }
            }
        } catch (Exception err) {
            System.out.println("Error");
            err.printStackTrace();
            return -1;
        } catch (Error err) {
            System.out.println("Error: can not play the audio");
            err.printStackTrace();
            return -1;
        }
        if (audio_ended) {
            return 0;
        }
        return 1;
    }

    public boolean stop_playing()  //停止播放并关闭缓冲区
    {
        try {
            aistream.close();
            dataline.drain();
            dataline.close();

            aistream = null;
            format = null;
            datalineinfo = null;
            dataline = null;
            _set = false;  //还原回没有初始化前
        } catch (Exception err) {
            System.out.println("Error");
            err.printStackTrace();
            return false;
        } catch (Error err) {
            System.out.println("Error: can not play the audio");
            err.printStackTrace();
            return false;
        }
        return true;
    }

    /*
     * play返回值
     * -1：异常
     * 0：播放结束
     * 1：正常
     */
    public int play() {
        boolean audio_ended = false;  //音频是否结束
        try {
            length = aistream.read(bytes);
            if (length <= 0)  //如果音频播放结束了就退出循环
            {
                audio_ended = true;
            } else {
                dataline.write(bytes, 0, length);
                played++;
            }
        } catch (Exception err) {
            System.out.println("Error");
            err.printStackTrace();
            return -1;
        } catch (Error err) {
            System.out.println("Error: can not play the audio");
            err.printStackTrace();
            return -1;
        }
        if (audio_ended) {
            return 0;
        }
        return 1;
    }

    public String getPath()  //获取音频路径
    {
        if (!_set) {
            return "Error";
        }
        return path;
    }

    public int getPlayed()  //获取已经播放的长度
    {
        if (!_set) {
            return -1;
        }
        return played;
    }

    public float getSecLength()  //获取时长
    {
        if (!_set) {
            return -1;
        }
        return framelength / sampleRate;
    }


}
