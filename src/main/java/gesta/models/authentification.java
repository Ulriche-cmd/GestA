package gesta.models;

import java.io.Serializable;

public class authentification implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1;
    private String login;
    private String mdp;
    private String role;

    public String getlogin() {
        return login;
    }

    public void setlogin(String login) {
        this.login = login;
    }

    public String getmdp() {
        return mdp;
    }

    public void setmdp(String mdp) {
        this.mdp = mdp;
    }
    
    public String getrole() {
        return role;
    }

    public void setrole(String role) {
        this.role = role;
    }
}