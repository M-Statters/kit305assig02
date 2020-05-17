package com.example.programming1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RaffleInfoActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    TextView customerInfo;
    Button changeInfo;
    Button cancel;
    Intent intent;
    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raffle_info);
        textView1 = findViewById(R.id.raffle_name2);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RaffleInfoActivity.this,textView1.getText(),Toast.LENGTH_LONG);
            }
        });
        textView2 = findViewById(R.id.description2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RaffleInfoActivity.this,textView2.getText(),Toast.LENGTH_LONG);
            }
        });
        customerInfo = findViewById(R.id.customer_name);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RaffleInfoActivity.this,customerInfo.getText(),Toast.LENGTH_LONG);
            }
        });
        changeInfo = findViewById(R.id.btn_change_info);
        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RaffleInfoActivity.this,ChangeInfoActivity.class);
                startActivity(intent);
            }
        });
        cancel = findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RaffleInfoActivity.this,MainActivity.class);
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
                intent = new Intent(RaffleInfoActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.close_raffle:
                intent = new Intent(RaffleInfoActivity.this,CloseRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.completed_raffle:
                intent = new Intent(RaffleInfoActivity.this,CompletedRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.cancelled_raffle:
                intent = new Intent(RaffleInfoActivity.this,CancelledRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.customer_info:
                intent = new Intent(RaffleInfoActivity.this,CustomerInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.ticket_list:
                intent = new Intent(RaffleInfoActivity.this,TicketListActivity.class);
                startActivity(intent);
                break;
            case R.id.winning:
                intent = new Intent(RaffleInfoActivity.this,winningActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
