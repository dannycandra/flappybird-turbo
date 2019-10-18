package com.hotpotsoft.hyperbird.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class GameScreen implements Screen {
    private com.hotpotsoft.hyperbird.gameworld.GameWorld world;
    private com.hotpotsoft.hyperbird.gameworld.GameRenderer renderer;
    private float runTime;

    private com.hotpotsoft.hyperbird.AdsController adsController;

    // This is the constructor, not the class declaration
    public GameScreen(com.hotpotsoft.hyperbird.AdsController adsController) {
        this.adsController = adsController;

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 136;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        int midPointY = (int) (gameHeight / 2);

        world = new com.hotpotsoft.hyperbird.gameworld.GameWorld(midPointY, adsController);
        renderer = new com.hotpotsoft.hyperbird.gameworld.GameRenderer(world, (int) gameHeight, midPointY );

        Gdx.input.setInputProcessor(new com.hotpotsoft.hyperbird.helpers.InputHandler(world));
    }

    @Override
    public void render(float delta) {
        runTime += delta;
        world.update(delta);
        renderer.render(runTime);
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("GameScreen - resizing");
    }

    @Override
    public void show() {
        System.out.println("GameScreen - show called");
    }

    @Override
    public void hide() {
        System.out.println("GameScreen - hide called");
    }

    @Override
    public void pause() {
        System.out.println("GameScreen - pause called");
    }

    @Override
    public void resume() {
        System.out.println("GameScreen - resume called");
    }

    @Override
    public void dispose() {
        // Leave blank
    }

}
