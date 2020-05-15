package com.example.programming1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TicketListActivity extends AppCompatActivity {
    Button ticket1;
    Button ticket2;
    Button ticket3;
    Button ticket4;
    PopupWindow popupWindow;
    private FloatingActionButton floatingActionButton;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_list);
        ticket1 = findViewById(R.id.ticket1);
        ticket1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.activity_customer_name,null);
                popupWindow = new PopupWindow(view,ticket1.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.showAsDropDown(ticket1);
            }
        });
        ticket2 = findViewById(R.id.ticket2);
        ticket2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.activity_customer_name,null);
                popupWindow = new PopupWindow(view,ticket2.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.showAsDropDown(ticket2);
            }
        });
        ticket3 = findViewById(R.id.ticket3);
        ticket3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.activity_customer_name,null);
                popupWindow = new PopupWindow(view,ticket3.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.showAsDropDown(ticket3);
            }
        });
        ticket4 = findViewById(R.id.ticket4);
        ticket4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.activity_customer_name,null);
                popupWindow = new PopupWindow(view,ticket4.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.showAsDropDown(ticket4);
            }
        });
        floatingActionButton = findViewById(R.id.fab_list);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TicketListActivity.this,AddTicketActivity.class);
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
                intent = new Intent(TicketListActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.close_raffle:
                intent = new Intent(TicketListActivity.this,CloseRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.completed_raffle:
                intent = new Intent(TicketListActivity.this,CompletedRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.cancelled_raffle:
                intent = new Intent(TicketListActivity.this,CancelledRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.customer_info:
                intent = new Intent(TicketListActivity.this,CustomerInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.ticket_list:
                intent = new Intent(TicketListActivity.this,TicketListActivity.class);
                startActivity(intent);
                break;
            case R.id.winning:
                intent = new Intent(TicketListActivity.this,winningActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}