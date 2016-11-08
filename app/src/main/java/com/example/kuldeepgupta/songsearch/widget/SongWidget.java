package com.example.kuldeepgupta.songsearch.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.kuldeepgupta.songsearch.R;
import com.example.kuldeepgupta.songsearch.SongLibraryActivity;
import com.example.kuldeepgupta.songsearch.util.CommonUtil;

import java.io.IOException;

import static com.example.kuldeepgupta.songsearch.widget.SongWidgetDataProvider.PLAY_ACTION;

/**
 * Created by kuldeep.gupta on 11/3/2016.
 */

public class SongWidget extends AppWidgetProvider {

    private static final String TAG = SongWidget.class.getName();

    private RemoteViews initViews(Context context,
                                  AppWidgetManager widgetManager, int widgetId) {

        //System.out.println("SongWidget initViews called..");
        RemoteViews mView = new RemoteViews(context.getPackageName(),
                R.layout.widget_list);
        Intent intent = new Intent(context, SongWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);


        mView.setRemoteAdapter(R.id.widget_listview, intent);

//        Intent configIntent = new Intent(context, SongLibraryActivity.class);
//
//        PendingIntent configPendingIntent = PendingIntent.getActivity(context, 0, configIntent, 0);

        // mView.setOnClickPendingIntent(R.id.widget_listview, configPendingIntent);
        //widgetManager.updateAppWidget(widgetId, mView);

        Intent intent1 = new Intent(context, SongWidget.class);
        intent1.setAction(PLAY_ACTION);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent1, 0);
        //mView.setPendingIntentTemplate(R.id.song_widget_layout, pendingIntent);
        mView.setOnClickPendingIntent(R.id.song_widget_layout, pendingIntent);

        return mView;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        //System.out.println("SongWidget onUpdate called..");
        for (int widgetId : appWidgetIds) {
            RemoteViews mView = initViews(context, appWidgetManager, widgetId);
            appWidgetManager.updateAppWidget(widgetId, mView);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        //System.out.println("click event received");
        super.onReceive(context, intent);
        if (intent.getAction().equals(PLAY_ACTION)) {
            //System.out.println(PLAY_ACTION + " action received");
            context.startActivity(new Intent(context,SongLibraryActivity.class));
//            String fileName = intent.getStringExtra(context.getString(R.string.song_filepath));
//            if (fileName != null) {
//                try {
//                    CommonUtil.playAudio(context, fileName);
//                } catch (IOException e) {
//                    Log.e(TAG, e.getMessage(), e);
//                    CommonUtil.makeToast(context, "Could not play the media file");
//                }
//            }
        }
    }
}
