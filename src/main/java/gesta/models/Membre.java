package gesta.models;

import java.sql.Date;

public class Membre {

	protected int id;
    protected String 	nom;
    protected String 	prenom;
    protected Date 		date_naissance;
    protected String 	adresse;
    protected Long   	telephone;
    protected String 	email;
    protected String 	cni;
    protected String 	role;
    protected String 	login;
    protected String 	mdp;
    
 public Membre() {}
    
    public Membre(String nom, String prenom, Date date_naissance, String adresse, Long telephone, String email, String cni, String role, String login,String mdp) {
    	super();
    	this.nom = nom;
    	this.prenom = prenom;
    	this.date_naissance = date_naissance;
    	this.adresse = adresse;
    	this.telephone = telephone;
    	this.email = email;
    	this.cni = cni;
    	this.role = role;
    	this.login = login;
    	this.mdp = mdp;
    }
    
    public Membre(int id, String nom, String prenom, Date date_naissance, String adresse, Long telephone, String email, String cni, String role, String login,String mdp) {
    	super();
    	this.id = id;
    	this.nom = nom;
    	this.prenom = prenom;
    	this.date_naissance = date_naissance;
    	this.adresse = adresse;
    	this.telephone = telephone;
    	this.email = email;
    	this.cni = cni;
    	this.role = role;
    	this.login = login;
    	this.mdp = mdp;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
    
    
	
}
