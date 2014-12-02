package com.irving.spaceinvaders.actors;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Explosion {
	
	PieceExplosion[] points = new PieceExplosion[8];
	private float velocity = 3;
	private float time  = 0;
	private int size = 7;
	private Color color = new Color(1, 0, 0, 1);
	
	static Sound sound = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
	public static ArrayList<Explosion> explosions;
	

	public Explosion(int posX, int posY) {
		for (int i = 0; i < points.length; i++) {
			float x2 = (float) (posX + (Math.cos(Math.toRadians(i * 45)) * size));
			float y2  = (float) (posY + (Math.sin(Math.toRadians(i * 45)) * size));
			points[i] = new PieceExplosion(posX, posY, x2, y2);
		}
		
		//sound.play();
	}
	
	public Explosion(int posX, int posY, int size, Color color) {
		this(posX, posY);
		this.size = size;
		this.color = color;
	}
	
	public void update() {
		int i = 0;
		for (PieceExplosion piece : points) {
			piece.x = (float) (piece.x + (Math.cos(Math.toRadians(i * 45)) * velocity));
			piece.y = (float) (piece.y + (Math.sin(Math.toRadians(i * 45)) * velocity));
			piece.x2  = (float) (piece.x + (Math.cos(Math.toRadians(i * 45)) * size));
			piece.y2  = (float) (piece.y + (Math.sin(Math.toRadians(i * 45)) * size));
			i++;
		}
	
		time += .2;
		velocity -= .05;
		
		if (time > 10)
			explosions.remove(this);
	}
	
	public void draw(ShapeRenderer shapeRenderer) {
		shapeRenderer.setColor(color);
		for (PieceExplosion piece : points) {
			shapeRenderer.rectLine(piece.x, piece.y, piece.x2, piece.y2, size);
		}
	}
}
