package au.edu.utas.username.kit305assig02;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/* As the comments of this code will never be marked I will leave you a picture of something that will never happen in this code.

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
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Open the database, so that we can read and write
        Database databaseConnection = new Database(this);
        final SQLiteDatabase dbR = databaseConnection.open();
        final SQLiteDatabase dbT = databaseConnection.open();

        ArrayList<Raffle> raffles =RaffleTable.selectAll(dbR);
        ArrayList<Ticket> tickets =TicketTable.selectAll(dbT);

        for (Raffle var : raffles)
        {
            //Log.d(TAG, var.getPropertyID());
            Log.d(TAG, var.getName());
        }

        for (Ticket var : tickets)
        {
            //Log.d(TAG, var.getPropertyID());
            Log.d(TAG, var.getName());
        }

        //List parts!
        ListView myList = findViewById(R.id.myList);

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
                final CardView raffleCard = findViewById(R.id.cardView);
                raffleCard.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        Intent i = new Intent(raffleCard.getContext(), RaffleDetails.class);
                        startActivityForResult(i, 0);
                    }
                });

            }
        });



        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

            }
        });
    }
}