package au.edu.utas.username.kit305assig02;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RaffleDetails extends AppCompatActivity
{
        private static final String TAG = "RaffleDetails Log";

        public static int CURRENT_RAFFLE;
        public static String RAFFLE_PRICE;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setTitle("Home"");
        getMenuInflater().inflate(R.menu.menu_raffle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.



        switch (item.getItemId())
        {
            case R.id.view_tickets:
                Intent t = new Intent(this, TicketList.class);
                t.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                startActivity(t);
                return true;
            case R.id.draw_raffle:
                // this was cancer
                Intent d = new Intent(this, RaffleDraw.class);
                d.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                startActivity(d);
                return true;
            case R.id.add_image:
                Intent i = new Intent(this, ImageSelect.class);
                i.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                startActivity(i);
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
            setContentView(R.layout.raffle_details);
            Database databaseConnection = new Database(this);
            final SQLiteDatabase dbR = databaseConnection.open();
            final SQLiteDatabase dbT = databaseConnection.open();

            final ArrayList<Raffle> raffles = RaffleTable.selectAll(dbR);
            final ArrayList<Ticket> tickets = TicketTable.selectAll(dbT);

            getSupportActionBar().setTitle(raffles.get(MainActivity.RAFFLE_ID).getName());

            /*TextView lblRaffleName = findViewById(R.id.lblRaffleName);
            // this took me way too long to do
            Log.d(TAG, "RAFFLE_ID: " + MainActivity.RAFFLE_ID);
            Log.d(TAG, "NAME: " + raffles.get(MainActivity.RAFFLE_ID).getName());
            lblRaffleName.setText(raffles.get(MainActivity.RAFFLE_ID).getName());*/

            TextView lblRaffleDescription = findViewById(R.id.lblDescription);
            Log.d(TAG, "DESCRIPTION: " + raffles.get(MainActivity.RAFFLE_ID).getDescription());
            lblRaffleDescription.setText("Raffle description:\n" + raffles.get(MainActivity.RAFFLE_ID).getDescription());

            TextView lblPrice = findViewById(R.id.lblPrice);
            lblPrice.setText("Ticket Price:\n" + "$ " + raffles.get(MainActivity.RAFFLE_ID).getPrice());

            TextView lblRaffleStatus = findViewById(R.id.lblRaffleStatus);
            Log.d(TAG, "STATUS: " + raffles.get(MainActivity.RAFFLE_ID).getStatus());
            if (raffles.get(MainActivity.RAFFLE_ID).getStatus() == 1)
                    { lblRaffleStatus.setText("Status:\nOpen"); }
            else    { lblRaffleStatus.setText("Status:\nClosed"); }




            Button btnUpdate = findViewById(R.id.btnUpdateDetails);
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    CURRENT_RAFFLE = MainActivity.RAFFLE_ID;
                    Intent i = new Intent(RaffleDetails.this, RaffleUpdate.class);
                    i.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                    startActivity(i);
                }
            });

            Button btnDelete = findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Log.d(TAG, "Deleting: " + raffles.get(MainActivity.RAFFLE_ID).getName());
                    RaffleTable.removeRaffle(dbR, raffles.get(MainActivity.RAFFLE_ID).getRaffleID(), "");
                    Intent i = new Intent(RaffleDetails.this, MainActivity.class);
                    startActivity(i);
                }
            });

            Button btnNewTicket = findViewById(R.id.btnNewTicket);
            btnNewTicket.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    CURRENT_RAFFLE = MainActivity.SELECTED_RAFFLE_ID;
                    RAFFLE_PRICE = raffles.get(MainActivity.RAFFLE_ID).getPrice();
                    Intent i = new Intent(RaffleDetails.this, NewTicket.class);
                    i.putExtra(String.valueOf(CURRENT_RAFFLE), MainActivity.RAFFLE_ID);
                    i.putExtra(String.valueOf(RAFFLE_PRICE), MainActivity.RAFFLE_ID);
                    startActivity(i);
                }
            });
        }
}
