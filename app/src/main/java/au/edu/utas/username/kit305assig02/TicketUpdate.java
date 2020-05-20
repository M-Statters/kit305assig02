package au.edu.utas.username.kit305assig02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TicketUpdate extends AppCompatActivity {
    private static final String TAG = "NewRaffle Log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_ticket);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase dbR = databaseConnection.open();
        final SQLiteDatabase dbT = databaseConnection.open();

        final ArrayList<Raffle> raffles = RaffleTable.selectAll(dbR);
        final ArrayList<Ticket> tickets = TicketTable.selectTicketsFromRaffle(dbT, MainActivity.SELECTED_RAFFLE_ID);
        final int noTickets = tickets.size();

        getSupportActionBar().setTitle(raffles.get(MainActivity.RAFFLE_ID).getName());

        TextView txtTicketName = findViewById(R.id.txtName);
        txtTicketName.setText(tickets.get(TicketList.TICKET).getName());

        TextView txtTicketEmail = findViewById(R.id.txtEmail);
        txtTicketEmail.setText(tickets.get(TicketList.TICKET).getEmail());

        TextView txtTicketPhone = findViewById(R.id.txtPhone);
        Log.d(TAG, "Phone: " + tickets.get(TicketList.TICKET).getPhone());
        //txtTicketPhone.setText(tickets.get(TicketList.TICKET).getPhone());

        Button btnUpdateTicket = findViewById(R.id.btnUpdateTicket);
        btnUpdateTicket.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                EditText ticketName = findViewById(R.id.txtName);
                String enteredName = ticketName.getText().toString();
                Log.d(TAG, "Name: " + enteredName);

                EditText ticketEmail = findViewById(R.id.txtEmail);
                String enteredEmail = ticketEmail.getText().toString();
                if (enteredEmail.matches("")) {
                    enteredEmail = "No email provided";
                }
                Log.d(TAG, "Email: " + enteredEmail);

                EditText ticketPhone = findViewById(R.id.intPhone);
                String enteredPhone = ticketPhone.getText().toString();
                if (enteredPhone.matches("")) {
                    enteredPhone = "No phone provided";
                }
                Log.d(TAG, "Phone: " + enteredPhone);

                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                String formattedDateTime = currentDateTime.format(formatter);


                Ticket ticket = new Ticket();
                ticket.setRaffleID(MainActivity.SELECTED_RAFFLE_ID);
                Log.d(TAG, "Raffle ID: " + RaffleDetails.CURRENT_RAFFLE);
                ticket.setName(enteredName);
                ticket.setEmail(enteredEmail);
                ticket.setPhone(enteredPhone);
                ticket.setTime(formattedDateTime);
                ticket.setPrice(RaffleDetails.RAFFLE_PRICE);

                TicketTable.update(dbT, ticket);

                Intent i = new Intent(TicketUpdate.this, TicketList.class);
                startActivity(i);
            }
        });
    }
}
