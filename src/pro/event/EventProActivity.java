package pro.event;

import pro.event.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class EventProActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    public void click(View view)
    {
    	switch(view.getId())
    	{
    	 case R.id.buttonload: startActivity(new Intent("pro.event.FIRSTSCREEN"));
    		 				   this.finish();
    		 				   break;
    	}
    }
}