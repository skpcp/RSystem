package com.example.bidzis.rsystem.Priority;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bidzis.rsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class PriorityChangePriorityNameWindow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_change_priority_name_window);

        String id = "";
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        //String ert= "";

        final EditText  etChangePriorityName = (EditText) findViewById(R.id.etChangePriorityNameWindow);
        final Button  btChangePriorityName = (Button) findViewById(R.id.btChangePriorityNameWindow);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        String example =  "{\n" +
                "  \"aName\": \"Natychmiast\",\n" +
                "  \"id\": 50\n" +
                "}";

        JSONObject prioritySave = new JSONObject();
        try{
            prioritySave = new JSONObject(example);
        } catch (JSONException e){
            e.printStackTrace();
        }


        assert btChangePriorityName !=null;
        final JSONObject finalNameChanged = prioritySave;
        final String finalId = id;
        btChangePriorityName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String url = getString(R.string.ip) + "/projektz/priorities/changePriorityName/";
                try{
                    assert etChangePriorityName != null;
                    finalNameChanged.put("aName", etChangePriorityName.getText().toString());
                    finalNameChanged.put("id", finalId);

                } catch (JSONException e){
                    e.printStackTrace();
                }

                JsonObjectRequest request = new JsonObjectRequest
                        (
                                Request.Method.POST, url, finalNameChanged , new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Priority name changed",
                                        Toast.LENGTH_LONG).show();
                            }
                        },
                                new Response.ErrorListener() {

                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                            Toast.makeText(getApplicationContext(), "Timeout",
                                                    Toast.LENGTH_LONG).show();
                                        } else if (error instanceof AuthFailureError) {
                                            Toast.makeText(getApplicationContext(), "1",
                                                    Toast.LENGTH_LONG).show();
                                        } else if (error instanceof ServerError) {
                                            Toast.makeText(getApplicationContext(), "Bląd serwera",
                                                    Toast.LENGTH_LONG).show();
                                        } else if (error instanceof NetworkError) {
                                            Toast.makeText(getApplicationContext(), "Problem z połączeniem internetowym",
                                                    Toast.LENGTH_LONG).show();
                                        } else if (error instanceof ParseError) {
                                            Toast.makeText(getApplicationContext(), "Błąd",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                        );
                requestQueue.add(request);
            }
        });




    }
}
