package au.edu.utas.username.kit305assig02;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TicketList extends AppCompatActivity
{
    private static final String TAG = "TicketList Log";

    public static int CURRENT_RAFFLE;
    public static int TICKET;

    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("Home"");
        getMenuInflater().inflate(R.menu.menu_ticket_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // this was cancer
        switch (item.getItemId())
        {
            case R.id.share:
                Log.d(TAG, "Share works");
                return true;
            case R.id.edit_ticket:

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_list_activity);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase dbR = databaseConnection.open();
        final SQLiteDatabase dbT = databaseConnection.open();

        final ArrayList<Raffle> raffles = RaffleTable.selectAll(dbR);
        final ArrayList<Ticket> tickets = TicketTable.selectTicketsFromRaffle(dbT, MainActivity.SELECTED_RAFFLE_ID);
        //Log.d(TAG, "String.valueOf(RaffleDetails.CURRENT_RAFFLE) " + RaffleDetails.CURRENT_RAFFLE);
        Log.d(TAG, "CURRENT_RAFFLE: " + MainActivity.SELECTED_RAFFLE_ID);
        Log.d(TAG, "tickets: " + tickets);
        //final ArrayList<Ticket> tickets = TicketTable.selectAll(dbT);


        getSupportActionBar().setTitle(raffles.get(MainActivity.RAFFLE_ID).getName() + " Tickets:");
        getSupportActionBar().getCustomView();

        final ListView myList = findViewById(R.id.myList);

        final TicketAdapter ticketListAdapter = new TicketAdapter(getApplicationContext(),R.layout.ticket_list, tickets);
        myList.setAdapter(ticketListAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d(TAG, "Click");
                Intent s = new Intent();
                s.setAction(Intent.ACTION_SEND);
                s.putExtra(Intent.EXTRA_TEXT,
                        "Ticket Details: \n" +
                                "Raffle Name: " + raffles.get(MainActivity.RAFFLE_ID).getName() +
                                "\nTicket Number: " + tickets.get(TicketList.TICKET).getTicketNumber() +
                                "\nName: " + tickets.get(TicketList.TICKET).getName() +
                                "\nEmail: " + tickets.get(TicketList.TICKET).getPhone() +
                                "\nPhone: " + tickets.get(TicketList.TICKET).getPhone() +
                                "\nPurchase Time: " + tickets.get(TicketList.TICKET).getTime() +
                                "\nPrice: $" + tickets.get(TicketList.TICKET).getPrice()
                );
                s.setType("text/plain");

                Intent shareIntent = Intent.createChooser(s, null);
                startActivity(shareIntent);
            }

        });
        myList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                Log.d(TAG, "Long Click");
                Intent u = new Intent(TicketList.this, TicketUpdate.class);
                u.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                u.putExtra(String.valueOf(TICKET), position);
                startActivity(u);
                return false;
            }
        });
    }
}
