package gesta.models;

import java.sql.Date;

public class Evenement {
	
	protected int id;
    protected String 	intitule;
    protected String 	description;
    protected Date 		date_debut;
    protected Date 		date_fin;
    
    Evenement() {}
    
    Evenement(String intitule, String desc,Date date_deb,Date date_fin) {
    	super();
    	this.intitule = intitule;
    	this.description = desc;
    	this.date_debut = date_deb;
    	this.date_fin = date_fin;
    }
    
    Evenement(int id, String intitule, String desc,Date date_deb,Date date_fin) {
    	super();
    	this.id = id;
    	this.intitule = intitule;
    	this.description = desc;
    	this.date_debut = date_deb;
    	this.date_fin = date_fin;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}
    
    
	
}
