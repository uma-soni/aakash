package pro.event;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ShowAlarm extends Activity {

	String name , phoneNumber;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nm.cancel(2);
		
		final AlertDialog.Builder alarmDialog = new AlertDialog.Builder(this); 
		
		alarmDialog.setPositiveButton("Call", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
				Intent contactIntent = new Intent(Intent.ACTION_PICK ,ContactsContract.Contacts.CONTENT_URI);
				//startActivityForResult(contactIntent, PICK_CONTACT);
				((DialogInterface) alarmDialog).dismiss();
			} }); 
				
		alarmDialog.setNegativeButton("Message", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// allow the user to send a message
				((DialogInterface) alarmDialog).dismiss();
			} });
		
		String show[]={"data retrieved from database"};
		alarmDialog.setCancelable(true);
		alarmDialog.setItems(show, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int item)
		    {
		
		    }
		});
		alarmDialog.setTitle("Alert Created !!");
		alarmDialog.show();
		Log.d("SHOWALARM..", "Reached till here..");
	}
	//@Override
	/*protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, intent);
		
		if(requestCode == PICK_CONTACT)
		{
			getContactInfo(intent);
		}
	}
	private void getContactInfo(Intent intent) {
		// TODO Auto-generated method stub
		Cursor cursor =  managedQuery(intent.getData(), null, null, null, null);      
		   while (cursor.moveToNext()) 
		   {           
		       String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
		       name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)); 

		       String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

		       if ( hasPhone.equalsIgnoreCase("1"))
		           hasPhone = "true";
		       else
		           hasPhone = "false" ;
		       if (Boolean.parseBoolean(hasPhone)) 
		       {
		        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId,null, null);
		        while (phones.moveToNext()) 
		        {
		          phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		        }
		        phones.close();
		       }
		   } */
	//}
}