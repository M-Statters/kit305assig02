package com.example.programming1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CloseRaffleActivity extends AppCompatActivity {
    private Button text1Click;
    private Button text2Click;
    private Button text3Click;
    private Button text4Click;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_close_raffle);
        text1Click = findViewById(R.id.Close_text1);
        text1Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CloseRaffleActivity.this,RaffleInfoActivity.class);
                startActivity(intent);
            }
        });
        text2Click = findViewById(R.id.Close_text2);
        text2Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CloseRaffleActivity.this,RaffleInfoActivity.class);
                startActivity(intent);
            }
        });
        text3Click = findViewById(R.id.Close_text3);
        text3Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CloseRaffleActivity.this,RaffleInfoActivity.class);
                startActivity(intent);
            }
        });
        text4Click = findViewById(R.id.Close_text4);
        text4Click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CloseRaffleActivity.this,RaffleInfoActivity.class);
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
                intent = new Intent(CloseRaffleActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.close_raffle:
                intent = new Intent(CloseRaffleActivity.this,CloseRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.completed_raffle:
                intent = new Intent(CloseRaffleActivity.this,CompletedRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.cancelled_raffle:
                intent = new Intent(CloseRaffleActivity.this,CancelledRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.customer_info:
                intent = new Intent(CloseRaffleActivity.this,CustomerInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.ticket_list:
                intent = new Intent(CloseRaffleActivity.this,TicketListActivity.class);
                startActivity(intent);
                break;
            case R.id.winning:
                intent = new Intent(CloseRaffleActivity.this,winningActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
