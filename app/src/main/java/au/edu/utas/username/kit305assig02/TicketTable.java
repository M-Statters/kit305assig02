package au.edu.utas.username.kit305assig02;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;

public class TicketTable
{
    public static Ticket createFromCursor(Cursor c)
    {
        if (c == null || c.isAfterLast() || c.isBeforeFirst())
        {
            return null;
        }

        else
        {
            Ticket p = new Ticket();
            p.setTicketID(c.getInt(c.getColumnIndex(KEY_TICKET_ID)));
            p.setRaffleID(c.getInt(c.getColumnIndex(KEY_RAFFLE_ID)));
            p.setName(c.getString(c.getColumnIndex(KEY_NAME)));
            p.setPhone(c.getString(c.getColumnIndex(KEY_PHONE)));
            p.setEmail(c.getString(c.getColumnIndex(KEY_EMAIL)));
            p.setTime(c.getString(c.getColumnIndex(KEY_TIME)));
            p.setPrice(c.getString((c.getColumnIndex(KEY_PRICE))));
            return p;
        }
    }

    public static final String TABLE_NAME     = "tickets";

    public static final String KEY_TICKET_ID   = "ticket_id";
    public static final String KEY_RAFFLE_ID   = "raffle_id";
    public static final String KEY_NAME        = "name";
    public static final String KEY_PHONE       = "phone";
    public static final String KEY_EMAIL       = "email";
    public static final String KEY_TIME        = "time";
    public static final String KEY_PRICE       = "price";

    public static final String CREATE_STATEMENT = "CREATE TABLE     "
            + TABLE_NAME
            + "    (" + KEY_TICKET_ID + " integer primary key autoincrement, "
            + KEY_RAFFLE_ID + " integer, "
            + KEY_NAME + " string not null, "
            + KEY_PHONE + " int not null, "
            + KEY_EMAIL + " string not null, "
            + KEY_TIME + " string not null, "
            + KEY_PRICE + " int not null "
            + ");";
    public static void insert(SQLiteDatabase db, Ticket t)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_RAFFLE_ID, t.getRaffleID());
        values.put(KEY_NAME, t.getName());
        values.put(KEY_PHONE, t.getPhone());
        values.put(KEY_EMAIL, t.getEmail());
        values.put(KEY_TIME, t.getTime());
        values.put(KEY_PRICE, t.getPrice());

        db.insert(TABLE_NAME, null, values);
    }

    public static void removeTicket(SQLiteDatabase db, int id, String name)
    {
        if (id != 0) { db.delete(TABLE_NAME, KEY_TICKET_ID + " = ?", new String[] {"" + id }); }

        else if (name != null) { db.delete(TABLE_NAME, KEY_NAME + " = ?", new String[] {"" + name }); }

        else {   }
    }

    public static ArrayList<Ticket> selectAll(SQLiteDatabase db)
    {
        ArrayList<Ticket> results = new ArrayList<>();

        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (c != null)
        {
            c.moveToFirst();

            while(!c.isAfterLast())
            {
                Ticket t = createFromCursor(c);
                results.add(t);

                c.moveToNext();
            }
        }

        return results;
    }


    // because logic never works with programing does it
    // so lamfo you don't get to see each raffles tickets
    public static ArrayList<Ticket> selectFromRaffle(SQLiteDatabase db, String raffleID)
    {
        ArrayList<Ticket> results = new ArrayList<>();

        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (c != null)
        {
            c.moveToFirst();

            while(!c.isAfterLast())
            {
                if (KEY_RAFFLE_ID.equals(raffleID))
                {
                    Ticket t = createFromCursor(c);
                    results.add(t);

                    c.moveToNext();
                }
                else
                {
                    c.moveToNext();
                }
            }
        }

        return  results;
    }

    public static void update(SQLiteDatabase db, Ticket t)
    {
        ContentValues values = new ContentValues();
        values.put(KEY_TICKET_ID, t.getTicketID());
        values.put(KEY_RAFFLE_ID, t.getRaffleID());
        values.put(KEY_NAME, t.getName());
        values.put(KEY_PHONE, t.getPhone());
        values.put(KEY_EMAIL, t.getEmail());
        values.put(KEY_TIME, t.getTime());
        values.put(KEY_PRICE, t.getPrice());

        db.update(TABLE_NAME, values, KEY_TICKET_ID+"= ?", new String[]{ ""+t.getTicketID() });
    }
}

