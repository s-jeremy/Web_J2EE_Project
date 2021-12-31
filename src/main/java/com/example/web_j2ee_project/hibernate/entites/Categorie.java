package com.example.web_j2ee_project.hibernate.entites;

import jakarta.persistence.*;

@Table(name = "categorie", indexes = {
        @Index(name = "titre_UNIQUE", columnList = "titre", unique = true)
})
@Entity
public class Categorie
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategorie", nullable = false)
    private Integer id;

    @Column(name = "titre", nullable = false, length = 60)
    private String titre;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    public Categorie(String titre, String description)
    {
        this.titre = titre;
        this.description = description;
    }

    public Categorie()
    {

    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getTitre()
    {
        return titre;
    }

    public void setTitre(String titre)
    {
        this.titre = titre;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public boolean isComplete()
    {
        if (!this.titre.isEmpty() &&
                !this.description.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}