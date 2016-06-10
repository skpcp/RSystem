package com.example.bidzis.rsystem.Priority;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class PriorityShowByName extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority_show_by_name);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);


        final EditText etInsertName  = (EditText) findViewById(R.id.etPriorityShowNameInsertName);
        final Button  btShowByName = (Button) findViewById(R.id.btPriorityShowNameInsertName);

        final TextView tvShowName  = (TextView) findViewById(R.id.tvPriorityShowByNameName);
        final TextView  tvResponse = (TextView) findViewById(R.id.tvPriorityShowByNameResponseTime);

        assert etInsertName != null;
        assert btShowByName != null;
        btShowByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = getString(R.string.ip)+"/projektz/priorities/getByName/"+etInsertName.getText().toString();
                JsonObjectRequest request = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                assert tvShowName != null;
                                tvShowName.setVisibility(View.VISIBLE);
                                assert tvResponse != null;
                                tvResponse.setVisibility(View.VISIBLE);
                                JSONObject json = null;

                                try {
                                    tvShowName.setText("Name: "+response.getString("name"));
                                    tvResponse.setText("Response time: "+response.getString("responseTime"));


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
        });



















    }
}
