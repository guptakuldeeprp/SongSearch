package com.example.kuldeepgupta.songsearch.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kuldeepgupta.songsearch.R;
import com.example.kuldeepgupta.songsearch.async.SongDownloadTask;
import com.example.kuldeepgupta.songsearch.service.download.android.DownloadInfo;

import java.util.List;

/**
 * Created by kuldeep.gupta on 10/26/2016.
 */

public class SongInfoArrayAdapter extends ArrayAdapter<DownloadInfo> {

    private static class ViewHolder {
        TextView textView;
        ProgressBar progressBar;
        Button button;
        DownloadInfo info;
    }


    private static final String TAG = SongInfoArrayAdapter.class.getSimpleName();

    public SongInfoArrayAdapter(Context context, int textViewResourceId,
                                    List<DownloadInfo> objects) {
        super(context, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final DownloadInfo info = getItem(position);
        // We need to set the convertView's progressBar to null.

        ViewHolder holder = null;

        if(null == row) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.song_download_row, parent, false);

            holder = new ViewHolder();
            holder.textView = (TextView) row.findViewById(R.id.downloadFileName);
            holder.progressBar = (ProgressBar) row.findViewById(R.id.downloadProgressBar);
            holder.button = (Button)row.findViewById(R.id.downloadButton);
            holder.info = info;

            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();

            holder.info.setProgressBar(null);
            holder.info = info;
            holder.info.setProgressBar(holder.progressBar);
        }

        holder.textView.setText(info.getSongInfo().getFullTitle());
        holder.progressBar.setProgress(info.getProgress());
        info.setProgressBar(holder.progressBar);

        holder.button.setEnabled(info.getDownloadState() == DownloadInfo.DownloadState.NOT_STARTED);
        final Button button = holder.button;
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                info.setDownloadState(DownloadInfo.DownloadState.QUEUED);
                button.setEnabled(false);
                button.invalidate();
                SongDownloadTask task = new SongDownloadTask(getContext(),info);
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });


        //TODO: When reusing a view, invalidate the current progressBar.

        return row;
    }

    


}
