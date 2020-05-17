package com.example.programming1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class CustomerChangeActivity extends AppCompatActivity {
    Button save;
    Button cancel;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_change);
        save = findViewById(R.id.btn_change_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CustomerChangeActivity.this,CustomerInfoActivity.class);
                startActivity(intent);
            }
        });
        cancel = findViewById(R.id.btn_cancel_save);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(CustomerChangeActivity.this,CustomerInfoActivity.class);
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
                intent = new Intent(CustomerChangeActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.close_raffle:
                intent = new Intent(CustomerChangeActivity.this,CloseRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.completed_raffle:
                intent = new Intent(CustomerChangeActivity.this,CompletedRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.cancelled_raffle:
                intent = new Intent(CustomerChangeActivity.this,CancelledRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.customer_info:
                intent = new Intent(CustomerChangeActivity.this,CustomerInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.ticket_list:
                intent = new Intent(CustomerChangeActivity.this,TicketListActivity.class);
                startActivity(intent);
                break;
            case R.id.winning:
                intent = new Intent(CustomerChangeActivity.this,winningActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
