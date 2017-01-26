package thel0neshadow.daijoubou_clock;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Date;

public class MainActivity extends Activity
{
    //to make our alarm manager
    AlarmManager alarmManager;
    TextView updateText;
    Context context;
    PendingIntent pendingIntent;
    int hour, minute;
    int intentId = 0;
    volatile boolean alarmSet;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;    //????

        //initialise our alarmManager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //initialise our text update box
        updateText = (TextView) findViewById(R.id.update_text);

        //create an instance of a calendar
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        //create intent to the AlarmReceiver class
        final Intent intent = new Intent(this.context, AlarmReceiver.class);

        //initialise start button
        Button startAlerts = (Button) findViewById(R.id.alerts_on);

        //create onClick listener to start alerts
        startAlerts.setOnClickListener(new View.OnClickListener()
        {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public void onClick(View v)
            {
                //setting calendar instance with the hour from system
                //get the int values of the hour
                String timeString
                        = DateFormat.getTimeInstance().format(new Date());
                String hourString, minuteString;

                hourString = timeString.substring(0, 2);
                minuteString = timeString.substring(3, 5);


//                Log.d("Time ", timeString);
//                Log.d("Hour ", hourString);
//                Log.d("Minute ", minuteString);

                if(hourString.contains(":"))
                {
                    hourString = hourString.substring(0, 1);
                }

                hour = Integer.parseInt(hourString);
                hour++;
                minute = Integer.parseInt(minuteString);

                if(timeString.contains("PM")) //account for lack of 24hr
                    hour += 12;

                if(!alarmSet)
                {
                    calendar.set(Calendar.HOUR_OF_DAY, (hour));
                    calendar.set(Calendar.MINUTE, 0);
                    Log.d("Calendar ", "set");
                    alarmSet = true;
                }

                //method that changes the updateText textbox
                updateText("Daijoubou On!");

                //put in extra string into intent
                //tells the clock that you pressed the "daijoubou on" button
                intent.putExtra("extra", "alerts on");

                //tells clock what sound file to play - pass hour
                intent.putExtra("sound", hour);

                //create a pending intent that delays the intent
                //until the specified calendar time
                intentId++;
                pendingIntent = PendingIntent.getBroadcast(MainActivity.this, intentId,
                      intent, PendingIntent.FLAG_CANCEL_CURRENT);

                //set the alarm manager
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_HOUR, pendingIntent);
                alarmSet = true;
            }
        });

        //initialise stop button
        Button stopAlerts = (Button) findViewById(R.id.alerts_off);
        //create onClick listener to stop alerts or undo alerts set
        stopAlerts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //method that changes the updateText textbox
                updateText("Daijoubou Off!");

                //cancel intent
                if(alarmSet)
                {
                    //due to some weird behaviour with destroying the app and reopening it
                    //setting a dummy alarm and stopping it seems to fix it
                    //set dummy alarm
                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                            AlarmManager.INTERVAL_HOUR, pendingIntent);
                    //then cancel
                    alarmManager.cancel(pendingIntent);
                    pendingIntent.cancel();
                }

//                //put in extra string into intent
//                //tells the clock that you pressed the "daijoubou off" button
                intent.putExtra("extra", "alerts off");
//
//                //also put int into daijoubou off section
//                //to prevent crashing in a Null Pointer Exception
                intent.putExtra("sound", hour);

                //stop the ringtone
                sendBroadcast(intent);
                alarmSet = false;
//                Log.d("Alarm: ", "unset");

            }
        });
    }

    private void updateText(String output)
    {
        updateText.setText(output);
    }

}
