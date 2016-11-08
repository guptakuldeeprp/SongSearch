package com.example.kuldeepgupta.songsearch;

import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.kuldeepgupta.songsearch.adapter.LibraryArrayAdapter;
import com.example.kuldeepgupta.songsearch.async.AsyncResponse;
import com.example.kuldeepgupta.songsearch.async.LibraryReadTask;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;

import java.util.List;
import java.util.ArrayList;

public class SongLibraryActivity extends AppCompatActivity {

    private static final String TAG = SongLibraryActivity.class.getName();
    private List<DownloadInfo> libList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {

            libList = (List<DownloadInfo>) savedInstanceState.get(getString(R.string.library_list_key));

        } else {
            libList = new ArrayList<DownloadInfo>();

        }

        setContentView(R.layout.activity_song_library_adapter);

        // Create a progress bar to display while the list loads
        final ProgressBar progressBar = new ProgressBar(this);
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        progressBar.setIndeterminate(true);
        final ListView listView = (ListView) findViewById(R.id.library_list);
        listView.setEmptyView(progressBar);
        listView.setDivider(this.getResources().getDrawable(R.drawable.transperent_color));

        LibraryReadTask readTask = new LibraryReadTask(SongLibraryActivity.this, new AsyncResponse<List<DownloadInfo>>() {
            @Override
            public void processFinish(List<DownloadInfo> downloadInfos) {
                libList = downloadInfos;

                LibraryArrayAdapter songListAdapter = new LibraryArrayAdapter(SongLibraryActivity.this, R.id.downloadListView, libList);
                listView.setAdapter(songListAdapter);
                progressBar.invalidate();
            }
        }); // for context and asyncresponse
        readTask.execute(new String[]{""});

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (libList != null && !libList.isEmpty()) {

            outState.putParcelableArrayList(getString(R.string.library_list_key), (ArrayList<? extends Parcelable>) libList);
        }
    }


}
