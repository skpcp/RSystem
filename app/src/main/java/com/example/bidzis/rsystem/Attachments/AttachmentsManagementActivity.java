package com.example.bidzis.rsystem.Attachments;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidzis.rsystem.History.HistoryGetAllActivity;
import com.example.bidzis.rsystem.History.HistoryRemoveActivity;
import com.example.bidzis.rsystem.History.HistorySaveActivity;
import com.example.bidzis.rsystem.History.HistorySaveWithAttachmentActivity;
import com.example.bidzis.rsystem.R;

public class AttachmentsManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attachments_management);

        final Button btGetAll = (Button) findViewById(R.id.btGetAllAttachments);
        final Button btRemove = (Button) findViewById(R.id.btRemoveAttachment);
        final Button btGetByType = (Button) findViewById(R.id.btGetAttachmentByMineType);

        assert btGetAll != null;
        btGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttachmentsManagementActivity.this, AttachmentsGetAllActivity.class);
                AttachmentsManagementActivity.this.startActivity(intent);
            }
        });
        assert btRemove != null;
        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttachmentsManagementActivity.this, AttachmentsRemoveActivity.class);
                AttachmentsManagementActivity.this.startActivity(intent);
            }
        });
        assert btGetByType != null;
        btGetByType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AttachmentsManagementActivity.this, AttachmentsGetByMineTypeActivity.class);
                AttachmentsManagementActivity.this.startActivity(intent);
            }
        });
    }
}
