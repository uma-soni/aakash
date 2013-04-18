package pro.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FilterDates extends Activity {


    public static int today=0;
    public static int tomorrow=0;
    public static int thisweek=0;
    public static int all=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filterdates);
	}
	public void filterclick(View v)
	{
		switch(v.getId())
		{
		case R.id.TodayButton:today=1;
							  startActivity(new Intent("pro.event.FILTERPROCESS"));
							  this.finish();
		                      break;
		case R.id.TomorrowButton:tomorrow=1;
								 startActivity(new Intent("pro.event.FILTERPROCESS"));
								 this.finish();
			                     break;
		case R.id.ThisWeekButton:thisweek=1;
								 startActivity(new Intent("pro.event.FILTERPROCESS"));
								 this.finish();
			                     break;
		case R.id.AllButton:all=1;
							startActivity(new Intent("pro.event.FILTERPROCESS"));
							this.finish();
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
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		super.finish();
	}
}
