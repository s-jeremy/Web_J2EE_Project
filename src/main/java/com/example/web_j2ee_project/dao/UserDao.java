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

    /**
     * Cherche si l'utilisateur existe dans la base
     *
     * @param username Le nom de l'utilisateur à rechercher
     * @return retourne un boolean true si l'utilisateur existe dans la base
     */
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

    /**
     * Fonction d'identification, recherche si l'utilisateur et le mot de passe associé sont présent
     * dans la base
     * @param username identifiant de l'utilisateur
     * @param pw mot de passe de l'utilisateur
     * @return retourne les informations associé à l'utilisateur si il existe avec le bon mot de passe associé
     */
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

    /**
     * Retourne la list de tous les utilisateurs de la base
     *
     * @return retourne une list d'objet Client contenant tous les utilisateur de la base
     */
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

    /**
     * Retourne l'utilisateur associé à l'id fournit
     *
     * @param id_utilisateur référence l'id de l'utilisateur à retourner
     * @return retourne l'utilisateur associé à l'id
     */
    public Client getUserById(int id_utilisateur){
        Client utilisateur = null;
        try{

            String query = "from Client where id =: id_utilisateur";
            Session session = this.factory.openSession();
            Query q = session.createQuery(query);
            q.setParameter("id_utilisateur", id_utilisateur);
            utilisateur=(Client) q.uniqueResult();


            session.close();

        } catch (Exception e){
            e.printStackTrace();
        }
        return utilisateur;
    }

}
