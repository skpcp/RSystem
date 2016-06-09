package com.example.bidzis.rsystem.Permission;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidzis.rsystem.R;

public class PermissionManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_management);
        final Button btGetAll = (Button) findViewById(R.id.btGetAllPermissions);
        final Button btRemove = (Button) findViewById(R.id.btRemovePermission);
        final Button btSave = (Button) findViewById(R.id.btSavePermission);

        assert btGetAll != null;
        btGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionManagementActivity.this, PermissionGetAllActivity.class);
                PermissionManagementActivity.this.startActivity(intent);
            }
        });
        assert btRemove != null;
        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionManagementActivity.this, PermissionRemoveActivity.class);
                PermissionManagementActivity.this.startActivity(intent);
            }
        });
        assert btSave != null;
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionManagementActivity.this, PermissionSaveActivity.class);
                PermissionManagementActivity.this.startActivity(intent);
            }
        });
    }
}

