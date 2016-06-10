package com.example.bidzis.rsystem.Attachments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.bidzis.rsystem.R;

public class AttachmentsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attachments_details);

        String id = "";
        Bundle extras = getIntent().getExtras();
        String blob = extras.getString("binary");

        assert blob != null;
        byte[] bitmapdata = decodeImage(blob);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata , 0, bitmapdata .length);




        ImageView myImage = (ImageView) findViewById(R.id.imageView2);

        myImage.setImageBitmap(bitmap);


    }
    public static byte[] decodeImage(String imageDataString) {
        return android.util.Base64.decode(imageDataString,1);
    }

}
