/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currencyConverterBG;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class CurrencyConverter {
    /**
     * Pasive Atributes
     */
    
    private double fromValue;
    private String from;
    private String to;
    ArrayList<ExchangeRate> exchangeRates; 

    /**
     * Default constructor
     */
    public CurrencyConverter(ArrayList<ExchangeRate> exchangeRates){
        this.fromValue = 0;
        this.from = "";
        this.to = "";
        this.exchangeRates = exchangeRates;
    }    

    /**
     * Constructor with parameters
     */
    public CurrencyConverter(String from, String to, double fromValue, ArrayList<ExchangeRate> exchangeRates){
        this.fromValue = fromValue;
        this.from = from;
        this.to = to;
        this.exchangeRates = exchangeRates;
    }
    
    public double getFromValue(){
        return this.fromValue;
    }
    
    public void setFromValue(double value){
        this.fromValue = value;
    }
    
    /**
     * En interfaz gráfica 
     * @param from
     * @param to
     * @param changeValue
     * @return 
     */
    public double convert(){
        String aux = this.from + "to" + this.to;
        double tempExchangeRate = 0;
        for(ExchangeRate er: this.exchangeRates){
            if(er.getName().equals(aux)){
                tempExchangeRate = er.getValue();
            }
        }
        return this.fromValue*tempExchangeRate;
    }
    
}
