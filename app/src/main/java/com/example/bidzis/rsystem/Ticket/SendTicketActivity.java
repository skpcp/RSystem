package com.example.bidzis.rsystem.Ticket;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bidzis.rsystem.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SendTicketActivity extends AppCompatActivity {

    Uri selectedfile;
    String obraz;
    String fileName;
    String mineType;
    final String[] id = {""};
    final String[] idAttachment = {""};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_ticket);
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final EditText etProjectName = (EditText) findViewById(R.id.etProjectNameSendTicket);
        final EditText etDescription = (EditText) findViewById(R.id.etDescriptionSendTicket);
        final Button btAddAttachment = (Button) findViewById(R.id.btAddAttachmentsSendTicket);
        final Button btSendTicket = (Button) findViewById(R.id.btSendTicketSendTicket);
        final Spinner spinnerPriority = (Spinner) findViewById(R.id.spinner);


        String ticketWithAttachmentString = "{\n" +
                "  \"attachments\": [\n" +
                "    0\n" +
                "  ],\n" +
                "  \"description\": \"string\",\n" +
                "  \"id\": 0,\n" +
                "  \"kind\": \"ZGLOSZENIE\",\n" +
                "  \"priority\": \"string\",\n" +
                "  \"project\": \"string\",\n" +
                "  \"type\": \"WEWNETRZNY\",\n" +
                "  \"user\": \"string\"\n" +
                "}";
        String tickedWichoutAttachmentString = "{\n" +
                "  \"description\": \"string\",\n" +
                "  \"id\": 0,\n" +
                "  \"kind\": \"ZGLOSZENIE\",\n" +
                "  \"priority\": \"string\",\n" +
                "  \"project\": \"string\",\n" +
                "  \"type\": \"WEWNETRZNY\",\n" +
                "  \"user\": \"string\"\n" +
                "}";
        JSONObject userLogin = new JSONObject();

        assert btAddAttachment != null;
        btAddAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent()
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);

            }
        });
        assert btSendTicket != null;
        btSendTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String binariesID = "";
                String attachmnetID = "";
                String binaries = getBinaries();
                String binariesString = "{\"binary\": \""+binaries+"\"}";
                String url  = getString(R.string.ip) + "/projektz/binaries/saveBinary";
                JSONObject binariesJson = new JSONObject();
                try {
                    binariesJson = new JSONObject(binariesString);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                saveBinaries(url,binariesJson);
                binariesID = id[0];


            }
        });
    }
    public void saveAttachments(String binariesID) throws JSONException {
        String attachmentString = "{\n" + "  \"binary\": 50,\n" +
                "  \"file_name\": \"string\",\n" +
                "  \"id\": 0,\n" +
                "  \"mine_type\": \"string\",\n" +
                "  \"name\": \"string\",\n" +
                "  \"type\": \"WEWNETRZNY\"\n" +
                "}";
        final String[] id = {""};
        String url  = getString(R.string.ip) + "/projektz/attachments/saveAttachment";
        JSONObject attachmentJson = new JSONObject();
        try {
            attachmentJson = new JSONObject(attachmentString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        attachmentJson.put("binary",binariesID);
        attachmentJson.put("file_name",fileName);
        attachmentJson.put("name",fileName);
        attachmentJson.put("mine_type",mineType);

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.POST, url, attachmentJson, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            idAttachment[0] = response.getString("id");
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
    public String getBinaries() {
        String path = getRealPathFromURI(selectedfile);
        fileName=path.substring(path.lastIndexOf("/")+1);
        mineType = path.substring(path.lastIndexOf(".")+1);
        File file = new File(path);
        String imageDataString = "";
        try {
            FileInputStream imageInFile = new FileInputStream(file);

            byte imageData[] = new byte[(int) file.length()];

            imageInFile.read(imageData);


            imageDataString = encodeImage(imageData);
            obraz = imageDataString;

            imageInFile.close();


            System.out.println("Image Successfully Manipulated!");
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return imageDataString;
    }
    public static String encodeImage(byte[] imageByteArray) {
        // return new String(Base64.encodeBase64URLSafeString(imageByteArray));
        //return Base64.encodeBase64String(imageByteArray);
        return android.util.Base64.encodeToString(imageByteArray,1);
    }
    public void saveBinaries(String url, JSONObject binariesJson){

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.POST, url, binariesJson, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            id[0] = response.getString("id");
                            try {
                                saveAttachments(id[0]);
                            } catch (JSONException e) {
                                e.printStackTrace();
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

    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString) {
        return android.util.Base64.decode(imageDataString,1);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode==RESULT_OK) {
            selectedfile = data.getData(); //The uri with the location of the file
        }
    }
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }



}
