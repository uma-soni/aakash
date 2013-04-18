package pro.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FilterEvent extends Activity{
  
	public static int academics=0;
	public static int anniversary=0;
	public static int birthday=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filterevent);
	}
	public void FilterDatesClick(View v)
	{
		switch(v.getId())
		{
		case R.id.AcademicsButton:academics=1;
								  startActivity(new Intent("pro.event.FILTERDATES"));
								  this.finish();
        						  break;
		case R.id.AnniversaryButton:anniversary=1;
									startActivity(new Intent("pro.event.FILTERDATES"));
									this.finish();
		                            break; 
		case R.id.BirthdayButton:birthday=1;
								 startActivity(new Intent("pro.event.FILTERDATES"));
								 this.finish();
		                         break; 
		case R.id.AllEventsButton:this.finish();
		                          break; 
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

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}

}
