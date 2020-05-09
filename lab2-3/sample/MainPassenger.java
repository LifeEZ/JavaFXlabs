package sample;

public class MainPassenger extends Passenger {

    public MainPassenger(){
        super();
    }

    public void GetOnPlain(Aircraft aircraft) {
        SeatId = aircraft.GetSeat();
    }

    public void GetOffPlane(Aircraft aircraft) {
        aircraft.FreeSeat(SeatId);
    }

}
