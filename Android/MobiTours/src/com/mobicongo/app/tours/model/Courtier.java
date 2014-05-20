/***********************************************************************
 * Module:  Courtier.java
 * Author:  Mishka
 * Purpose: Defines the Class Courtier
 ***********************************************************************/
package com.mobicongo.app.tours.model;


public class Courtier extends Person {

   public enum Specifications{
	   AGENCE_VOYAGE("Agence de voyage"),
	   COMPAGNIES("Compagnie aviation");
	   
	   final String name_spec;
	   
	   private Specifications(String name_spec){
		   this.name_spec=name_spec;
	   }
	   @Override public String toString(){
	        return name_spec;
	    }
   }
	
   public Courtier(){
	   //null constructor
   }
   
	   private int courtierId;
	   private Specifications specification;
	   
	public int getCourtierId() {
		return courtierId;
	}
	public void setCourtierId(int courtierId) {
		this.courtierId = courtierId;
	}
	public Specifications getSpecification() {
		return specification;
	}
	public void setSpecification(Specifications specification) {
		this.specification = specification;
	}

}