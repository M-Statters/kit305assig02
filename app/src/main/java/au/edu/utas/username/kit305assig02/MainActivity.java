package au.edu.utas.username.kit305assig02;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //This is the tag that is used for the Logcat output (useful for filtering output)
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        Raffle raffle1 = new Raffle();
        raffle1.setName("Fund Raiser");
        raffle1.setPrice(2);
        raffle1.setMaxTickets(100);

        Raffle raffle2 = new Raffle();
        raffle2.setName("Chocolate Wheel");
        raffle2.setPrice(1);
        raffle2.setMaxTickets(25);

        Ticket ticket1 = new Ticket();
        ticket1.setName("John Smith");
        ticket1.setPhone(0415276342);
        ticket1.setEmail("john.smmith@gmail.com");

        //RaffleTable.insert(db, raffle1);
        //RaffleTable.insert(db, raffle2);

        //TicketTable.insert(db, ticket1);

        //RaffleTable.removeRaffle(db,0, "Fund Raiser");

        //TicketTable.removeTicket(db, 0, "John Smith");

        final ArrayList<Raffle> raffles =RaffleTable.selectAll(db);
        //final ArrayList<Ticket> tickets =TicketTable.selectAll(db);

        for (Raffle var : raffles)
        {
            //Log.d(TAG, var.getPropertyID());
            Log.d(TAG, var.getName());
        }

        //List parts!
        ListView myList = findViewById(R.id.myList);

        final RaffleAdapter raffleListAdapter = new RaffleAdapter(getApplicationContext(),R.layout.raffle_list, raffles);
        myList.setAdapter(raffleListAdapter);

        //final TicketAdapter ticketListAdapter = new TicketAdapter(getApplicationContext(),R.layout.raffle_list, tickets);
        //myList.setAdapter(ticketListAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }
}