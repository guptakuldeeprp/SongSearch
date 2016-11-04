package com.example.kuldeepgupta.songsearch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.kuldeepgupta.songsearch.adapter.SongInfoArrayAdapter;
import com.example.kuldeepgupta.songsearch.async.AsyncResponse;
import com.example.kuldeepgupta.songsearch.async.SongSearchTask;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;
import com.example.kuldeepgupta.songsearch.util.CommonUtil;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.analytics.HitBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.kuldeepgupta.songsearch.AppTracker.tracker;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    ProgressDialog dialog;
    List<DownloadInfo> songList;
    private InterstitialAd interstitialAd;
    private Toolbar mToolbar;
    private ListView listView;
    private SongInfoArrayAdapter songListAdapter;
    //private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        //getMenuInflater().inflate(R.menu.menu, mToolbar);

        if (savedInstanceState != null) {

            songList = (List<DownloadInfo>) savedInstanceState.get(getString(R.string.song_list_key));

        } else {
            songList = new ArrayList<DownloadInfo>();

        }

        //AppTracker application = (AppTracker) getApplication();
        //mTracker = application.getDefaultTracker();


        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ad_unit_id));

        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                tracker().send(new HitBuilders.EventBuilder("add", "closed")
                        .setLabel("Ads")
                        .build());
                requestNewInterstitial();
                String searchString = getSearchString();
                if (searchString == null || searchString.trim().length() == 0)
                    CommonUtil.makeToast(MainActivity.this, getString(R.string.empty_song));
                initiateSongSearch(searchString);
            }
        });

        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tracker().send(new HitBuilders.EventBuilder("SongSearch", "search")
                        .setLabel("Search")
                        .build());
                String searchString = getSearchString();
                if (searchString == null || searchString.trim().length() == 0) {
                    CommonUtil.makeToast(MainActivity.this, getString(R.string.empty_song));
                    songList = null;
                    return;
                }

                if (!CommonUtil.isNetworkConnected(MainActivity.this)) {
                    CommonUtil.makeToast(MainActivity.this, getString(R.string.network_unavailable));
                }

                if (interstitialAd.isLoaded()) {
                    Log.i(TAG, "Interstitial ad is loaded. Will be shown");
                    interstitialAd.show();
                } else {
                    Log.i(TAG, "Interstitial ad is not loaded. Will not be shown");
                    initiateSongSearch(searchString);
                }
            }
        });

        requestNewInterstitial();

        listView = (ListView) findViewById(R.id.downloadListView);
        songListAdapter = new SongInfoArrayAdapter(MainActivity.this, R.id.downloadListView, songList);
        listView.setAdapter(songListAdapter);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (songList != null && !songList.isEmpty()) {

            outState.putParcelableArrayList(getString(R.string.song_list_key), (ArrayList<? extends Parcelable>) songList);

        }
    }

    @Override
    public boolean onPrepareOptionsMenu(final Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.menu, menu);
        //return true;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.library:
                Intent intent = new Intent(this, SongLibraryActivity.class);
                this.startActivity(intent);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private String getSearchString() {
        return ((EditText) findViewById(R.id.song)).getText().toString();
    }


    //    @Override
//    public void processFinish(List<DownloadInfo> downloadInfos) {
//        ListView listView = (ListView) findViewById(R.id.downloadListView);
//        SongInfoArrayAdapter songListAdapter = new SongInfoArrayAdapter(this, R.id.downloadListView, downloadInfos);
//        listView.setAdapter(songListAdapter);
//        dialog.dismiss();
//    }

    private void requestNewInterstitial() {
        Log.i(TAG, "Loading a new interstitial ad..");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        interstitialAd.loadAd(adRequest);
    }

    private void initiateSongSearch(String searchString) {
        dialog = ProgressDialog.show(MainActivity.this, "",
                "Searching. Please wait...", true);

        Log.i(TAG, "Searching for song " + searchString);
        SongSearchTask searchTask = new SongSearchTask(MainActivity.this, new AsyncResponse<List<DownloadInfo>>() {
            @Override
            public void processFinish(List<DownloadInfo> downloadInfos) {
                songList = downloadInfos;
                //ListView listView = (ListView) findViewById(R.id.downloadListView);
                //listView.setEmptyView(findViewById(R.id.empty));
                // SongInfoArrayAdapter songListAdapter = new SongInfoArrayAdapter(MainActivity.this, R.id.downloadListView, downloadInfos);

                songListAdapter.clear();
                songListAdapter.addAll(songList);
                songListAdapter.notifyDataSetChanged();
                listView.setAdapter(songListAdapter);
                dialog.dismiss();
            }
        }); // for context and asyncresponse
        searchTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{searchString});
    }

}
