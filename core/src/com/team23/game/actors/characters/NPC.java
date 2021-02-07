package com.team23.game.actors.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.team23.game.ai.InfiltratorAI;
import com.team23.game.ai.graph.PathGraph;
import com.team23.game.save.NPCInfo;
//import org.graalvm.compiler.lir.aarch64.AArch64Move;

/***
 * N P C
 */
public class NPC extends Character {

    //Constants
    private float movSpeed;
    private InfiltratorAI ai;
    private boolean facingRight;

    public NPC(NPCInfo info, PathGraph pathGraph) {
        this(info.position.toVector2(), pathGraph, info.moveSpeed);
    }

    public NPC(Vector2 position, PathGraph graph, float movSpeed) {
        super(position, movSpeed);
        this.setSize(150, 170);
        setPosition(position.x, position.y);
        ai = new InfiltratorAI(graph);
        facingRight=true;
    }

    @Override
    public void act(float delta) {
        ai.update(delta, new Vector2(getX(), getY()));
        super.act(delta);
    }

    @Override
    protected Texture getTexture(){
        return new Texture(Gdx.files.internal("Characters/infiltratorSprite.png"));
    }

    @Override
    protected void handleMovement() {
        if(ai.left(new Vector2(getX(),getY()),false)){
            Vector2 pos = movementSystem.left();
            setPosition(pos.x, pos.y);
            if (facingRight==true){
                this.getTextureRegion().flip(true,false);
                facingRight=false;
            }
        }
        if(ai.right(new Vector2(getX(),getY()),false)){
            Vector2 pos = movementSystem.right();
            setPosition(pos.x, pos.y);
            if (facingRight==false){
                this.getTextureRegion().flip(true,false);
                facingRight=true;
            }
        }
        if(ai.up(new Vector2(getX(),getY()),false)){
            Vector2 pos = movementSystem.up();
            setPosition(pos.x, pos.y);
        }
        if(ai.down(new Vector2(getX(),getY()),false)){
            Vector2 pos = movementSystem.down();
            setPosition(pos.x, pos.y);
        }
    }


    public void resetTexture(){
        this.getTextureRegion().setTexture(getTexture());
    }

}
