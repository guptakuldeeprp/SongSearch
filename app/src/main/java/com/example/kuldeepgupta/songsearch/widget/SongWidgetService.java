package com.example.kuldeepgupta.songsearch.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by kuldeep.gupta on 11/3/2016.
 */

public class SongWidgetService  extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        System.out.println("SongWidgetService onGetViewFactory called..");
        return new SongWidgetDataProvider(getApplicationContext());
    }
}
