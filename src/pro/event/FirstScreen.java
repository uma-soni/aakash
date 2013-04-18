package pro.event;

import pro.event.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FirstScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstscreen);
    }
    public void clickHandler(View view)
    {
    	switch(view.getId())
    	{
    	case R.id.goToCalendar : startActivity(new Intent("pro.event.CALENDAR"));
			                 break;
    	 case R.id.AddEvent : startActivity(new Intent("pro.event.ADDEVENT"));
    		 				break;
    	 case R.id.viewEvent:startActivity(new Intent("pro.event.VIEWEVENT"));
    	 				     break;
    	}
    }
}