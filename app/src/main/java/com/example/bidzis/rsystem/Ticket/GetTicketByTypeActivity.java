package com.example.bidzis.rsystem.Ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.bidzis.rsystem.R;

import java.util.List;

public class GetTicketByTypeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_ticket_by_type);



        final TextView tvSelectTicketTypeGetTicketByType = (TextView) findViewById(R.id.tvSelectTicketTypeGetTicketByType);
        final Spinner spinnerSelectTicketTypeGetTicketByType = (Spinner) findViewById(R.id.spinnerSelectTicketTypeGetTicketByType);
        final Button btGetTicketGetTicketByType = (Button) findViewById(R.id.btGetTicketGetTicketByType);
        final ListView listViewGetTicketByType = (ListView) findViewById(R.id.listViewGetTicketByType);


    }
}
