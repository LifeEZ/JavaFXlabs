package sample;


public abstract class Passenger {

    int SeatId;

    public Passenger() {
        SeatId = 0;
    }

    abstract public void GetOnPlain(Aircraft aircraft);

    abstract public void GetOffPlane(Aircraft aircraft);

}
