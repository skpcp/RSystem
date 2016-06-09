package com.example.bidzis.rsystem.Role;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidzis.rsystem.Priority.PriorityChangePriorityName;
import com.example.bidzis.rsystem.R;

public class RoleManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_management);

        final Button btShowAll = (Button) findViewById(R.id.btRoleShowAllRole);
        final Button btSave = (Button) findViewById(R.id.btRoleSave);
        final Button btRemove = (Button) findViewById(R.id.btRoleRemove);

        assert btShowAll != null;
        btShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleManagement.this, RoleShowAll.class);
                RoleManagement.this.startActivity(intent);

            }
        });

        assert btSave != null;
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleManagement.this, RoleSave.class);
                RoleManagement.this.startActivity(intent);

            }
        });

        assert btRemove != null;
        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RoleManagement.this, RoleRemove.class);
                RoleManagement.this.startActivity(intent);

            }
        });









    }
}
