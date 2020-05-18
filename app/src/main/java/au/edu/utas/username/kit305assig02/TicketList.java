package au.edu.utas.username.kit305assig02;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TicketList extends AppCompatActivity
{
    private static final String TAG = "TicketList Log";

    public static String CURRENT_RAFFLE;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_list);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase dbR = databaseConnection.open();
        final SQLiteDatabase dbT = databaseConnection.open();

        // doesn't work don't know why SQL is awful
        final ArrayList<Ticket> tickets = TicketTable.selectTicketsFromRaffle(dbT, String.valueOf(RaffleDetails.CURRENT_RAFFLE));
        Log.d(TAG, "String.valueOf(RaffleDetails.CURRENT_RAFFLE) " + RaffleDetails.CURRENT_RAFFLE);
        Log.d(TAG, "tickets: " + tickets);
        //final ArrayList<Ticket> tickets = TicketTable.selectAll(dbT);


        getSupportActionBar().setTitle("Raffle Management Application");

        final ListView myList = findViewById(R.id.lstTickets);

        final TicketAdapter ticketListAdapter = new TicketAdapter(getApplicationContext(),R.layout.ticket_list, tickets);
        myList.setAdapter(ticketListAdapter);
    }
}
