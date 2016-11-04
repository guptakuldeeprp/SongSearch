package com.example.kuldeepgupta.songsearch.util;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.view.Gravity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.Normalizer;
import java.util.regex.Pattern;

/**
 * Created by kuldeep.gupta on 10/25/2016.
 */

public class CommonUtil {

    private static final String TAG = CommonUtil.class.getName();
    public static final String APP_BASE_FLDR = "SongSearch";

    public static String cleanUrlParts(String... parts) {

        StringBuilder urlBuilder = new StringBuilder();
        if (parts != null) {
            for (String part : parts) {
                urlBuilder.append(deAccent(part).replace(" ", "+").replaceAll("[!@#$%^&*(){}:\"<>?]", ""));
            }
        }
        return urlBuilder.toString();
    }

    public static String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public static String fetchIpFromAmazon() throws IOException {
        URL url = null;
        url = new URL("http://checkip.amazonaws.com/");
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(url.openStream()));
        try {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Failed to get IP from Amazon";
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static File getSongDownloadDir(Context context) {
        File baseDir = null;
        if (CommonUtil.isExternalStorageWritable()) {
            baseDir = new File(context.getExternalFilesDir(null) + File.separator + APP_BASE_FLDR);
        } else {
            baseDir = new File(context.getFilesDir() + File.separator + APP_BASE_FLDR);
        }

        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }
        return baseDir;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static void makeToast(Context context, String msg) {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, Gravity.CENTER, 0);
        toast.show();
    }

    public static void playAudio(Context context, String filePath) throws IOException {
//        Intent viewMediaIntent = new Intent();
//        viewMediaIntent.setAction(android.content.Intent.ACTION_VIEW);
//        File file = new File(filePath);
//        viewMediaIntent.setDataAndType(Uri.fromFile(file), "audio/*");
//        viewMediaIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        context.startActivity(viewMediaIntent);

        MediaPlayer mpintro = MediaPlayer.create(context, Uri.fromFile(new File(filePath)));
        mpintro.setLooping(true);
        mpintro.start();
    }

}
