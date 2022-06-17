package grade;

public class Interval {

    private double obergrenze;
    private double untergrenze;

    public Interval(double untergrenze, double obergrenze) {
        this.obergrenze = obergrenze;
        this.untergrenze = untergrenze;
    }

    public double getObergrenze() {
        return obergrenze;
    }

    public double getUntergrenze() {
        return untergrenze;
    }
}
