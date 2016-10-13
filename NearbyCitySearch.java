/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nearbycitysearch;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import static nearbycitysearch.locDetails.geoList;

/**
 *
 * @author Sayyed Shozib Abbas
 */
public class NearbyCitySearch {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileName = "C:\\GeoLiteCity-Location.csv";
        String colSplit = ",";
        String lineSplit = "";
        String line = null;
        
        locDetails.geoList = new LinkedList<locDetails>();
        
        System.out.println("Initializing Data...");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            int i = 0;
            
            while ((line = br.readLine()) != null) {
                
                if(i < 2)
                {
                    i++;
                    continue;
                }
                
                String[] row = line.split(colSplit);
                
                row[3] = row[3].replace("\"", "");
                
                locDetails temp = new locDetails();
                temp.locId = Integer.parseInt(row[0]);
                temp.country = row[1];
                temp.city = row[3];
                temp.latitude = row[5];
                temp.longitude = row[6];
                
                geoList.add(temp);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }    
        
        System.out.println("Data Initialization Completed...");
        
        nearbyCities();
        
    }
    
    public static void countrySearch() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a city name: ");
        String cityName = reader.nextLine();
        System.out.println("Searching location details for "+cityName);
        
        int length = geoList.size();
                
        for(int i = 0; i < length; i++) {
            if(cityName.equals(geoList.get(i).city)) {
                System.out.println("City Found!");
                System.out.println("Longitude: "+geoList.get(i).longitude);
                System.out.println("Latitude: "+geoList.get(i).latitude);
                
                return;
            }
        }
    } 
    
    public static void nearbyCities() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Enter a city name: ");
        String cityName = reader.nextLine();
        System.out.println("Searching location details for "+cityName);
        
        int length = geoList.size();
        
        float lat1 = 0, lon1 = 0;
        
        for(int i = 0; i < length; i++) {
            if(cityName.equals(geoList.get(i).city)) {
                System.out.println("City Found!");
                lon1 = Float.parseFloat(geoList.get(i).longitude);
                lat1 = Float.parseFloat(geoList.get(i).latitude);
                
                i = length + 1;
            }
        }
   }
}
