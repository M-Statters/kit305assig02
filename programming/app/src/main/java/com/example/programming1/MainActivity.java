package com.example.programming1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
        private Button text1Click;
        private Button text2Click;
        private Button text3Click;
        private Button text4Click;
        private FloatingActionButton floatingActionButton;
        Intent intent;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            text1Click = findViewById(R.id.text1);
            text1Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(MainActivity.this,RaffleInfoActivity.class);
                    startActivity(intent);
                }
            });
            text2Click = findViewById(R.id.text2);
            text2Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(MainActivity.this,RaffleInfoActivity.class);
                    startActivity(intent);
                }
            });
            text3Click = findViewById(R.id.text3);
            text3Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(MainActivity.this,RaffleInfoActivity.class);
                    startActivity(intent);
                }
            });
            text4Click = findViewById(R.id.text4);
            text4Click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(MainActivity.this,RaffleInfoActivity.class);
                    startActivity(intent);
                }
            });
            floatingActionButton = findViewById(R.id.fab);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(MainActivity.this,NewRaffleActivity.class);
                    startActivity(intent);
                }
            });
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.open_raffle:
                intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.close_raffle:
                intent = new Intent(MainActivity.this,CloseRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.completed_raffle:
                intent = new Intent(MainActivity.this,CompletedRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.cancelled_raffle:
                intent = new Intent(MainActivity.this,CancelledRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.customer_info:
                intent = new Intent(MainActivity.this,CustomerInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.ticket_list:
                intent = new Intent(MainActivity.this,TicketListActivity.class);
                startActivity(intent);
                break;
            case R.id.winning:
                intent = new Intent(MainActivity.this,winningActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

