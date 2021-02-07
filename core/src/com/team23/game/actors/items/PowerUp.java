package com.team23.game.actors.items;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.team23.game.ai.InfiltratorAI;

public class PowerUp extends item {

    private InfiltratorAI ai;
    private String name;
    private boolean activated;


    public PowerUp(Vector2 position, String name) {
        super(position, name);
        this.name = name;
        setPosition(position.x,position.y);
        activated = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!activated) {
            super.draw(batch,parentAlpha);
        }
    }


    @Override
    protected Texture getTexture(String spriteName){
        return new Texture(Gdx.files.internal("PowerUps/"+spriteName+".png"));
    }


    public String getName(){
        return name;
    }

    public boolean isActivated() {
        return activated;
    }

    public void activate(){
        activated = true;
    }
}
