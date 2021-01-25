package com.team23.game.ui.uistages;

import java.util.EventObject;

public class FrameEvent extends EventObject {
    private static final long serialVersionUID = 6496098798146410884L;

    private String doorState = "";// 表示门的状态，有“开”和“关”两种

    public FrameEvent(Object source, String doorState) {
        super(source);
        this.doorState = doorState;
    }

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public FrameEvent(Object source) {
        super(source);
    }

    public void setDoorState(String doorState) {
        this.doorState = doorState;
    }

    public String getDoorState() {
        return this.doorState;
    }
}
