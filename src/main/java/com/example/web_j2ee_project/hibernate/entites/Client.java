package com.example.web_j2ee_project.hibernate.entites;

import jakarta.persistence.*;

@Table(name = "client")
@Entity
public class Client
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 60)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nom", nullable = false, length = 60)
    private String nom;

    @Column(name = "prenom", nullable = false, length = 60)
    private String prenom;

    @Column(name = "mail", nullable = false, length = 60)
    private String mail;

    @Column(name = "adresse", nullable = false, length = 60)
    private String adresse;

    @Column(name = "role", nullable = false, length = 60)
    private String role;

    @Column(name = "bloquer")
    private Integer bloquer;


    public Client(String username, String password, String nom, String prenom, String mail, String adresse, String role, int bloquer)
    {
        this.username = username;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adresse = adresse;
        this.role = role;
        this.bloquer = bloquer;
    }

    public Client()
    {
    }

    public String getAdresse()
    {
        return adresse;
    }

    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }

    public String getPrenom()
    {
        return prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom = prenom;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getRole()
    {
        return role;
    }

    public Integer getBloquer()
    {
        return bloquer;
    }

    public void setBloquer(Integer bloquer)
    {
        this.bloquer = bloquer;
    }

    public boolean isComplete()
    {
        if (!this.username.isEmpty() &&
                !this.password.isEmpty() &&
                !this.nom.isEmpty() &&
                !this.prenom.isEmpty() &&
                !this.adresse.isEmpty() &&
                !this.mail.isEmpty() &&
                !this.role.isEmpty() ){
            return true;
        }
        else{
            return false;
        }
    }

}

