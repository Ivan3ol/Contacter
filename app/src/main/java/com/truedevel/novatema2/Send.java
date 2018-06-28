package com.truedevel.novatema2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Send extends AppCompatActivity {
    EditText message_on;
    ImageView telega;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        message_on = findViewById(R.id.input_message);
        telega = findViewById(R.id.telega);

        telega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentMessageTelegram( message_on.getText().toString());
            }
        });
    }

    void intentMessageTelegram(String msg)
    {
        final String appName = "org.telegram.messenger";
        final boolean isAppInstalled = isAppAvailable(getApplicationContext(), appName);
        if (isAppInstalled)
        {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            myIntent.setPackage(appName);
            myIntent.putExtra(Intent.EXTRA_TEXT, msg);

            this.startActivity(Intent.createChooser(myIntent, "Share with"));
            Toast.makeText(this, "Telegram is detected", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Telegram not Installed", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isAppAvailable(Context context, String appName)
    {
        PackageManager pm = context.getPackageManager();
        try
        {
            pm.getPackageInfo(appName, PackageManager.GET_ACTIVITIES);
            return true;
        }
        catch (PackageManager.NameNotFoundException e)
        {
            return false;
        }
    }
}
