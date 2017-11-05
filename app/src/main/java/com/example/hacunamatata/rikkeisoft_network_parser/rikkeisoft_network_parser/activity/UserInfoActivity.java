package com.example.hacunamatata.rikkeisoft_network_parser.rikkeisoft_network_parser.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.hacunamatata.rikkeisoft_network_parser.R;
import com.example.hacunamatata.rikkeisoft_network_parser.rikkeisoft_network_parser.entity.User;

public class UserInfoActivity extends AppCompatActivity {

    TextView tvUserID;
    TextView tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        // Initialize components
        tvUserID = findViewById(R.id.tv_detail_userID);
        tvBody = findViewById(R.id.tv_detail_body);

        // Get and set data to views
        getDataForInsert();
    }

    private void getDataForInsert() {
        Intent intent = getIntent();
        User user = intent.getParcelableExtra(MainActivity.PARCEL_KEY);
        tvUserID.setText(String.valueOf(user.getUserId()));
        tvBody.setText(user.getBody());
    }
}
