package com.example.lolaabudu.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    //this Activity uses implicit intent

    private EditText mLocationEditText;   //for openLocation
    private EditText mShareTextEditText;   //for shareText



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText mWebsiteEditText = findViewById(R.id.website_edittext);     //saves what is put inside the editText view as an id
        Button websiteButton = findViewById(R.id.open_website_button);   //saves the button's

        mLocationEditText = findViewById(R.id.location_edittext);    //for openLocation
        mShareTextEditText = findViewById(R.id.share_edittext);      //for shareText


        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userWebsiteInput = mWebsiteEditText.getText().toString();   //saves the users  into thios string variable

                Intent intent = new Intent(Intent.ACTION_VIEW);    //creates an implicit intent for some type of action
                intent.setData(Uri.parse(userWebsiteInput));
                startActivity(intent);
            }
        });
    }
//    public void openWebsite(View view) {
//
//    }

    public void openLocation(View view) {     //this method finds location
        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void shareText(View view) {    //this method shares things with others
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(R.string.share_text_with)
                .setText(txt)
                .startChooser();
    }
}
