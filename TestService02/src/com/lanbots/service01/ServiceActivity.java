package com.lanbots.service01;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class ServiceActivity extends Service {
	
	private static final String TAG = "TAG"; 
	private MediaPlayer mediaPlayer;

	// �÷�������Ҫ��
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		 Log.i(TAG, "����onCreate");
		mediaPlayer = MediaPlayer.create(this, R.raw.test);
		//ѭ������
		mediaPlayer.setLooping(true);

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "����onStartCommand");
		mediaPlayer.start();
		return START_STICKY;
		
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG, "����onDestroy"); 
		
		mediaPlayer.stop();
		mediaPlayer.release();
		super.onDestroy();
	}

}
