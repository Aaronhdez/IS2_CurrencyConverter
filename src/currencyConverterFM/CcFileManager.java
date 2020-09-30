/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currencyConverterFM;

/**
 *
 * @author Usuario
 */

import currencyConverterBG.ExchangeRate;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CcFileManager {

    ArrayList<ExchangeRate> exchangeRatesFromCSV; 
    
    public CcFileManager(){
        this.exchangeRatesFromCSV = new ArrayList<ExchangeRate>();
        try {
            init(this.exchangeRatesFromCSV);
        } catch (Exception ex) {
            Logger.getLogger(CcFileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Main loader
     * 
     * Extracts every exchange rate present in <filename.csv>
     * and creates an instance of exchangeRate from it, which is stored in
     * an ArrayList
     * 
     * @param exchangeRatesFromCSV empty-by-default aL of exchangeRate instances
     */
    private void init(ArrayList<ExchangeRate> exchangeRatesFromCSV) throws Exception{
        File file = new File("./files/assets/exchangeRates.txt");
        FileInputStream fsr = new FileInputStream(file);
        InputStreamReader isr = new InputStreamReader(fsr, "cp1252");
        BufferedReader buffer = new BufferedReader(isr);
        try{
            String line = buffer.readLine();
            while(line != null){
                
                String name = line.substring(0, line.indexOf(";"));
                //line = line.substring(line.indexOf(";")+1, line.lastIndexOf(";"));
                line = line.substring(line.indexOf(";")+1, line.length());
                String value = line.substring(0,line.indexOf(";"));
                
                ExchangeRate newCurrency = new ExchangeRate(Double.parseDouble(value), name);
                exchangeRatesFromCSV.add(newCurrency);
                
                line = buffer.readLine();
            }
        }catch(Exception e1){
            System.out.print(e1);
        } finally {
            try {
                fsr.close();
            } catch(Exception e2) {
                System.out.print(e2);
            }
        }
    }
    
    /***
     * Return current Arraylist of exchange rates
     * 
     * @return current Arraylist
     */
    public ArrayList<ExchangeRate> returnCurrentExchangeRates(){
        return this.exchangeRatesFromCSV;
    }
    
}
