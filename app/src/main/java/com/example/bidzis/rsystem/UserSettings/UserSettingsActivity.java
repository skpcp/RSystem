package com.example.bidzis.rsystem.UserSettings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidzis.rsystem.R;
import com.example.bidzis.rsystem.User.RemoveUsersActivity;

public class UserSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        final Button btChangeEmail = (Button) findViewById(R.id.btChangeEmail);
        final Button btChangeLogin = (Button) findViewById(R.id.btChangeLogin);
        final Button btChangePassword = (Button) findViewById(R.id.btChangePassword);


        Bundle extras = getIntent().getExtras();
        final String aId = extras.getString("id");
        String elo = "";
        assert btChangeEmail != null;
        btChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserSettingsActivity.this, ChangeEmailActivity.class);
                intent.putExtra("id",aId);
                UserSettingsActivity.this.startActivity(intent);
            }
        });
        assert btChangeLogin != null;
        btChangeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserSettingsActivity.this, ChangeLoginActivity.class);
                intent.putExtra("id",aId);
                UserSettingsActivity.this.startActivity(intent);
            }
        });
        assert btChangePassword != null;
        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(UserSettingsActivity.this, ChangePasswordActivity.class);
                intent.putExtra("id",aId);
                UserSettingsActivity.this.startActivity(intent);
            }
        });
    }
}
