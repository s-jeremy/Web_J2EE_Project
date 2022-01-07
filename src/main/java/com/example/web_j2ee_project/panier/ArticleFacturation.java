package com.example.web_j2ee_project.panier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleFacturation
{
    private Integer id_prod;
    private Integer quantite_produit;

    public ArticleFacturation(@JsonProperty("id_produit") Integer id_prod, @JsonProperty("quantite_produit") Integer quantite_produit)
    {
        this.id_prod = id_prod;
        this.quantite_produit = quantite_produit;
    }

    public Integer getId()
    {
        return id_prod;
    }

    public void setId(Integer id_prod)
    {
        this.id_prod = id_prod;
    }

    public Integer getQuantiteProduit()
    {
        return quantite_produit;
    }

    public void setQuantiteProduit(Integer quantite_produit)
    {
        this.quantite_produit = quantite_produit;
    }

    @Override
    public String toString()
    {
        return "ArticleFacturation{" +
                "id=" + id_prod +
                ", quantite_produit=" + quantite_produit +
                '}';
    }
}