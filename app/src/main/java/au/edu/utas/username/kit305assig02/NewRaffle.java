package au.edu.utas.username.kit305assig02;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewRaffle extends AppCompatActivity
{
    private static final String TAG = "NewRaffle Log";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_raffle);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();

        Button btnCreateRaffle = findViewById(R.id.btnCreate);
        btnCreateRaffle.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                EditText raffleName = findViewById(R.id.txtName);
                String enteredName = raffleName.getText().toString();
                Log.d(TAG, "Name: " + enteredName);

                EditText raffleDescription = findViewById(R.id.txtDescription);
                String enteredDescription = raffleDescription.getText().toString();
                Log.d(TAG, "Description: " + enteredDescription);

                // This is the dumbest shit I have ever seen
                EditText rafflePrice = findViewById(R.id.intPrice);
                String enteredPrice = rafflePrice.getText().toString();
                double valuePrice = Double.parseDouble(enteredPrice);
                Log.d(TAG, "Price: " + valuePrice);

                EditText raffleMax = findViewById(R.id.intMaxTickets);
                String enteredMax = raffleMax.getText().toString();
                Log.d(TAG, "Max Tickets: " + raffleMax);
                int valueMax = Integer.parseInt(enteredMax);

                Raffle raffle = new Raffle();
                raffle.setName(enteredName);
                raffle.setDescription(enteredDescription);
                raffle.setPrice(valuePrice);
                raffle.setMaxTickets(valueMax);
                raffle.setStatus(1);

                RaffleTable.insert(db, raffle);

                Intent i = new Intent(NewRaffle.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
