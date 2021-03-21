package hu.nive.ujratervezes.zarovizsga.workhours;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkHours {


    public String minWork(String filestr) {
        Path file = Path.of(filestr);
        try(BufferedReader br =Files.newBufferedReader(file)) {
            int minHours = 25;
            String toPrint = "";
            String line;
            return getMinLine(br, minHours, toPrint);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read file");
        }
    }

    private String getMinLine(BufferedReader br, int minHours, String toPrint) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            int hours = Integer.parseInt(parts[1]);
            if(hours < minHours) {
                minHours = hours;
                toPrint = String.format("%s: %s", parts[0], parts[2]);
            }
        }
        return toPrint;
    }
}

//Munkaórák
//A WorkHours osztályban implementáld a String minWork(String file) metódust! A metódus paraméterként megkapja egy
// fájl elérési útvonalát. A tesztesetben ez a src/test/resources/hu/nive/ujratervezes/zarovizsga/workhours/workhours.txt,
// így ebbe a könyvtárba tedd a fájlt!
//
//A fájl formátuma:
//
//John Doe,8,2021-01-01
//Jack Doe,7,2021-01-02
//Jack Doe,8,2021-01-03
//John Doe,2,2021-01-04
//John Doe,8,2021-01-05
//John Doe,7,2021-01-06
//A dolgozó neve, mennyi órát dolgozott egy nap, és a nap. Azt kell visszaadni, hogy ki melyik nap dolgozott a legkevesebbet.
// A fenti fájl alapján John Doe 2021-01-04-én csak 2 órát dolgozott, így a következő Stringet kell visszaadni:
// John Doe: 2021-01-04. Egy napra, egy alkalmazotthoz mindig csak egy sor van.