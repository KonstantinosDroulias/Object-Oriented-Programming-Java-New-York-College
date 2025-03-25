package weeksTasks.week01.CarRental;

public class Car {
    private String model;
    private String plateNumber;
    private int km;
    private boolean rental;

    public Car(String model, String plateNumber, int km) {
        this.model = model;
        this.plateNumber = plateNumber;
        this.km = km;
        rental = false;
    }

    public String getModel() {
        return model;
    }
    public String getPlate() {
        return plateNumber;
    }
    public int getKm() {
        return km;
    }
    public boolean isRented() {
        return rental;
    }

    public void setRented(boolean rental) {
        this.rental = rental;
    }
    public void setKM(int km) {
        this.km = km;
    }

}
