public class Car {
    private final String manufacturer;
    private final String model;
    private final Integer year;
    private String license;

    public Car(String manufacturer, String model, Integer year, String license) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.license = license;
    }

    public Car(String manufacturer, String model, Integer year) {
        this(manufacturer, model, year, "");
    }

    public Car(String manufacturer, String model, String license) {
        this(manufacturer, model, -1, license);
    }

    public Car(String manufacturer, String model) {
        this(manufacturer, model, -1, "");
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}