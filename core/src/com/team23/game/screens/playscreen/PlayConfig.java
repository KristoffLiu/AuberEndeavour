package com.team23.game.screens.playscreen;

/***
 * Play Config
 */
public class PlayConfig {
    public PlayMode mode;
    public PlayDifficulty difficulty;
    public PlayState state;
    public boolean teleporterStatus = false;

    public PlayConfig(PlayMode mode, PlayDifficulty difficulty, PlayState playState){
        this.mode = mode;
        this.difficulty = difficulty;
        this.state = playState;
    }

    public static PlayConfig simpleNewGame(){
        return new PlayConfig(PlayMode.newGame, PlayDifficulty.simple, PlayState.PLAYING);
    }

    public static PlayConfig normalNewGame(){
        return new PlayConfig(PlayMode.newGame, PlayDifficulty.normal, PlayState.PLAYING);
    }

    public static PlayConfig difficultNewGame(){
        return new PlayConfig(PlayMode.newGame, PlayDifficulty.difficult, PlayState.PLAYING);
    }

    public static PlayConfig loadedGame(){
        return new PlayConfig(PlayMode.loadedGame, PlayDifficulty.normal, PlayState.PLAYING);
    }

    public static PlayConfig demoGame(){
        return new PlayConfig(PlayMode.demoGame, PlayDifficulty.normal, PlayState.PLAYING);
    }
}
