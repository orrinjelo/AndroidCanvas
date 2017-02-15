package com.orrinjelo.sidemenudemo1;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SideMenuDemo1 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("img/button1.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		int h = Gdx.graphics.getHeight();
		int left = -80;
		batch.draw(img, left, 0, 100, h/7);
		batch.draw(img, left, h/7, 100, h/7);
		batch.draw(img, left, 2*h/7, 100, h/7);
		batch.draw(img, left, 3*h/7, 100, h/7);
		batch.draw(img, left, 4*h/7, 100, h/7);
		batch.draw(img, left, 5*h/7, 100, h/7);
		batch.draw(img, left, 6*h/7, 100, h/7);
		batch.end();
	}
}
