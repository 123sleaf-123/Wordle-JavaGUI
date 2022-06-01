/**
 * Title:        GameController.java
 * Copyright:    Copyright(c) 2022
 *
 * @author Zhiye Huang
 * @version 1.0
 * @description The game controller to accept refresh request, strat game, quit or urge WordleFrame to refresh.
 **/
public class GameController {
    private WordleFrame frame;

    public GameController(WordleFrame frame) {
        this.frame = frame;
    }

    public WordleFrame getFrame() {
        return frame;
    }

    public void setFrame(WordleFrame frame) {
        this.frame = frame;
    }
}
