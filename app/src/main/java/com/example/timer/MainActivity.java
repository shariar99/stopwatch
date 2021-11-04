package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekbar;
    Boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;

    public  void resetTimer(){

        textView.setText("0:30");
        seekbar.setProgress(30);
        seekbar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterIsActive = false;
    }

    public void buttonClicked(View view){
        if(counterIsActive){
            resetTimer();



        }else {
            counterIsActive = true;
            seekbar.setEnabled(false);
            goButton.setText("Stop!");


             countDownTimer = new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    Log.i("Finished", "Timer all Done!!");
                    resetTimer();

                }
            }.start();
        }
    }

    public void updateTimer (int secoundLeft){
        int minute = secoundLeft/60;
        int secound = secoundLeft - (minute*60);
        String secoundString = Integer.toString(secound);

        if(secound<= 9){
            secoundString="0"+secoundString;

        }

        textView.setText(Integer.toString(minute) + ":" + secoundString);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekbar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);
        goButton= findViewById(R.id.button);

        seekbar.setMax(600);
        seekbar.setProgress(30);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}