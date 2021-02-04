package com.team23.game.screens.playscreen;

import com.team23.game.GameEntry;

public class PlayConfig {
    public PlayMode mode;
    public PlayDifficulty difficulty;
    public PlayState state;

    public PlayConfig(PlayMode mode, PlayDifficulty difficulty, PlayState playState){
        this.mode = mode;
        this.difficulty = difficulty;
        this.state = playState;
    }

    public static PlayConfig simpleNewGame(){
        return new PlayConfig(PlayMode.newGame, PlayDifficulty.simple, PlayState.playing);
    }

    public static PlayConfig normalNewGame(){
        return new PlayConfig(PlayMode.newGame, PlayDifficulty.normal, PlayState.playing);
    }

    public static PlayConfig difficultNewGame(){
        return new PlayConfig(PlayMode.newGame, PlayDifficulty.normal, PlayState.playing);
    }

    public static PlayConfig loadedGame(){
        return new PlayConfig(PlayMode.loadedGame, PlayDifficulty.normal, PlayState.playing);
    }

    public static PlayConfig demoGame(){
        return new PlayConfig(PlayMode.demoGame, PlayDifficulty.normal, PlayState.playing);
    }
}
