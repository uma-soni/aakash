package pro.event;

import java.util.Calendar;
import java.util.TimeZone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class Alarm extends BroadcastReceiver
{
	static Calendar cal;
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		context.startService(new Intent(context , AlarmService.class));
		Log.d("NOTE","Entered Receiver..");
		
		/*if(intent.getAction().compareTo(Intent.ACTION_BOOT_COMPLETED) == 0)
		{
			context.startService(new Intent(context , AlarmService.class));
			Log.d("Received", "ACTION_BOOT_COMPLETED");
		}
		else if(intent.getAction().compareTo(Intent.ACTION_TIME_TICK) == 0)
		{
			Log.d("Received", "ACTION_TIME_TICK");
		}*/
	}
}
