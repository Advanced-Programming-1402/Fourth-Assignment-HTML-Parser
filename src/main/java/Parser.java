import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.List;

public class Parser {
    static List<Country> countries = new ArrayList<>();

    public static List<Country> sortByName()
    {
        List<Country> sortedByName = new ArrayList<>(countries);
        Collections.sort(sortedByName, new Comparator<Country>()
        {
            @Override
            public int compare(Country PHcountry1, Country PHcountry2)
            {
                String name1 = PHcountry1.getName();
                String name2 = PHcountry2.getName();
                return name1.compareTo(name2);
            }
        });

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
                return Integer.compare(population2, population1);
            }
        });

        return sortedByPopulation;
    }

    public static List<Country> sortByArea()
    {
        List<Country> sortedByArea = new ArrayList<>(countries);
        Collections.sort(sortedByArea, new Comparator<Country>()
        {
            @Override
            public int compare(Country PHcountry1, Country PHcountry2)
            {
                double area1 = PHcountry1.getArea();
                double area2 = PHcountry2.getArea();
                return Double.compare(area2, area1);
            }
        });
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

        String sortedByNameCountries = "SORTER BY NAME:";
        String sortedByAreaCountries = "SORTED BY AREA";
        String sortedByPopulationCountries = "SORTED BY POPULATION";

        for (Country item : sortByName())
        {
            sortedByNameCountries = sortedByNameCountries + "\n" + "\n" + item.getName() + " - " + item.getArea() + " - " + item.getPopulation();
        }

        for (Country item : sortByArea())
        {
            sortedByAreaCountries = sortedByAreaCountries + "\n" + "\n" + item.getName() + " - " + item.getArea() + " - " + item.getPopulation();
        }

        for (Country item : sortByPopulation())
        {
            sortedByPopulationCountries = sortedByPopulationCountries + "\n" + "\n" + item.getName() + " - " + item.getArea() + " - " + item.getPopulation();
        }

        JFrame frame = new JFrame("Countries");

        JTextArea textArea1 = new JTextArea();
        textArea1.setBounds(10, 30, 280, 450);
        textArea1.setText(sortedByNameCountries);
        textArea1.setEditable(false);
        JScrollPane scroll1 = new JScrollPane(textArea1);
        scroll1.setBounds(10, 30, 280, 450);
        scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JTextArea textArea2 = new JTextArea();
        textArea2.setBounds(300, 30, 280, 450);
        textArea2.setText(sortedByAreaCountries);
        textArea2.setEditable(false);
        JScrollPane scroll2 = new JScrollPane(textArea2);
        scroll2.setBounds(300, 30, 280, 450);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JTextArea textArea3 = new JTextArea();
        textArea3.setBounds(590, 30, 280, 450);
        textArea3.setText(sortedByPopulationCountries);
        textArea3.setEditable(false);
        JScrollPane scroll3 = new JScrollPane(textArea3);
        scroll3.setBounds(590, 30, 280, 450);
        scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setSize(900, 550);
        frame.add(scroll1);
        frame.add(scroll2);
        frame.add(scroll3);
        frame.setVisible(true);

    }
}
