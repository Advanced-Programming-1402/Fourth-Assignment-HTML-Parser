import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.util.Collections.checkedCollection;
import static java.util.Collections.sort;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public static List<Country> sortByName(){
        List<Country> sortedByName = new ArrayList<>(countries);
        // Sort countries alphabetically (least)
        //TODO
        Collections.sort(sortedByName, Comparator.comparing(Country::getName));
        return  sortedByName;
    }

    public static List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        // Sort countries by population (most)
        //TODO
        Collections.sort(sortedByPopulation, Comparator.comparing(Country::getPopulation).reversed());
        return sortedByPopulation;
    }

    public static List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        // Sort countries by area (most)
        //TODO
        Collections.sort(sortedByArea, Comparator.comparing(Country::getArea).reversed());
        Collections.unmodifiableList(sortedByArea);
        return sortedByArea;
    }

    public static void setUp() throws IOException {

//        Parse the HTML file using Jsoup
//        TODO
            File file = new File("src\\Resources\\country-list.html");
            Document doc = Jsoup.parse(file, null);
            Element country = doc.select("section#countries").first();
            Elements div = country.select("div.col-md-4.country");
            for (Element country2 : div) {
                String countryName = country2.select(".country-name").text();
                String capitalCity = country2.select(".country-capital").text();
                int population = Integer.parseInt(country2.select(".country-population").text());
                double area = Double.parseDouble(country2.select(".country-area").text());
                Country country1 = new Country(countryName, capitalCity, population, area);
                Parser.countries.add(country1);
            }


        // Extract data from the HTML
        //TODO
//        Document doc = Jsoup.connect("file:///C:/Users/Asus/Desktop/Fourth-Assignment-HTML-Parser/src/Resources/country-list.html").get();
//        <p>
//                Element paragraphs = doc.select("p");
//        for (Element p : paragraphs) {
//            String text = p.text();
//            System.out.println(text);
//        }


        // Iterate through each country div to extract country data
        //TODO
    }

    public static void main(String[] args) throws IOException {
        setUp();
        sortByArea();
        sortByPopulation();
        sortByName();
         Scanner in = new Scanner(System.in);
         while (true) {
             System.out.println("Enter 1 to see countries sort by name\nEnter 2 to see countries sort by area\nEnter 3 to see countries sort by population\nEnter 4 to see the information of the country you want\nEnter 5 to exit ");
             int in1 = in.nextInt();
             if (in1 == 1) {
                 for (int i = 0; i < (countries.size() - 1); i++) {
                     System.out.println(sortByName().get(i).getName());
                 }
             }
             if (in1 == 2) {
                 for (int i = 0; i < (countries.size() - 1); i++) {
                     System.out.println(sortByArea().get(i).getName() + " : " + sortByArea().get(i).getArea() + "(km^2)");
                 }
             }
             if (in1 == 3) {
                 for (int i = 0; i < sortByPopulation().size() ; i++) {
                     System.out.println(sortByPopulation().get(i).getName() + " : " + sortByPopulation().get(i).getPopulation());
                 }
             }
             if (in1 == 4) {
                 int cnt = 0;
                 while (true) {
                     System.out.println("Enter the name of country\nEnter <back> to back to the menu");
                     Scanner input = new Scanner(System.in);
                     String name = input.nextLine();
                     if (name.equals("back")){
                         break;
                     }
                     for (int i = 0; i < countries.size(); i++) {
                         if (countries.get(i).getName().equals(name)) {
                             System.out.println(countries.get(i).getName() + " , population : " + countries.get(i).getPopulation() + " , Area : " + countries.get(i).getArea());
                             cnt = 1;
                             break;
                         }
                         if (i == countries.size() - 1  && (cnt == 0)){
                             System.out.println("Enter the correct name of country");
                         }
                     }
                 }
             }
             if (in1 == 5){
                 break;
             }
         }
    }
}

