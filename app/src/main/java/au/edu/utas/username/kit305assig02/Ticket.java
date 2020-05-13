package au.edu.utas.username.kit305assig02;

import java.time.Instant;

public class Ticket
{
    private int mTicketID;
    private int mRaffleID;
    private String mName;
    private String mPhone;
    private String mEmail;
    private String mTime;
    private String mPrice;

    public int getTicketID() { return  mTicketID; }
    public void setTicketID(int s) { this.mTicketID = s; }

    public int getRaffleID() { return  mRaffleID; }
    public void setRaffleID(int s) { this.mRaffleID = s; }

    public String getName() { return  mName; }
    public void setName(String s) { this.mName = s; }

    public String getPhone() { return  mPhone; }
    public void setPhone(String s) { this.mPhone = s; }

    public String getEmail() { return  mEmail; }
    public void setEmail(String s) { this.mEmail = s; }

    public String getTime() { return mTime; }
    public void setTime(String s) { this.mTime = s;}

    public String getPrice() { return  mPrice; }
    public void setPrice(String s) { this.mPrice = s; }
}
