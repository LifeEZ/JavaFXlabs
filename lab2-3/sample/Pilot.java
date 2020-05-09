package sample;

public class Pilot extends Staff {

    public Pilot() {

    }

    public void Aviate(Aircraft aircraft) {
        if (!aircraft.GetFlightStatus()) {
            aircraft.GainHeight();
            aircraft.SetFlightStatus(true);
        }
        else {
            aircraft.LooseHeight();
            aircraft.SetFlightStatus(false);
        }
    }

}
