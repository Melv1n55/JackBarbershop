package com.example.androidbarberbooking.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidbarberbooking.R;

import androidx.fragment.app.Fragment;

public class BookingStep3Fragment extends Fragment {

    static BookingStep3Fragment instance;
    public static BookingStep3Fragment getInstance() {
        if (instance == null)
            instance = new BookingStep3Fragment();
        return instance;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_booking_step_three,container,false);
    }
}
