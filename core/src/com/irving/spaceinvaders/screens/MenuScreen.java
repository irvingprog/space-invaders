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
import com.irving.spaceinvaders.actors.Explosion;

public class MenuScreen extends AbstractScreen {
	
	private BitmapFont font;
	private SpriteBatch spriteBatch;
	private ShapeRenderer shapeRenderer;
	private Random random;
	private float time;
	private float timeExplosion;
	private ArrayList<Explosion> explosions = new ArrayList<Explosion>();

	public MenuScreen(SpaceInvaders game) {
		super(game);
	}
	
	@Override
	public void show() {
		font = new BitmapFont();
		
		shapeRenderer = game.shapeRenderer;
		spriteBatch = game.spriteBatch;
		random = new Random();
		
		timeExplosion = random.nextInt(3);
		
		Explosion.explosions = explosions;
	}
	
	private void update() {
		time += Gdx.graphics.getDeltaTime();
		
		if (time > timeExplosion) {
			explosions.add(new Explosion(random.nextInt(600), random.nextInt(400), 7, 
					new Color(random.nextInt(), random.nextInt(), 
							random.nextInt(), 1)));
			timeExplosion = time + random.nextFloat();
		}
		
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
		}
	}
	
	private void inputKeyBoard() {
		if (Gdx.input.isKeyJustPressed(Keys.SPACE))
			game.setScreen(new PlayScreen(game));
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(random.nextFloat() - .2f, 0, random.nextFloat(), 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		update();
		inputKeyBoard();
		
		// draw explosions
		shapeRenderer.begin(ShapeType.Line);
		shapeRenderer.setColor(1, 1, 1, 1);
		for (int i = 0; i < 100; i++) {
			int x = random.nextInt(640);
			shapeRenderer.point(x, 5 * i, 0);
		}
		
		for (Explosion explosion : explosions) {
			explosion.draw(shapeRenderer);
		}
		shapeRenderer.end();
		
		// draw text
		spriteBatch.begin();
		font.setScale(4);
		font.setColor(1, .8f, .4f, 1);
		font.draw(spriteBatch, "* Space Invaders *", 80, 400);
		font.setColor(1, 1, 1, 1);
		font.setScale(2);
		font.draw(spriteBatch, "Press SPACE to start", 180, 250);
		spriteBatch.end();	
	}
}
