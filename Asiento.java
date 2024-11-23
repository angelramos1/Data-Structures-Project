public class Asiento {
    private boolean reserved;
    private String level;
    private String numberOfSeat;

    public Asiento(String level, String numberOfSeat){
        this.level = level;
        this.numberOfSeat = numberOfSeat;
        this.reserved = false;
    }

    public boolean isReserved(){
        return reserved;
    }
    public String getLevel(){
        return level;
    }
    public String getNumberOfSeat(){
        return numberOfSeat;
    }
    public void setReserved(boolean reserved){
        this.reserved = reserved;
    }
    
}
