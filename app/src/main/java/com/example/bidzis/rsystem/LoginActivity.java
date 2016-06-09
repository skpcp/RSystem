package com.example.bidzis.rsystem;

import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bidzis.rsystem.Ticket.SendTicketActivity;
import com.example.bidzis.rsystem.User.UserManagementActivity;
import com.example.bidzis.rsystem.User.UserSiteActivity;
import com.example.bidzis.rsystem.UserSettings.UserSettingsActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button btSignIn = (Button) findViewById(R.id.btSignin);
        final EditText etLogin = (EditText) findViewById(R.id.etLogin);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        assert btSignIn != null;
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLoginString = "{\n" +
                        "  \"login\": \"string\",\n" +
                        "  \"password\": \"string\"\n" +
                        "}";
                JSONObject userLogin = new JSONObject();
                try {
                    userLogin = new JSONObject(userLoginString);
                    userLogin.put("login",etLogin.getText().toString());
                    userLogin.put("password",etPassword.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                singInUser(userLogin);



//                Intent intent  = new Intent(LoginActivity.this, SendTicketActivity.class);
//                LoginActivity.this.startActivity(intent);
            }
        });
    }
    public void singInUser(JSONObject userLogin){
        Intent intent  = new Intent(LoginActivity.this, UserSiteActivity.class);
        LoginActivity.this.startActivity(intent);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url  = getString(R.string.ip) + "/projektz/users/userLogin";
        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.POST, url, userLogin, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONObject object = new JSONObject();
                        try {
                            object = (JSONObject) response.get("role");
                            if ("ADMIN".equals(object.getString("name")))
                            {
                                Intent intent  = new Intent(LoginActivity.this, UserSiteActivity.class);
                                try {
                                    intent.putExtra("id",response.getString("id"));
                                    intent.putExtra("login",response.getString("login"));


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                LoginActivity.this.startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
                                    Toast.makeText(getApplicationContext(), "Nie znaleziono użytkownika w bazie",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });


        requestQueue.add(request);
    }
}
