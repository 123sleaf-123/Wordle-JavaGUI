import Containers.WordleFrame;
import MusicPlayer.StartPanelPlayer;

/**
 * Title:        Wordle.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The main program of Wordle.
 **/
public class Wordle {
    public static void main(String[] args) {
        Runnable r1 = () -> {
            WordleFrame frame = new WordleFrame();
        };

        Runnable r2 = () -> {
            StartPanelPlayer player = new StartPanelPlayer();
            int au_return;
            while(true)
            {
                au_return = player.play();  //播放1秒
                if(au_return==0)  //如果播放结束
                {
                    System.out.println("audio ended");
                    player.playFrom(0);
                    break;
                }
                else if(au_return==-1)
                {
                    System.out.println("Error");
                    break;
                }
            }
            player.stop_playing();
        };

        r1.run();
        r2.run();
    }
}
