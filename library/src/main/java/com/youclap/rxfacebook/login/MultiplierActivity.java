package com.youclap.rxfacebook.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * TODO REMOVE
 */
public class MultiplierActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        Button b = new Button(this);
        b.setText("Magic");
        layout.addView(b);

        setContentView(layout);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();

                int number = intent.getIntExtra("value", 0);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("resultValue", number * 5);

                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
