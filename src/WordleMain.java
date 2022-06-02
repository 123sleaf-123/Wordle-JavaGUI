import Supporter.InputProcessor;
import Supporter.WordleLogic;
import Supporter.WordleMemory;

/**
 * Title:        WordleMain.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The main program of the Wordle game.
 **/
public class WordleMain {
//    private GameController gc;
//    private WordleFrame frame;
    private InputProcessor filter;
    private WordleLogic cpu;
    private WordleMemory memory;


    public WordleMain(int size) {
        memory = new WordleMemory(size);
//        filter = new filter();
//        cpu = new Supporter.WordleLogic();
    }

//    public WordleMain(GameController gc, WordleFrame frame) {
//        this.gc = gc;
//        this.frame = frame;
//    }

//    private void init() {
//        gc.getFrame().refresh();
//    }
}
