import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.WildcardType;
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
        for (Element div : divs) {
            String name = div.select(".country-name").text();
            String capital = div.select(".country-capital").text();
            int population = Integer.parseInt(div.select(".country-population").text());
            double area = Double.parseDouble(div.select(".country-area").text());
            Country country1 = new Country(name , capital , population , area);
            countries.add(country1);
        }
    }
    public void menu() throws Exception{
        for (int i = 0 ; i < countries.size() ; i++ ){
            System.out.println(i + "- Country: " + countries.get(i).getName() + ", capital: " + countries.get(i).getCapital() + ", population: " + countries.get(i).getPopulation() + ", area: " + countries.get(i).getArea());
        }
        int stop = 1;
        while (stop != 0) {
            System.out.println("Please Enter The number Of What you Want : ");
            System.out.println("1.Sorted By Population\n2.Sorted By Area\n3.Sorted By Name\n4.Back");
            Scanner input = new Scanner(System.in);
            int userInput = input.nextInt();
            while (userInput > 4 || userInput < 1) {
                System.out.println("Invalid Data !!");
                System.out.print("Try Again : ");
                userInput = input.nextInt();
            }
            switch (userInput) {
                case 1 :
                    Parser parser = new Parser();
                    for (int i = 0 ; i < parser.sortByPopulation().size() ; i++) {
                        System.out.println(i + "- Country: " + parser.sortByPopulation().get(i).getName() + ", capital: " + parser.sortByPopulation().get(i).getCapital() + ", population: " + parser.sortByPopulation().get(i).getPopulation() + ", area: " + parser.sortByPopulation().get(i).getArea());
                    }
                    File file = new File("sorted-by-population.txt");
                    try {
                        if(file.createNewFile()) {
                            System.out.println("A new file with this name : " + file.getName() + " created for you :)");
                        }
                        else {
                            System.out.println("the file is exist : " + file.getName());
                        }
                        FileWriter Writer = new FileWriter("sorted-by-population.txt");
                        for (int i = 0 ; i  < sortByPopulation().size() ; i++) {
                            Writer.write((i + "- Country: " + parser.sortByPopulation().get(i).getName() + ", capital: " + parser.sortByPopulation().get(i).getCapital() + ", population: " + parser.sortByPopulation().get(i).getPopulation() + ", area: " + parser.sortByPopulation().get(i).getArea()).toString() + '\n');
                        }
                        Writer.close();
                    }catch (Exception e) {
                        System.out.println("Error");
                        e.printStackTrace();
                    }
                    break;
                case 2 :
                    Parser parser1 = new Parser();
                    for (int i = 0 ; i < parser1.sortByArea().size() ; i++) {
                        System.out.println(i + "- Country: " + parser1.sortByArea().get(i).getName() + ", capital: " + parser1.sortByArea().get(i).getCapital() + ", population: " + parser1.sortByArea().get(i).getPopulation() + ", area: " + parser1.sortByArea().get(i).getArea());
                    }
                    File file1 = new File("sorted-by-area.txt");
                    try {
                        if(file1.createNewFile()) {
                            System.out.println("A new file with this name : " + file1.getName() + " created for you :)");
                        }
                        else {
                            System.out.println("the file is exist : " + file1.getName());
                        }
                        FileWriter Writer = new FileWriter("sorted-by-area.txt");
                        for (int i = 0 ; i  < sortByArea().size() ; i++) {
                            Writer.write((i + "- Country: " + parser1.sortByArea().get(i).getName() + ", capital: " + parser1.sortByArea().get(i).getCapital() + ", population: " + parser1.sortByArea().get(i).getPopulation() + ", area: " + parser1.sortByArea().get(i).getArea()).toString() + '\n');
                        }
                        Writer.close();
                    }catch (Exception e) {
                        System.out.println("Error");
                        e.printStackTrace();
                    }
                    break;
                case 3 :
                    Parser parser2 = new Parser();
                    for (int i = 0 ; i < parser2.sortByName().size() ; i++) {
                        System.out.println(i + "- Country: " + parser2.sortByName().get(i).getName() + ", capital: " + parser2.sortByName().get(i).getCapital() + ", population: " + parser2.sortByName().get(i).getPopulation() + ", area: " + parser2.sortByName().get(i).getArea());
                    }
                    File file2 = new File("sorted-by-name.txt");
                    try {
                        if(file2.createNewFile()) {
                            System.out.println("A new file with this name : " + file2.getName() + " created for you :)");
                        }
                        else {
                            System.out.println("the file is exist : " + file2.getName());
                        }
                        FileWriter Writer = new FileWriter("sorted-by-name.txt");
                        for (int i = 0 ; i  < sortByName().size() ; i++) {
                            Writer.write((i + "- Country: " + parser2.sortByName().get(i).getName() + ", capital: " + parser2.sortByName().get(i).getCapital() + ", population: " + parser2.sortByName().get(i).getPopulation() + ", area: " + parser2.sortByName().get(i).getArea()).toString() + "\n");
                        }
                        Writer.close();
                    }catch (Exception e) {
                        System.out.println("Error");
                        e.printStackTrace();
                    }
                    break;
                case 4 :
                    stop = 0;
                    break;
            }
        }
    }

    public static void main(String[] args) {
      try {
          Parser obj = new Parser();
          obj.setUp();
          obj.menu();
      } catch (Exception e) {
          System.out.println("----------------");
          e.getStackTrace();
          System.out.println("----------------");
      }
        //you can test your code here before you run the unit tests ;)
    }
}
