package com.spardha.ritesh.fcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.spardha.ritesh.R;
import com.spardha.ritesh.activity.ActivityHome;
import com.spardha.ritesh.models.FCMUpdateMessage;

/**
 * Created by ritesh on 10/9/16.
 */

public class DataSyncService extends Service {

    private static boolean isCalled = false;
    private static final String TAG = "DataSyncService";

    @Override
    public void onCreate() {
        super.onCreate();
        if (!isCalled) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
        Log.d(TAG, "service started");
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("updates");
        ValueEventListener testListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> updates = dataSnapshot.getChildren();
                for (DataSnapshot msg : updates
                        ) {

                    FCMUpdateMessage fcmMsg = msg.getValue(FCMUpdateMessage.class);
                    Log.d(TAG, fcmMsg.msg_title);
                    //sendNotification(fcmMsg.msg_title);
                }

                //Log.i(TAG, "test msg: " + dataSnapshot.getValue(FCMUpdateMessage.class));
                //Toast.makeText(getApplicationContext(),"holla test msg: "+dataSnapshot.getValue(String.class),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        myRef.addValueEventListener(testListener);

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Service destroyed");
    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, ActivityHome.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_book_white_24dp)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }
}
