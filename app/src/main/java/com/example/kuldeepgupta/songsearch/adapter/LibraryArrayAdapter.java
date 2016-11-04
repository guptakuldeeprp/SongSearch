package com.example.kuldeepgupta.songsearch.adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;


import com.example.kuldeepgupta.songsearch.AppTracker;
import com.example.kuldeepgupta.songsearch.R;
import com.example.kuldeepgupta.songsearch.async.LibraryReadTask;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;
import com.example.kuldeepgupta.songsearch.util.CommonUtil;
import com.example.kuldeepgupta.songsearch.util.MedialPlayer;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.example.kuldeepgupta.songsearch.AppTracker.tracker;

/**
 * Created by kuldeep.gupta on 11/1/2016.
 */

public class LibraryArrayAdapter extends ArrayAdapter<DownloadInfo> {

    private static final String TAG = LibraryArrayAdapter.class.getName();
    private Context context;
    private ToggleButton lastPlayedButton;
    private int lastPosition;

    private static class ViewHolder {
        TextView textView;
        ToggleButton playPauseButton;
        DownloadInfo info;

    }

    public LibraryArrayAdapter(Context context, int textViewResourceId, List<DownloadInfo> objects) {
        super(context, textViewResourceId, objects);
        this.context = context;
    }


    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View row = convertView;
        final DownloadInfo info = getItem(position);
        // We need to set the convertView's progressBar to null.

        LibraryArrayAdapter.ViewHolder holder = null;

        if (null == row) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.library_row, parent, false);

            holder = new LibraryArrayAdapter.ViewHolder();
            holder.textView = (TextView) row.findViewById(R.id.songInfo);
            final ToggleButton playPauseButton = (ToggleButton) row.findViewById(R.id.playPauseButton);

            playPauseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Perform action on clicks
                    try {
                        if (playPauseButton.isChecked()) { // Checked - Pause icon visible
                            //CommonUtil.playAudio(context, info.getFilename());
                            if (lastPlayedButton != null && lastPosition != position) {
                                System.out.println("last pos: " + lastPosition + ", position: " + position);
                                lastPlayedButton.setChecked(false);
                            }

                            MedialPlayer.getInstance().play(context, info.getFilename());
                            lastPlayedButton = playPauseButton;
                            lastPosition = position;
                        } else { // Unchecked - Play icon visible
                            MedialPlayer.getInstance().pause();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, e.getMessage(), e);
                        CommonUtil.makeToast(context, "Could not play the media file");
                    }
                }
            });
            holder.playPauseButton = playPauseButton;
            holder.info = info;

            row.setTag(holder);
        } else {
            holder = (LibraryArrayAdapter.ViewHolder) row.getTag();

            //holder.info.setProgressBar(null);
            holder.info = info;
            //holder.info.setProgressBar(holder.progressBar);
        }

        holder.textView.setText(info.getSongInfo().getFullTitle());

//        row.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                tracker().send(new HitBuilders.EventBuilder("SongListen", info.getSongInfo().getTitle())
//                        .setLabel("Listen")
//                        .build());
//                String fileName = info.getFilename();
//
//                try {
//                    CommonUtil.playAudio(context, fileName);
//                } catch (IOException e) {
//                    Log.e(TAG, e.getMessage(), e);
//                    CommonUtil.makeToast(context, "Could not play the media file");
//                }
//            }
//        });

        //TODO: When reusing a view, invalidate the current progressBar.

        return row;
    }


}
