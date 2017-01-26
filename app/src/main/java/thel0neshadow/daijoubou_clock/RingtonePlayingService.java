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
    ********************************************
*/

public class RingtonePlayingService extends Service
{

    MediaPlayer mediaPlayer;
    int startId;
    boolean isRunning;
    int soundId;
//    private Context context;
//    private AssetManager assetManager;
//    private String externalStoragePath;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
//        Log.i("LocalService", "Received start id " + startId + ": " + intent);

        //fetch the extra string values
        String state = intent.getExtras().getString("extra");

        //fetch soundId value
        soundId = intent.getExtras().getInt("sound");

//        Log.e("In RPS", "woo!");
//        Log.e("Ringtone extra: " , state);
//        Log.e("SoundId: " , String.valueOf(soundId));

        //notification
        //set up the notification service
        NotificationManager notificationManager = (NotificationManager)
            getSystemService(NOTIFICATION_SERVICE);
        //set up an intent that goes to the main activity
        Intent intentMainActivity = new Intent(this.getApplicationContext(), MainActivity.class);
        //set up a pending intent
        PendingIntent pendingIntentMainActivity = PendingIntent.getActivity(this, 0,
                intentMainActivity, 0);


//        //make bitmap here
//        InputStream in = null;
//        Bitmap notifBitmap = null;
//        try
//        {
//            in = assetManager.open("drawable/ic_stat_name.png");
//            notifBitmap = BitmapFactory.decodeStream(in);
//            if(notifBitmap == null)
//                throw new IOException("Couldn't load bitmap");
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            if(in != null)
//            {
//                try
//                {
//                    in.close();
//                } catch (IOException e)
//                {
//
//                }
//            }
//        }

        String timeString = DateFormat.getTimeInstance().format(new Date());
        String hourString = timeString.substring(0, 2);
        if(hourString.contains(":"))
            hourString = hourString.substring(0, 1);

        if(timeString.contains("PM"))
            hourString = "1" + hourString;

        //put notification here
        //make the notification parameters
        Notification notificationPopup = new Notification.Builder(this)
                .setCategory(Notification.CATEGORY_ALARM)
                .setContentTitle("Teitoku!")
                .setContentText("It's " + hourString + "00 hours! <3")
                .setContentIntent(pendingIntentMainActivity)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setVibrate(new long[] {500, 250, 500, 250})
                .setAutoCancel(false)
                .build();

//                .setLargeIcon(notifBitmap)

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
//            Log.e("There is no music", "want on");

            switch (soundId)
            {
            case 0:
                //create instance of the media player + file
                mediaPlayer = MediaPlayer.create(this, R.raw.haruna_00hrs);
                //start playback
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
            soundId++; //increment to play correct sound next notification

            //set up notification call command
            notificationManager.notify(0, notificationPopup);
//            Log.d("Notif", "notified");
        }
        //if there is music playing and the user pressed "daijoubou off"
        //music should stop playing
        else if(this.isRunning && startId == 0)
        {
//            Log.e("There is music", "want end");
            //stop ringtone
            mediaPlayer.stop();
            mediaPlayer.reset();

            this.isRunning = false;
            this.startId = 0;
        }
        //these are if the user presses random buttons
        //just to bug-proof the app
        //if there is no music playing and the user pressed "daijoubou off"
        else if(!this.isRunning && startId == 0)
        {
//            Log.e("There is no music", "want end");

            this.isRunning = false;
            this.startId = 0;
        }
        //if there is music playing and the user played "daijoubou on"
        //do nothing
        else if(this.isRunning && startId == 1)
        {
//            Log.e("There is music", "want on");

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
//                    Log.e("Mediaplayer", "destroyed");
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
//        Log.e("On destroy called", "ta da");

        super.onDestroy();
        this.isRunning = false;

        if(mediaPlayer != null)
            mediaPlayer.release();
    }
}
