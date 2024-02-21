package org.example;

import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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
                    ResultSetMetaData metaData = myResultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    Articolo myArticolo = new Articolo();
//                    for (int i = 1; i <= columnCount; i++) {
                        myArticolo.setArticoloID(myResultSet.getInt("ArticoloID"));
                        myArticolo.setNome(myResultSet.getString("Nome"));
                        myArticolo.setDescrizione(myResultSet.getString("Descrizione"));
                        myArticolo.setPrezzoUnitarioVendita(myResultSet.getFloat("PrezzoUnitarioVendita"));
                        myArticolo.setGiacenza(myResultSet.getInt("Quantita"));
                        myArticolo.setAliquotaIVA(myResultSet.getInt("AliquotaIVA"));
//                    }
                    myArrayList.add(myArticolo);
                    System.out.println();
                }
                myResultSet.close();


                conn.close();
            }
            else {
                System.out.println("Problema nella connessione");
            }

        }
        catch (SQLException e){
            System.out.println("eccezione " + e.getMessage());
        }
    }
}