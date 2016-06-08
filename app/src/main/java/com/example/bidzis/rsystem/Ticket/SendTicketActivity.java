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

import com.example.bidzis.rsystem.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SendTicketActivity extends AppCompatActivity {

    Uri selectedfile;
    String obraz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_ticket);

        final EditText etProjectName = (EditText) findViewById(R.id.etProjectNameSendTicket);
        final EditText etDescription = (EditText) findViewById(R.id.etDescriptionSendTicket);
        final Button btAddAttachment = (Button) findViewById(R.id.btAddAttachmentsSendTicket);
        final Button btSendTicket = (Button) findViewById(R.id.btSendTicketSendTicked);
        final Spinner spinnerPriority = (Spinner) findViewById(R.id.spinner);

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
    }
    public String getFilePath() {
        String path = getRealPathFromURI(selectedfile);
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
