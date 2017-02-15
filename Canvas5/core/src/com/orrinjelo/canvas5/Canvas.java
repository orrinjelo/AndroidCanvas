package com.orrinjelo.canvas5;


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

public class Canvas extends ApplicationAdapter {

	private JCanvas canvas;
	private OrthographicCamera camera;
	private InputMultiplexer inputMultiplexer;
	private JInputProcessor inputProcessor;
	private JGestureHandler gestureHandler;
	
	public OrthographicCamera getCamera()
	{
		return camera;
	}
	
    public void initializeInputProcessors()
    {
    	inputMultiplexer = new InputMultiplexer();
    	
    	Gdx.input.setInputProcessor(inputMultiplexer);
    	
    	inputProcessor = new JInputProcessor(this.canvas);
    	gestureHandler = new JGestureHandler(this.canvas);
    	
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
		canvas = new JCanvas(new Texture("img/autumn-landscape-15602-1680x1050.jpg"), this);
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
