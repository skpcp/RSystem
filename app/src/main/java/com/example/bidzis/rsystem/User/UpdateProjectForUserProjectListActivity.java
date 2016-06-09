package com.example.bidzis.rsystem.User;

import android.content.Context;
import android.content.Intent;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.bidzis.rsystem.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class UpdateProjectForUserProjectListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_project_for_user_project_list);


        final Map<String, String> map = new HashMap<String, String>();
        Bundle extras = getIntent().getExtras();
        final String aIdUser = extras.getString("id");

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final JSONObject[] jsonObject = {null};
        final JSONArray[] jsonArray = {null};
        String url = getString(R.string.ip)+"/projektz/projects/getAll";
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
                                    jsonObject[0] = (JSONObject) jsonArray[0].get(i);
                                    JSONObject priorityJson = (JSONObject) jsonObject[0].get("priority");
                                    value.add(i, jsonObject[0].getString("name")+"\n"+"Version: "+ jsonObject[0].getString("version")+"\n"+"Priority: "+ priorityJson.getString("name")+"\n"+"Response time: "+priorityJson.getString("responseTime"));
                                    map.put(jsonObject[0].getString("name"),jsonObject[0].getString("id"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        final ListView listview = (ListView) findViewById(R.id.listViewUpdateProjectForUserProjectList);
                        Iterator it = value.iterator();

                        final ArrayList<String> list = new ArrayList<String>();
                        while ( it.hasNext( ) ) {
                            list.add((String) it.next());
                        }
                        final StableArrayAdapter adapter = new StableArrayAdapter(UpdateProjectForUserProjectListActivity.this,
                                android.R.layout.simple_list_item_1, list);
                        listview.setAdapter(adapter);
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                final String aTekst = ((TextView)view).getText().toString();
                                String aName = String.valueOf(aTekst.charAt(0));
                                for (int i = 1; i < aTekst.length();i++)
                                {
                                    if(aTekst.charAt(i)=='\n') {
                                        break;
                                    }else aName = aName + String.valueOf(aTekst.charAt(i));
                                }
                                String value = map.get(aName);
                                updateProjects(value,aIdUser);
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
    public void updateProjects(String aIdProject, String aIdUser){
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String addProjectString = "{\n" +
                "  \"id\": 0,\n" +
                "  \"projects\": [\n" +
                "    {\n" +
                "      \"id\": 0\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        JSONObject addProjectJson = new JSONObject();
        try {
            addProjectJson = new JSONObject(addProjectString);
            JSONArray projectArray = new JSONArray();
            projectArray = (JSONArray) addProjectJson.get("projects");
            JSONObject oneProject = new JSONObject();
            oneProject = projectArray.getJSONObject(0);
            oneProject.put("id",aIdProject);
            //projectArray.put(oneProject);
            addProjectJson.put("projects",projectArray);
            addProjectJson.put("id",aIdUser);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        String url = getString(R.string.ip)+"/projektz/users/updateProjectsForUser";
        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.POST, url, addProjectJson, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Change Project For User Sucesfull",
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
}

