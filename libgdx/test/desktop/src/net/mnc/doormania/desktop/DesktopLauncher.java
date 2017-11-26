package net.mnc.doormania.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.mnc.doormania.Launcher;

public class DesktopLauncher
{
	public static void main (String[] arg)
    {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.title = "Working Title";

		// different sizes ResolutionDP

		// rare androids
		  config.height = 480;
		  config.width = 800;

        // Galaxy Note 2 --
         //config.height = 720;
         //config.width = 1280;

		// iphone 2G, 3G, 3GS --
		// config.height = 480;
		// config.width = 320;

		// iphone 6 Plus Retina --
		// config.height = 640;
		// config.width = 960;

		// iphone 5, 5S --
		// config.height = 640;
		// config.width = 1136;

		// iphone 6 --
		// config.height = 640;
		// config.width = 1136;

		// iphone 6  --
		// config.height = 750;
		// config.width = 1334;

		// iphone 6  --
		// config.height = 1125;
		// config.width = 2001;

		// Galaxy tab 3 --
		// config.height = 800;
		// config.width = 1280;

		// Galaxy nexus --
		// config.height = 720;
		// config.width = 1280;

		// Galaxy Note 3 --
		// config.height = 1080;
		// config.width = 1920;

		new LwjglApplication(new Launcher(), config);
	}
}
