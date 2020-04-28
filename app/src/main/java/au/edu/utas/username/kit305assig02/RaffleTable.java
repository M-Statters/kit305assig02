package au.edu.utas.username.kit305assig02;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class RaffleTable
{
    public static Raffle createFromCursor(Cursor c)
    {
        if (c == null || c.isAfterLast() || c.isBeforeFirst())
        {
            return null;
        }

        else
        {
            Raffle p = new Raffle();
            p.setRaffleID(c.getInt(c.getColumnIndex(KEY_RAFFLE_ID)));
            p.setName(c.getString(c.getColumnIndex(KEY_NAME)));
            p.setPrice(c.getInt(c.getColumnIndex(KEY_PRICE)));
            p.setMaxTickets(c.getInt(c.getColumnIndex(KEY_MAX_TICKETS)));
            return p; }
    }

    public static final String TABLE_NAME     = "raffle";

    public static final String KEY_RAFFLE_ID   = "raffle_id";
    public static final String KEY_NAME        = "name";
    public static final String KEY_PRICE       = "price";
    public static final String KEY_MAX_TICKETS = "max_tickets";

    public static final String CREATE_STATEMENT = "CREATE TABLE     "
            + TABLE_NAME
            + "    (" + KEY_RAFFLE_ID + " integer primary key autoincrement, "
            + KEY_NAME + " string not null, "
            + KEY_PRICE + " double not null, "
            + KEY_MAX_TICKETS + " int not null "
            + ");";
    public static void insert(SQLiteDatabase db, Raffle r)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, r.getName());
        values.put(KEY_PRICE, r.getPrice());
        values.put(KEY_MAX_TICKETS, r.getMaxTickets());

        db.insert(TABLE_NAME, null, values);
    }

    public static void removeRaffle(SQLiteDatabase db, int id, String name)
    {
        if (id != 0) { db.delete(TABLE_NAME, KEY_RAFFLE_ID + " = ?", new String[] {"" + id }); }

        else if (name != null) { db.delete(TABLE_NAME, KEY_NAME + " = ?", new String[] {"" + name }); }

        else {   }
    }

    public static ArrayList<Raffle> selectAll(SQLiteDatabase db)
    {
        ArrayList<Raffle> results = new ArrayList<Raffle>();

        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (c != null)
        {
            c.moveToFirst();

            while(!c.isAfterLast())
            {
                Raffle r = createFromCursor(c);
                results.add(r);

                c.moveToNext();
            }
        }

        return results;
    }

    public static void update(SQLiteDatabase db, Raffle r)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_RAFFLE_ID, r.getRaffleID());
        values.put(KEY_NAME, r.getName());
        values.put(KEY_PRICE, r.getPrice());
        values.put(KEY_MAX_TICKETS, r.getMaxTickets());

        db.update(TABLE_NAME, values, KEY_RAFFLE_ID+"= ?", new String[]{ ""+r.getRaffleID() });
    }
}

