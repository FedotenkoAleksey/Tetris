package rotation;

/**
 * A class which holds a score integer and gameOver boolean and is able to
 * increment score and setGameOver().
 *
 * @author ALEXXX
 */
class Score {

    int score;
    boolean gameOver;

    public Score() {
        score = 0;
        gameOver = false;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver() {
        gameOver = true;
    }

}
