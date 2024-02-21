package org.example;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Logger logger = LogManager.getLogger();
        logger.info("Hello");

        try {
            System.out.println("provo a connettermi al server");
            // registriamo il Driver per accedere a SQLServer

            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            System.out.println("Driver sql server registrato");

            String dbURL = "jdbc:sqlserver://localhost; databaseName=DBGestionaleVI;"
                    + "integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

            // creiamo la connection (conn) al server
            Connection conn = DriverManager.getConnection(dbURL);

            if (conn != null) {
                System.out.println("Siamo connessi");

                System.out.println("Visualizza lista articoli");
                Statement myStatement = conn.createStatement();
                String StrSQL = "SELECT * FROM TArticoli";

                // ci serve un oggetto che conterra i record
                ResultSet myResultSet = myStatement.executeQuery(StrSQL);

                // dichiare e inizializzo una ArrayList
                ArrayList<Articolo> myArrayList = new ArrayList<Articolo>();

                // scorriamo il ResultSet un record alla volta

                while (myResultSet.next()) {
                    Articolo myArticolo = new Articolo();
                    myArticolo.setArticoloID(myResultSet.getInt("ArticoloID"));
                    myArticolo.setNome(myResultSet.getString("Nome"));
                    myArticolo.setDescrizione(myResultSet.getString("Descrizione"));
                    myArticolo.setPrezzoUnitarioVendita(myResultSet.getFloat("PrezzoUnitarioVendita"));
                    myArticolo.setGiacenza(myResultSet.getInt("Giacenza"));
                    myArticolo.setAliquotaIVA(myResultSet.getInt("AliquotaIVA"));
                    myArrayList.add(myArticolo);
                }
                myResultSet.close();

//                for (Articolo articolo: myArrayList) {
//                    System.out.print(articolo.getArticoloID() + " ");
//                    System.out.print(articolo.getNome() + " ");
//                    System.out.print(articolo.getDescrizione() + " ");
//                    System.out.print(articolo.getPrezzoUnitarioVendita() + " ");
//                    System.out.print(articolo.getGiacenza() + " ");
//                    System.out.print(articolo.getAliquotaIVA());
//                    System.out.println();
//                }

                // All'utente viene chiesto un range di prezzo: prezzomin e un prezzomax
                // Viene stampato in output un json con la lista degli articoli con prezzo compreso fra
                // prezzomin e prezzomax(sql between appure and)
                // Se non ci sono articoli nel range -> lo comunica

                Gson myGson = new Gson();
                String str = myGson.toJson(myArrayList);
                System.out.println(str);


                conn.close();
            }
            else {
                System.out.println("Problema nella connessione");
            }

        }
        catch (SQLException e){
            System.out.println("eccezione " + e.getMessage());
        }
        finally {

        }
    }
}