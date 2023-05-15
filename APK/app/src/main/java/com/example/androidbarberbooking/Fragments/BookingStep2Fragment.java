package com.example.androidbarberbooking.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidbarberbooking.R;

import androidx.fragment.app.Fragment;

public class BookingStep2Fragment extends Fragment {

    static BookingStep2Fragment instance;
    public static BookingStep2Fragment getInstance() {
        if (instance == null)
            instance = new BookingStep2Fragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_booking_step_two,container,false);
    }
}
