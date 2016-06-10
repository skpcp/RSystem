package com.example.bidzis.rsystem.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidzis.rsystem.R;
import com.example.bidzis.rsystem.Ticket.GetTicketsByUserActivity;
import com.example.bidzis.rsystem.Ticket.SendTicketActivity;
import com.example.bidzis.rsystem.UserSettings.UserSettingsActivity;

public class SimpleUserSiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_user_site);

        Bundle extras = getIntent().getExtras();
        final String aId = extras.getString("id");
        final String login = extras.getString("login");

        final Button btSettings = (Button) findViewById(R.id.btSettingsSimpleUserSite);
        final Button btSendTicket = (Button) findViewById(R.id.btSendTicketSimpleUserSite);
        final Button btShowAllTicket = (Button) findViewById(R.id.tbShowSendTicketSimpleUserSite);
        final Button btShowAllProjects = (Button) findViewById(R.id.btShowYourProjectsSimpleUserSite);

        assert btSettings != null;
        btSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(SimpleUserSiteActivity.this, UserSettingsActivity.class);
                intent.putExtra("id",aId);
                SimpleUserSiteActivity.this.startActivity(intent);
            }
        });
        assert btSendTicket != null;
        btSendTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(SimpleUserSiteActivity.this, SendTicketActivity.class);
                intent.putExtra("login",login);
                SimpleUserSiteActivity.this.startActivity(intent);
            }
        });
        assert btShowAllTicket != null;
        btShowAllTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(SimpleUserSiteActivity.this, GetTicketsByUserActivity.class);
                intent.putExtra("id",aId);
                SimpleUserSiteActivity.this.startActivity(intent);
            }
        });
        assert btShowAllProjects != null;
        btShowAllProjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
