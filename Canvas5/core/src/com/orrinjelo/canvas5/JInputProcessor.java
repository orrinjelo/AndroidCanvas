package com.orrinjelo.canvas5;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;


public class JInputProcessor implements InputProcessor {

	JCanvas parent;
	int startX, startY;
	boolean isTouched;
	Vector3 touchPos;
	
	public JInputProcessor(JCanvas parent)
	{
		this.parent = parent;
		this.startX = 0;
		this.startY = 0;
		this.isTouched = false;
		touchPos = new Vector3(0f, 0f, 0f);
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
		/*
		this.startX = Gdx.input.getX(pointer);
		this.startY = Gdx.input.getY(pointer);
		return false;
		*/
		parent.getParent().getCamera().unproject(touchPos.set(Gdx.input.getX(pointer), Gdx.input.getY(pointer),  0));

        if(parent.getClickspace().contains(touchPos.x, touchPos.y)){
            //Gdx.app.log("bTouched", "bTouched is true");
            isTouched = true;
            return true;
        }else{
            Gdx.app.log("bTouched", "bTouched is false");
            isTouched = false;
        }
        return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		/*
		if (this.startX != Gdx.input.getX(pointer) || this.startY != Gdx.input.getY(pointer))
			this.parent.translateImage(this.startX - Gdx.input.getX(pointer), 
									   this.startY - Gdx.input.getY(pointer));
		this.startX = Gdx.input.getX(pointer);
		this.startY = Gdx.input.getY(pointer);
		return true;
		*/
		
		this.parent.getParent().getCamera().unproject(touchPos.set(Gdx.input.getX(pointer), Gdx.input.getY(pointer), 0));
		if(!isTouched){
            //Gdx.app.log("bTouched", "bTouched is false - touchDragged");
        }else{
            //Gdx.app.log("bTouched", "bTouched is true - touchDragged");
            //Gdx.app.log("Movement", this.touchPos);
            this.parent.getClickspace().x = touchPos.x - 64 / 2;
            return true;
        }
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