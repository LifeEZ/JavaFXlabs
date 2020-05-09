package sample;

import java.util.Vector;

public class Aircraft {
    private Vector<Boolean> Seats;
    private Vector<Boolean> FirstClass;
    private Vector<FirstClassPassenger> FCPassengers;
    private Vector<MainPassenger> Passengers;
    private Vector<Staff> StaffMembers;
    private int Height = 0;
    private boolean FlightStatus;

    public int GetHeight() {
        return Height;
    }

    public int GetFCFreeSeats() {
        int result = 0;
        for (Boolean firstClass : FirstClass) {
            if (firstClass) {
                result++;
            }
        }
        return result;
    }

    public int GetFreeSeats() {
        int result = 0;
        for (Boolean seat : Seats) {
            if (seat) {
                result++;
            }
        }
        return result;
    }

    public Aircraft() {
        FirstClass = new Vector<>();
        Seats = new Vector<>();
        FCPassengers = new Vector<>();
        Passengers = new Vector<>();
        StaffMembers = new Vector<>();
        for (int i = 0; i < 50; i++) {
            Seats.add(true);
        }
        for (int i = 0; i < 20; i++) {
            FirstClass.add(true);
        }
        FlightStatus = false;
    }

    public boolean GetFlightStatus() {
        return FlightStatus;
    }

    public void SetFlightStatus(boolean status) {
        this.FlightStatus = status;
    }

    public void GainHeight() {
        Height += 100;
    }

    public void LooseHeight() {
        Height -= 100;
    }

    public int GetSeat() {
        int temp = 0;

        for (int i = 0; i < Seats.size(); i++) {
            if (Seats.elementAt(i)) {
                Seats.set(i, false);
                return i;
            }
        }
        return temp;
    }

    public void FreeFCSeat(int id) {
        FirstClass.set(id, true);
        FCPassengers.remove(0);
    }

    public void NewFCPassenger(FirstClassPassenger passenger) {
        FCPassengers.add(passenger);
    }

    public int GetFCSeat() {
        int temp = 0;
        for (int i = 0; i < FirstClass.size(); i++) {
            if (FirstClass.elementAt(i)) {
                FirstClass.set(i, false);
                return i;
            }
        }
        return temp;
    }

    public void FreeSeat(int id) {
        Seats.set(id, true);
        Passengers.remove(0);
    }

    public void NewPassenger(MainPassenger passenger) {
        Passengers.add(passenger);
    }

    public void NewStaffMember(Staff member) {
        StaffMembers.add(member);
    }

    public Vector<MainPassenger> GetPassengers() {
        return Passengers;
    }

    public Vector<FirstClassPassenger> GetFCPassengers(){
        return FCPassengers;
    }
}
