package com.team23.game.screens.startscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.team23.game.GameEntry;
import com.team23.game.ui.frame.Frame;

/***
 * Start Screen
 */
public class StartScreen implements Screen {

    private GameEntry gameEntry;
    public StartPage startPage;
    public CreateNewGamePage createNewGamePage;
    public Frame frame;
    private Texture introTexture;


    float stateTime = 0f;
    SpriteBatch backgroundBatch;
    private TextureRegion[] backgroundFrames;
    private Animation walkAnimation;
    private TextureRegion currentFrame;

    public StartScreen(final GameEntry gameEntry){
        this.gameEntry = gameEntry;
        introTexture = new Texture("IntroV2.png");

        frame = new Frame();
        startPage = new StartPage();
        createNewGamePage = new CreateNewGamePage();
        frame.navigate(startPage);

        int background_frameCols = 2;
        int background_frameRows = 2;
        Texture hitbarTexture = new Texture(Gdx.files.internal("Background/space.png"));
        int perCellWidth = hitbarTexture.getWidth() / background_frameCols;
        int perCellHeight = hitbarTexture.getHeight() / background_frameRows;
        TextureRegion[][] background_cellRegions = TextureRegion.split(hitbarTexture, perCellWidth, perCellHeight);
        backgroundFrames = new TextureRegion[background_frameRows * background_frameCols];
        int index = 0;
        for (int row = 0; row < background_frameRows; row++) {
            for (int col = 0; col < background_frameCols; col++) {
                backgroundFrames[index++] = background_cellRegions[row][col];
            }
        }
        walkAnimation = new Animation(0.5F, backgroundFrames);
        walkAnimation.setPlayMode(Animation.PlayMode.LOOP);

        Gdx.input.setInputProcessor(startPage);
    }

    @Override
    public void show() {
        backgroundBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(startPage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(21/255f,25/255f,38/255f,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

//        //draw buttons
//        gameEntry.batch.begin();
//        float zoom=gamecam.viewportWidth/introTexture.getWidth()/2;
//        gameEntry.batch.draw(introTexture,40,40,introTexture.getWidth()*2,introTexture.getHeight()*2);
//        gameEntry.batch.end();

        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime);
        backgroundBatch.begin();
        backgroundBatch.draw(currentFrame, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        backgroundBatch.end();

        frame.render();
    }


    /***
     * resize the width and the height
     */
    @Override
    public void resize(int width, int height) {

    }

    /***
     * pause the game
     */
    @Override
    public void pause() {

    }

    /***
     * resume the game
     */
    @Override
    public void resume() {

    }

    /***
     * hide the game
     */
    @Override
    public void hide() {

    }

    /***
     * dispose this object
     */
    @Override
    public void dispose() {

    }
}
