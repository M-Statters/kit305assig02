package au.edu.utas.username.kit305assig02;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class RaffleDetails extends AppCompatActivity
{
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.raffle_details);
            Database databaseConnection = new Database(this);
            final SQLiteDatabase dbR = databaseConnection.open();
            final SQLiteDatabase dbT = databaseConnection.open();

            //int raffleID =
            //String raffleName =

        }
}
