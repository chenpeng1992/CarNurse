package com.carnurse.ui;

import com.carnurse.app.R;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.app.Activity;
import android.content.Intent;

public class SplashActivity extends Activity {

	private Handler handler=new Handler();
	private Runnable runnable=new Runnable(){

		@Override
		public void run() {
			handler.removeCallbacks(runnable);
			Intent intent=new Intent(SplashActivity.this, MainActivity.class);
			startActivity(intent);
			finish();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		handler.postDelayed(runnable, 3000);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_UP)
			handler.post(runnable);
		return super.onTouchEvent(event);
	}
}
