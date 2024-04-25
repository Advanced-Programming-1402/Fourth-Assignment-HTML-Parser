import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Country> countries = new ArrayList<>();
    static List<Elements> htmlElementsList = new ArrayList<>();

    public static List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        Collections.sort(countries, Comparator.comparingDouble(country -> (double) Country.getArea()).reversed());
        // Sort countries by population (most)
        for(Country theCountry: countries){
            System.out.println(theCountry);
        }
        return  sortedByName;
    }

    public static List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        Collections.sort(countries, Comparator.comparingDouble(country -> (double) Country.getArea()).reversed());
        // Sort countries by population (most)
        for(Country theCountry: countries){
            System.out.println(theCountry);
        }

        return sortedByPopulation;
    }

    public static List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        Collections.sort(countries, Comparator.comparingDouble(country -> (double) Country.getArea()).reversed());
        // Sort countries by population (most)
        for(Country theCountry: countries){
            System.out.println(theCountry);
        }

        return sortedByArea;
    }

//    public static void areaSorting(){
//        Collections.sort(htmlElementsList, Comparator.comparingInt(country -> (int) Country.getArea()).reversed());
//        for(Elements element:htmlElementsList){
//            System.out.print("Country: " + Country.getName() + " ");
//            System.out.print("Capital: " + Country.getCapital() + " ");
//            System.out.print("Population: " + Country.getPopulation() + " ");
//            System.out.print("Area (km2): " + Country.getArea());
//            System.out.println();
//        }
//    }

    public static void setUp() {

        try {
            //Parse the HTML file using Jsoup
            Document doc = Jsoup.parse(new File("src/Resources/country-list.html"),"utf-8");

            // Extract data from the HTML
            Elements countryDivs = doc.select(".country");

            // Iterate through each country div to extract country data
            for (Element countryDiv : countryDivs) {
                // getting attributes
                String name = countryDiv.select(".country-name").text();
                String capital = countryDiv.select(".country-capital").text();
                int population = Integer.parseInt(countryDiv.select(".country-population").text());
                double area = Double.parseDouble(countryDiv.select(".country-area").text());
                //creating instance
                countries.add(new Country(name, capital, population, area));
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        //you can test your code here before you run the unit tests ;)
        Country.getName();
        Country.getArea();
        Country.getPopulation();
        Country.getCapital();
        setUp();
        System.out.println("Hello. please choose what do you wanna see");
        System.out.println("1: see countries sorted by Area.");
        System.out.println("2: see countries sorted by Population.");
        System.out.println("3: see countries sorted by Name.");

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        switch(num){
            case 1:
                sortByArea();
                break;

            case 2:
                sortByPopulation();
                break;

            case 3:
                sortByName();
                break;

            default:
                break;
        }

    }
}
