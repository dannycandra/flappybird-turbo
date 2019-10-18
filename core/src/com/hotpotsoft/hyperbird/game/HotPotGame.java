package com.hotpotsoft.hyperbird.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.hotpotsoft.hyperbird.helpers.AssetLoader;
import com.hotpotsoft.hyperbird.screens.GameScreen;

public class HotPotGame extends Game {
	private com.hotpotsoft.hyperbird.AdsController adsController;

	public HotPotGame(){
	}

	public HotPotGame(com.hotpotsoft.hyperbird.AdsController adsController){
		this.adsController = adsController;
	}

	@Override
	public void create() {
		Gdx.app.log("HotPotGame", "created");
		AssetLoader.load();
		setScreen(new GameScreen(adsController));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
