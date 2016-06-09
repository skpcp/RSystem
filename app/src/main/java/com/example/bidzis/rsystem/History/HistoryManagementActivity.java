package com.example.bidzis.rsystem.History;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidzis.rsystem.Permission.PermissionGetAllActivity;
import com.example.bidzis.rsystem.Permission.PermissionRemoveActivity;
import com.example.bidzis.rsystem.R;

public class HistoryManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_management);
        final Button btGetAll = (Button) findViewById(R.id.btGetAllHistories);
        final Button btRemove = (Button) findViewById(R.id.btRemoveHistory);
        final Button btSave = (Button) findViewById(R.id.btSaveHistory);
        final Button btSaveWithAttachment = (Button) findViewById(R.id.btSaveHistoryWithAttachment);

        assert btGetAll != null;
        btGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryManagementActivity.this, HistoryGetAllActivity.class);
                HistoryManagementActivity.this.startActivity(intent);
            }
        });
        assert btRemove != null;
        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryManagementActivity.this, HistoryRemoveActivity.class);
                HistoryManagementActivity.this.startActivity(intent);
            }
        });
        assert btSave != null;
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryManagementActivity.this, HistorySaveActivity.class);
                HistoryManagementActivity.this.startActivity(intent);
            }
        });
        assert btSaveWithAttachment != null;
        btSaveWithAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryManagementActivity.this, HistorySaveWithAttachmentActivity.class);
                HistoryManagementActivity.this.startActivity(intent);
            }
        });
    }
}
