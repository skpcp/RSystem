package com.example.bidzis.rsystem.Project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

public class ProjectChangeNameResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_change_name_result);

        String id = "";
        Bundle extras = getIntent().getExtras();
        id = extras.getString("id");

        final EditText etNewName = (EditText) findViewById(R.id.etProjectsNameProjectChangeNameResult);
        final Button btChange = (Button) findViewById(R.id.btChangeProjectChangeNameResult);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        String jsonString = "{\n" +
                "  \"id\": 0,\n" +
                "  \"name\": \"string\"\n" +
                "}";

        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final JSONObject finalJson = json;
        assert btChange != null;
        final String finalId = id;
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    assert finalJson != null;
                    assert etNewName != null;
                    finalJson.put("id", finalId);
                    finalJson.put("name", etNewName.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = getString(R.string.ip) + "/projektz/projects/changeNameForProject";
                JsonObjectRequest request = new JsonObjectRequest
                        (Request.Method.POST, url, finalJson, new Response.Listener<JSONObject>() {


                            @Override
                            public void onResponse(JSONObject response) {

                                Toast.makeText(getApplicationContext(), "Project Changed",

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
