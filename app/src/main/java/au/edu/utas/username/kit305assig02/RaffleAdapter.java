package au.edu.utas.username.kit305assig02;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
        TextView lblName = row.findViewById(R.id.lblName);
        lblName.setText(r.getName());
        TextView lblPrice = row.findViewById(R.id.lblPrice);
        lblPrice.setText("Ticket Cost $"+r.getPrice());
        TextView lblMaxTickets = row.findViewById(R.id.lblMaxTickets);
        lblMaxTickets.setText("Total Tickets: "+r.getMaxTickets());

        return row;
    }
}
