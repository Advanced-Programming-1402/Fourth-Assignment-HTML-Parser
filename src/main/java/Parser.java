import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public List<Country> sortByName() throws Exception{
        List<Country> sortedByName = new ArrayList<>(countries);
        // Sort countries alphabetically (least)
        //TODO
        Collections.sort(sortedByName , Comparator.comparing(Country::getName));
        return sortedByName;
    }

    public List<Country> sortByPopulation(){
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        // Sort countries by population (most)
        //TODO
        Collections.sort(sortedByPopulation , Comparator.comparing(Country::getPopulation).reversed());
        return sortedByPopulation;
    }

    public List<Country> sortByArea(){
        List<Country> sortedByArea = new ArrayList<>(countries);
        // Sort countries by area (most)
        //TODO
        Collections.sort(sortedByArea , Comparator.comparing(Country::getArea).reversed());
        return sortedByArea;
    }

    public void setUp() throws IOException {

        //Parse the HTML file using Jsoup
        //TODO
        File file = new File("src\\Resources\\country-list.html");
        Document doc = Jsoup.parse(file , null);

        // Extract data from the HTML
        //TODO
        Element country = doc.select("section#countries").first();
        Elements divs = country.select("div.col-md-4.country");
        // Iterate through each country div to extract country data
        //TODO
//        for (Element div : divs) {
//            System.out.println(div.text());
//        }
        for(Element div : divs) {
            String name = div.select("h3.country-name").text();
            String capital = div.select("span.country-capital").text();
            int population = Integer.parseInt(div.select("span.country-population").text());
            double area = Double.parseDouble(div.select("span.country-area").text());
            Country country1 = new Country(name , capital , population , area);
            countries.add(country1);
        }
    }

    public static void main(String[] args) {
      try {
          Parser obj = new Parser();
          obj.setUp();
          System.out.println("hello");
          System.out.println(countries.size());
          for (int i = 0 ; i < countries.size() ; i++) {
              System.out.println(countries.get(i));
          }
          for (int i = 0 ; i < obj.sortByName().size() ; i++) {
              System.out.println(obj.sortByName().get(i));
          }
          System.out.println("hello");
      } catch (Exception e) {
          System.out.println("----------------");
          e.getStackTrace();
          System.out.println("----------------");
      }
        //you can test your code here before you run the unit tests ;)
    }
}
