package com.irving.spaceinvaders.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.irving.spaceinvaders.SpaceInvaders;
import com.irving.spaceinvaders.actors.Bullet;
import com.irving.spaceinvaders.actors.Explosion;
import com.irving.spaceinvaders.actors.Invader;
import com.irving.spaceinvaders.actors.PlayerAircraft;

public class PlayScreen extends AbstractScreen {
	/*
	 * Play screen
	 * draw and update the actors(aircraft, invaders, bullets, explosions, etc)
	 * */

	private PlayerAircraft playerAircraft;
	
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Invader> invaders = new ArrayList<Invader>();
	private ArrayList<Explosion> explosions = new ArrayList<Explosion>();
	
	private ShapeRenderer shapeRenderer;
	private SpriteBatch spriteBatch;
	private BitmapFont font;
		
	private float timeSpawnInvaders = .5f; // time to do spawn of invaders
	private Random random = new Random();
	
	public PlayScreen(SpaceInvaders game) {
		super(game);
	}

	@Override
	public void show() {
		shapeRenderer = game.shapeRenderer;
		spriteBatch = game.spriteBatch;
		
		font = new BitmapFont();
		
		playerAircraft = new PlayerAircraft();

		// link bullets to PlayerAircraft and Bullet
		PlayerAircraft.bullets = bullets;
		Bullet.bullets = bullets;
		
		Explosion.explosions = explosions;
		
		createInvaders();
	}
	
	private void createInvaders() {
		for (int i = 0; i < 6; i++) {
			invaders.add(new Invader(250 + i * 60, 430));
		}
	}
	
	private void checkCollisions() {
		ArrayList<Bullet> localBullets = new ArrayList<Bullet>(bullets);
		ArrayList<Invader> localInvaders = new ArrayList<Invader>(invaders);
		
		for (Bullet bullet : localBullets) {
			for (Invader invader : localInvaders) {
				if (bullet.getPosX() + bullet.getWidth() >= invader.getPosX() &&
					bullet.getPosX() <= invader.getPosX() + invader.getWidth() &&
					bullet.getPosY() + bullet.getHeight() >= invader.getPosY() &&
					bullet.getPosY() <= invader.getPosY() + invader.getHeight()) {
					bullets.remove(bullet);
					invaders.remove(invader);
					explosions.add(new Explosion(invader.getPosX(), invader.getPosY(), 
							7, invader.getColor()));
					
				}
			}
		}
		
		for (Invader invader : invaders) {
			if (playerAircraft.getPosX() + playerAircraft.getWidth() >= invader.getPosX() &&
				playerAircraft.getPosX() <= invader.getPosX() + invader.getWidth() &&
				playerAircraft.getPosY() + playerAircraft.getHeight() >= invader.getPosY() &&
				playerAircraft.getPosY() <= invader.getPosY() + invader.getHeight() && playerAircraft.isLive()) {
				
				explosions.add(new Explosion(invader.getPosX(), invader.getPosY()));
				explosions.add(new Explosion(invader.getPosX(), invader.getPosY(), 10, 
						new Color(0, 1, .5f, 1)));
				playerAircraft.setLive(false);
				
			}
		}
		
	}
	
	private void update() {		

		// Update movement of bullets
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
		}
		
		// Update movement of invaders
		for (int i = 0; i < invaders.size(); i++) {
			invaders.get(i).update();
		}
		
		// Update movement of explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
		}
		
		playerAircraft.update();
		checkCollisions();
		respawnInvaders();

	}
	
	private void respawnInvaders() {
		// Create invaders
		if (invaders.size() == 0 || timeSpawnInvaders % 100 == 0) {
			createInvaders();
			timeSpawnInvaders = .5f;
		}
		
		timeSpawnInvaders += .5; // time to do spawn of invaders
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(random.nextFloat() - .2f, 0, random.nextFloat(), 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		update();
		
		spriteBatch.begin();
		font.setScale(1);
		font.draw(spriteBatch, "Epilepsy, where?", 10, 20);

		if (!playerAircraft.isLive()) {
			font.setScale(3);
			font.draw(spriteBatch, "GAME OVER", 180, 300);
			font.setScale(2);
			font.draw(spriteBatch, "PRESS SPACE TO COME BACK TO PLAY", 20, 240);
			if (Gdx.input.isKeyJustPressed(Keys.SPACE))
				game.setScreen(new PlayScreen(game));
		}
		
		spriteBatch.end();
		
		// Generate stars
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 1, 1, 1);
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(640);
			shapeRenderer.point(x, 5 * i, 0);
		}
		playerAircraft.draw(shapeRenderer);
		
		// draw bullets
		for (Bullet bullet : bullets) {
			bullet.draw(shapeRenderer);
		}

		// draw invaders
		shapeRenderer.setAutoShapeType(true);
		shapeRenderer.set(ShapeType.Filled);
		for (Invader invader : invaders) {
			invader.draw(shapeRenderer);
		}
		
		for (Explosion explosion : explosions) {
			explosion.draw(shapeRenderer);
		}
		shapeRenderer.end();
	}
}
