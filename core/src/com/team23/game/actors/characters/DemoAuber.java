package com.team23.game.actors.characters;

import com.badlogic.gdx.math.Vector2;
import com.team23.game.TileWorld;
import com.team23.game.ai.DemoAI;
import com.team23.game.ai.graph.PathGraph;
import com.team23.game.screens.playscreen.Hud;

import java.util.ArrayList;

/***
 * Demo Auber class
 */
public class DemoAuber extends Auber{
    private DemoAI ai;

    /***
     *
     * @param position
     * @param graph
     * @param movSpeed
     */
    public DemoAuber(Vector2 position, PathGraph graph,float movSpeed) {
        super(position, movSpeed);
        this.setSize(150, 170);
        ai = new DemoAI(graph);
    }

    /***
     *
     * @param tiles Contains the teleporters
     * @return
     */
    @Override
    public boolean teleportCheck(TileWorld tiles) {
        return false;
    }

    /***
     *
     * @param delta
     */
    @Override
    public void act(float delta) {
        ai.update(getPosition());
        super.act(delta);
    }

    /***
     * handle the movement of the auber
     */
    @Override
    protected void handleMovement() {
        //Left movement
        if(ai.left(getPosition())){
            Vector2 position = movementSystem.left();
            setPosition(position.x,position.y);
            if (facingRight){
                this.getTextureRegion().flip(true,false);
                facingRight=false;
            }
        }
        //Right movement
        if(ai.right(getPosition())){
            Vector2 position = movementSystem.right();
            setPosition(position.x,position.y);
            if (!facingRight){
                this.getTextureRegion().flip(true,false);
                facingRight=true;
            }
        }
        //Up movement
        if(ai.up(getPosition())){
            Vector2 position = movementSystem.up();
            setPosition(position.x,position.y);
        }
        //Down movement
        if(ai.down(getPosition())){
            Vector2 position = movementSystem.down();
            setPosition(position.x,position.y);
        }
    }


    /***
     * Arrests the infiltrator if in range and puts it in jail
     * @param infiltrators A list of all infiltrators in the game
     * @param hud The games HUD overlay
     */
    @Override
    public void arrest(ArrayList<Infiltrator> infiltrators, Hud hud) {
        for (Infiltrator infiltrator : infiltrators) {
            if (Math.abs(infiltrator.getX() - this.getX()) < 200 && Math.abs(infiltrator.getY() - this.getY()) < 200) {
                infiltrator.arrest(new Vector2((float)Math.random()*1000+1200,(float)Math.random()*400+5400));
                hud.infiltratorCaught(infiltrators);
            }
        }
    }
}
