package au.edu.utas.username.kit305assig02;

public class Raffle
{
    private int mRaffleID;
    private String mName;
    private double mPrice;
    private int mMaxTickets;

    public int getRaffleID() {return mRaffleID; }
    public void setRaffleID(int s) { this.mRaffleID = s; }

    public String  getName() {return mName; }
    public void setName(String s) { this.mName = s; }

    public double getPrice() {return mPrice; }
    public void setPrice(double s) { this.mPrice = s; }

    public int getMaxTickets() {return mMaxTickets; }
    public void setMaxTickets(int s) { this.mMaxTickets = s; }
}
