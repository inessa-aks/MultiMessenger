package com.comp.multimessenger;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
 
public class MainActivity extends Activity {
   
  final String LOG_TAG = "myLogs";
  
  public static String packageName = "com.whatsapp";
  public static PackageManager packageManager;
  public static String trackingActivityName = "Conversation";
  public static TextView textView;

   
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        packageManager = getPackageManager();
        textView = (TextView)findViewById(R.id.textView);
    }

    public void onSendMessage(View v) {
    	try {
    		Intent sendIntent = new Intent(this, MessengerActivity.class)
    			.setPackage(packageName)
                .addCategory(Intent.CATEGORY_LAUNCHER)
                .setAction(Intent.ACTION_SEND)
                .putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                .setType("text/plain");
            startActivity(sendIntent);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
/*    	
    	try {    		
            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";
            PackageInfo info = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA);
            waIntent.setPackage(packageName);
            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Send..."));//фишка тут
       } catch (NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
       }
       */
    }
    
    public void onClickStart(View v) {
      startService(new Intent(this, MonitorService.class));
    }
     
    public void onClickStop(View v) {
      stopService(new Intent(this, MonitorService.class));
    }
}