package pro.event;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FilterProcess extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 super.onCreate(savedInstanceState);
		 Database db = new Database(this);
		 ListView lv = (ListView)findViewById(android.R.id.list);
	     ArrayAdapter<String> adapter = null;
		 Log.d("3","before try");
		 try
		 {
		 Log.d("4","in try");
		 db.open();
	     Log.d("5","before getting data");
	     ArrayList<String> listarray = new ArrayList<String>();
	     if(FilterEvent.academics==1 && FilterDates.today==1)
	     {
	            listarray = db.getTodaysData("Academics");
	            FilterEvent.academics=0;
	            FilterDates.today=0;
	     }
	     else if(FilterEvent.anniversary==1 && FilterDates.today==1)
	     {
		        listarray = db.getTodaysData("Anniversary");
		        FilterEvent.anniversary=0;
		        FilterDates.today=0;
	     }
	     else if(FilterEvent.birthday==1 && FilterDates.today==1)
	     {
		        listarray = db.getTodaysData("Birthday");
		        FilterEvent.birthday=0;
		        FilterDates.today=0;
	     }
	     else if(FilterEvent.academics==1 && FilterDates.tomorrow==1)
	     {
		        listarray = db.getTomorrowsData("Academics");
		        FilterEvent.academics=0;
		        FilterDates.tomorrow=0;
	     }
	     else if(FilterEvent.anniversary==1 && FilterDates.tomorrow==1)
	     {
		        listarray = db.getTomorrowsData("Anniversary");
		        FilterEvent.anniversary=0;
		        FilterDates.tomorrow=0;
	     }
	     else if(FilterEvent.birthday==1 && FilterDates.tomorrow==1)
	     {
		        listarray = db.getTomorrowsData("Birthday");
		        FilterEvent.birthday=0;
		        FilterDates.tomorrow=0;
	     }
	     else if(FilterEvent.academics==1 && FilterDates.thisweek==1 )
	     {
		    	 listarray = db.getThisWeeksData("Academics");
		    	 FilterEvent.academics=0;
		    	 FilterDates.thisweek=0;
	     }
	     else if(FilterEvent.anniversary==1 && FilterDates.thisweek==1 )
	     {
		    	 listarray = db.getThisWeeksData("Anniversary");
		    	 FilterEvent.anniversary=0;
		    	 FilterDates.thisweek=0;
	     }
	     else if(FilterEvent.birthday==1 && FilterDates.thisweek==1 )
	     {
		    	 listarray = db.getThisWeeksData("Birthday");
		    	 FilterEvent.birthday=0;
		    	 FilterDates.thisweek=0;
	     }
	     else if(FilterEvent.academics==1 && FilterDates.all==1)
	     {
		         listarray = db.getAllData("Academics");
		         FilterEvent.academics=0;
		         FilterDates.all=0;
	     }
	     else if(FilterEvent.anniversary==1 && FilterDates.all==1)
	     {
		         listarray = db.getAllData("Anniversary");
		         FilterEvent.anniversary=0;
		         FilterDates.all=0;
	     }
	     else if(FilterEvent.birthday==1 && FilterDates.all==1)
	     {
		         listarray = db.getAllData("Birthday");
		         FilterEvent.birthday=0;
		         FilterDates.all=0;
	     }
		 adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listarray);
	     Log.d("6","after getting data");
		 for(int i=0;i<listarray.size();i++)
		 {
		  Log.d("list",listarray.get(i));
		 }
		 Log.d("10","before setting adapter");
		 Log.d("adapter",""+adapter.getCount());
		 Log.d("11","after setting adapter");
         db.close();
		}catch(Exception e)
		{
			Log.d("Exception",e.toString());
		}
		finally{
			Log.d("7","Before finally");
			setListAdapter(adapter);
		    setContentView(R.layout.listview);
			Log.d("8","After finally");
		}
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
}
