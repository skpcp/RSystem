package com.example.bidzis.rsystem.Project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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

public class ProjectDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);

        String id = "";
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        final TextView tvName = (TextView) findViewById(R.id.tvNameProjectDetails);
        final TextView tvVersion = (TextView) findViewById(R.id.tvVersionProjectDetails);
        final TextView tvPriority = (TextView) findViewById(R.id.tvPriorityProjectDetails);
        final TextView tvResponseTime = (TextView) findViewById(R.id.tvResponseTimeProjectDetails);
        final TextView tvDescription = (TextView) findViewById(R.id.tvDescriptionProjectDetails);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = getString(R.string.ip) + "/projektz/projects/getById/" + id;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = (JSONObject) response.get("priority");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            tvName.setText("Name: " + response.getString("name"));
                            tvVersion.setText("Version: " + response.getString("version"));
                            tvPriority.setText("Priority: " + jsonObject.getString("name"));
                            tvResponseTime.setText("Response Time: " + jsonObject.getString("responseTime"));
                            tvDescription.setText("Description: " + response.getString("description"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
                            Toast.makeText(getApplicationContext(), "Nie znaleziono użytkownika w bazie",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });

        requestQueue.add(request);

    }
}


