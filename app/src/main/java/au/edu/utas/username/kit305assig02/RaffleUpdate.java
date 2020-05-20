package au.edu.utas.username.kit305assig02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RaffleUpdate extends AppCompatActivity
{
    private static final String TAG = "UpdateRaffle Log";

    public static int RAFFLE_ID;
    public static int SELECTED_RAFFLE;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_raffle);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase dbR = databaseConnection.open();
        final SQLiteDatabase dbT = databaseConnection.open();

        final ArrayList<Raffle> raffles = RaffleTable.selectAll(dbR);
        // final ArrayList<Ticket> tickets = TicketTable.selectAll(dbT);
        final ArrayList<Ticket> tickets = TicketTable.selectTicketsFromRaffle(dbT, MainActivity.SELECTED_RAFFLE_ID);
        final long noTickets = tickets.size();

        getSupportActionBar().setTitle("Raffle Management Application");

        TextView txtRaffleName = findViewById(R.id.txtName);
        txtRaffleName.setText(raffles.get(RaffleDetails.CURRENT_RAFFLE).getName());

        TextView txtRaffleDescription = findViewById(R.id.txtDescription);
        txtRaffleDescription.setText(raffles.get(RaffleDetails.CURRENT_RAFFLE).getDescription());

        TextView intPrice = findViewById(R.id.intPrice);
        intPrice.setText(raffles.get(RaffleDetails.CURRENT_RAFFLE).getPrice());

        TextView intTickets = findViewById(R.id.intMaxTickets);
        intTickets.setText(raffles.get(RaffleDetails.CURRENT_RAFFLE).getMaxTickets());

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (noTickets >= 1)
                {
                    AlertDialog.Builder builderDelete = new AlertDialog.Builder(RaffleUpdate.this);
                    builderDelete.setMessage("This Raffle has already been started").setTitle("Can Not Edit Raffle");
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
                else {

                    EditText raffleName = findViewById(R.id.txtName);
                    String enteredName = raffleName.getText().toString();
                    Log.d(TAG, "Name: " + enteredName);

                    EditText raffleDescription = findViewById(R.id.txtDescription);
                    String enteredDescription = raffleDescription.getText().toString();
                    Log.d(TAG, "Description: " + enteredDescription);

                    // This is the dumbest shit I have ever seen
                    EditText rafflePrice = findViewById(R.id.intPrice);
                    String enteredPrice = rafflePrice.getText().toString();
                    //int valuePrice = Integer.parseInt(enteredPrice);
                    Log.d(TAG, "Price: " + enteredPrice);

                    EditText raffleMax = findViewById(R.id.intMaxTickets);
                    String enteredMax = raffleMax.getText().toString();
                    Log.d(TAG, "Max Tickets: " + enteredMax);
                    //int valueMax = Integer.parseInt(enteredMax);

                    raffles.get(SELECTED_RAFFLE).setName(enteredName);
                    raffles.get(SELECTED_RAFFLE).setDescription(enteredDescription);
                    raffles.get(SELECTED_RAFFLE).setPrice(enteredPrice);
                    raffles.get(SELECTED_RAFFLE).setMaxTickets(enteredMax);
                    raffles.get(SELECTED_RAFFLE).setStatus(1);

                    RaffleTable.update(dbR, raffles.get(SELECTED_RAFFLE));

                    Intent i = new Intent(RaffleUpdate.this, RaffleDetails.class);
                    i.putExtra(String.valueOf(SELECTED_RAFFLE), raffles.get(SELECTED_RAFFLE).getRaffleID());
                    startActivity(i);
                }
            }
        });
    }
}
