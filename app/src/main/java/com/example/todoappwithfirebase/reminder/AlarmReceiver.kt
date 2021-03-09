package com.example.todoappwithfirebase.reminder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavDeepLinkBuilder
import com.example.todoappwithfirebase.MainActivity
import com.example.todoappwithfirebase.R
import timber.log.Timber

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Timber.d("onReceive: ")

        val notificationId = 114514

        val name = "channel name desu"
        val descriptionText = "channel no description desu"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        // Register the channel with the system
        val notificationManager: NotificationManager =
            getSystemService(context!!, NotificationManager::class.java) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        
        val bundle = Bundle()
        bundle.putLong("taskId", intent!!.getLongExtra("taskId", -1))

        Timber.d("onReceive: hoge bundle=$bundle")

        val pendingIntent = NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.navigation_graph)
            .setDestination(R.id.taskDetailFragment)
            .setArguments(bundle)
            .createPendingIntent()


        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_baseline_task_alt_24)
            .setContentTitle("test title")
            .setContentText("test description")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(notificationId, builder.build())
        }

    }

    companion object {
        private const val CHANNEL_ID = "todo app desuyo"
        private const val TAG = "AlarmReceiver"
    }

}