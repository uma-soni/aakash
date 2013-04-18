package pro.event;

import java.lang.reflect.Field;
import java.util.ArrayList;

import android.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlarmService extends Service
{

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	/*	Log.d("AlarmService", "Service created..");
		
		//Toast display = Toast.makeText(this, "ALARM is ringing !!", Toast.LENGTH_LONG);
		//display.show();
      
		MediaPlayer mPlayer = MediaPlayer.create(this,pro.event.R.raw.alarmclock);
		mPlayer.start();
		
		//Dialog d = new Dialog(this);
		//d.setTitle("Alarm is ringing");
		//TextView tv = new TextView(this);
		//tv.setText("ALARM !!!!!!!!!!!!!!!!!!");
		//d.show();
		//d.setContentView(tv);
		*/
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.d("AlarmService", "Service created..");
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//TODO Auto-generated method stub
		
		
		//int Hours = intent.getIntExtra("Hour", -1);
		//int Minutes = intent.getIntExtra("Minute", -1);
		
		Bundle bundleHours = intent.getExtras();
		int Hours = intent.getIntExtra("EventPro.pro.event.Hour" , -1);
		int Minutes = intent.getIntExtra("EventPro.pro.event.Minute",-1);
		
		Log.i("Alarm at", "" + Hours);
		Log.i("Alarm at", "" + Minutes);
	
		Database database = new Database(AlarmService.this);
		database.open();
		
		ArrayList<String> Name = database.getEventName(Hours , Minutes);
		ArrayList<String> Type = database.getEventType(Hours , Minutes);
		ArrayList<String> Priority = database.getEventPriority(Hours , Minutes);
		
		database.close();
		String nameTypeString = "";
		
		for(int i=0;i<Name.size();i++)
		{
			Log.i("Name" + i , Name.get(i));
			Log.i("Type" + i , Type.get(i));
			nameTypeString = nameTypeString + Name.get(i) + " " + Type.get(i) + " " + Priority.get(i) + "\n" ;
		}
		
		MediaPlayer mPlayer = MediaPlayer.create(this,pro.event.R.raw.alarmclock);
		mPlayer.start();
		
		NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification notification = new Notification(pro.event.R.drawable.abc , "Alarm", System.currentTimeMillis());
		
		Intent shwAlarm = new Intent(this.getApplicationContext() , ShowAlarm.class);
				
		PendingIntent pendingIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, shwAlarm, 0);
		notification.setLatestEventInfo(this, "Event Pro",nameTypeString, pendingIntent);
	
		notification.flags = Notification.FLAG_NO_CLEAR;
		int NOTIFICATION_ID = 2;
        nm.notify(NOTIFICATION_ID, notification);
        
		return super.onStartCommand(intent, flags, startId);
	}
}