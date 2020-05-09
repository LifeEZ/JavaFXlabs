package sample;

import java.util.Random;

public class FirstClassPassenger extends Passenger {

    private boolean HungerStatus;

    public FirstClassPassenger() {
        super();
        Random random = new Random(System.currentTimeMillis());
        HungerStatus = random.nextBoolean();
    }

    public boolean GetHungerStatus() {
        return HungerStatus;
    }

    public void ReduceHunger(){
        HungerStatus = false;
    }

    public void GetOnPlain(Aircraft aircraft) {
        SeatId = aircraft.GetFCSeat();
    }

    public void GetOffPlane(Aircraft aircraft) {
        aircraft.FreeFCSeat(SeatId);
    }

}
