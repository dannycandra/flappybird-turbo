package com.hotpotsoft.hyperbird.gameworld;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.hotpotsoft.hyperbird.gameobjects.Bird;
import com.hotpotsoft.hyperbird.gameobjects.ScrollHandler;

public class GameWorld {
    private Bird bird;
    private ScrollHandler scroller;
    private Rectangle ground;
    private int score = 0;

    private int midPointY;

    private GameState currentState;

    private com.hotpotsoft.hyperbird.AdsController adsController;

    public enum GameState {
        READY, RUNNING, GAMEOVER, HIGHSCORE
    }

    public GameWorld(int midPointY, com.hotpotsoft.hyperbird.AdsController adsController) {
        this.adsController = adsController;

        currentState = GameState.READY;
        this.midPointY = midPointY;
        bird = new Bird(33, midPointY - 5, 17, 12);
        // The grass should start 66 pixels below the midPointY
        scroller = new ScrollHandler(this, midPointY + 66);
        ground = new Rectangle(0, midPointY + 66, 137, 11);

        adsController.showBannerAd();
    }

    public void update(float delta) {

        switch (currentState) {
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                adsController.hideBannerAd();
            default:
                updateRunning(delta);
                break;
        }

    }

    private void updateReady(float delta) {
    }

    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        bird.update(delta);
        scroller.update(delta);

        if (scroller.collides(bird) && bird.isAlive()) {
            scroller.stop();
            bird.die();
            com.hotpotsoft.hyperbird.helpers.AssetLoader.dead.play();
        }

        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scroller.stop();
            bird.die();
            bird.decelerate();
            currentState = GameState.GAMEOVER;
            adsController.showBannerAd();
        }
    }

    public Bird getBird() {
        return bird;

    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int increment) {
        score += increment;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        bird.onRestart(midPointY - 5);
        scroller.onRestart();
        currentState = GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }
}
