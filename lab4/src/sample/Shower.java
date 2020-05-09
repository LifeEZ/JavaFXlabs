package sample;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.util.Vector;

public class Shower {
    private final ListView<String> list = new ListView<>();
    private final Vector<Human> humans = new Vector<>();
    private final AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            ObservableList<String> elements = FXCollections.observableArrayList();
            for (Human tempHuman:humans) {
                elements.add(tempHuman.getGenderString());
            }
            getListView().setItems(elements);
        }
    };

    public Shower() {
        timer.start();
    }

    public void addHuman(Human human) {
        humans.add(human);
    }

    public Vector<Human> getHumans() {
        return humans;
    }

    public boolean onlyMales() {
        if (humans.isEmpty())
            return true;
        return humans.elementAt(0).isMale();
    }

    public boolean onlyFemales() {
        if (humans.isEmpty())
            return true;
        return humans.elementAt(0).isFemale();
    }

    public void close() {
        for (Human i : humans) {
            i.closeThread();
        }
        timer.stop();
    }

    public ListView<String> getListView() {
        return list;
    }

    public void handle() {
        getHumans().remove(0);
    }
}
