package com.mk.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class MkGame extends Game {

	public int width, height;
	public SpriteBatch batch;
	public BitmapFont font;

	public void create() {
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();
		batch = new SpriteBatch();
		font = makeFont();
		this.setScreen(new MenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

	public BitmapFont makeFont(){
		FreeTypeFontGenerator fontGen = new FreeTypeFontGenerator(Gdx.files.internal("snfbstrd.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 64;
		parameter.color = Color.WHITE;
		parameter.characters += "АаБбВвГгДдЕеЁёЖжЗзИиЙйКкЛлМмНнОоПпРрСсТтУуФфХхЦцЧчШшЩщЪъЫыЬьЭэЮюЯя";
		font = fontGen.generateFont(parameter);
		return font;
	}
}

