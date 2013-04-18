package pro.event;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.content.ContentValues;
import android.text.format.Time;
import android.util.Log;
import android.widget.DatePicker;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Database 
{
	private static final String DATABASE_NAME = "EventApps1";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE = "EventTab1";
	private static final String EVENT_ID = "_id";
	private static final String EVENT_TITLE = "Title";
	private static final String EVENT_TYPE = "Type";
	private static final String EVENT_DATE = "Date";
	private static final String EVENT_MONTH = "Month";
	private static final String EVENT_YEAR = "Year";
	private static final String EVENT_PRIORITY = "Priority";
	private static final String EVENT_HOUR = "Hour";
	private static final String EVENT_MINUTE = "Minute";
	private static final String EVENT_OCCURRENCE = "Occurrence";
	
	private static final String INBUILTEVENT_TABLE = "InBuiltEventsTab1";
	private static final String INBUILT_ID = "_id";
	private static final String EVENT_IMAGE = "Image";	
	
	private dbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;
	int Month;
	private static class dbHelper extends SQLiteOpenHelper
	{
		public dbHelper(Context context)
		{
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
		  db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
				      EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				      EVENT_TITLE + " TEXT, " + EVENT_TYPE + " TEXT, " + 
				      EVENT_DATE + " INTEGER, " + EVENT_MONTH + " INTEGER, " + EVENT_YEAR + " INTEGER, " + EVENT_PRIORITY + " INTEGER, " + 
				      EVENT_HOUR + " INTEGER, " + EVENT_MINUTE + " INTEGER, "+ EVENT_OCCURRENCE + " TEXT);");
		  
		  db.execSQL("CREATE TABLE " + INBUILTEVENT_TABLE + " (" +
			      INBUILT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			      EVENT_TITLE + " TEXT, " + EVENT_DATE + " INTEGER, " +
			      EVENT_MONTH + " INTEGER, " + EVENT_IMAGE + " TEXT);");
	  db.execSQL("INSERT INTO " + INBUILTEVENT_TABLE + " VALUES(null,'Republic Day',26,1,'republicday');");
	  db.execSQL("INSERT INTO " + INBUILTEVENT_TABLE + " VALUES(null,'Independence Day',15,8,'independenceday');");
	  db.execSQL("INSERT INTO " + INBUILTEVENT_TABLE + " VALUES(null,'Maharashtra Day',1,5,'maharashtraday');");
	  db.execSQL("INSERT INTO " + INBUILTEVENT_TABLE + " VALUES(null,'Teachers Day',5,9,'teachersday');");
	  db.execSQL("INSERT INTO " + INBUILTEVENT_TABLE + " VALUES(null,'Gandhi Jayanti',2,10,'gandhijayanti');");
	  db.execSQL("INSERT INTO " + INBUILTEVENT_TABLE + " VALUES(null,'Childrens Day',14,11,'childrensday');");
	  db.execSQL("INSERT INTO " + INBUILTEVENT_TABLE + " VALUES(null,'Christmas',25,12,'christmas');");
	  db.execSQL("INSERT INTO " + INBUILTEVENT_TABLE + " VALUES(null,'New Year',1,1,'newyear');");
	}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + INBUILTEVENT_TABLE);
			onCreate(db);
		}
	
	}
	public Database(Context c)
	{
		ourContext = c;
	}
	public void open()throws SQLiteException
	{
		ourHelper = new dbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
	}
	public void close()
	{
		ourHelper.close();
	}
	public long createEntry(String title,String type,int date,int month,int year,String priority,int hour,int min,String occurrence)
	{
		ContentValues cv = new ContentValues();
		
		cv.put(EVENT_TITLE, title);
		cv.put(EVENT_TYPE, type);
		cv.put(EVENT_DATE, date);
		cv.put(EVENT_MONTH, month);
		cv.put(EVENT_YEAR, year);
		cv.put(EVENT_PRIORITY, priority);
		cv.put(EVENT_HOUR,hour);
		cv.put(EVENT_MINUTE,min);
		cv.put(EVENT_OCCURRENCE, occurrence);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	public ArrayList<String> getData()
	{
		int i;
		final ArrayList<String> data = new ArrayList<String>(); 
		String[] columns = new String[]{EVENT_TITLE,EVENT_TYPE,EVENT_DATE,EVENT_MONTH,EVENT_YEAR,EVENT_PRIORITY,EVENT_HOUR,EVENT_MINUTE,EVENT_OCCURRENCE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns , null , null , null , null , null);   // 2nd argument tells which columns to return.
		
		
		
		int iTitle = c.getColumnIndex(EVENT_TITLE);
		int iType = c.getColumnIndex(EVENT_TYPE);
		int iDate = c.getColumnIndex(EVENT_DATE);
		int iMonth = c.getColumnIndex(EVENT_MONTH);
		
		int iYear = c.getColumnIndex(EVENT_YEAR);
		int iPriority = c.getColumnIndex(EVENT_PRIORITY);
		int iHour = c.getColumnIndex(EVENT_HOUR);
		int iMin = c.getColumnIndex(EVENT_MINUTE);
		int iOccur = c.getColumnIndex(EVENT_OCCURRENCE);
		for(c.moveToFirst(); !c.isAfterLast() ; c.moveToNext())
		{
			data.add(c.getString(iTitle) + " " + c.getString(iType) + " \n" + c.getString(iDate) + "-" + c.getString(iMonth) + "-" + c.getString(iYear) + " " + c.getString(iHour) + ":" + c.getString(iMin));
		}
		c.close();
		return(data);
	}
	public ArrayList<String> getEventName(int hours, int minutes) {
		// TODO Auto-generated method stub
	
		final ArrayList<String> data = new ArrayList<String>();
		String[] columns = new String[]{EVENT_TITLE};
		//String select = ("SELECT " +  EVENT_TITLE + " FROM " + DATABASE_TABLE + " WHERE "+ "(" + EVENT_HOUR + " = " + hours + " AND " + EVENT_MINUTE + " = " + minutes + " )" );
		String select = (EVENT_HOUR + " = " + hours + " AND " + EVENT_MINUTE + " = " + minutes);
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, select , null , null , null , null);   // 2nd argument tells which columns to return.
		//Cursor c = ourDatabase.rawQuery(select, columns);
		int iTitle = c.getColumnIndex(EVENT_TITLE) ;
		
		for(c.moveToFirst() ; !c.isAfterLast() ; c.moveToNext())
		{
			data.add(c.getString(iTitle));
			Log.d("Got Name" , c.getString(iTitle));
		}
		return data;
	}
	public ArrayList<String> getEventType(int hours, int minutes) {
		// TODO Auto-generated method stub

		final ArrayList<String> data = new ArrayList<String>();
		String[] columns = new String[]{EVENT_TYPE};
		//String select = ("SELECT " +  EVENT_TYPE + " FROM " + DATABASE_TABLE + " WHERE "+ "(" + EVENT_HOUR + " = " + hours + " AND " + EVENT_MINUTE + " = " + minutes + " )" );
		String select = (EVENT_HOUR + " = " + hours + " AND " + EVENT_MINUTE + " = " + minutes);
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, select , null , null , null , null);   // 2nd argument tells which columns to return.
		//Cursor c = ourDatabase.rawQuery(select, columns);
		
		int iType = c.getColumnIndex(EVENT_TYPE) ;
		
		for(c.moveToFirst() ; !c.isAfterLast() ; c.moveToNext())
		{
			data.add(c.getString(iType));
			Log.d("Got Title" , c.getString(iType));
		}
		return data;
	}
	
	public ArrayList<String> getEventPriority(int hours, int minutes) {

		// TODO Auto-generated method stub

		final ArrayList<String> data = new ArrayList<String>();
		String[] columns = new String[]{EVENT_PRIORITY};
		//String select = ("SELECT " +  EVENT_TYPE + " FROM " + DATABASE_TABLE + " WHERE "+ "(" + EVENT_HOUR + " = " + hours + " AND " + EVENT_MINUTE + " = " + minutes + " )" );
		String select = (EVENT_HOUR + " = " + hours + " AND " + EVENT_MINUTE + " = " + minutes);
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, select , null , null , null , null);   // 2nd argument tells which columns to return.
		//Cursor c = ourDatabase.rawQuery(select, columns);
		
		int iPriority = c.getColumnIndex(EVENT_PRIORITY) ;
		
		for(c.moveToFirst() ; !c.isAfterLast() ; c.moveToNext())
		{
			data.add(c.getString(iPriority));
			Log.d("Got Title" , c.getString(iPriority));
		}
		return data;

		
		
	}

	public ArrayList<String> getEvents(int dayOfMonth,int month,int year)
	{
		ArrayList<String> data = new ArrayList<String>();
		String[] columns = new String[]{EVENT_TITLE,EVENT_TYPE,EVENT_DATE,EVENT_MONTH,EVENT_YEAR};
		Log.d("get events query",EVENT_DATE + " = " + dayOfMonth + " AND " + EVENT_MONTH + " = " + month + " AND " + EVENT_YEAR + " = " + year);
		Cursor events = ourDatabase.query(DATABASE_TABLE, columns, EVENT_DATE + " = " + dayOfMonth + " AND " + EVENT_MONTH + " = " + month + " AND " + EVENT_YEAR + " = " + year, null, null, null, null);
		int iTitle = events.getColumnIndex(EVENT_TITLE);
		int iType = events.getColumnIndex(EVENT_TYPE);
		for(events.moveToFirst(); !events.isAfterLast(); events.moveToNext())
		{
			data.add(events.getString(iTitle) + " " + events.getString(iType) + "\n");
		}
		events.close();
		return data;
	}
	public ArrayList<String> getInbuiltEvents(int dayOfMonth,int month)
	{
		ArrayList<String> event = new ArrayList<String>();
		String []col = new String[]{EVENT_TITLE,EVENT_DATE,EVENT_MONTH};
		Cursor c;
		c = ourDatabase.query(INBUILTEVENT_TABLE, col, EVENT_DATE + " = " + dayOfMonth + " AND " + EVENT_MONTH + " = " + month, null, null, null, null);
		int iTitle = c.getColumnIndex(EVENT_TITLE);
		int iDate = c.getColumnIndex(EVENT_DATE);
		int iMonth = c.getColumnIndex(EVENT_MONTH);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext())
		 event.add(c.getString(iTitle) + " " + c.getString(iDate) + " " + c.getString(iMonth));
		c.close();
		return event;
	}
	public String getImage(int dayOfMonth,int month)
	{
		String image = null;
		Cursor c;
		c = ourDatabase.query(INBUILTEVENT_TABLE, new String[]{EVENT_IMAGE},EVENT_DATE + " = " + dayOfMonth + " AND " + EVENT_MONTH + " = " + month , null, null, null, null);
		c.moveToFirst();
		if(c.getCount()!=0)
		{
			int iImage = c.getColumnIndex(EVENT_IMAGE);
		    image = c.getString(iImage);
		}
		return image;
	}
	public ArrayList<String> getTodaysData(String type)
	{
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		int day=today.monthDay;
		int month=today.MONTH;
		int year=today.YEAR;

		
		final ArrayList<String> data = new ArrayList<String>(); 
		String[] columns = new String[]{EVENT_TITLE,EVENT_TYPE,EVENT_DATE,EVENT_MONTH,EVENT_YEAR,EVENT_PRIORITY,EVENT_HOUR,EVENT_MINUTE,EVENT_OCCURRENCE};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, EVENT_DATE + "=" + day, null , null , null , null);   // 2nd argument tells which columns to return.
		
		int iTitle = c.getColumnIndex(EVENT_TITLE);
		int iType = c.getColumnIndex(EVENT_TYPE);
		int iHour = c.getColumnIndex(EVENT_HOUR);
		int iMin = c.getColumnIndex(EVENT_MINUTE);
		for(c.moveToFirst(); !c.isAfterLast() ; c.moveToNext())
		{
			String str=c.getString(iType);
			 Collator usCollator = Collator.getInstance(Locale.US);
			 usCollator.setStrength(Collator.PRIMARY);
			 if (usCollator.compare(str,type) == 0)
			{
			if(Integer.parseInt(c.getString(iMin))<10)
			    data.add(c.getString(iTitle) + " " + c.getString(iType) + " \n" + " " + c.getString(iHour) + ":" + "0"+ c.getString(iMin));
			else
				data.add(c.getString(iTitle) + " " + c.getString(iType) + " \n" + " " + c.getString(iHour) + ":" + c.getString(iMin));
			}
		}
		c.close();
		return(data);
	}
	public int getNextDay(Time today)
	{
	
		int day=today.monthDay;
		int month=	today.MONTH;
		Log.d("Month",""+month);
		int year=today.YEAR;
		Month=month-1;
        switch(month-1)
        {
        case 1:if(day==31)
        		{
        	     day=1;
        	     if(month<=11)
        	       Month= month+1;
        		}
        		else
        			day = day+1;
                break;
        case 2:if(day==28)
		 		{
        			day=1;
        			if(month<=11)
        				Month= month+1;
		 		}
				else
					day = day+1;
        		break;
        case 3:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
						day = day+1;
        		break; 
        case 4:if(day==30)
				{
        			day=1;		
        			if(month<=11)
        					Month= month+1;
				}
				else
						day = day+1;
        		break;
        case 5:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
						day = day+1;
        		break;
        case 6:if(day==30)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
						day = day+1;
        		break;
        case 7:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
						day = day+1;
        		break;
        case 8:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break;
        case 9:if(day==30)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break;
        case 10:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break;
        case 11:if(day==30)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}	
				else
							day = day+1;
        		break;
        case 12:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
        			else
        					Month=1;
				}
				else
							day = day+1;
        		break;
        		}
				return day;
	}
	public int getThisWeekDay(Time today)
	{
		int day=today.monthDay;
		int month=	today.MONTH;
		Month=month-1;
		for(int i=1;i<=7;i++)
		{
        switch(month-1)
        {
        case 1:if(day==31)
        		{
        	     day=1;
        	     if(month<=11)
        	       Month= month+1;
        		}
        		else
        			day = day+1;
               break;
        case 2:if(day==28)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break;
        case 3:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break; 
        case 4:if(day==30)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break;	
        case 5:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break;
        case 6:if(day==30)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break;
        case 7:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break;
        case 8:if(day==31)
        		{
        			day=1;
        			if(month<=11)
        					Month= month+1;
        		}
				else
							day = day+1;
        		break;
        case 9:if(day==30)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break;
        case 10:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
				}
				else
							day = day+1;
        		break;
        case 11:if(day==30)
        		{
        			day=1;
        			if(month<=11)
        					Month= month+1;
        		}
				else
							day = day+1;
        		break;
        case 12:if(day==31)
				{
        			day=1;
        			if(month<=11)
        					Month= month+1;
        			else
        					Month=1;
				}	
				else
							day = day+1;
        		break;
        }
	}
	return day;
	}
	public ArrayList<String> getTomorrowsData(String type)
	{
		int i;
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		Log.d("In funct month",""+today.MONTH);
		int day = getNextDay(today);
        Log.d("Tomorrow",""+day);
        Log.d("Tomorrow month",""+Month);
		final ArrayList<String> data = new ArrayList<String>(); 
		String[] columns = new String[]{EVENT_TITLE,EVENT_TYPE,EVENT_DATE,EVENT_MONTH,EVENT_YEAR,EVENT_PRIORITY,EVENT_HOUR,EVENT_MINUTE,EVENT_OCCURRENCE};
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, EVENT_DATE + "=" + day, null , null , null , null);   // 2nd argument tells which columns to return.
		
		int iTitle = c.getColumnIndex(EVENT_TITLE);
		int iType = c.getColumnIndex(EVENT_TYPE);
		int iHour = c.getColumnIndex(EVENT_HOUR);
		int iMin = c.getColumnIndex(EVENT_MINUTE);
		int iMonth = c.getColumnIndex(EVENT_MONTH);

		for(c.moveToFirst(); !c.isAfterLast() ; c.moveToNext())
		{
			String str=c.getString(iType);
			 Collator usCollator = Collator.getInstance(Locale.US);
			 usCollator.setStrength(Collator.PRIMARY);
			 if (usCollator.compare(str,type) == 0 && Integer.parseInt(c.getString(iMonth))==Month)
			{
			if(Integer.parseInt(c.getString(iMin))<10)
			    data.add(c.getString(iTitle) + " " + c.getString(iType) + " \n" + " " + c.getString(iHour) + ":" + "0"+ c.getString(iMin));
			else
				data.add(c.getString(iTitle) + " " + c.getString(iType) + " \n" + " " + c.getString(iHour) + ":" + c.getString(iMin));
			}
		}
		c.close();
		return(data);
	}
	public ArrayList<String> getThisWeeksData(String type)
	{
		int i;
		Time today = new Time(Time.getCurrentTimezone());
		today.setToNow();
		int day = getThisWeekDay(today);

		final ArrayList<String> data = new ArrayList<String>(); 
		String[] columns = new String[]{EVENT_TITLE,EVENT_TYPE,EVENT_DATE,EVENT_MONTH,EVENT_YEAR,EVENT_PRIORITY,EVENT_HOUR,EVENT_MINUTE,EVENT_OCCURRENCE};
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, EVENT_DATE + "<=" + day, null , null , null , null);   // 2nd argument tells which columns to return.
		
		int iTitle = c.getColumnIndex(EVENT_TITLE);
		int iType = c.getColumnIndex(EVENT_TYPE);
		int iHour = c.getColumnIndex(EVENT_HOUR);
		int iMin = c.getColumnIndex(EVENT_MINUTE);
		int iMonth = c.getColumnIndex(EVENT_MONTH);
		for(c.moveToFirst(); !c.isAfterLast() ; c.moveToNext())
		{
			String str=c.getString(iType);
			 Collator usCollator = Collator.getInstance(Locale.US);
			 usCollator.setStrength(Collator.PRIMARY);
			 if (usCollator.compare(str,type) == 0 && Integer.parseInt(c.getString(iMonth))==Month)
			 {
				 	if(Integer.parseInt(c.getString(iMin))<10)
				 		data.add(c.getString(iTitle) + " " + c.getString(iType) + " \n" + " " + c.getString(iHour) + ":" + "0"+ c.getString(iMin));
				 	else
				 		data.add(c.getString(iTitle) + " " + c.getString(iType) + " \n" + " " + c.getString(iHour) + ":" + c.getString(iMin));
			 }
		}
		c.close();
		return(data);
	}
	public ArrayList<String> getAllData(String type)
	{
		final ArrayList<String> data = new ArrayList<String>(); 
		String[] columns = new String[]{EVENT_TITLE,EVENT_TYPE,EVENT_DATE,EVENT_MONTH,EVENT_YEAR,EVENT_PRIORITY,EVENT_HOUR,EVENT_MINUTE,EVENT_OCCURRENCE};
		
	    Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null , null , null , null , null);   // 2nd argument tells which columns to return.
		
		int iTitle = c.getColumnIndex(EVENT_TITLE);
		int iType = c.getColumnIndex(EVENT_TYPE);
		int iHour = c.getColumnIndex(EVENT_HOUR);
		int iMin = c.getColumnIndex(EVENT_MINUTE);
		
		for(c.moveToFirst(); !c.isAfterLast() ; c.moveToNext())
		{
			 String str=c.getString(iType);
			 Collator usCollator = Collator.getInstance(Locale.US);
			 usCollator.setStrength(Collator.PRIMARY);
			 if (usCollator.compare(str,type) == 0)
			  {
				 if(Integer.parseInt(c.getString(iMin))<10)
					 data.add(c.getString(iTitle) + " " + c.getString(iType) + " \n" + " " + c.getString(iHour) + ":" + "0"+ c.getString(iMin));
				 else
					 data.add(c.getString(iTitle) + " " + c.getString(iType) + " \n" + " " + c.getString(iHour) + ":" + c.getString(iMin));
			  }
		}
		c.close();
		return(data);
	}

}
