package com.mk.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
    private final Viewport viewport;
    Rectangle bucket;
    Rectangle floor;
    Array<Rectangle> raindrops;
    long lastDropTime;
    int dropsGathered;
    int lives = 3;
    BitmapFont font;

    public GameScreen(final MkGame game) {
        this.game = game;

        dropImage = new Texture(Gdx.files.internal("drop.png"));
        bucketImage = new Texture(Gdx.files.internal("bucket.png"));

        dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.mp3"));

        camera = new OrthographicCamera();
        camera.position.set(new Vector3(game.width / 2f, game.height / 2f, 0));
        viewport = new ScreenViewport(camera);

        font = game.makeFont();

        bucket = new Rectangle();
        bucket.x = game.width / 2f - 64f / 2f;
        bucket.y = 20;
        bucket.width = 64;
        bucket.height = 64;

        floor = new Rectangle();
        floor.x = 0;
        floor.y = 0;
        floor.width = game.width;
        floor.height = 1;

        raindrops = new Array<Rectangle>();
        spawnRaindrop();
    }

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, game.width-64);
        raindrop.y = game.height+64;
        raindrop.width = 64;
        raindrop.height = 64;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        game.batch.setProjectionMatrix(camera.combined);
        camera.update();

        game.batch.begin();

        for (Rectangle raindrop : raindrops) {
            game.batch.draw(dropImage, raindrop.x, raindrop.y);
        }

        game.batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);

        font.draw(game.batch, "Собрано: " + dropsGathered, 0, game.height);
        font.draw(game.batch, "Жизни: " + lives, 0, game.height-50);
        game.batch.end();

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