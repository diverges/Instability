package com.cosmosengine.SoundManager;

import java.applet.AudioClip;

/**
 * The base sound class for all sound files.
 */
public class CosmosSound {

    private AudioClip song; // Sound player
    private boolean isPlaying = false;

    CosmosSound(AudioClip song) {
        this.song = song;
    }

    public void playSound() {
        isPlaying = true;
        song.loop(); // Play
    }

    public void stopSound() {
        isPlaying = false;
        song.stop(); // Stop
    }

    public void playSoundOnce() {
        song.play(); // Play only once
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
