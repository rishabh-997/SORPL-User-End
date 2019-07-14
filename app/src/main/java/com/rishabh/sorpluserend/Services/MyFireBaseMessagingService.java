package com.rishabh.sorpluserend.Services;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.rishabh.sorpluserend.R;
import com.rishabh.sorpluserend.Splash.MVP.SplashActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyFireBaseMessagingService extends FirebaseMessagingService
{
    private String TAG="MyFirebaseService";
    private static int nid=0;
    private Bitmap bitmap;
    private String imageUri;


    int notificationId = 1;
    String channelId = "channel-01";
    String channelName = "Channel Name";
    int importance = NotificationManager.IMPORTANCE_HIGH;

    @SuppressLint("LogNotTimber")
    @Override
    public void onNewToken(String s)
    {
        Log.i("FCm","s");
        super.onNewToken(s);
    }

    @SuppressLint("LogNotTimber")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.i(TAG, "Message data payload: " + remoteMessage.getData());
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.i(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }

        imageUri = remoteMessage.getData().get("image");
        if(imageUri!=null)
        {
            if(imageUri.isEmpty())
            {
                imageUri="https://firebasestorage.googleapis.com/v0/b/sorpl-client.appspot.com/o/sorpl_not.png?alt=media&token=94e84267-25f1-4eff-ac42-1deee24d9199";
                bitmap=getBitmapfromUrl(imageUri);
                Log.i("image is:", "no image recieved");
                sendSmallNotification(remoteMessage,bitmap);
            }

            else
            {
                Log.i("image is:", imageUri);
                bitmap=getBitmapfromUrl(imageUri);
                sendImageNotification(remoteMessage,bitmap);
            }
        }
        else
        {
            Log.i("Image UrL", "Not Recieved");
            String tit=remoteMessage.getData().get("title");
            String mes=remoteMessage.getData().get("message");
            sendNotification_NoImage(tit,mes);
        }
    }

    private void sendSmallNotification(RemoteMessage remoteMessage, Bitmap bitmap)
    {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent=new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notification=new NotificationCompat.Builder(this);
        notification.setContentTitle(remoteMessage.getData().get("title"));
        notification.setContentText(remoteMessage.getData().get("message"));
        notification.setAutoCancel(true);
        notification.setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(remoteMessage.getData().get("title")));
        notification.setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("message")));
        notification.setSound(defaultSoundUri);
        notification.setSmallIcon(R.drawable.barrel);
        notification.setLargeIcon(bitmap);
        notification.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        notification.setPriority(NotificationCompat.PRIORITY_HIGH);
        notification.setContentIntent(pendingIntent);
        notification.setChannelId(channelId);

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        notificationManager.notify(notificationId,notification.build());
    }

    private void sendNotification_NoImage(String tit, String mes)
    {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent=new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notification=new NotificationCompat.Builder(this);
        notification.setContentTitle(tit);
        notification.setContentText(mes);
        notification.setAutoCancel(true);
        notification.setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(tit));
        notification.setStyle(new NotificationCompat.BigTextStyle().bigText(mes));
        notification.setSound(defaultSoundUri);
        notification.setSmallIcon(R.drawable.barrel);
        notification.setContentIntent(pendingIntent);
        notification.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        notification.setPriority(NotificationCompat.PRIORITY_HIGH);
        notification.setChannelId(channelId);

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);



        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        notificationManager.notify(notificationId,notification.build());
    }

    //we need this module only for creating notification when app is in foreground
    @SuppressLint("NewApi")
    private void sendImageNotification(RemoteMessage remoteMessage, Bitmap bitmap)
    {
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent=new Intent(this, SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notification=new NotificationCompat.Builder(this);
        notification.setContentTitle(remoteMessage.getData().get("title"));
        notification.setContentText(remoteMessage.getData().get("message"));
        notification.setAutoCancel(true);
        notification.setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(remoteMessage.getData().get("title")));
        notification.setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("message")));
        notification.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));
        notification.setSound(defaultSoundUri);
        notification.setSmallIcon(R.drawable.barrel);
        notification.setContentIntent(pendingIntent);
        notification.setChannelId(channelId);

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        notificationManager.notify(notificationId,notification.build());
    }

    /*
     *To get a Bitmap image from the URL received
     * */
    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }
}
