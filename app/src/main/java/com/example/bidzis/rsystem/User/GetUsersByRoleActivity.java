package com.example.bidzis.rsystem.User;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bidzis.rsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GetUsersByRoleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_users_by_role);
        final Spinner spinnerSetRole = (Spinner) findViewById(R.id.spinnerGetUserByRole);
        final Button btGetUsersByRole = (Button) findViewById(R.id.btGetUsersByRoleGetUsersByRole);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.role_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSetRole.setAdapter(adapter);

        assert btGetUsersByRole != null;
        btGetUsersByRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final JSONArray[] jsonArray = {null};
                String url = getString(R.string.ip)+"/projektz/users/getByRole/"+spinnerSetRole.getSelectedItem().toString();
                JsonArrayRequest request = new JsonArrayRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                jsonArray[0] = response;
                                ArrayList<String> value = new ArrayList<>();
                                if (jsonArray[0] != null) {
                                    int len = jsonArray[0].length();
                                    for (int i=0;i<len;i++){
                                        try {
                                            JSONObject jsonObject = (JSONObject) jsonArray[0].get(i);
                                            JSONObject roleJson = (JSONObject) jsonObject.get("role");
                                            value.add(i, jsonObject.getString("name")+" "+jsonObject.getString("surname")+"\n"+jsonObject.getString("email")+"\n"+roleJson.getString("name"));

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                final ListView listview = (ListView) findViewById(R.id.listviewShowUsersByRole);
                                Iterator it = value.iterator();

                                final ArrayList<String> list = new ArrayList<String>();
                                while ( it.hasNext( ) ) {
                                    list.add((String) it.next());
                                }
                                final StableArrayAdapter adapter = new StableArrayAdapter(GetUsersByRoleActivity.this,
                                        android.R.layout.simple_list_item_1, list);
                                listview.setAdapter(adapter);
                                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        final ArrayList<Integer> help = new ArrayList<Integer>();
                                        for (int j = 0;j < 10;j++){
                                            help.add(j);
                                        }

//                                final String aTekst = ((TextView)view).getText().toString();
//                                String aId = String.valueOf(aTekst.charAt(0));
//                                for (int i = 1; i < aTekst.length();i++)
//                                {
//                                    if(aTekst.charAt(i)=='\n')
//                                        break;
//                                    if(help.contains(Integer.valueOf(String.valueOf(aTekst.charAt(i))))){
//                                        aId = aId + String.valueOf(aTekst.charAt(i));
//                                    }else break;
//                                }
                                    }
                                });
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                    Toast.makeText(getApplicationContext(), "Timeouttt",
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
    private class StableArrayAdapter extends ArrayAdapter<String> {

        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId,
                                  List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

    }
}
