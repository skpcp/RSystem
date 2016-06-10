package com.example.bidzis.rsystem.User;

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
import com.example.bidzis.rsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

public class SaveUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_user);

        final EditText etEmail = (EditText) findViewById(R.id.etEmailSaveUser);
        final EditText etLogin = (EditText) findViewById(R.id.etLoginSaveUser);
        final EditText etPassword = (EditText) findViewById(R.id.etPasswordSaveUser);
        final EditText etName = (EditText) findViewById(R.id.etNameSaveUser);
        final EditText etSurname = (EditText) findViewById(R.id.etSurnameSaveUser);

        final Button btSaveUser = (Button) findViewById(R.id.btSaveUserSaveUser);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        assert btSaveUser != null;
        btSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userString = "{\n" +
                        "  \"email\": \"strings\",\n" +
                        "  \"login\": \"strindg\",\n" +
                        "  \"md5pass\": \"string\",\n" +
                        "  \"name\": \"stridng\",\n" +
                        "  \"surname\": \"stdring\"\n" +
                        "}";
                JSONObject userJson = new JSONObject();
                try {
                    userJson = new JSONObject(userString);
                    userJson.put("email",etEmail.getText().toString());
                    userJson.put("login",etLogin.getText().toString());
                    userJson.put("md5pass",etPassword.getText().toString());
                    userJson.put("name",etName.getText().toString());
                    userJson.put("surname",etSurname.getText().toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                String passwordPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
                boolean zmiennaPomocnicza = false;
                if (email.matches(emailPattern)) {
                    if (password.matches(passwordPattern)) {
                        zmiennaPomocnicza = true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid password ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }
                if (zmiennaPomocnicza) {
                    String urlSave = getString(R.string.ip) + "/projektz/users/saveUser";
                    JsonObjectRequest request = new JsonObjectRequest
                            (Request.Method.POST, urlSave, userJson, new Response.Listener<JSONObject>() {


                                @Override
                                public void onResponse(JSONObject response) {
                                    Toast.makeText(getApplicationContext(), "Register Succesful",
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
                                        Toast.makeText(getApplicationContext(), "Nie znaleziono użytkownika w bazie",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                    requestQueue.add(request);
                }

            }
        });



    }
}
