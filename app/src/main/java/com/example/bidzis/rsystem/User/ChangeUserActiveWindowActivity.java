package com.example.bidzis.rsystem.User;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class ChangeUserActiveWindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_active_window);

        final Spinner spinnerSelectActivity = (Spinner) findViewById(R.id.spinnerSelectUserActiveChangeUserActive);
        final Button btChangeActivity = (Button) findViewById(R.id.btChangeActivityChangeUserActive);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activity_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectActivity.setAdapter(adapter);

        Bundle extras = getIntent().getExtras();
        final String aId = extras.getString("id");

        assert btChangeActivity != null;
        btChangeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String activity = "";
                if ("Active".equals(spinnerSelectActivity.getSelectedItem().toString())){
                    activity = "true";
                }else activity = "false";
                String activeString = "{\n" +
                        "  \"active\": true,\n" +
                        "  \"id\": 0\n" +
                        "}";
                JSONObject activeJson = new JSONObject();
                try {
                    activeJson = new JSONObject(activeString);
                    activeJson.put("id",aId);
                    activeJson.put("active",activity);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = getString(R.string.ip)+"/projektz/users/changeActivity";
                JsonObjectRequest request = new JsonObjectRequest
                        (Request.Method.POST, url, activeJson, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Change Activity Sucesfull",
                                        Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {

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
                                    Toast.makeText(getApplicationContext(), "Activate Succesful",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                requestQueue.add(request);
            }
        });
    }
}
