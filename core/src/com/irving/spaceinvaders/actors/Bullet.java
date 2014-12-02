package com.irving.spaceinvaders.actors;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Bullet {
	/*
	 * Rrepresents a Bullet with velocity and vertical direction
	 * */
	private int posX;
	private int posY;
	private int velocity = 5;
	private int height = 20;
	private int width = 4;
	public static ArrayList<Bullet> bullets;
	
	public Bullet(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void update() {
		posY = getPosY() + velocity;
		
		if (this.getPosY() > 480) {
			// If get out of the screen, then remove of the bullets ArrayList
 			bullets.remove(this);
		}
	
	}
	
	public void draw(ShapeRenderer shapeRenderer) {		
		shapeRenderer.setColor(0, 1, .5f, 1);
		shapeRenderer.rect(getPosX(), getPosY(), getWidth(), getHeight());
	}

	public int getWidth() {
		return width;
	}

	public int getPosX() {
		return posX;
	}

	public int getHeight() {
		return height;
	}

	public int getPosY() {
		return posY;
	}


}
