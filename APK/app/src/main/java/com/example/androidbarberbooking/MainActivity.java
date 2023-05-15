package com.example.androidbarberbooking;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.AccessToken;
import com.google.android.gms.common.internal.service.Common;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(Common.IS_LOGIN, true);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_main);
            ButterKnife.bind(MainActivity.this);
        }
    }
}