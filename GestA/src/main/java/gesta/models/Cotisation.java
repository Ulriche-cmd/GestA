package gesta.models;

import java.sql.Date;

public class Cotisation {

	protected int id;
    protected Float 	montant;
    protected Date 		date_cotisation;
    protected int 		id_evenement;
    protected int 		id_membre;
    
    Cotisation() {}
    
    Cotisation(Float montant, Date date_cotisation, int id_eve, int id_memb) {
    	super();
    	this.montant = montant;
    	this.date_cotisation = date_cotisation;
    	this.id_evenement = id_eve;
    	this.id_membre = id_memb;
    }
    
    Cotisation(int id, Float montant, Date date_cotisation, int id_eve, int id_memb) {
    	super();
    	this.id = id;
    	this.montant = montant;
    	this.date_cotisation = date_cotisation;
    	this.id_evenement = id_eve;
    	this.id_membre = id_memb;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getMontant() {
		return montant;
	}

	public void setMontant(Float montant) {
		this.montant = montant;
	}

	public Date getDate_cotisation() {
		return date_cotisation;
	}

	public void setDate_cotisation(Date date_cotisation) {
		this.date_cotisation = date_cotisation;
	}

	public int getId_evenement() {
		return id_evenement;
	}

	public void setId_evenement(int id_evenement) {
		this.id_evenement = id_evenement;
	}

	public int getId_membre() {
		return id_membre;
	}

	public void setId_membre(int id_membre) {
		this.id_membre = id_membre;
	}
}
