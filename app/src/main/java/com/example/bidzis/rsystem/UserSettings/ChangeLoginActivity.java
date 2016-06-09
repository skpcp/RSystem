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

public class ChangeLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_login);

        final EditText etNewLogin = (EditText) findViewById(R.id.etNewLoginChangeLogin);
        final EditText etPassword = (EditText) findViewById(R.id.etPasswordChangeLogin);

        final Button btChangeLogin = (Button) findViewById(R.id.btChangeLoginChangeLogin);


        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        final String aId = extras.getString("id");

        assert btChangeLogin != null;
        btChangeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginChangeString = "{\n" +
                        "  \"id\": 0,\n" +
                        "  \"login\": \"string\",\n" +
                        "  \"password\": \"string\"\n" +
                        "}";
                JSONObject loginChangeJson = new JSONObject();
                try {
                    loginChangeJson = new JSONObject(loginChangeString);
                    loginChangeJson.put("id",aId);
                    loginChangeJson.put("login",etNewLogin.getText().toString());
                    loginChangeJson.put("password",etPassword.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String url = getString(R.string.ip)+"/projektz/users/changeLogin";
                JsonObjectRequest request = new JsonObjectRequest
                        (Request.Method.POST, url, loginChangeJson, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(getApplicationContext(), "Change Login Sucesfull",
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
