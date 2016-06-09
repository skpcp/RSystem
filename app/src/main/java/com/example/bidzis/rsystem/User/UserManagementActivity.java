package com.example.bidzis.rsystem.User;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidzis.rsystem.R;
import com.example.bidzis.rsystem.Ticket.SendTicketActivity;

public class UserManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);
        final Button btShowAllUSers = (Button) findViewById(R.id.btGetAllUsersUsersManagement);
        final Button btGetUsersByActivity = (Button) findViewById(R.id.btGetUsersByActivityUsersManagement);
        final Button btGetUserByNameAndSurname = (Button) findViewById(R.id.btGetUserByNameAndSurname);
        final Button btGetUsersByRole = (Button) findViewById(R.id.btGetUsersByRole);
        final Button btRemoveUser = (Button) findViewById(R.id.btRemoveUsers);
        final Button btSaveUser = (Button) findViewById(R.id.btSaveUser);
        final Button btChangeActivity = (Button) findViewById(R.id.btChangeUsersActivity);
        final Button btUpdateProjectsForUser = (Button) findViewById(R.id.btAddProjectForUser);


        assert btShowAllUSers != null;
        btShowAllUSers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserManagementActivity.this, ShowAllUsersActivity.class);
                UserManagementActivity.this.startActivity(intent);
            }
        });
        assert btGetUsersByActivity != null;
        btGetUsersByActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserManagementActivity.this, GetUserByActivityActivity.class);
                UserManagementActivity.this.startActivity(intent);
            }
        });
        assert btGetUserByNameAndSurname != null;
        btGetUserByNameAndSurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserManagementActivity.this, GetUserByNameAndSurnameActivity.class);
                UserManagementActivity.this.startActivity(intent);
            }
        });
        assert btGetUsersByRole != null;
        btGetUsersByRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserManagementActivity.this, GetUsersByRoleActivity.class);
                UserManagementActivity.this.startActivity(intent);
            }
        });
        assert btRemoveUser != null;
        btRemoveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserManagementActivity.this, RemoveUsersActivity.class);
                UserManagementActivity.this.startActivity(intent);
            }
        });
        assert btSaveUser != null;
        btSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserManagementActivity.this, SaveUserActivity.class);
                UserManagementActivity.this.startActivity(intent);
            }
        });
        assert btChangeActivity != null;
        btChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserManagementActivity.this, ChangeUserActiveActivity.class);
                UserManagementActivity.this.startActivity(intent);
            }
        });
        assert btUpdateProjectsForUser != null;
        btUpdateProjectsForUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserManagementActivity.this, UpdateProjectForUserActivity.class);
                UserManagementActivity.this.startActivity(intent);
            }
        });

    }
}
