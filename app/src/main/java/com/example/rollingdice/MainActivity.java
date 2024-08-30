package com.example.rollingdice;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnRoll;
    ImageView imageView;
    TextView txtResult;
    final int[] images = {R.drawable.dice_1, R.drawable.dice_2, R.drawable.dice_3, R.drawable.dice_4, R.drawable.dice_5, R.drawable.dice_6};
    int number;

    private void initControl() {
        btnRoll = findViewById(R.id.btnRoll);
        txtResult = findViewById(R.id.txtResult);
        btnRoll.setOnClickListener(view -> {
            Thread t1 = new Thread(() -> {
                for(int i=0;i<30;i++){
                    Random random = new Random();
                    number = random.nextInt(5);
                    imageView.setImageResource(images[number]);
                    try {
                        Thread.sleep(75);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                txtResult.setText(String.valueOf(number+1));
            });
            t1.start();
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.dice);
        initControl();
    }
}