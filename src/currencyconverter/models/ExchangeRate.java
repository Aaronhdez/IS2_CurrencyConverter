/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package currencyconverter.models;

/**
 *
 * @author Usuario
 */
public class ExchangeRate {

    private double value;
    private String name;
    
    public ExchangeRate(double localExchangeRate, String exchangeName){
        this.value = localExchangeRate;
        this.name = exchangeName;
    }

    /**
     * Returns current exchange rate value
     * @return String current exchange rate value
     */
    public double getValue(){
        return this.value;
    }
    
    /**
     * Returns current exchange rate name
     * @return String current exchange rate name
     */
    public String getName(){
        return this.name;
    }
    
}
