package com.example.bidzis.rsystem.History;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class HistorySaveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_save);

        final EditText etDescription = (EditText) findViewById(R.id.etDescriptionHistorySaveWithAttachment);
        final Button btSave = (Button) findViewById(R.id.btSaveHistorySaveWithAttachment);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String user = "GandalfTheGray";
        //final String type = "WEWNETRZNY";

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerTypeHistorySaveWithAttachment);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        String jsonString = "{\"description\": \"string\"\n" + "}";
        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assert btSave != null;
        final JSONObject finalJson = json;
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    finalJson.put("type", spinner.getSelectedItem().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    assert finalJson != null;
                    assert etDescription != null;
                    finalJson.put("description",etDescription.getText().toString());
                    finalJson.put("user", user);
                    //finalJson.put("type", type);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = getString(R.string.ip) + "/projektz/histories/saveHistory";
                JsonObjectRequest request = new JsonObjectRequest
                        (Request.Method.POST, url, finalJson, new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {

                                Toast.makeText(getApplicationContext(), "History Added",

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
                                            Toast.makeText(getApplicationContext(), "Server Error",
                                                    Toast.LENGTH_LONG).show();
                                        } else if (error instanceof NetworkError) {
                                            Toast.makeText(getApplicationContext(), "Network Error",
                                                    Toast.LENGTH_LONG).show();
                                        } else if (error instanceof ParseError) {
                                            Toast.makeText(getApplicationContext(), "Parse Error",
                                                    Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });


                requestQueue.add(request);

            }
        });

    }
}