package com.team23.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.team23.game.GameEntry;
import com.team23.game.ui.UIStage;
import com.team23.game.ui.controls.Button;
import com.team23.game.ui.controls.ButtonClickListener;
import com.team23.game.ui.controls.ClickableUIElement;
import com.team23.game.ui.controls.labels.LabelStyles;

public class StartScreen implements Screen {

    private GameEntry gameEntry;
    public UIStage uiStage;
    private Label labelGameTitle;
    private Texture introTexture;

    float stateTime = 0f;
    SpriteBatch backgroundBatch;
    private TextureRegion[] backgroundFrames;
    private Animation walkAnimation;
    private TextureRegion currentFrame;

    public StartScreen(final GameEntry gameEntry){
        this.gameEntry = gameEntry;
        introTexture = new Texture("IntroV2.png");
        uiStage = new UIStage(new StretchViewport(GameEntry.VIEW_WIDTH, GameEntry.VIEW_HEIGHT));

        labelGameTitle = new Label("TEAM 23 - Auber Game 2", LabelStyles.getGameTitleLabelStyle());
        labelGameTitle.setPosition(uiStage.getWidth()/2-labelGameTitle.getWidth()/2,800);

        Button playButton = new Button(this.uiStage);
        playButton.setTextures(
                "Menu/Buttons/playNormal.png",
                "Menu/Buttons/playHovered.png",
                "Menu/Buttons/playPressed.png",
                "");
        playButton.setWidth(playButton.getWidth()/2f);
        playButton.setHeight(playButton.getHeight()/2f);
        playButton.setPosition(uiStage.getWidth()/2-playButton.getWidth()/2,500);
        playButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameEntry.createPlayScreen(false);
                gameEntry.gameState=0;
            }
        });

        Button loadButton = new Button(this.uiStage);
        loadButton.setTextures(
                "Menu/Buttons/loadNormal.png",
                "Menu/Buttons/loadHovered.png",
                "Menu/Buttons/loadPressed.png",
                "");
        loadButton.setWidth(loadButton.getWidth()/2f);
        loadButton.setHeight(loadButton.getHeight()/2f);
        loadButton.setPosition(uiStage.getWidth()/2-playButton.getWidth()/2,350);
        loadButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
            }
        });

        Button demoButton = new Button(this.uiStage);
        demoButton.setTextures(
                "Menu/Buttons/demoNormal.png",
                "Menu/Buttons/demoHovered.png",
                "Menu/Buttons/demoPressed.png",
                "");
        demoButton.setWidth(demoButton.getWidth()/2f);
        demoButton.setHeight(demoButton.getHeight()/2f);
        demoButton.setPosition(uiStage.getWidth()/2-playButton.getWidth()/2,200);
        demoButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                gameEntry.createPlayScreen(true);
                gameEntry.gameState=0;
            }
        });

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

        uiStage.addActor(labelGameTitle);
        uiStage.addActor(playButton);
        uiStage.addActor(demoButton);
        Gdx.input.setInputProcessor(uiStage);
    }

    @Override
    public void show() {
        backgroundBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(uiStage);
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

        uiStage.act();
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {



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

    }
}
