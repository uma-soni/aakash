package pro.event;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewEvent extends ListActivity {
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>()
        protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
    		super.onCreate(savedInstanceState);
        Log.d("1","Entered");
		//setContentView(R.layout.list_view);
	     Log.d("2","set layout");
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
	     listarray = db.getData();
	     adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listarray);
	     Log.d("6","after getting data");
		for(int i=0;i<listarray.size();i++)
		{
		 Log.d("list",listarray.get(i));
		}
		Log.d("10","before setting adapter");
		Log.d("adapter",""+adapter.getCount());
		
		Log.d("11","after setting adapter");
		//Log.d("adapter",""+adapter.getCount());
		 db.close();
		}catch(Exception e)
		{
			Log.d("Exception",e.toString());
		}
		finally{
			Log.d("7","Before finally");
			setListAdapter(adapter);
		    setContentView(R.layout.list_view);
			Log.d("8","After finally");
		}
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	public void FilterEventClick(View v)
	{
		switch(v.getId())
		{
		  case R.id.FilterEventButton:startActivity(new Intent("pro.event.FILTEREVENT"));
			                          break; 
		}
	}
}
