package com.mk.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen implements Screen {
    final MkGame game;
    Texture dropImage;
    Texture bucketImage;
    Sound dropSound;
    OrthographicCamera camera;
    private Viewport viewport;
    Rectangle bucket;
    Rectangle floor;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;
    int lives = 3;

    public GameScreen(final MkGame game) {
        this.game = game;

        dropImage = new Texture(Gdx.files.internal("drop.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));

        camera = new OrthographicCamera();
        camera.position.set(new Vector3(game.width / 2f, game.height / 2f, 0));
        viewport = new ScreenViewport(camera);

        // Начало записи кода

        // Конец записи кода
    }

    // Эту функцию необходимо будет написать
    private void spawnRaindrop() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        game.batch.setProjectionMatrix(camera.combined);
        camera.update();

        game.batch.begin();

        // Начало записи кода

        // Конец записи кода
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        dropImage.dispose();
        bucketImage.dispose();
        dropSound.dispose();
    }

}