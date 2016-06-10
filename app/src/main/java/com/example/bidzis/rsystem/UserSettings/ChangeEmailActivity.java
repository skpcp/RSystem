package com.example.bidzis.rsystem.UserSettings;

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

public class ChangeEmailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);

        final EditText etEmail = (EditText) findViewById(R.id.etNewEmailChangeEmail);
        final EditText etPassword = (EditText) findViewById(R.id.etPasswordChangeEmail);

        final Button btChangeEmail = (Button) findViewById(R.id.btChangeEmailChangeEmail);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        final String aId = extras.getString("id");

        assert btChangeEmail != null;
        btChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailChangeString = "{\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"id\": 0,\n" +
                        "  \"password\": \"string\"\n" +
                        "}";
                JSONObject emailChangeJson = new JSONObject();
                try {
                    emailChangeJson = new JSONObject(emailChangeString);
                    emailChangeJson.put("id",aId);
                    emailChangeJson.put("email",etEmail.getText().toString());
                    emailChangeJson.put("password",etPassword.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String email = etEmail.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (email.matches(emailPattern)) {
                    String url = getString(R.string.ip) + "/projektz/users/changeEmail";
                    JsonObjectRequest request = new JsonObjectRequest
                            (Request.Method.POST, url, emailChangeJson, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(getApplicationContext(), "Change Email Sucesfull",
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
                }else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
