package com.example.kuldeepgupta.songsearch.widget;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.kuldeepgupta.songsearch.R;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;
import com.example.kuldeepgupta.songsearch.util.ProviderUtil;

import java.util.List;

/**
 * Created by kuldeep.gupta on 11/3/2016.
 */

public class SongWidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    public static final String PLAY_ACTION = "PLAY_ACTION";
    private List<DownloadInfo> songList;
    private Context context;

    public SongWidgetDataProvider(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate() {
        songList = ProviderUtil.getDownloadedSongs(context);
        System.out.println("songList size: " + songList.size());
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return songList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        System.out.println("SongWidgetDataProvider getViewAt called: " + position);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_list_item);

        if (songList != null && !songList.isEmpty()) {
            remoteViews.setTextViewText(R.id.widget_song_title, songList.get(position).getSongInfo().getFullTitle());
            //remoteViews.setTextViewText(R.id.widget_song_filepath, songList.get(position).getFilename());

//            Intent intent = new Intent();
//            intent.putExtra(context.getString(R.string.song_filepath), songList.get(position).getFilename());

            //intent.setAction(PLAY_ACTION);

            //PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);

//            remoteViews.setOnClickFillInIntent(R.id.stock_row, intent);

        }
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


}
