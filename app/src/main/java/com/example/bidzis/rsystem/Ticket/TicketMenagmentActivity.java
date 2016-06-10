package com.example.bidzis.rsystem.Ticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidzis.rsystem.R;

public class TicketMenagmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_menagment);


        final Button btGetAllTicketMenagment = (Button) findViewById(R.id.btGetAllTicketMenagment);
        final Button btGetTicketByTypeTicketMenagment = (Button) findViewById(R.id.btGetTicketByTypeTicketMenagment);
        final Button btGetByPriorityTicketMenagment = (Button) findViewById(R.id.btGetByPriorityTicketMenagment);
        final Button btGetByProjectTicketMenagment = (Button) findViewById(R.id.btGetByProjectTicketMenagment);
        final Button btGetByUserTicketMenagment = (Button) findViewById(R.id.btGetByUserTicketMenagment);
        final Button btRemoveTicketTicketMenagment = (Button) findViewById(R.id.btRemoveTicketTicketMenagment);
        String test = "";


        assert btGetAllTicketMenagment != null;
        btGetAllTicketMenagment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(TicketMenagmentActivity.this, GetAllActivity.class);
                TicketMenagmentActivity.this.startActivity(intent);
            }
        });

        assert btGetTicketByTypeTicketMenagment != null;
        btGetTicketByTypeTicketMenagment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(TicketMenagmentActivity.this, GetTicketByTypeActivity.class);
                TicketMenagmentActivity.this.startActivity(intent);
            }
        });
        assert btGetByProjectTicketMenagment != null;
        btGetByProjectTicketMenagment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(TicketMenagmentActivity.this, GetTicketByProjectListActivity.class);
                TicketMenagmentActivity.this.startActivity(intent);
            }
        });
        assert btGetByUserTicketMenagment != null;
        btGetByUserTicketMenagment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(TicketMenagmentActivity.this, GetTicketsByUserListActivity.class);
                TicketMenagmentActivity.this.startActivity(intent);
            }
        });
        assert btGetByPriorityTicketMenagment != null;
        btGetByPriorityTicketMenagment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(TicketMenagmentActivity.this, GetTicketByTicketTypeListPriorityActivity.class);
                TicketMenagmentActivity.this.startActivity(intent);
            }
        });
        assert btRemoveTicketTicketMenagment != null;
        btRemoveTicketTicketMenagment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(TicketMenagmentActivity.this, RemoveTicketActivity.class);
                TicketMenagmentActivity.this.startActivity(intent);
            }
        });
    }
}
