package au.edu.utas.username.kit305assig02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

import static android.database.DatabaseUtils.STATEMENT_SELECT;
import static android.database.DatabaseUtils.longForQuery;
import static android.icu.text.MessagePattern.ArgType.SELECT;

//

public class RaffleDraw extends AppCompatActivity
{
    private static final String TAG = "RaffleDraw Log";

    public static int CURRENT_RAFFLE;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.raffle_draw);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase dbR = databaseConnection.open();
        final SQLiteDatabase dbT = databaseConnection.open();

        final ArrayList<Raffle> raffles = RaffleTable.selectAll(dbR);
        final ArrayList<Ticket> tickets = TicketTable.selectTicketsFromRaffle(dbT, MainActivity.SELECTED_RAFFLE_ID);
        // final ArrayList<Ticket> tickets = TicketTable.selectAll(dbT);
        Log.d(TAG, "CURRENT_RAFFLE: " + CURRENT_RAFFLE);
        Log.d(TAG, "tickets" + tickets);

        getSupportActionBar().setTitle("Raffle Management Application");

        TextView lblraffleName = findViewById(R.id.raffleName);
        lblraffleName.setText(raffles.get(MainActivity.RAFFLE_ID).getName());
        Log.d(TAG, "Raffle ID: " + MainActivity.RAFFLE_ID);


        final Button btnDraw = findViewById(R.id.btnDrawRaffle);
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //char[] raffleList = new char[0];
                //long noTickets = DatabaseUtils.longForQuery(dbT, "SELECT COUNT (*) FROM " + TicketTable.KEY_RAFFLE_ID + " WHERE " + MainActivity.RAFFLE_ID + "=?",
                        //new String[] { String.valueOf(raffleList) });

                // long noTickets = DatabaseUtils.queryNumEntries(dbT, TicketTable.TABLE_NAME);
                long noTickets = tickets.size();
                if (noTickets < 1)
                {
                    AlertDialog.Builder builderDelete = new AlertDialog.Builder(RaffleDraw.this);
                    builderDelete.setMessage("Please enter tickets first.").setTitle("No tickets found");
                    builderDelete.setCancelable(true);
                    builderDelete.setPositiveButton("OK", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            dialog.cancel();
                        }
                    });
                    AlertDialog dialogDelete = builderDelete.create(); dialogDelete.show();
                }
                else
                {
                    Log.d(TAG, "Number of tickets: " + noTickets);
                    final int winner = new Random().nextInt((int) noTickets);
                    Log.d(TAG, "Winner ID: " + winner);
                    TextView txtWinner = findViewById(R.id.txtWinner);
                    txtWinner.setText(tickets.get(winner).getName());
                    raffles.get(MainActivity.RAFFLE_ID).setDescription(raffles.get(MainActivity.RAFFLE_ID).getDescription() + "\nWinner was: " + tickets.get(winner).getName() + "\nWith Ticket Number: " + tickets.get(winner).getTicketNumber());
                    RaffleTable.update(dbR, raffles.get(MainActivity.RAFFLE_ID));
                    btnDraw.setEnabled(false);
                }
            }
        });

        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                raffles.get(MainActivity.RAFFLE_ID).setStatus(0);
                RaffleTable.update(dbR, raffles.get(MainActivity.RAFFLE_ID));
                Intent i = new Intent(RaffleDraw.this, RaffleDetails.class);
                startActivity(i);
            }
        });

        Button btnBack = findViewById(R.id.btnCancel);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(), RaffleDetails.class);
                startActivityForResult(i, 0);
            }
        });
    }
}
