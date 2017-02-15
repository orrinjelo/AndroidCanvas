package com.orrinjelo.canvas5;


import java.util.Stack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Screen;

import com.orrinjelo.canvas5.Canvas;


public class JCanvas implements Screen {
	private Stack<Texture> layers;
	private Texture currentLayer;
	
	private int Width;
	private int Height;
	private int ImgWidth;
	private int ImgHeight;
	private Canvas parent;
	private double HWratio;
	private int XOffset;
	private int YOffset;
	private Rectangle clickspace;
	
	private float zoom = 1.0f;
	
	private SpriteBatch batch;
	
	public JCanvas(Texture tx, Canvas parent) {
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
		XOffset = 0;
		YOffset = 0;
		
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
		
		clickspace = new Rectangle(parent.getCamera().position.x - w/2 + XOffset, 
								   parent.getCamera().position.y - h/2 + YOffset,
								   h, w);
		
		parent.getCamera().zoom = zoom;
		//parent.getCamera().translate((float)XOffset, (float)YOffset);
		parent.getCamera().update();
		
    	batch.setProjectionMatrix(parent.getCamera().combined);
		
    	Gdx.app.log("HWratio", "HWratio is " + Double.toString(HWratio));
		
    	Gdx.app.log("HWratio", "Height is " + Integer.toString(Height));
    	Gdx.app.log("HWratio", "Width is " + Integer.toString(Width));
    	Gdx.app.log("HWratio", "h is " + Integer.toString(h));
    	Gdx.app.log("HWratio", "w is " + Integer.toString(w));


    	if (Width < w && w > h)
		{
			w = Width;
			h = (int)(HWratio * w);
		} else if (Height < h)
		{
			h = Height;
			w = (int)((double)h/HWratio);
		}
    	
    	Gdx.app.log("HWratioPost", "Height is " + Integer.toString(Height));
    	Gdx.app.log("HWratioPost", "Width is " + Integer.toString(Width));
    	Gdx.app.log("HWratioPost", "h is " + Integer.toString(h));
    	Gdx.app.log("HWratioPost", "w is " + Integer.toString(w));
		
		batch.draw(currentLayer, parent.getCamera().position.x - w/2 + XOffset, parent.getCamera().position.y - h/2 + YOffset, w, h);
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
    
    public void translateImage(float deltaX, float deltaY)
    {
    	XOffset += deltaX * zoom;
    	YOffset -= deltaY * zoom;
    	
    	if (XOffset > (Width/ 2)) XOffset = (int)((Width / 2));
    	if (XOffset < (-Width / 2)) XOffset = (int)((-Width / 2));
    	if (YOffset > (Height / 2)) YOffset = (int)((Height / 2));
    	if (YOffset < (-Height / 2)) YOffset = (int)((-Height / 2));
		
    }
    
    public Canvas getParent()
    {
    	return this.parent;
    }
    
    public Rectangle getClickspace()
    {
    	return this.clickspace;
    	
    }
    
}
