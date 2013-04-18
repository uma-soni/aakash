package pro.event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.graphics.drawable.Drawable;
import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

public class MyCalendar extends Activity{
    CalendarView cv;
    Cursor c;
	ArrayList<String> events = new ArrayList<String>();
	ArrayList<String> in_built = new ArrayList<String>();
	ArrayList<String> user = new ArrayList<String>();
    ArrayAdapter<String> eventAdapter = null;
    ListView eventView;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		cv = (CalendarView)findViewById(R.id.calendarView);
		eventView = (ListView)findViewById(R.id.calendarList);
		Calendar cal = new GregorianCalendar();
		Database db = new Database(MyCalendar.this);
		db.open();
		/*test = db.getInbuiltEvents();
		Log.d("in-built events size",""+test.size());
		for(int i = 0 ; i<test.size() ; i++)
			Log.d("In-built Events",test.get(i));
		Log.d("check",db.getImage(26, 1));*/
		String im = db.getImage(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1);
		if(im!=null)
		{
			Drawable image = getResources().getDrawable(getResources().getIdentifier(im, "drawable", getPackageName()));
			cv.setBackgroundDrawable(image);
		}
		else
			cv.setBackgroundDrawable(null);
		in_built = db.getInbuiltEvents(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1);
		user = db.getEvents(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH)+1,cal.get(Calendar.YEAR));
		events.addAll(in_built);
		events.addAll(user);
		Log.d("calendar", ""+events.size());
		db.close();
		if(events.isEmpty())
			events.add("No Events");
	    eventAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,events);
	    Log.d("calendar","before setting adapter");
		eventView.setAdapter(eventAdapter);
		Log.d("calendar","after setting adapter");
		cv.setOnDateChangeListener(myDateChangeListener);
	}
    @Override
	public Resources getResources() {
		// TODO Auto-generated method stub
		return super.getResources();
	}
	private CalendarView.OnDateChangeListener myDateChangeListener = 
			new CalendarView.OnDateChangeListener() {
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			Database db = new Database(MyCalendar.this);
			try
			{
				db.open();
				events.clear();
				user.clear();
				in_built.clear();
				eventAdapter.clear();
				in_built = db.getInbuiltEvents(dayOfMonth, month+1);
				user = db.getEvents(dayOfMonth,month+1,year);
				events.addAll(in_built);
				events.addAll(user);
				Log.d("calendar", ""+events.size());
				if(events.isEmpty())
					events.add("No Events");
				//eventAdapter.addAll(events);
		
				db.close();
			}catch(Exception e)
			{
				Log.d("Calendar",e.toString());
			}
		}
	};
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

}
