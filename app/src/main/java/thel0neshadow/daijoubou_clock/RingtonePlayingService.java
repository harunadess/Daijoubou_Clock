package thel0neshadow.daijoubou_clock;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;

import java.util.Date;

/**
 * Created by Jordan on 07/01/2017.
 */

/*
    TODO: make it work, but better
    might implement some form of changing wallpaper or something
    ********************************************
*/

public class RingtonePlayingService extends Service
{

    private MediaPlayer mediaPlayer;
    private int startId;
    private boolean isRunning;
    private int soundId;
    private int intentId;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        //fetch the extra string values
        String state = intent.getExtras().getString("extra");

        //fetch soundId value
        intentId = intent.getExtras().getInt("intent");

        //notification
        //set up the notification service
        NotificationManager notificationManager = (NotificationManager)
            getSystemService(NOTIFICATION_SERVICE);

        //set up an intent that goes to the main activity
        Intent intentMainActivity = new Intent(this.getApplicationContext(), MainActivity.class);

        //set up a pending intent
        PendingIntent pendingIntentMainActivity = PendingIntent.getActivity(this, intentId,
                intentMainActivity, PendingIntent.FLAG_CANCEL_CURRENT);
//        PendingIntent pendingIntentMainActivity = PendingIntent.getActivity(this, 0,
//                intentMainActivity, 0);

        String timeString = DateFormat.getTimeInstance().format(new Date());

        //Get hour from time string
        String hourString = timeString.substring(0, 2);

        //Account for single digit times
        if(hourString.contains(":"))
            hourString = hourString.substring(0, 1);

        //Account for 12 hour clocks
        if(timeString.contains("PM"))
            hourString = "1" + hourString;

        //Make sure soundId that is to be played is the correct hour
        soundId = Integer.parseInt(hourString);

        //put notification here
        //make the notification parameters
        Notification notificationPopup = new Notification.Builder(this)
                .setCategory(Notification.CATEGORY_ALARM)
                .setContentTitle("Teitoku!")
                .setContentText("It's " + hourString + "00 hours! <3")
                .setContentIntent(pendingIntentMainActivity)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setVibrate(new long[] {0, 100, 100, 100})  //{delay, vibrate, sleep, vibrate}
                .setAutoCancel(false)
                .build();

        //this converts the extra strings from the intent
        //to startId value 0 or 1
        assert state != null;
        switch (state)
        {
            case "alerts on":
                startId = 1;
//                Log.e("start ID is ", "" + startId);
                break;
            case "alerts off":
                startId = 0;
//                Log.e("start ID is ","" + startId);
                break;
            default:
                startId = 0;
                break;
        }

        //if else statements
        //if there is no music playing and the user pressed "daijoubou on"
        //music should start playing
        if(!this.isRunning && startId == 1)
        {
            switch (soundId)
            {
            case 0:
                //create instance of the media player + file
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_00hrs);
                mediaPlayer.start();
                break;
            case 1:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_01hrs);
                mediaPlayer.start();
                break;
            case 2:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_02hrs);
                mediaPlayer.start();
                break;
            case 3:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_03hrs);
                mediaPlayer.start();
                break;
            case 4:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_04hrs);
                mediaPlayer.start();
                break;
            case 5:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_05hrs);
                mediaPlayer.start();
                break;
            case 6:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_06hrs);
                mediaPlayer.start();
                break;
            case 7:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_07hrs);
                mediaPlayer.start();
                break;
            case 8:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_08hrs);
                mediaPlayer.start();
                break;
            case 9:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_09hrs);
                mediaPlayer.start();
                break;
            case 10:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_10hrs);
                mediaPlayer.start();
                break;
            case 11:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_11hrs);
                mediaPlayer.start();
                break;
            case 12:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_12hrs);
                mediaPlayer.start();
                break;
            case 13:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_13hrs);
                mediaPlayer.start();
                break;
            case 14:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_14hrs);
                mediaPlayer.start();
                break;
            case 15:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_15hrs);
                mediaPlayer.start();
                break;
            case 16:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_16hrs);
                mediaPlayer.start();
                break;
            case 17:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_17hrs);
                mediaPlayer.start();
                break;
            case 18:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_18hrs);
                mediaPlayer.start();
                break;
            case 19:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_19hrs);
                mediaPlayer.start();
                break;
            case 20:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_20hrs);
                mediaPlayer.start();
                break;
            case 21:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_21hrs);
                mediaPlayer.start();
                break;
            case 22:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_22hrs);
                mediaPlayer.start();
                break;
            case 23:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_23hrs);
                mediaPlayer.start();
                break;
            default:
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_00hrs);
                mediaPlayer.start();
                break;
            }

            this.isRunning = true;
            this.startId = 0;
//            soundId++; //increment to play correct sound next notification
                        //possibly fixed now using the time got in this class

            //set up notification call command
            notificationManager.notify(0, notificationPopup);
        }
        //if there is music playing and the user pressed "daijoubou off"
        //music should stop playing
        else if(this.isRunning && startId == 0)
        {
            mediaPlayer.stop();
            mediaPlayer.reset();    //reset the mediaPlayer

            this.isRunning = false;
            this.startId = 0;
        }
        //these are if the user presses random buttons
        //just to bug-proof the app
        //if there is no music playing and the user pressed "daijoubou off"
        else if(!this.isRunning && startId == 0)
        {
            this.isRunning = false;
            this.startId = 0;
        }
        //if there is music playing and the user played "daijoubou on"
        //do nothing
        else if(this.isRunning && startId == 1)
        {
            this.isRunning = true;
            this.startId = 0;
        }
        //catch odd events
        else
        {
//            Log.e("else", "somehow got here");
        }

        if(mediaPlayer != null)
        {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
            {
                @Override
                public void onCompletion(MediaPlayer mp)
                {
                    mp.stop();
                    mp.release();
                    mp = null;
                }
            });
            this.isRunning = false;
            this.startId = 0;
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        this.isRunning = false;

        if(mediaPlayer != null)
            mediaPlayer.release();
    }
}
