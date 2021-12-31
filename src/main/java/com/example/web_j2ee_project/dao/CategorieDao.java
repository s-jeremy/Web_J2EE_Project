package com.example.web_j2ee_project.dao;

import com.example.web_j2ee_project.hibernate.entites.Categorie;
import com.example.web_j2ee_project.hibernate.entites.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class CategorieDao
{

    private SessionFactory factory;

    public CategorieDao(SessionFactory factory){
        this.factory = factory;
    }

    public List<Categorie> getCategories(){
        List<Categorie> categorieList = null;
        try{

            String query = "from Categorie";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);

            categorieList =q.getResultList();


            session.close();


        } catch (Exception e){
            e.printStackTrace();
        }
        return categorieList;
    }
}
