package com.team23.game.screens.playscreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.team23.game.GameEntry;
import com.team23.game.ShipSystem;
import com.team23.game.actors.characters.Infiltrator;
import com.team23.game.ui.controls.Button;
import com.team23.game.ui.controls.ButtonClickListener;
import com.team23.game.ui.controls.TextBlock;
import com.team23.game.ui.controls.UIElement;
import com.team23.game.ui.pages.UIPage;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/***
 * Head Up Display
 */
public class Hud extends UIPage {
    public PlayScreen playScreen;
    private Viewport viewport;
    private int systemsUp;
    private TextBlock systemLabel;
    private TextBlock systemTextLabel;

    private int infiltratorsRemaining;
    private TextBlock infiltratorLabel;
    private TextBlock infiltratorTextLabel;

    private TextBlock attackLabel;
    private TextBlock attackTextLabel;

    private TextBlock hallucinateLabel;

    private BitmapFont font;

    private ArrayList<Infiltrator> enemies;
    private ArrayList<ShipSystem> systems;

    /***
     * constructor
     * used for buttons,text, etc
     */
    public Hud(final PlayScreen playScreen){
        this.playScreen = playScreen;
        this.enemies = playScreen.enemies;
        this.systems = playScreen.tiles.getSystems();

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        systemsUp = systems.size();
        infiltratorsRemaining = enemies.size();

        System.out.format("%d / 15 systems",systemsUp);
        font=new BitmapFont();
        font.getData().setScale(3f);
        float fontSize = 0.5f;

        systemTextLabel = new TextBlock();
        systemTextLabel.setText("systems operational");
        systemTextLabel.setFontSize(fontSize);
        systemTextLabel.setFontColor(1,1,1,1);
        systemTextLabel.setRelativePosition(1350,50, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.topAlignment);

        //operational systems
        systemLabel = new TextBlock();
        systemLabel.setText(String.format("%d / 15",systemsUp));
        systemLabel.setFontSize(fontSize);
        systemLabel.setFontColor(1,1,1,1);
        systemLabel.setRelativePosition(1700,50, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.topAlignment);

        infiltratorTextLabel = new TextBlock();
        infiltratorTextLabel.setText("infiltrators remaining");
        infiltratorTextLabel.setFontSize(fontSize);
        infiltratorTextLabel.setFontColor(1,1,1,1);
        infiltratorTextLabel.setRelativePosition(1350,100, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.topAlignment);

        //remaining infiltrators
        infiltratorLabel = new TextBlock();
        infiltratorLabel.setText(String.format("%d / 8 ",infiltratorsRemaining));
        infiltratorLabel.setFontSize(fontSize);
        infiltratorLabel.setFontColor(1,1,1,1);
        infiltratorLabel.setRelativePosition(1700,100, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.topAlignment);

        attackTextLabel = new TextBlock();
        attackTextLabel.setText("Current attacks");
        attackTextLabel.setFontSize(fontSize);
        attackTextLabel.setFontColor(1,1,1,1);
        attackTextLabel.setRelativePosition(1350,150, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.topAlignment);


        //systems under attack
        attackLabel = new TextBlock();
        attackLabel.setText("None");
        attackLabel.setFontSize(fontSize);
        attackLabel.setFontColor(1,1,1,1);
        attackLabel.setRelativePosition(1700,150, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.topAlignment);


        //hallucination warning
        hallucinateLabel = new TextBlock();
        hallucinateLabel.setText("");
        hallucinateLabel.setFontSize(fontSize);
        hallucinateLabel.setFontColor(1,1,1,1);
        hallucinateLabel.setRelativePosition(1350,300, UIElement.HorizontalAlignment.leftAlignment, UIElement.VerticalAlignment.topAlignment);

        Button saveButton = new Button();
        saveButton.setTextures(
                "ui/HUD/save.png",
                "ui/HUD/save.png",
                "ui/HUD/save.png",
                ""
        );
        saveButton.setSize(70f, 70f);
        saveButton.setRelativePosition(50,50);
        saveButton.setClickListener(new ButtonClickListener(){
            /** Called when a mouse button or a finger touch goes up anywhere, but only if touchDown previously returned true for the mouse
             * button or touch. The touchUp event is always {@link Event#handle() handled}.
             * @see ButtonClickListener */
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                playScreen.save();
            }
        });

        this.addUIElement(systemLabel);
        this.addUIElement(systemTextLabel);
        this.addUIElement(infiltratorLabel);
        this.addUIElement(infiltratorTextLabel);
        this.addUIElement(attackLabel);
        this.addUIElement(attackTextLabel);
        this.addUIElement(hallucinateLabel);
        this.addUIElement(saveButton);
    }

    /**
     * Updates the HUD to decrease the amount of infiltrators
     */
    public void infiltratorCaught(){
        infiltratorsRemaining-=1;
        infiltratorLabel.setText(String.format("%d / 8",infiltratorsRemaining));
    }

    /**
     * Sets the HUD's hallucination warning off or on
     * @param show If the hallucination warning should be shown or not
     */
    public void showHallucinateLabel(boolean show){
        if (show){
            hallucinateLabel.setText("You are hallucinating \n Go to infirmary to heal ");
        }else{
            hallucinateLabel.setText("");
        }
    }

    /**
     * Updates the HUD's display on what rooms have systems under attack
     * @param systems List of all the systems on the map
     */
    public void updateAttacks(List<ShipSystem> systems){
        /*Update hud to reflect attacks*/
        String room=new String();
        systemsUp=0;
        for (ShipSystem system:systems){
            if (system.getState()==1){
                if (!room.contains(system.getRoom())){
                    room+=system.getRoom();
                    room+="\n";
                }
            }
            if (system.getState()!=2){systemsUp+=1;}
        }
        if( room.length()<1){
            room="None";
        }
        attackLabel.setText(room);
        systemLabel.setText(String.format("%d / 15",systemsUp));
    }
    public int getInfiltratorsRemaining(){return infiltratorsRemaining;}
    public int getSystemsUp(){return systemsUp;}

    public void save(){

    }
}
