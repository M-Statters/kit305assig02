package au.edu.utas.username.kit305assig02;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RaffleDetails extends AppCompatActivity
{
        private static final String TAG = "RaffleDetails Log";

        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.raffle_details);
            Database databaseConnection = new Database(this);
            final SQLiteDatabase dbR = databaseConnection.open();
            final SQLiteDatabase dbT = databaseConnection.open();

            final ArrayList<Raffle> raffles =RaffleTable.selectAll(dbR);

            TextView lblRaffleName = findViewById(R.id.lblRaffleName);
            // this took me way too long to do
            Log.d(TAG, "RAFFLE_ID: " + MainActivity.RAFFLE_ID);
            Log.d(TAG, "NAME: " + raffles.get(MainActivity.RAFFLE_ID).getName());
            lblRaffleName.setText(raffles.get(MainActivity.RAFFLE_ID).getName());

            TextView lblRaffleDescription = findViewById(R.id.lblDescription);
            Log.d(TAG, "DESCRIPTION: " + raffles.get(MainActivity.RAFFLE_ID).getDescription());
            lblRaffleDescription.setText("Raffle description:\n" + raffles.get(MainActivity.RAFFLE_ID).getDescription());

            TextView lblRecentPurchase = findViewById(R.id.lblRecentPurchase);
            //lblRecentPurchase.setText(

            TextView lblRaffleStatus = findViewById(R.id.lblRaffleStatus);
            Log.d(TAG, "STATUS: " + raffles.get(MainActivity.RAFFLE_ID).getStatus());
            if (raffles.get(MainActivity.RAFFLE_ID).getStatus() == 1)
                    { lblRaffleStatus.setText("Open"); }
            else    { lblRaffleStatus.setText("Closed"); }

            Button btnDelete = findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Log.d(TAG, "Deleting: " + raffles.get(MainActivity.RAFFLE_ID).getName());
                    RaffleTable.removeRaffle(dbR, raffles.get(MainActivity.RAFFLE_ID).getRaffleID(), "");
                    Intent i = new Intent(RaffleDetails.this, MainActivity.class);
                    startActivity(i);
                }
            });
        }
}
