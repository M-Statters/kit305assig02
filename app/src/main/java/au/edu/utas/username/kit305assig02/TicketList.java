package au.edu.utas.username.kit305assig02;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TicketList extends AppCompatActivity
{
    private static final String TAG = "TicketList Log";

    public static int CURRENT_RAFFLE;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_list);
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

        final ListView myList = findViewById(R.id.lstTickets);

        final TicketAdapter ticketListAdapter = new TicketAdapter(getApplicationContext(),R.layout.ticket_list, tickets);
        myList.setAdapter(ticketListAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

            }

        });
    }
}
