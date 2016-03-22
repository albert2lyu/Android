package com.lanbots.broadcastreceiver;
/*
 * 用来处理Android中发出的广播事件
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TestReceiver extends BroadcastReceiver {

	public TestReceiver(){
		System.out.println("TestReceiver");
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("onReceive");
	}

}
