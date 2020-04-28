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

public class TicketAdapter extends ArrayAdapter<Ticket>
{
    private int mLayoutResourceID;
    public TicketAdapter(Context context, int resource, List<Ticket> objects)
    {
        super(context, resource, objects);
        this.mLayoutResourceID = resource;
    }

    /*@NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater)getContext().getSystemService((Service.LAYOUT_INFLATER_SERVICE));

        View row = layoutInflater.inflate(mLayoutResourceID, parent, false);
        Ticket t = this.getItem(position);
        TextView lblTicketName = row.findViewById(R.id.lblTicketName);
        lblTicketName.setText(t.getName());
        //TextView lblPhone = row.findViewById(R.id.lblPhone);
        //lblPhone.setText(t.getPhone());
        TextView lblEmail = row.findViewById(R.id.lblEmail);
        lblEmail.setText(t.getEmail());

        return row;
    }*/
}
