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

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final EditText etOldPassword = (EditText) findViewById(R.id.etOldPasswordChangePassword);
        final EditText etNewPassword = (EditText) findViewById(R.id.etNewPasswordChangePassword);

        final Button btChangePassword = (Button) findViewById(R.id.btChangePasswordChangePassword);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        Bundle extras = getIntent().getExtras();
        final String aId = extras.getString("id");

        assert btChangePassword != null;
        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passwordChangeString = "{\n" +
                        "  \"id\": 0,\n" +
                        "  \"newPassword\": \"string\",\n" +
                        "  \"oldPassword\": \"string\"\n" +
                        "}";
                JSONObject passwordChangeJson = new JSONObject();
                try {
                    passwordChangeJson = new JSONObject(passwordChangeString);
                    passwordChangeJson.put("id",aId);
                    passwordChangeJson.put("oldPassword",etOldPassword.getText().toString());
                    passwordChangeJson.put("newPassword",etNewPassword.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String password = etNewPassword.getText().toString().trim();

                String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
                if (password.matches(passwordPattern)) {
                    String url = getString(R.string.ip) + "/projektz/users/changePasswordForUser";
                    JsonObjectRequest request = new JsonObjectRequest
                            (Request.Method.POST, url, passwordChangeJson, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(getApplicationContext(), "Change Password Sucesfull",
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
                    Toast.makeText(getApplicationContext(), "Invalid password ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}