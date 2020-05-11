package au.edu.utas.username.kit305assig02;

public class Raffle
{
    private int mRaffleID;
    private String mName;
    private String mDescription;
    private double mPrice;
    private int mMaxTickets;
    private boolean mStatus;

    public int getRaffleID() {return mRaffleID; }
    public void setRaffleID(int s) { this.mRaffleID = s; }

    public String  getName() {return mName; }
    public void setName(String s) { this.mName = s; }

    public String  getDescription() {return mDescription; }
    public void setDescription(String s) { this.mDescription = s; }

    public double getPrice() {return mPrice; }
    public void setPrice(double s) { this.mPrice = s; }

    public int getMaxTickets() {return mMaxTickets; }
    public void setMaxTickets(int s) { this.mMaxTickets = s; }

    public boolean getStatus() {return mStatus; }
    public void setStatus(boolean s) { this.mStatus = s; }
}
