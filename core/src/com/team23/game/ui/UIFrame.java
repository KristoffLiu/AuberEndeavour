package com.team23.game.ui;

import com.badlogic.gdx.Gdx;

import java.util.Stack;

public class UIFrame{
    public Stack<UIPage> history;
    public UIPage currentPage;

    public UIFrame(){
        history = new Stack<>();
    }

    public void navigate(UIPage uiPage){
        history.push(uiPage);
        if(currentPage != null){
            currentPage.hide();
        }
        currentPage = history.peek();
        currentPage.setParentFrame(this);
        currentPage.appear();
        Gdx.input.setInputProcessor(currentPage);
    }

    public void goBack(){
        currentPage.hide();
        history.pop();
        currentPage = history.peek();
        currentPage.appear();
        Gdx.input.setInputProcessor(currentPage);
    }

    public void render() {
        currentPage.act();
        currentPage.draw();
    }
}
