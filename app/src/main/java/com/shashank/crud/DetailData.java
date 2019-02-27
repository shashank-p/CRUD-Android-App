package com.shashank.crud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailData extends AppCompatActivity {
    TextView uid, name, address, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data);

        uid = (TextView) findViewById(R.id.uid);
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        phone = (TextView) findViewById(R.id.phone);

        uid.setText(getIntent().getStringExtra("uid"));
        name.setText(getIntent().getStringExtra("name"));
        address.setText(getIntent().getStringExtra("address"));
        phone.setText(getIntent().getStringExtra("phone"));
    }
}
