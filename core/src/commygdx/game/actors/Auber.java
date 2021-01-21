package commygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import commygdx.game.TileWorld;
import commygdx.game.inputs.PlayerInput;
import commygdx.game.stages.Hud;

import java.util.ArrayList;

public class Auber extends Character {

    private final float MOV_SPEED = 6f;
    protected boolean facingRight;

    public Auber(Vector2 position, SpriteBatch batch) {
        super(position,batch);
        shuffle();
        movementSystem.setSpeed(MOV_SPEED);
        facingRight=true;
    }

    @Override
    protected Texture getTexture() {
        return new Texture(Gdx.files.internal("Characters/auberSprite.png"));
    }

    @Override
    protected void handleMovement() {
        //Left movement
        if(PlayerInput.getDirection()==1){
            Vector2 position = movementSystem.left();
            setPosition(position.x,position.y);
            if (facingRight==true){
                sprite.flip(true,false);
                facingRight=false;
            }
        }
        //Right movement
        if(PlayerInput.getDirection()==2){
            Vector2 position = movementSystem.right();
            setPosition(position.x,position.y);
            if (facingRight==false){
                sprite.flip(true,false);
                facingRight=true;
            }
        }
        //Up movement
        if(PlayerInput.getDirection()==3){
            Vector2 position = movementSystem.up();
            setPosition(position.x,position.y);
        }
        //Down movement
        if(PlayerInput.getDirection()==4){
            Vector2 position = movementSystem.down();
            setPosition(position.x,position.y);
        }
    }

    /**
     * Checks if the auber is on a teleporter
     * @param tiles Contains the teleporters
     * @return True if the auber is on a teleporter, false otherwise
     */
    public boolean  teleportCheck(TileWorld tiles){
        //check if standing on teleporter
        for ( Rectangle rect : tiles.getTeleporters().values()) {
            if(sprite.getBoundingRectangle().contains(rect)){
                return true;
            }
        }
        return false;
    }

    /**
     * Arrests any infiltrators in range of the player
     * @param infiltrators A list of all infiltrators in the game
     * @param hud The games HUD overlay
     */
    public void arrest(ArrayList<Infiltrator> infiltrators,Hud hud){
        if(PlayerInput.arrest()) {
            for (Infiltrator infiltrator : infiltrators) {
                if (Math.abs(infiltrator.getX() - this.getX()) < 100 && Math.abs(infiltrator.getY() - this.getY()) < 100) {
                    infiltrator.arrest(new Vector2((float)Math.random()*1000+1200,(float)Math.random()*400+5400));
                    hud.infiltratorCaught();
                }

            }
        }
    }
    //moves the camera to the auber when game starts
    public void shuffle(){
        Vector2 position = movementSystem.left();
        setPosition(position.x,position.y);
    }

}

