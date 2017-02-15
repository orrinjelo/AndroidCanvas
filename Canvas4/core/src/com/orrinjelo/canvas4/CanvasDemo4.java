package com.orrinjelo.canvas4;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class CanvasDemo4 extends ApplicationAdapter {

	private Canvas canvas;
	private OrthographicCamera camera;
	private InputMultiplexer inputMultiplexer;
	private MyInputProcessor inputProcessor;
	private MyGestureHandler gestureHandler;
	
	public OrthographicCamera getCamera()
	{
		return camera;
	}
	
    public void initializeInputProcessors()
    {
    	inputMultiplexer = new InputMultiplexer();
    	
    	Gdx.input.setInputProcessor(inputMultiplexer);
    	
    	inputProcessor = new MyInputProcessor(this.canvas);
    	gestureHandler = new MyGestureHandler(this.canvas);
    	
    	inputMultiplexer.addProcessor(new GestureDetector(gestureHandler));
    	inputMultiplexer.addProcessor(inputProcessor);
    }
	
	@Override
	public void create () {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(w,h);
		camera.position.set(w/2, h/2, 0);
		camera.update();
		canvas = new Canvas(new Texture("domocolor.jpg"), this);
		canvas.show();
		
		initializeInputProcessors();
	}
	
	@Override
	public void resize(int width, int height) {
		canvas.resize(width, height);
	}

	@Override
	public void render() {
		canvas.render(0.1f);
	}
	
	@Override
	public void dispose() {
		canvas.dispose();
	}
}
