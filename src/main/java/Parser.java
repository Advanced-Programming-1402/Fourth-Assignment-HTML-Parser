import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

//import static com.sun.tools.javac.jvm.ByteCodes.swap;
//import static java.util.Collections.swap;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public static List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        // Sort countries alphabetically (least)
        //TODO
        int n = countries.size();
        String[] names = new String[n];
        for (int i=0; i<countries.size(); i++) {
            names[i] = countries.get(i).getName();
        }
        Arrays.sort(names);
        /*System.out.println(names.length);
        for (int i=0; i<names.length; i++) {
            System.out.println(names[i]);
        }*/
        for (int i=0; i<names.length; i++) {
            for (int j=0; j<countries.size(); j++) {
                if (names[i].equals(countries.get(j).getName())) {
                    sortedByName.set(i, countries.get(j));
                    break;
                }
            }
        }
        for (int i=0; i<countries.size(); i++) {
            System.out.println(countries.get(i).toString());
        }
        //Arrays.sort(new ArrayList[]{names});
        return  sortedByName;
    }

    public static List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        // Sort countries by population (most)
        //TODO
        for (int i=0; i<countries.size(); i++) {
            for (int j=0; j<countries.size(); j++) {
                if (countries.get(i).getPopulation() > countries.get(j).getPopulation()) {
                    Country temp = countries.get(i);
                    //countries.get(i) = countries.get(j);
                    countries.set(i, countries.get(j));
                    countries.set(j, temp);
                }
            }
        }
        for (int i=0; i<countries.size(); i++) {
            System.out.println(countries.get(i).toString());
        }

        //return sortedByPopulation;
        return countries;
    }


    public static List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        // Sort countries by area (most)
        //TODO
        for (int i=0; i<countries.size(); i++) {
            for (int j=0; j<countries.size(); j++) {
                if (countries.get(i).getArea() > countries.get(j).getArea()) {
                    Country temp = countries.get(i);
                    //countries.get(i) = countries.get(j);
                    countries.set(i, countries.get(j));
                    countries.set(j, temp);
                }
            }
        }
        for (int i=0; i<countries.size(); i++) {
            System.out.println(countries.get(i).toString());
        }
        //return sortedByArea;
        return countries;
    }

    public static void setUp() throws IOException {

        //Parse the HTML file using Jsoup
        File file = new File("src//Resources//country-list.html");
        Document doc = Jsoup.parse(file , null);
        //TODO

        // Extract data from the HTML
        Element country = doc.select("section#countries").first();
        Elements div = country.select("div.col-md-4.country");
        for ( Element country2 : div ) {
            String countryName = country2.select(".country-name").text();
            String capitalCity = country2.select(".country-capital").text();
            int population = Integer.parseInt(country2.select(".country-population").text());
            double area = Double.parseDouble(country2.select(".country-area").text());
            Country country1 = new Country(countryName, capitalCity, population, area);
            Parser.countries.add(country1);
        }
        //TODO

        // Iterate through each country div to extract country data
        //TODO
    }

    public static void main(String[] args) throws IOException {
        //you can test your code here before you run the unit tests ;
        setUp();
        System.out.println("On what basis do you want us to sort the countries?");
        System.out.println("1-Sort by name");
        System.out.println("2-Sort by population");
        System.out.println("3-Sort by area");
        Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n==1) {
                sortByName();
                break;
            }
            else if (n==2) {
                sortByPopulation();
                break;
            }
            else if (n==3) {
                sortByArea();
                break;
            }
            else {
                System.out.println("Pleas enter correct input!");

            }
        }
    }
}
