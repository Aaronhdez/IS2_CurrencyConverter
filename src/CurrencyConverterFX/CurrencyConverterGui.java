/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CurrencyConverterFX;
import currencyConverterBG.*;
import currencyConverterFM.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Usuario
 */
public class CurrencyConverterGui extends JFrame {
    /**
     * Structural Attributes
     */
    CurrencyConverter conversor;
    CcFileManager localCcFileManager;
    CurrencyFileManager localCurrencyFileManager;
    
    /**
     * Swing Attributes
     */
    //CENTER
    JPanel centerPanel;
    JTextField fromValue;
    JComboBox comboFrom;
    JComboBox comboTo;
    
    //EAST
    JPanel eastPanel;
    JButton convertBtn;
    
    //SOUTH
    JPanel southPanel;
    JTextField resultField;
    
    /**
     * Graphical Constructor
     */
    public CurrencyConverterGui(String t){
        super(t);
        Container container = new JPanel(new BorderLayout());
        container = getContentPane();
        this.initStructuralAssets();
        initGraphicalAssets(container);
        
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }

    /**
     * BACKGROUND ASSETS CONFIGURATION
     */
    private void initStructuralAssets(){
        /* Main Assets */
        this.localCcFileManager = new CcFileManager();
        this.localCurrencyFileManager = new CurrencyFileManager();
        this.conversor = new CurrencyConverter(localCcFileManager.returnCurrentExchangeRates());    
    }    

    /**
     * GRAPHICAL SETTINGS AND BUILD-UP
     */    
    private void initGraphicalAssets(Container container){
        JPanel main = new JPanel(new BorderLayout());
        initCenterPanel(main);
        initEastPanel(main);
        initSouthPanel(main);
        container.add(main, BorderLayout.CENTER);
        initEvents();
    }    

    private void initCenterPanel(JPanel main){
        this.fromValue = new JTextField(15);
        this.centerPanel = new JPanel(new BorderLayout());
        
        //Value Selector
        JPanel valueSelector = new JPanel();
        valueSelector.add(new JLabel("Cantidad:"), BorderLayout.WEST);
        valueSelector.add(this.fromValue, BorderLayout.CENTER);
        centerPanel.add(valueSelector, BorderLayout.NORTH);
        
        //Currency Selectors
        JPanel currencySelectors = new JPanel();
        this.comboFrom = new JComboBox(this.localCurrencyFileManager.returnCurrencies().toArray());
        this.comboTo = new JComboBox(this.localCurrencyFileManager.returnCurrencies().toArray());
        currencySelectors.add(comboFrom, BorderLayout.EAST);
        currencySelectors.add(new JLabel("   -->   "), BorderLayout.CENTER);
        currencySelectors.add(comboTo, BorderLayout.WEST);
        
        centerPanel.add(currencySelectors, BorderLayout.SOUTH);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        main.add(centerPanel, BorderLayout.CENTER);
    }
    
    private void initEastPanel(JPanel main){
        this.convertBtn = new JButton("Convertir");
        this.eastPanel = new JPanel();
        eastPanel.add(this.convertBtn);
        eastPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        main.add(eastPanel, BorderLayout.EAST);
    }
    
    private void initSouthPanel(JPanel main){
        this.resultField = new JTextField(15);
        this.resultField.setEditable(false);
        this.southPanel = new JPanel();
        this.southPanel.add(new JLabel("Valor al cambio: "), BorderLayout.WEST);
        this.southPanel.add(this.resultField, BorderLayout.CENTER);
        southPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        main.add(southPanel, BorderLayout.SOUTH);
    }
    
    private void initEvents(){
        this.convertBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evn0){
                currencyConverterBG.Currency a = (currencyConverterBG.Currency)comboFrom.getSelectedItem();
                currencyConverterBG.Currency b = (currencyConverterBG.Currency)comboTo.getSelectedItem();
                
                conversor = new CurrencyConverter(a.getTag(),b.getTag(),Double.parseDouble(fromValue.getText()),localCcFileManager.returnCurrentExchangeRates());
                String aux = conversor.convert()+"";
                resultField.setText(aux);
                conversor = new CurrencyConverter(localCcFileManager.returnCurrentExchangeRates()); 
            }
        });
    }
    
    public static void main(String args[]){
        CurrencyConverterGui app = new CurrencyConverterGui("Conversor Monetario");
    }
}
