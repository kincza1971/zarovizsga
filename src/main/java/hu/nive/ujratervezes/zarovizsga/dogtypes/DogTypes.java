package hu.nive.ujratervezes.zarovizsga.dogtypes;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DogTypes {
    private final DataSource dataSource;

    public DogTypes(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getDogsByCountry(String country) {
        try (Connection conn = dataSource.getConnection()){
            try (PreparedStatement ps = conn.prepareStatement("SELECT NAME FROM dog_types WHERE country = ?")) {
                ps.setString(1,country);
                ResultSet rs = ps.executeQuery();
                return getListFromResultSet(rs);
            }
        } catch (SQLException sqle) {
           throw new IllegalStateException("Cannot connect: " + sqle.getMessage());
        }
    }

    private List<String> getListFromResultSet(ResultSet rs) {
        List<String> result = new ArrayList<>();
        try (rs){
            while (rs.next()) {
                result.add(rs.getString("name").toLowerCase(Locale.ROOT));
            }
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot execute query: " + sqle.getMessage());
        }
        return result;
    }

}
//Kutyafajták
//A DogTypes osztályba dolgozz, mely egy DataSource-ot kap konstruktorba. A DogTypesTest hívja meg, ott van a DataSource deklarálva.
// Nyugodtan írd át a benne lévő értékeket.
//
//Legyen egy List<String> getDogsByCountry(String country) metódusa, mely adatbázisból visszaadja az adott országhoz
// tartozó kutyafajtákat! A teszteset a V1__dogs.sql állományt futtatja le Flyway-jel. Létrehoz egy dog_types táblát,
// amiből a lényeges a name és a country oszlop. Adatokat is beszúrja. Ha lassú, akkor csak azokat a sorokat hagyd benne,
// amiben szerepel a HUNGARY szó!
//
//Vigyázz, a metódus kisbetűsen kapja az ország nevét, de az adatbázisban csupa nagybetűsen szerepel. Vigyázz,
// a kutyák fajtái nagybetűkkel vannak, de kisbetűkkel kell visszaadni. (Konvertálásokat végezheted Javaban vagy SQL-ben is.)
//
//Név szerint sorbarendezve add vissza őket!