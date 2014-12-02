package com.irving.spaceinvaders.actors;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Invader {
	/*
	 * Represents a Invader with random color
	 * Movements are left, right and down in the limits of the screen
	 */
	private int posX;
	private int posY;
	private int velocity = -3;
	private int height = 25;
	private int width = 40;

	private Random random = new Random();
	
	private Color color = new Color(random.nextFloat() + .5f, random.nextFloat() + .5f, 
			random.nextFloat() + .5f, 1);
	
	public Invader(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	public void update() {
		posX = getPosX() + velocity;
		
		// change direction if get the limits of the screen
		if (getPosX() <= 50 || getPosX() >= 580) {
			velocity = velocity * -1;
			posY = getPosY() - (getHeight() + 20);
		}
	
	}
	
	public void draw(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(color);
		shapeRenderer.rect(getPosX(), getPosY(), getWidth(), getHeight());
		// eyes
		shapeRenderer.setColor(0, 0, 0, 1);
		shapeRenderer.setColor(0, 0, 0, 1);
		shapeRenderer.rect(getPosX() + 10, getPosY() + 10, 5, 5);
		shapeRenderer.rect(getPosX() + 25, getPosY() + 10, 5, 5);
		
		shapeRenderer.setColor(color);
		
		shapeRenderer.rect(getPosX() - 5, getPosY() + 5, 5, 10);
		shapeRenderer.rect(getPosX() + getWidth(), getPosY() + 5, 5, 10);
		
		shapeRenderer.rect(getPosX(), getPosY() - 5, 5, 5);
		shapeRenderer.rect(getPosX() + 35, getPosY() - 5, 5, 5);
		
		shapeRenderer.rect(getPosX() + 5, getPosY() - 10, 10, 5);
		shapeRenderer.rect(getPosX() + 25, getPosY() - 10, 10, 5);
		
		shapeRenderer.rect(getPosX(), getPosY() + getHeight(), 10, 5);
		shapeRenderer.rect(getPosX() + getWidth() - 10, getPosY() + getHeight(), 10, 5);
		
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Color getColor() {
		return color;
	}

}
