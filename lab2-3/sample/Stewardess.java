package sample;

import java.util.Collection;
import java.util.Vector;

public class Stewardess extends Staff {

    public Stewardess() {

    }

    public String ServeCoffee() {
        return "Serving coffee...";
    }

    public int ServeFood(Collection<FirstClassPassenger> passengers) {
        int counter = 0;
        for (FirstClassPassenger passenger : passengers) {
            if (passenger.GetHungerStatus()) {
                passenger.ReduceHunger();
                counter++;
            }
        }

        return counter;
    }

}
