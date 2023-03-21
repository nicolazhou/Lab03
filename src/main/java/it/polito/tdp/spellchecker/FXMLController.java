package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;


import java.util.*;


public class FXMLController {

	private Dictionary model;
	private String language;
	
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClearText;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private ComboBox<String> cmbLanguange;

    @FXML
    private TextArea txtErrors;

    @FXML
    private TextArea txtTesto;
    

    @FXML
    private Label lblTime;


    @FXML
    private Label lblErrors;
    
    @FXML
    void doClearText(ActionEvent event) {
    	
    	this.txtTesto.clear();
    	this.txtErrors.clear();
    	
    	this.lblErrors.setText("");
    	this.lblTime.setText("");
    	
    }

    @FXML
    void doSpellCheck(ActionEvent event) {

    	this.txtErrors.clear();
    	
    	if(this.cmbLanguange.getValue() == null && this.language == null) {
    		
    		this.txtErrors.setText("Choose the language !");
    		return;
    		
    	} else {
    	
	    	if(this.cmbLanguange.getValue().compareTo("English")==0 && this.language != "English") {
	    		
	    		model.loadDictionary("English");
	    		this.language = "English";
	    		
	    	}
	    	
	    	if(this.cmbLanguange.getValue().compareTo("Italian")==0 && this.language != "Italian") {
	    		
	    		model.loadDictionary("Italian");
	    		this.language = "Italian";
	    		
	    	}
    	
    	}
    	
    	if(this.txtTesto.getText().compareTo("")!=0) {
	    	
    		String testo = this.txtTesto.getText().toLowerCase().replaceAll("[.,\\\\/#!?$%\\\\^&\\\\*;:{}=\\\\-_`~()\\\\[\\\\]\\\"]", "");
	    	
	    	
	    	String[] inputTxt = testo.split(" ");
	    	
	    	
	    	List<String> listaInput = new ArrayList<String>();
	    	
	    	for(String s : inputTxt) {
	    		listaInput.add(s);
	    	}
	    	
	    	
	    	long start = System.nanoTime();
	    	
	    	//List<RichWord> lista = model.spellCheckText(listaInput);
	    	//List<RichWord> lista = model.spellCheckTextLinear(listaInput);
	    	List<RichWord> lista = model.spellCheckTextDichotomic(listaInput);
	    	
	    	long end = System.nanoTime();
	    	
	    	long time = end-start;
	    	
	    	int err = 0;
	    	for(RichWord rw : lista) {
	    		
	    		if(!rw.isCorrect()) {
	    			this.txtErrors.appendText(rw.getWord() + "\n");
	    			err++;
	    		}
	    	}
	    	
	    	this.lblErrors.setText("The text contains " + err + " errors");
	    	
	    	this.lblTime.setText("Spell check completed in " + time/1000000000.0 +" seconds");
    	
    	}
    	
    	
    }

    
    @FXML
    void initialize() {
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbLanguange != null : "fx:id=\"cmbLanguange\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrors != null : "fx:id=\"txtErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrors != null : "fx:id=\"lblErrors\" was not injected: check your FXML file 'Scene.fxml'.";
        
        // Aggiungo lingue
        this.cmbLanguange.getItems().add("English");
        this.cmbLanguange.getItems().add("Italian");
    }
    
    
    public void setModel(Dictionary model) {
    	this.model = model;
    }

    
}
