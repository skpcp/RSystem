package com.example.bidzis.rsystem.Attachments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bidzis.rsystem.R;

public class AttachmentsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attachments_details);

        String id = "";
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
    }
}
