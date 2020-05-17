package com.example.programming1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;

public class AddTicketActivity extends AppCompatActivity {
    private PopupWindow popupWindow;
    private Button customer;
    Button finish;
    Button cancel;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ticket);
        customer = findViewById(R.id.ticket_customer_info);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = getLayoutInflater().inflate(R.layout.activity_list,null);
                popupWindow = new PopupWindow(view,customer.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT,true);
                popupWindow.showAsDropDown(customer);
            }
        });
        finish = findViewById(R.id.btn_ticket_finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AddTicketActivity.this,TicketListActivity.class);
                startActivity(intent);
            }
        });
        cancel = findViewById(R.id.btn_ticket_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(AddTicketActivity.this,TicketListActivity.class);
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
                intent = new Intent(AddTicketActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.close_raffle:
                intent = new Intent(AddTicketActivity.this,CloseRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.completed_raffle:
                intent = new Intent(AddTicketActivity.this,CompletedRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.cancelled_raffle:
                intent = new Intent(AddTicketActivity.this,CancelledRaffleActivity.class);
                startActivity(intent);
                break;
            case R.id.customer_info:
                intent = new Intent(AddTicketActivity.this,CustomerInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.ticket_list:
                intent = new Intent(AddTicketActivity.this,TicketListActivity.class);
                startActivity(intent);
                break;
            case R.id.winning:
                intent = new Intent(AddTicketActivity.this,winningActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
