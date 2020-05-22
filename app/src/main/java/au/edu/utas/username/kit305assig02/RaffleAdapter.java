package au.edu.utas.username.kit305assig02;

import android.app.Service;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class RaffleAdapter extends ArrayAdapter<Raffle>
{
    private int mLayoutResourceID;
    public RaffleAdapter(Context context, int resource, List<Raffle> objects)
    {
        super(context, resource, objects);
        this.mLayoutResourceID = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService((Service.LAYOUT_INFLATER_SERVICE));


        View row = layoutInflater.inflate(mLayoutResourceID, parent, false);
        Raffle r = this.getItem(position);
        TextView lblRaffleName = row.findViewById(R.id.lblRaffleName);
        lblRaffleName.setText(r.getName());
        TextView lblRaffleID = row.findViewById(R.id.lblRaffleID);
        //lblRaffleID.setText("Raffle ID: " + String.valueOf(r.getRaffleID()));
        TextView lblPrice = row.findViewById(R.id.lblPrice);
        lblPrice.setText("Ticket Cost $"+r.getPrice());
        TextView lblTotalTickets = row.findViewById(R.id.lblTotalTickets);
        // if I could get the selectTicketsFromRaffle function working properly this would display the number of sold tickets. At the moment it displays the max tickets value.
        lblTotalTickets.setText("Max Tickets: " + r.getMaxTickets());
        //lblTotalTickets.setText("Current Tickets: " + );

        return row;
    }
}
