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

        // Конец записи кода
    }

    // Эту функцию необходимо будет написать
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

        // Начало записи кода

        for (Rectangle raindrop : raindrops) {
            game.batch.draw(dropImage, raindrop.x, raindrop.y);
        }

        game.batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);

        game.font.draw(game.batch, "Score: " + dropsGathered, 0, game.height);
        game.font.draw(game.batch, "Lives: " + lives, 0, game.height-40);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            bucket.x = touchPos.x - 64f/2f;
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            bucket.x -= 200 * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            bucket.x += 200 * Gdx.graphics.getDeltaTime();

        if (bucket.x < 0)
            bucket.x = 0;
        if (bucket.x > game.width - 64)
            bucket.x = game.width - 64;

        if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
            spawnRaindrop();

        Iterator<Rectangle> iter = raindrops.iterator();
        while (iter.hasNext()) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (raindrop.y + 64 < 0)
                iter.remove();
            if (raindrop.overlaps(bucket)) {
                dropsGathered++;
                dropSound.play();
                iter.remove();
            }
            if (raindrop.overlaps(floor)){
                lives--;
                iter.remove();
                if (lives <= 0)
                    game.setScreen(new MenuScreen(game));
            }
        }
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