package au.edu.utas.username.kit305assig02;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewTicket extends AppCompatActivity
{
    private static final String TAG = "NewRaffle Log";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_ticket);
        Database databaseConnection = new Database(this);
        final SQLiteDatabase db = databaseConnection.open();



    }
}
