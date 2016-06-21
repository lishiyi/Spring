package info.shiyi;

import org.springframework.beans.factory.annotation.Autowired;

public class TextEditor {
	   @Autowired
	   private SpellChecker spellChecker;
	   public TextEditor() {
	      System.out.println("Inside TextEditor constructor." );
	   }  
	   public SpellChecker getSpellChecker( ){
	      return spellChecker;
	   }  
	   public void spellCheck(){
	      spellChecker.checkSpelling();
	   }
	}