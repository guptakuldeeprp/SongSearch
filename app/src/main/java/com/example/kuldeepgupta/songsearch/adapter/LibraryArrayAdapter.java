package com.example.kuldeepgupta.songsearch.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.kuldeepgupta.songsearch.AppTracker;
import com.example.kuldeepgupta.songsearch.R;
import com.example.kuldeepgupta.songsearch.async.LibraryReadTask;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;


import java.util.List;

import static com.example.kuldeepgupta.songsearch.AppTracker.tracker;

/**
 * Created by kuldeep.gupta on 11/1/2016.
 */

public class LibraryArrayAdapter extends ArrayAdapter<DownloadInfo> {

    private static class ViewHolder {
        TextView textView;
        DownloadInfo info;

    }

    public LibraryArrayAdapter(Context context, int textViewResourceId, List<DownloadInfo> objects) {
        super(context, textViewResourceId, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final DownloadInfo info = getItem(position);
        // We need to set the convertView's progressBar to null.

        LibraryArrayAdapter.ViewHolder holder = null;

        if(null == row) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.library_row, parent, false);

            holder = new LibraryArrayAdapter.ViewHolder();
            holder.textView = (TextView) row.findViewById(R.id.songInfo);
            holder.info = info;

            row.setTag(holder);
        } else {
            holder = (LibraryArrayAdapter.ViewHolder) row.getTag();

            //holder.info.setProgressBar(null);
            holder.info = info;
            //holder.info.setProgressBar(holder.progressBar);
        }

        holder.textView.setText(info.getSongInfo().getFullTitle());

        row.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tracker().send(new HitBuilders.EventBuilder("SongListen", info.getSongInfo().getTitle())
                        .setLabel("Listen")
                        .build());
                String fileName = info.getFilename();
                MediaPlayer mpintro = MediaPlayer.create(getContext(), Uri.parse(fileName));
                mpintro.setLooping(true);
                mpintro.start();
            }
        });

        //TODO: When reusing a view, invalidate the current progressBar.

        return row;
    }
}
