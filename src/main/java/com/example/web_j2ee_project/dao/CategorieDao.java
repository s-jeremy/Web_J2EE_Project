package com.example.web_j2ee_project.dao;

import com.example.web_j2ee_project.hibernate.entites.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CategorieDao
{

    private SessionFactory factory;

    public CategorieDao(SessionFactory factory){
        this.factory = factory;
    }

    /*public Client getUserByEmailPw(String id, String pw){
        Client utilisateur = null;


        try{

            String query = "from Client where username =: u and password =: p";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("u", id);
            q.setParameter("p",pw);
            utilisateur=(Client) q.uniqueResult();


            session.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return utilisateur;
    }*/
}
