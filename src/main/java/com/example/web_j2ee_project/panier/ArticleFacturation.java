package com.example.web_j2ee_project.panier;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

public class ArticleFacturation
{
    private Integer id;
    private Integer quantiteProduit;

    public ArticleFacturation(@JsonProperty("id") Integer id, @JsonProperty("quantiteProduit") Integer quantiteProduit)
    {
        this.id = id;
        this.quantiteProduit = quantiteProduit;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getQuantiteProduit()
    {
        return quantiteProduit;
    }

    public void setQuantiteProduit(Integer quantiteProduit)
    {
        this.quantiteProduit = quantiteProduit;
    }

    @Override
    public String toString()
    {
        return "ArticleFacturation{" +
                "id=" + id +
                ", quantiteProduit=" + quantiteProduit +
                '}';
    }
}