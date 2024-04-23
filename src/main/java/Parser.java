import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public List<Country> sortByName()
    {
        List<Country> sortedByName = new ArrayList<>(countries);
        // Sort countries alphabetically (least)
        //TODO
        return  sortedByName;
    }

    public static List<Country> sortByPopulation()
    {
        List<Country> sortedByPopulation = new ArrayList<>(countries);
        Collections.sort(sortedByPopulation, new Comparator<Country>()
        {
            @Override
            public int compare(Country PHcountry1, Country PHcountry2)
            {
                int population1 = PHcountry1.getPopulation();
                int population2 = PHcountry2.getPopulation();
                return Integer.compare(population1, population2);
            }
        });

        return sortedByPopulation;
    }

    public List<Country> sortByArea()
    {
        List<Country> sortedByArea = new ArrayList<>(countries);
        // Sort countries by area (most)
        //TODO
        return sortedByArea;
    }

    public static void setUp()
    {
        try {
            File HTMLfile = new File("src/Resources/country-list.html");
            Document document = Jsoup.parse(HTMLfile, "UTF-8");

            Elements elements = document.select("div.country");

            for (Element element : elements)
            {
                String name = element.select("h3.country-name").text();
                String capital = element.select("span.country-capital").text();
                int population = Integer.parseInt(element.select("span.country-population").text());
                double area = Double.parseDouble(element.select("span.country-area").text());
                Country PHcountry = new Country(name, capital, population, area);
                countries.add(PHcountry);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public static void main(String[] args)
    {
        setUp();

        for (Country item : countries)
        {
            System.out.println(item.getName());
        }

        System.out.println(countries.size());

        for (Country item : sortByPopulation())
        {
            System.out.println(item.getPopulation() + " " + item.getName());
        }

    }
}
