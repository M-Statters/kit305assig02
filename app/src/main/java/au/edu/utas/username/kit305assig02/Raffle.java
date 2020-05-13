package au.edu.utas.username.kit305assig02;

public class Raffle
{
    private int mRaffleID;
    private String mName;
    private String mDescription;
    private String mPrice;
    private String mMaxTickets;
    private int mStatus;

    public int getRaffleID() {return mRaffleID; }
    public void setRaffleID(int s) { this.mRaffleID = s; }

    public String  getName() {return mName; }
    public void setName(String s) { this.mName = s; }

    public String  getDescription() {return mDescription; }
    public void setDescription(String s) { this.mDescription = s; }

    public String getPrice() {return mPrice; }
    public void setPrice(String s) { this.mPrice = s; }

    public String getMaxTickets() {return mMaxTickets; }
    public void setMaxTickets(String s) { this.mMaxTickets = s; }

    public int getStatus() {return mStatus; }
    public void setStatus(int s) { this.mStatus = s; }
}
