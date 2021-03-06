package com.example.gd.ex4;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

/**
 * Created by gd on 16/10/19.
 */
public class DynamicReceiver extends BroadcastReceiver {

    private static final String DYNAMICACTION = "com.example.gd.ex4.dynamicreceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(DYNAMICACTION)) {
            Bundle bundle = intent.getExtras();
            String text = bundle.getString("message");

            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.dynamic);

            //获取状态通知栏管理
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            //实例化通知栏构造器Notification.Builder
            Notification.Builder builder = new Notification.Builder(context);
            //对Builder进行配置
            builder.setContentTitle("动态广播")
                    .setContentText(text)
                    .setSmallIcon(R.mipmap.dynamic)
                    .setLargeIcon(bitmap)
                    .setWhen(System.currentTimeMillis());

            Intent intent1 = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent1, 0);
            builder.setContentIntent(pendingIntent);
            //绑定Notification，发送通知请求
            Notification notify = builder.build();
            manager.notify(0, notify);

        }
    }
}
