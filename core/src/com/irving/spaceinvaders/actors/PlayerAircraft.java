package com.irving.spaceinvaders.actors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class PlayerAircraft {
	/*
	 * Represents an Aircraft that can shoot and move using the keyboard
	 * */
	private int posX = 340;
	private int posY = 10;
	private int velocity = 6;
	private int height = 30;
	private int width = 50;
	private boolean live = true;
	public static ArrayList<Bullet> bullets;
	public static Sound shotSound = Gdx.audio.newSound(Gdx.files.internal("shot.wav"));
	
	public void update() {
		if (isLive()) {			
			if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
				moveRight();
			} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
				moveLeft();
			}
			
			if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
				shot();
			}
		}
	}
	
	private void moveLeft() {
		posX = getPosX() - velocity;
	}
	
	private void moveRight() {
		posX = getPosX() + velocity;
	}
	
	private void shot() {
		Bullet newBullet = new Bullet(getPosX() + 15, getPosY() + 40);
		Bullet newBullet2 = new Bullet(getPosX() + 30, getPosY() + 40);
		
		bullets.add(newBullet);
		bullets.add(newBullet2);
		
		//shotSound.play();
	}

	public void draw(ShapeRenderer shapeRenderer) {
		if (isLive()) {
			shapeRenderer.setColor(0, 1, .5f, 1);
			
			shapeRenderer.rect(getPosX(), getPosY(), getWidth(), 20); // Body
			shapeRenderer.rect(getPosX() + 15, getPosY() + 20, 20, 10); // Cabin

		}
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}

	public int getPosY() {
		return posY;
	}

	public int getHeight() {
		return height;
	}

	public int getPosX() {
		return posX;
	}

	public int getWidth() {
		return width;
	}

}
