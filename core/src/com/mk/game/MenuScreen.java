package com.mk.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MenuScreen implements Screen {

    MkGame game;

    Camera camera;
    Viewport viewport;
    TextButton startButton;
    TextButton exitButton;

    public MenuScreen(final MkGame game) {
        this.game = game;
        startButton = new TextButton(game.width/4, game.height/2, "Начать!", game);
        exitButton = new TextButton(game.width/4, game.height/2-200, "Выход", game);
        camera = new OrthographicCamera();
        camera.position.set(new Vector3(game.width / 2f, game.height / 2f, 0));
        viewport = new ScreenViewport(camera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        startButton.draw(game.batch);
        exitButton.draw(game.batch);

        game.batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (startButton.isHit((int) touchPos.x, (int) touchPos.y)) {
                game.setScreen(new GameScreen(game));
                dispose();
            }

            if (exitButton.isHit((int) touchPos.x, (int) touchPos.y)) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        startButton.dispose();
        exitButton.dispose();
    }

}