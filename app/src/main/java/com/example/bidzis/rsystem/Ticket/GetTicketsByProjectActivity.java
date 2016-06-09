package com.example.bidzis.rsystem.Ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bidzis.rsystem.R;

public class GetTicketsByProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_tickets_by_project);

        final ListView listViewGetTicketsByProject = (ListView) findViewById(R.id.listViewGetTicketsByProject);


    }
}
