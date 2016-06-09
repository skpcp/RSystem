package com.example.bidzis.rsystem.Ticket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.bidzis.rsystem.R;

public class TicketMenagmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_menagment);


        final Button btGetAllTicketMenagment = (Button) findViewById(R.id.btGetAllTicketMenagment);
        final Button btGetByTypeTicketMenagment = (Button) findViewById(R.id.btGetByTypeTicketMenagment);
        final Button btGetTicketByTypeTicketMenagment = (Button) findViewById(R.id.btGetTicketByTypeTicketMenagment);
        final Button btGetByPriorityTicketMenagment = (Button) findViewById(R.id.btGetByPriorityTicketMenagment);
        final Button btGetByProjectTicketMenagment = (Button) findViewById(R.id.btGetByProjectTicketMenagment);
        final Button btGetByUserTicketMenagment = (Button) findViewById(R.id.btGetByUserTicketMenagment);
        final Button btSendTicketTicketMenagment = (Button) findViewById(R.id.btSendTicketTicketMenagment);
        final Button btSaveTicketTicketMenagment = (Button) findViewById(R.id.btSaveTicketTicketMenagment);
        final Button btSaveTicketWithAttachmentTicketMenagment = (Button) findViewById(R.id.btSaveTicketWithAttachmentTicketMenagment);
        final Button btRemoveTicketTicketMenagment = (Button) findViewById(R.id.btRemoveTicketTicketMenagment);
        final Button btAddHistoryTicketMenagment = (Button) findViewById(R.id.btAddHistoryTicketMenagment);


    }
}
