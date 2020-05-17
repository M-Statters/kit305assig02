package au.edu.utas.username.kit305assig02;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewTicket extends AppCompatActivity
{
    private static final String TAG = "NewRaffle Log";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_ticket);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase dbT = databaseConnection.open();

        getSupportActionBar().setTitle("Raffle Management Application");

        Button btnCreateRaffle = findViewById(R.id.btnCreate);
        btnCreateRaffle.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view)
            {
                EditText tickets = findViewById(R.id.intTickets);
                String enteredTickets = tickets.getText().toString();
                int noTickets = Integer.parseInt(enteredTickets);
                Log.d(TAG, "Number of tickets: " + noTickets);
                for (int j = 0; j < noTickets; j++)
                {
                    EditText ticketName = findViewById(R.id.txtName);
                    String enteredName = ticketName.getText().toString();
                    Log.d(TAG, "Name: " + enteredName);

                    EditText ticketEmail = findViewById(R.id.txtEmail);
                    String enteredEmail = ticketEmail.getText().toString();
                    if (enteredEmail.matches(""))
                    {
                       enteredEmail = "No email provided";
                    }
                    Log.d(TAG, "Email: " + enteredEmail);

                    EditText ticketPhone = findViewById(R.id.intPhone);
                    String enteredPhone = ticketPhone.getText().toString();
                    if (enteredPhone.matches(""))
                    {
                        enteredPhone = "No phone provided";
                    }
                    Log.d(TAG, "Phone: " + enteredPhone);

                    LocalDateTime currentDateTime = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                    String formattedDateTime = currentDateTime.format(formatter);



                    Ticket ticket = new Ticket();
                    ticket.setRaffleID(RaffleDetails.CURRENT_RAFFLE);
                    Log.d(TAG, "Raffle ID: " + RaffleDetails.CURRENT_RAFFLE);
                    ticket.setName(enteredName);
                    ticket.setEmail(enteredEmail);
                    ticket.setPhone(enteredPhone);
                    ticket.setTime(formattedDateTime);
                    ticket.setPrice(RaffleDetails.RAFFLE_PRICE);

                    TicketTable.insert(dbT, ticket);
                }

                Intent i = new Intent(NewTicket.this, RaffleDetails.class);
                startActivity(i);
            }
        });
    }
}
