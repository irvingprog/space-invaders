package com.irving.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.irving.spaceinvaders.screens.MenuScreen;


public class SpaceInvaders extends Game {
	/*
	 * Main applications, set the start Screen
	 * Start in MenuScreen
	 * */
	
	public ShapeRenderer shapeRenderer;
	public SpriteBatch spriteBatch;

			
	@Override
	public void create () {
		shapeRenderer= new ShapeRenderer();
		spriteBatch = new SpriteBatch();
		
		setScreen(new MenuScreen(this));
	}
	
}
