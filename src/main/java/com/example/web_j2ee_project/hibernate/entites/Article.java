package com.example.web_j2ee_project.hibernate.entites;

import jakarta.persistence.*;

@Table(name = "article", indexes = {
        @Index(name = "categorie_idx", columnList = "id_categorie")
})
@Entity
public class Article
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produit", nullable = false)
    private Integer id;

    @Column(name = "nom_produit", nullable = false, length = 60)
    private String nomProduit;

    @Column(name = "description_produit", nullable = false, length = 1000)
    private String descriptionProduit;

    @Column(name = "prix_produit", nullable = false)
    private Integer prixProduit;

    @Column(name = "quantite_produit", nullable = false)
    private Integer quantiteProduit;

    @JoinColumn(name = "id_categorie", nullable = false)
    private Integer idCategorie;

    public Article(String nomProduit, String descriptionProduit, Integer prixProduit, Integer quantiteProduit, Integer idCategorie)
    {
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.quantiteProduit = quantiteProduit;
        this.idCategorie = idCategorie;
    }

    public Article()
    {

    }

    public Integer getIdCategorie()
    {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie)
    {
        this.idCategorie = idCategorie;
    }

    public Integer getQuantiteProduit()
    {
        return quantiteProduit;
    }

    public void setQuantiteProduit(Integer quantiteProduit)
    {
        this.quantiteProduit = quantiteProduit;
    }

    public Integer getPrixProduit()
    {
        return prixProduit;
    }

    public void setPrixProduit(Integer prixProduit)
    {
        this.prixProduit = prixProduit;
    }

    public String getDescriptionProduit()
    {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit)
    {
        this.descriptionProduit = descriptionProduit;
    }

    public String getNomProduit()
    {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit)
    {
        this.nomProduit = nomProduit;
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
        if (!this.nomProduit.isEmpty() &&
                !this.descriptionProduit.isEmpty()){
            return true;
        }
        else{
            return false;
        }
    }
}