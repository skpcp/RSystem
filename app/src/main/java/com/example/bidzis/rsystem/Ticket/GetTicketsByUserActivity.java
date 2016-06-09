package com.example.bidzis.rsystem.Ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.bidzis.rsystem.R;

import java.util.List;

public class GetTicketsByUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_tickets_by_user);

        final ListView listViewGetTicketsByUser = (ListView) findViewById(R.id.listViewGetTicketsByUser);
        

    }
}
