package au.edu.utas.username.kit305assig02;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewRaffle extends AppCompatActivity
{
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

                EditText raffleDescription = findViewById(R.id.txtDescription);
                String enteredDescription = raffleDescription.getText().toString();

                // This is the dumbest shit I have ever seen
                EditText rafflePrice = findViewById(R.id.intPrice);
                String enteredPrice = rafflePrice.getText().toString();
                double valuePrice = Double.parseDouble(enteredPrice);

                EditText raffleMax = findViewById(R.id.intMaxTickets);
                String enteredMax = raffleMax.getText().toString();
                int valueMax = Integer.parseInt(enteredMax);

                Raffle raffle = new Raffle();
                raffle.setName(enteredName);
                raffle.setDescription(enteredDescription);
                raffle.setPrice(valuePrice);
                raffle.setMaxTickets(valueMax);
                raffle.setStatus(true);

                RaffleTable.insert(db, raffle);

                Intent i = new Intent(NewRaffle.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
