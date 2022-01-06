package com.example.web_j2ee_project.dao;

import com.example.web_j2ee_project.hibernate.entites.Categorie;
import com.example.web_j2ee_project.hibernate.entites.Facture;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class FactureDao
{

    private SessionFactory factory;

    public FactureDao(SessionFactory factory){
        this.factory = factory;
    }

    public List<Facture> getFactures(){
        List<Facture> factureList = null;
        try{

            String query = "from Facture";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);

            factureList =q.getResultList();


            session.close();


        } catch (Exception e){
            e.printStackTrace();
        }
        return factureList;
    }
}
