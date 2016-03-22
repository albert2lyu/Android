package com.example.gethtml02;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetWorkUtils {
	
	private Context context; 
	// ��·���ӹ������ 
	public ConnectivityManager connectivityManager; 
	public NetWorkUtils(Context context) { 
	this.context = context; 
	// ��ȡ�������ӵĶ��� 
	connectivityManager = (ConnectivityManager) context 
	.getSystemService(Context.CONNECTIVITY_SERVICE); 
	} 
	//public void setActiveNetWork() { 
	public boolean setActiveNetWork() { 
	boolean flag = false; 
	// ��ȡ���õ��������Ӷ��� 
	NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo(); 
	if (networkInfo == null) { 
	new AlertDialog.Builder(context) 
	.setTitle("���粻����") 
	.setMessage("������������?") 
	.setPositiveButton("ȷ��", 
	new DialogInterface.OnClickListener() { 
	@Override 
	public void onClick(DialogInterface dialog, 
	int which) { 
	Toast.makeText(context, "���ȷ��", 
	Toast.LENGTH_LONG).show(); 
	// ������ͼ 
	Intent intent = new Intent(); 
	intent.setAction(Intent.ACTION_MAIN); 
	intent.addCategory("android.intent.category.LAUNCHER"); 
	intent.setComponent(new ComponentName( 
	"com.android.settings", 
	"com.android.settings.Settings")); 
	intent.setFlags(0x10200000); 
	// ִ����ͼ 
	context.startActivity(intent); 
	} 
	}) 
	.setNegativeButton("ȡ��", 
	new DialogInterface.OnClickListener() { 
	@Override 
	public void onClick(DialogInterface dialog, 
	int which) { 
	} 
	// ����.show(); 
	}).show(); 
	} 
	//�ж������Ƿ���� 
	if(networkInfo!=null){ 
	flag=true; 
	} 
	return flag; 
	} 
	
}
