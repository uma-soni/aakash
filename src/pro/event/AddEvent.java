package pro.event;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

public class AddEvent extends Activity 
{
	EditText title , priority ;
	DatePicker dp;
	TimePicker tp;
	RadioGroup rgType , rgOccur;
	String type,occur;
	Calendar calendar;
	static int variable;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addevent);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	 public void eventHandler(View view)
	    {
	    	switch(view.getId())
	    	{
	    	 case R.id.SubmitButton : title = (EditText)findViewById(R.id.editTitle);
	    	 						  rgType = (RadioGroup)findViewById(R.id.groupType);
	    	 						  rgOccur = (RadioGroup)findViewById(R.id.groupOccur);
	    	 						  priority = (EditText)findViewById(R.id.editPriority);
	    	 						  dp = (DatePicker)findViewById(R.id.datePicker);
	    	 						  tp = (TimePicker)findViewById(R.id.timePicker);
	    	 						 
	    	 						 RadioButton temp1 = (RadioButton)findViewById(rgType.getCheckedRadioButtonId());
	    	 						
	    	 						 int year = dp.getYear();
	    	 						 int date = dp.getDayOfMonth();
	    	 						 int month = dp.getMonth() + 1;
	    	 						 
	    	 						RadioButton temp2 = (RadioButton)findViewById(rgOccur.getCheckedRadioButtonId());
    	 						    boolean flag = true;
	    	 						try{
	    	 							 String titletext=title.getText().toString();
	    	 							 type = (String) temp1.getText();
	    	 							 occur = (String) temp2.getText();
	    	 							 String prioritytext=priority.getText().toString();
	    	 							
	    	 							 int hour = tp.getCurrentHour();
	    	 							 int minute = tp.getCurrentMinute();
	    	 							
	    	 							String tptext=tp.toString();
	    	 							Database database= new Database(AddEvent.this);
	    	 							database.open();
	    	 							database.createEntry(titletext, type, date,month,year, prioritytext, hour, minute, occur);    	 							
	    	 							database.close();
	    	 							
	    	 							// Setting up the Alarm
	    	 							
	    	 							calendar = Calendar.getInstance();
    	 								Date alarmTime = new Date(System.currentTimeMillis());
    	 								//Date alarmTime1 = new Date();                                    is sufficient
    	 								alarmTime.setDate(dp.getDayOfMonth());
    	 								alarmTime.setMonth(dp.getMonth());
    	 								alarmTime.setSeconds(0);
    	 								alarmTime.setHours(hour);
    	 								alarmTime.setMinutes(minute);
	    	 							calendar.setTimeInMillis(alarmTime.getTime());
	    	 							Intent alrmService = new Intent(getApplicationContext() , AlarmService.class);
	    	 							alrmService.putExtra("EventPro.pro.event.Hour", hour);
	    	 							alrmService.putExtra("EventPro.pro.event.Minute", minute);
	    	 							
	    	 							PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, alrmService ,PendingIntent.FLAG_UPDATE_CURRENT);
	    	 						 	 							
	    	 							//int Hours = alrmService.getIntExtra("", defaultValue);
	    	 								    	 							
	    	 							AlarmManager alarms = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
	    	 					        alarms.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent );
	    	 					  
	    	 					        
	    	 						}
	    	 						catch(Exception e)
	    	 						{	
	    	 							flag = false;	    	 							
	    	 							e.printStackTrace();
	    	 						}
	    	 						finally
	    	 						{
	    	 							Dialog d = new Dialog(this);
	    	 							d.setTitle("Event Saved");
	    	 							TextView tv = new TextView(this);
	    	 							if(flag)
	    	 							{
	    	 								
	    	 								//cal.set(Calendar.YEAR, dp.getYear());
	    	 								//cal.set(Calendar.MONTH, dp.getMonth());
	    	 								//cal.set(Calendar.DAY_OF_MONTH, dp.getDayOfMonth());
	    	 								//cal.set(Calendar.HOUR_OF_DAY, tp.getCurrentHour());
	    	 								//cal.set(Calendar.MINUTE,tp.getCurrentMinute());
	    	 								//cal.set(Calendar.SECOND, 0);
	    	 								//Intent intent = new Intent(AddEvent.this,Alarm.class);
	    	 								//intent.putExtra("alarm !!!", "EventPro" );
	    	 								//PendingIntent alarmIntent = PendingIntent.getBroadcast(AddEvent.this, variable, intent,PendingIntent.FLAG_UPDATE_CURRENT);
	    	 								//variable++;
	    	 								//AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);	    	 								
	    	 								//	am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
	    	 								//   	tv.setText("Event saved !!!");
	    	 								 
	    	 							}
	    	 							else
	    	 								tv.setText("Operation Failure");
	    	 							d.show();
	    	 							d.setContentView(tv);
	    	 							this.finish();
	    	 						}
	    	 						
	    	 						break;
	  /*  	 case R.id.CancelButton : startActivity(new Intent("pro.event.SHOWCALENDAR"));
	    	 				          break;*/
	    	}
	    }
}
