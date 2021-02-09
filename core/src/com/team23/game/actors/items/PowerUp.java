package com.team23.game.actors.items;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.team23.game.ai.InfiltratorAI;
import com.team23.game.save.PowerUpInfo;

/***
 * PowerUp class
 */
public class PowerUp extends Item {

    private InfiltratorAI ai;
    private String name;
    private boolean activated;

    public PowerUp(PowerUpInfo powerUpInfo) {
        this(powerUpInfo.position.toVector2(), powerUpInfo.name);
    }


    /**
     *
     * @param position The position of the powerup in the world
     * @param name the name of the powerup
     */
    public PowerUp(Vector2 position, String name) {
        super(position, name);
        this.name = name;
        setPosition(position.x,position.y);
        activated = false;
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