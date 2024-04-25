import java.util.Objects;

public class Country {
    private static String name;
    private static String capital;
    private static int population;
    private static double area;

    public Country(String name, String capital, int population, double area) {
        this.name = name;
        this.capital = capital;
        this.population = population;
        this.area = area;

    }

    public static String getName() {
        return name;
    }

    public static String getCapital() {
        return capital;
    }

    public static int getPopulation() {
        return population;
    }

    public static double getArea() {
        return area;
    }

    @Override
    public String toString() {
        String string = "";
        string = string + this.name;
        string = string + " " + this.capital;
        string = string + " " + this.population;
        string = string + this.area;
        return string;
    }

    @Override
    public boolean equals(Object o) {
        Country country = (Country) o;
        return population == country.population &&
                Double.compare(country.area, area) == 0 &&
                Objects.equals(name, country.name) &&
                Objects.equals(capital, country.capital);
    }
}
