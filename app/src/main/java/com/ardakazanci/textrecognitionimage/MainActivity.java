package com.ardakazanci.textrecognitionimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    ImageView detectImageView;
    TextView detectResultTextView;
    Button processButton;

    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        detectImageView = findViewById(R.id.img_detect);
        detectResultTextView = findViewById(R.id.tv_detect);
        processButton = findViewById(R.id.btn_process);


        bitmap = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.hello_world_text
        );

        detectImageView.setImageBitmap(bitmap);

        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();

                if (!textRecognizer.isOperational()) {

                    Log.e(LOG_TAG, "Detector dependencies are not yet available");

                } else {

                    Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                    SparseArray<TextBlock> items = textRecognizer.detect(frame);
                    StringBuilder stringBuilder = new StringBuilder();

                    for (int i = 0; i < items.size(); ++i) {

                        TextBlock item = items.valueAt(i);
                        stringBuilder.append(item.getValue());
                        stringBuilder.append("\n");

                    }

                    detectResultTextView.setText(stringBuilder.toString());

                }


            }
        });


    }
}
