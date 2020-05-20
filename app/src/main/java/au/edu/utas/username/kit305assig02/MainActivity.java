package au.edu.utas.username.kit305assig02;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/* As the comments of this code will never be marked I will leave you a picture of something that will never happen in this code.
   You will find notes of anger and frustration within read at your own discretion, thank you for compounding my hatred of programing
   I did intend on doing HD level but it really wasn't worth the effort
                               |       |
                                \\_V_//
                                \/=|=\/
                                 [=v=]
                               __\___/_____
                              /..[  _____  ]
                             /_  [ [  M /] ]
                            /../.[ [ M /@] ]
                           <-->[_[ [M /@/] ]
                          /../ [.[ [ /@/ ] ]
     _________________]\ /__/  [_[ [/@/ C] ]
    <_________________>>0---]  [=\ \@/ C / /
       ___      ___   ]/000o   /__\ \ C / /
          \    /              /....\ \_/ /
       ....\||/....           [___/=\___/
      .    .  .    .          [...] [...]
     .      ..      .         [___/ \___]
     .    0 .. 0    .         <---> <--->
  /\/\.    .  .    ./\/\      [..]   [..]
 / / / .../|  |\... \ \ \    _[__]   [__]_
/ / /       \/       \ \ \  [____>   <____]

*/

public class MainActivity extends AppCompatActivity
{
    //This is the tag that is used for the Logcat output (useful for filtering output)
    private static final String TAG = "MainActivity Log";

    // This is stupid, why can't you just read shit into the clicks
    public static int RAFFLE_ID;
    public static int SELECTED_RAFFLE;
    public static int SELECTED_RAFFLE_ID;
    public static int MAX_TICKETS;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase dbR = databaseConnection.open();
        final SQLiteDatabase dbT = databaseConnection.open();

        final ArrayList<Raffle> raffles =RaffleTable.selectAll(dbR);

        getSupportActionBar().setTitle("Raffle Management Application");

        for (Raffle var : raffles)
        {
            //Log.d(TAG, var.getPropertyID());
            Log.d(TAG, var.getName());
        }

        //List parts!
        final ListView myList = findViewById(R.id.myList);

        final RaffleAdapter raffleListAdapter = new RaffleAdapter(getApplicationContext(),R.layout.raffle_list, raffles);
        myList.setAdapter(raffleListAdapter);

        FloatingActionButton btnCreateNewRaffle = findViewById(R.id.btnCreateNewRaffle);
        btnCreateNewRaffle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(view.getContext(), NewRaffle.class);
                startActivityForResult(i, 0);
            }
        });

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // when did you teach us to pull stuff out of the database
                Raffle r = raffles.get(position);
                RAFFLE_ID = position;
                SELECTED_RAFFLE_ID = r.getRaffleID();
                MAX_TICKETS = Integer.valueOf(r.getMaxTickets());
                Intent i = new Intent(view.getContext(), RaffleDetails.class);
                i.putExtra(String.valueOf(SELECTED_RAFFLE), r.getRaffleID());
                i.putExtra(String.valueOf(SELECTED_RAFFLE_ID), r.getRaffleID());
                i.putExtra(String.valueOf(MAX_TICKETS), r.getRaffleID());
                Log.d(TAG, "SELECTED_RAFFLE: " + SELECTED_RAFFLE);
                Log.d(TAG, "RAFFLE_ID: " + RAFFLE_ID);
                Log.d(TAG, "SELECTED_RAFFLE_ID: " + SELECTED_RAFFLE_ID);
                startActivity(i);
            }
        });
    }
}