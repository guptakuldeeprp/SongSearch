package com.example.kuldeepgupta.songsearch.async;

/**
 * Created by kuldeep.gupta on 10/27/2016.
 */

public interface AsyncResponse<T> {

    void processFinish(T t);

}
