package com.cosmosengine.SoundManager;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;
import java.util.HashMap;

import com.cosmosengine.GameFrame;

/**
 * The loader class for retrieving cached sounds. Will add the sound to the cache if it is not already there.
 */
public class SoundLoader {
	private static SoundLoader single = new SoundLoader();

	public static SoundLoader get() {
		return single;
	}

	private HashMap<String, CosmosSound> sounds = new HashMap<String, CosmosSound>();

	public CosmosSound getSound(String ref) {
		if (sounds.get(ref) != null) return sounds.get(ref);
		AudioClip clip = null;
		try {
			URL url = GameFrame.class.getClassLoader().getResource(
				"assets/sounds/" + ref);
			if (url != null) clip = Applet.newAudioClip(url); // Load the Sound
			else
				System.err.println("assets/sounds/" + ref + " not found.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (clip == null) return null;
		CosmosSound sound = new CosmosSound(clip);
		sounds.put(ref, sound);
		return sound;
	}
}
