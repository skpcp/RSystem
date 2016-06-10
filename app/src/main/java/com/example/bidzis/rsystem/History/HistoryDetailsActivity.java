package com.example.bidzis.rsystem.History;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bidzis.rsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class HistoryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_details);
        String id = "";
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");
        final TextView tvName = (TextView) findViewById(R.id.tvNameHistoryDetails);
        final TextView tvSurname = (TextView) findViewById(R.id.tvSurnameHistoryDetails);
        final TextView tvEmail = (TextView) findViewById(R.id.tvEmailHistoryDetails);
        final TextView tvLogin = (TextView) findViewById(R.id.tvLoginHistoryDetails);
        final TextView tvActive = (TextView) findViewById(R.id.tvActiveHistoryDetails);
        final TextView tvRole = (TextView) findViewById(R.id.tvRoleHistoryDetails);
        final TextView tvType = (TextView) findViewById(R.id.tvTypeHistoryDetails);
        final TextView tvDate = (TextView) findViewById(R.id.tvDateHistoryDetails);
        final TextView tvDescription = (TextView) findViewById(R.id.tvDescriptionHistoryDetails);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = getString(R.string.ip) + "/projektz/histories/getById/" + id;

        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = (JSONObject) response.get("user");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject jsonObject1 = null;
                        try {
                            jsonObject1 = (JSONObject) jsonObject.get("role");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            tvName.setText("Name: " + jsonObject.getString("name"));
                            tvSurname.setText("Surname: " + jsonObject.getString("surname"));
                            tvEmail.setText("Email: " + jsonObject.getString("email"));
                            tvLogin.setText("Login: " + jsonObject.getString("login"));
                            tvActive.setText("Active: " + jsonObject.getString("active"));
                            tvRole.setText("Role: " + jsonObject1.getString("name"));
                            tvType.setText("Type: " + response.getString("type"));
                            tvDate.setText("Date: " + response.getString("date"));
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
