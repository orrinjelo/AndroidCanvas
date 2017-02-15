package com.orrinjelo.canvas4;

import com.badlogic.gdx.InputProcessor;

public class MyInputProcessor implements InputProcessor {

	Canvas parent;
	
	public MyInputProcessor(Canvas parent)
	{
		this.parent = parent;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if (amount > 0 && parent.getZoom() < 2f )
			parent.setZoom(parent.getZoom() + 0.1f);
		if (amount < 0 && parent.getZoom() > 0.1 )
			parent.setZoom(parent.getZoom() - 0.1f);
		return true;
	}

}
