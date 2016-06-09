package com.example.bidzis.rsystem.Project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidzis.rsystem.History.HistoryGetAllActivity;
import com.example.bidzis.rsystem.History.HistoryRemoveActivity;
import com.example.bidzis.rsystem.History.HistorySaveActivity;
import com.example.bidzis.rsystem.R;

public class ProjectManagementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_management);
        final Button btGetAll = (Button) findViewById(R.id.btGetAllProjects);
        final Button btChangeName = (Button) findViewById(R.id.btChangeProjectsName);
        final Button btRemove = (Button) findViewById(R.id.btRemoveProject);
        final Button btSave = (Button) findViewById(R.id.btSaveProject);

        assert btGetAll != null;
        btGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectManagementActivity.this, ProjectGetAllActivity.class);
                ProjectManagementActivity.this.startActivity(intent);
            }
        });
        assert btRemove != null;
        btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectManagementActivity.this, ProjectRemoveActivity.class);
                ProjectManagementActivity.this.startActivity(intent);
            }
        });
        assert btSave != null;
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectManagementActivity.this, ProjectSaveActivity.class);
                ProjectManagementActivity.this.startActivity(intent);
            }
        });
        assert btChangeName != null;
        btChangeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectManagementActivity.this, ProjectChangeNameActivity.class);
                ProjectManagementActivity.this.startActivity(intent);
            }
        });
    }
}