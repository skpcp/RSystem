package com.example.bidzis.rsystem.Priority;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.bidzis.rsystem.R;

public class PriorityManagement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_management);

        final Button btChangePriorityName = (Button) findViewById(R.id.btPriorityChangePriorityName);
        final Button btShowAllPriority = (Button) findViewById(R.id.btPriorityShowAll);
        final Button btShowByName = (Button) findViewById(R.id.btPriorityShowByName);
        final Button btSavePriority = (Button) findViewById(R.id.btPrioritySave);
        final Button btRemovePriority = (Button) findViewById(R.id.btPriorityRemove);

        assert btChangePriorityName != null;
        btChangePriorityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PriorityManagement.this, PriorityChangePriorityName.class);
                PriorityManagement.this.startActivity(intent);

            }
        });
        assert btShowAllPriority != null;
        btShowAllPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PriorityManagement.this, PriorityShowAll.class);
                PriorityManagement.this.startActivity(intent);

            }
        });
        assert btShowByName != null;
        btShowByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PriorityManagement.this, PriorityShowByName.class);
                PriorityManagement.this.startActivity(intent);

            }
        });
        assert btSavePriority != null;
        btSavePriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PriorityManagement.this, PrioritySave.class);
                PriorityManagement.this.startActivity(intent);

            }
        });
        assert btRemovePriority != null;
        btRemovePriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PriorityManagement.this, PriorityRemove.class);
                PriorityManagement.this.startActivity(intent);

            }
        });













    }
}
