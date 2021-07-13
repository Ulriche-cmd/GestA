package gesta.models;

import java.sql.Date;

/**
 * @author christian
 *
 */
public class Demande {

	protected int id;
    protected String 	nom;
    protected String 	prenom;
    protected Date 		date_naissance;
    protected String 	adresse;
    protected Long   	telephone;
    protected String 	email;
    protected String 	cni;
    protected String 	description;
    protected Boolean 	etat;
    protected String 	date_demande;
    
    public Demande() {}
    
    public Demande(String nom, String prenom, Date date_naissance, String adresse, Long telephone, String email, String cni, String description, Boolean etat,String date_demande) {
    	super();
    	this.nom = nom;
    	this.prenom = prenom;
    	this.date_naissance = date_naissance;
    	this.adresse = adresse;
    	this.telephone = telephone;
    	this.email = email;
    	this.cni = cni;
    	this.description = description;
    	this.etat = etat;
    	this.date_demande = date_demande;
    }
    
    public Demande(int id, String nom, String prenom, Date date_naissance, String adresse, Long telephone, String email, String cni, String description, Boolean etat,String date_demande) {
    	super();
    	this.id = id;
    	this.nom = nom;
    	this.prenom = prenom;
    	this.date_naissance = date_naissance;
    	this.adresse = adresse;
    	this.telephone = telephone;
    	this.email = email;
    	this.cni = cni;
    	this.description = description;
    	this.etat = etat;
    	this.date_demande = date_demande;
    }
    
    public Demande(String nom, String prenom, java.util.Date date_naissance2, String adresse, Long telephone, String email, String cni, String description) {
    	super();
    	this.nom = nom;
    	this.prenom = prenom;
    	this.date_naissance = (Date) date_naissance2;
    	this.adresse = adresse;
    	this.telephone = telephone;
    	this.email = email;
    	this.cni = cni;
    	this.description = description;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDate_naissance() {
		return date_naissance;
	}

	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Long getTelephone() {
		return telephone;
	}

	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCni() {
		return cni;
	}

	public void setCni(String cni) {
		this.cni = cni;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getEtat() {
		return etat;
	}

	public void setEtat(Boolean etat) {
		this.etat = etat;
	}

	public String getDate_demande() {
		return date_demande;
	}

	public void setDate_demande(String date_demande) {
		this.date_demande = date_demande;
	}
    
    
	
}
