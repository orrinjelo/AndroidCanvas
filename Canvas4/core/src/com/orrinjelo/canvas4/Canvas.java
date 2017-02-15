package com.orrinjelo.canvas4;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Screen;

import com.orrinjelo.canvas4.Canvas;


public class Canvas implements Screen {
	private Stack<Texture> layers;
	private Texture currentLayer;
	
	private int Width;
	private int Height;
	private int ImgWidth;
	private int ImgHeight;
	private CanvasDemo4 parent;
	private double HWratio;
	
	private float zoom = 1.0f;
	
	private SpriteBatch batch;
	
	public Canvas(Texture tx, CanvasDemo4 parent) {
		batch = new SpriteBatch();
		layers = new Stack<Texture>();
		layers.push(currentLayer);
		currentLayer = tx;
		Width = Gdx.graphics.getWidth();
		Height = Gdx.graphics.getHeight();
		ImgWidth = tx.getWidth();
		ImgHeight = tx.getHeight();
		this.parent = parent;
		int w = currentLayer.getWidth();
		int h = currentLayer.getHeight();
		HWratio = (double)h / (double)w;
		
		parent.getCamera().zoom = zoom;
		parent.getCamera().update();
	}
		
	@Override
    public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		int w = currentLayer.getWidth();
		int h = currentLayer.getHeight();

		Width = Gdx.graphics.getWidth();
		Height = Gdx.graphics.getHeight();
		
		parent.getCamera().zoom = zoom;
		parent.getCamera().update();
		
    	batch.setProjectionMatrix(parent.getCamera().combined);
		
		if (Width < w)
		{
			w = Width;
			h = (int)(HWratio * w);
		} else if (Height < h)
		{
			h = Height;
			w = (int)((double)h/HWratio);
		}
		
		batch.draw(currentLayer, parent.getCamera().position.x - w/2, parent.getCamera().position.y - h/2, h, w);
		batch.end();
    }
 
    @Override
    public void resize(int width, int height) {
    	parent.getCamera().setToOrtho(false);
    	batch.setProjectionMatrix(parent.getCamera().combined);
    }
 
    @Override
    public void show() {

    }
 
    @Override
    public void hide() {
    // TODO Auto-generated method stub
    }
 
    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void resume() {
       
    }
 
    @Override
    public void dispose() {
    	Texture temp = null;
		while (!layers.isEmpty())
   			temp = layers.pop();
   			if (temp != null)
   				temp.dispose();
 
    }

    public void setZoom(float newZoom)
    {
    	this.zoom = newZoom;
    }
    
    public float getZoom()
    {
    	return zoom;
    }
    

}
