package com.example.bidzis.rsystem.Role;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.bidzis.rsystem.R;

public class RoleSave extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_save);

        final EditText  etRoleName = (EditText) findViewById(R.id.etRoleName);
        final EditText etPermissionName = (EditText) findViewById(R.id.etRolePermissionName);

        final Button btSave = (Button) findViewById(R.id.btRoleSave);
/*
        final JSONArray[] jsonArray = {null};
        final Map<String, String> map = new HashMap<String, String>();
        final EditText etDescription = (EditText) findViewById(R.id.etDescriptionHistorySaveWithAttachment);
        final Button btSave = (Button) findViewById(R.id.btSaveHistorySaveWithAttachment);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final String user = "GandalfTheGray";
        final String[] idAttachment = {""};
        final boolean[] flaga = {false};

        final Spinner spinner = (Spinner) findViewById(R.id.spinnerTypeHistorySaveWithAttachment);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        String jsonString = "{\n" +
                "  \"id\": \"0\", +
                "  \"name\": \"string\"," +"
                "  \"permissions\": [\n" +
                "    {\n" +
                "      \"id\": 0\n" +
                "    }\n" +
                "  ],\n" +
                "  \"type\": \"WEWNETRZNY\",\n" +
                "  \"user\": \"string\"\n" +
                "}";
        {
                "name": "string",
                "permissions": [
            {
                "id": 51
            }
            ]
        }
        JSONObject json = null;
        try {
            json = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assert btSave != null;
        final JSONObject finalJson = json;
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flaga[0]) {
                    try {
                        finalJson.put("type", spinner.getSelectedItem().toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        assert finalJson != null;
                        assert etDescription != null;
                        finalJson.put("description", etDescription.getText().toString());
                        finalJson.put("user", user);
                        JSONArray jsonArray1 = new JSONArray();
                        String ajdik = "{\n" +
                                "      \"id\": 0\n" +
                                "    }";
                        JSONObject jsonObject = new JSONObject(ajdik);
                        jsonObject.put("id", idAttachment[0].toString());
                        jsonArray1.put(jsonObject);
                        finalJson.put("attachments", jsonArray1);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String url = getString(R.string.ip) + "/projektz/histories/saveHistoryWithAttachments";
                    JsonObjectRequest request = new JsonObjectRequest
                            (Request.Method.POST, url, finalJson, new Response.Listener<JSONObject>() {


                                @Override
                                public void onResponse(JSONObject response) {

                                    Toast.makeText(getApplicationContext(), "History Added",

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
                else {
                    Toast.makeText(getApplicationContext(), "You have to add the attachment",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        String url = getString(R.string.ip) + "/projektz/attachments/getAllAtachments";
        JsonArrayRequest request2 = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        jsonArray[0] = response;
                        ArrayList<String> value = new ArrayList<>();
                        if (jsonArray[0] != null) {
                            int len = jsonArray[0].length();
                            for (int i = 0; i < len; i++) {
                                try {
                                    JSONObject jsonObject = (JSONObject) jsonArray[0].get(i);
                                    value.add(i,"Name: " + jsonObject.getString("name") + "\n"+ "File_name: " + jsonObject.getString("file_name")+ "\n");
                                    map.put(jsonObject.getString("name"),jsonObject.getString("id"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        final ListView listview = (ListView) findViewById(R.id.lvHistorySaveWithAttachment);
                        Iterator it = value.iterator();

                        final ArrayList<String> list = new ArrayList<String>();
                        while (it.hasNext()) {
                            list.add((String) it.next());
                        }
                        final StableArrayAdapter adapter = new StableArrayAdapter(HistorySaveWithAttachmentActivity.this,
                                android.R.layout.simple_list_item_1, list);
                        assert listview != null;
                        listview.setAdapter(adapter);
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                final String aTekst = ((TextView)view).getText().toString();
                                String aName = String.valueOf(aTekst.charAt(6));
                                for (int i = 7; i < aTekst.length();i++)
                                {
                                    if(aTekst.charAt(i)=='\n') {
                                        break;
                                    }else aName = aName + String.valueOf(aTekst.charAt(i));
                                }
                                String value = map.get(aName);
                                idAttachment[0] = value;
                                flaga[0] = true;


                            }
                        });
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
                            Toast.makeText(getApplicationContext(), "Błąd",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        requestQueue.add(request2);

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





*/














    }
}
