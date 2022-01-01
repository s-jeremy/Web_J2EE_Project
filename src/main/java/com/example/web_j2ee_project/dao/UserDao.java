package com.example.web_j2ee_project.dao;

import com.example.web_j2ee_project.hibernate.entites.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao {

    private SessionFactory factory;

    public UserDao(SessionFactory factory){
        this.factory = factory;
    }

    public boolean checkIfUserExist(String username){
        boolean exist = true;
        try{

            String query = "from Client where username =: u";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("u", username);
            Client utilisateur=(Client) q.uniqueResult();

            if (utilisateur==null){
                exist = false;
            }
            session.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return exist;
    }

    public Client getUserByEmailPw(String username, String pw){
        Client utilisateur = null;
        try{

            String query = "from Client where username =: u and password =: p";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("u", username);
            q.setParameter("p",pw);
            utilisateur=(Client) q.uniqueResult();


            session.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return utilisateur;
    }

    public List<Client> getUsers(){
        List<Client> list = null;
        try{

            String query = "from Client";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            list =  q.getResultList();


            session.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
