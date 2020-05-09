package sample;


public class Human {

    public void closeThread() {
        synchronized (thread) {
            thread.notify();
        }
    }

    private enum Gender {male, female}
    private Gender gender;
    private final Thread thread;

    public Human(String gender, Shower shower) {
        String temp = gender.toLowerCase();
        if (temp.equals("male")) {
            this.gender = Gender.male;
        }
        if (temp.equals("female")) {
            this.gender = Gender.female;
        }
        thread = new Thread(() -> wash(shower));
        thread.start();
    }

    public void wash(Shower shower) {
        try {
            synchronized (thread) {
                thread.wait(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shower.handle();
    }

    public String getGenderString(){
        return gender == Gender.male ? "male" : "female";
    }

    public boolean isMale(){
        return gender == Gender.male;
    }

    public boolean isFemale(){
        return gender == Gender.female;
    }
}
