package com.example.laowai.blink_led;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Locale;
import android.media.MediaPlayer;
import android.content.SharedPreferences;

import static com.example.laowai.blink_led.Main2Activity.my_settings_xxx;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;

    String my_url = "http://dreamgoals.info/node/write.php?file_name=control.txt&data_string=";
    private static final int REQ_CODE_SPEECH_INPUT = 100;

    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;

    private TextView mTextMessage;
    private WebView wv1;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:


                    MediaPlayer.create(getApplicationContext(), R.raw.door_bell).start();


                    mTextMessage.setText(R.string.title_home);
                    wv1.loadUrl(my_url+"nnnnnnnnnnnnnn");
                    return true;
                case R.id.navigation_dashboard:
                    //mTextMessage.setText(R.string.title_dashboard);
                    startVoiceInput();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);

                    return true;
            }
            return false;
        }
    };


    /** Called when the user taps the STAR button or settings */
    public void start_settings() {
        Intent intent = new Intent(this, Main2Activity.class);
        // EditText editText = (EditText) findViewById(R.id.editText);
        // String message = editText.getText().toString();
        // intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //ACTION_RECOGNIZE_SPEECH
      //  intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);


        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US");
        intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE,true); // i think this needs a language defined to work

        // intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.SIMPLIFIED_CHINESE);
        //intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "zh-CN");
        //android.speech.extra.PREFER_OFFLINE
        //intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 50);

        //The amount of time that it should take after we stop hearing speech to consider the input complete.
        //intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 2);
       // I successfully implemented my Speech-Service with offline capabilities by using onPartialResults when offline and onResults when online.
//<uses-sdk android:minSdkVersion="4"/>
        //intent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{"zh-CN"});
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
            //intent.putExtra(RecognizerIntent.EXTRA_PREFER_OFFLINE,true);

            //speechRecognizer.stopListening();

        } catch (ActivityNotFoundException a) {

        }
    }






    @Override
public boolean onCreateOptionsMenu(Menu menu){
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.option_menu,menu);
    return super.onCreateOptionsMenu(menu);
}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setRequestedOrientation (SCREEN_ORIENTATION_PORTRAIT);     //  Fixed Portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //startup sound
        MediaPlayer.create(getApplicationContext(), R.raw.airplane_ding).start();

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);






        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        wv1=(WebView)findViewById(R.id.my_webview);
        //wv1.setWebViewClient(new MyBrowser());
        wv1.clearCache(true);
        ///!@#$%
        wv1.setWebChromeClient(new WebChromeClient());
    }


String my_color = "red"; // default color
String on_off_blink = "on_off_blink";
String final_command = "final";
    //button clicks------------------------------------------------------------------
    public void buttonOnClick(View view) {
        int the_id = view.getId();
        //Toast.makeText(this, "xxx", Toast.LENGTH_SHORT).show();
        if (the_id == R.id.b1) {
            my_color = "red";
            final_command = my_color+" "+on_off_blink;
            mTextMessage.setText(final_command);
        }
        if (the_id == R.id.b2) {
            my_color = "green";
            final_command = my_color+" "+on_off_blink;
            mTextMessage.setText(final_command);
        }
        if (the_id == R.id.b3) {
            my_color = "blue";
            final_command = my_color+" "+on_off_blink;
            mTextMessage.setText(final_command);
        }
//--
        if (the_id == R.id.b_on) {
            on_off_blink = "on";
            final_command = my_color+" "+on_off_blink+" **"+String.valueOf(8877552)+"**";
            mTextMessage.setText(final_command);
            wv1.loadUrl(my_url+final_command);
        }
        if (the_id == R.id.b_blink) {
            on_off_blink = "blink";
            final_command = my_color+" "+on_off_blink+" **"+String.valueOf(8877552)+"**";
            mTextMessage.setText(final_command);
            wv1.loadUrl(my_url+final_command);
        }
        if (the_id == R.id.b_off) {
            on_off_blink = "off";
            final_command = my_color+" "+on_off_blink+" **"+String.valueOf(8877552)+"**";
            mTextMessage.setText(final_command);
            wv1.loadUrl(my_url+final_command);
        }
    }

    //menu clicks------------------------------------------------------------------

    public void menu_clicked(MenuItem item) {

        int the_id = item.getItemId();
        if (the_id == R.id.item_1) {Toast.makeText(this, "item_1", Toast.LENGTH_SHORT).show();}
        if (the_id == R.id.item_2) {Toast.makeText(this, "item_2", Toast.LENGTH_SHORT).show();}
        if (the_id == R.id.bell) {
            Toast.makeText(this, "bell", Toast.LENGTH_SHORT).show();

            wv1.getSettings().setLoadsImagesAutomatically(true);
            wv1.getSettings().setJavaScriptEnabled(true);
            wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

            //temporarily using username for test

           EditText my_edit_text = (EditText) findViewById(R.id.edit_text_url);
           my_edit_text.setText("http://dreamgoals.info/node/view.php");
            String my_url = my_edit_text.getText().toString();
            //String my_url = "http://dreamgoals.info/node/spank.php";
            //String my_url = "http://dreamgoals.info/node/view.php";
            wv1.loadUrl(my_url);

        }

        if (the_id == R.id.battery) {
            Toast.makeText(this, "battery", Toast.LENGTH_SHORT).show();
            //SharedPreferences prefs = getSharedPreferences(my_settings_xxx, MODE_PRIVATE);
            SharedPreferences prefs = getSharedPreferences(my_settings_xxx, MODE_PRIVATE);
            String temp_yyy = prefs.getString("key_url","default_default");
//            //EditText edit_text_url_v = (EditText)findViewById(R.id.edit_text_url);
//            edit_text_url_v.setText(temp_yyy);
            mTextMessage.setText(temp_yyy);
        }
        if (the_id == R.id.lock) {
            Toast.makeText(this, "lock saves settings", Toast.LENGTH_SHORT).show();
           // start_settings();
        }
        if (the_id == R.id.star) {
            Toast.makeText(this, "star displays settings", Toast.LENGTH_SHORT).show();
            start_settings();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mVoiceInputTv.setText(result.get(0));
                    wv1.loadUrl(my_url+""+result.get(0));
                }
                break;
            }

        }
    }


    //        public void stopListening() {
//            checkIsCalledFromMainThread();
//            putMessage(Message.obtain(mHandler, MSG_STOP));
//        }
}
