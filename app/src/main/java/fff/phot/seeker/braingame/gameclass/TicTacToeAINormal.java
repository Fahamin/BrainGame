package fff.phot.seeker.braingame.gameclass;

import java.util.Random;

/**
 * Created by jubor on 8/25/2017.
 */

public class TicTacToeAINormal extends TicTacToeAI{

    public TicTacToeAINormal() {
    }


    protected void generateMove(){

        Random rngX = new Random();
        Random rngY = new Random();

        do{
            moveX = rngX.nextInt(3);
            moveY = rngY.nextInt(3);
        }while((board[moveX][moveY]!=0));

    }

}