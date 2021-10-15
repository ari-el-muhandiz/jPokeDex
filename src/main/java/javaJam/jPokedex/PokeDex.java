package javaJam.jPokedex;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PokeDex {
    public ArrayList<String> databases = new ArrayList<>();
    private Document doc;
    private String search;
    private Pokemon pokemon;

    public PokeDex(String db) {
        this.pokemon = new Pokemon();
        try {
            File myObj = new File(db);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.databases.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getDatabases() {
        return databases;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public void analyze() {
        ArrayList<String> temp = new ArrayList<>();

        try {
            doc = Jsoup.connect(String.format("https://bulbapedia.bulbagarden.net/wiki/%s", this.search)).get();

            Elements t = doc.select(".mw-parser-output > table.roundy table.roundy > tbody > tr").first().children();

            for (Element el : t) {
                if (el.is("td")) {
                    this.pokemon.setEngName(el.select("big").first().text());
                    this.pokemon.setJpName(el.select("i").first().text());
                } else {
                    this.pokemon.setPokeDexNumber(el.text());
                }
            }

            Element bioElement = doc.select(".mw-parser-output > h2").first();
            Elements elements = bioElement.nextElementSiblings();
            for (Element el : elements) {
                if (el.is("p")) {
                    temp.add(el.text());
                }
                if (el.is("h2")) {
                    this.pokemon.setDescription(temp);
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void print() {
        System.out.println("Name (English Version): " + ConsoleColors.GREEN_BRIGHT + this.pokemon.getEngName() + ConsoleColors.RESET);
        System.out.println("Name (Japanese Version): " + ConsoleColors.GREEN_BRIGHT + this.pokemon.getJpName() + ConsoleColors.RESET);
        System.out.println("PokeDex Number: " + ConsoleColors.GREEN_BRIGHT + this.pokemon.getPokeDexNumber() + ConsoleColors.RESET);
        System.out.println("Description: " + ConsoleColors.GREEN_BRIGHT + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE_BRIGHT + String.join("\n", this.pokemon.getDescription()) + ConsoleColors.RESET);
    }
}
