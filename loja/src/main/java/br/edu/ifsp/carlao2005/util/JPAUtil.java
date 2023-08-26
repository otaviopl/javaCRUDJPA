package br.edu.ifsp.carlao2005.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
public class JPAUtil {
    // Atributo FACTORY, constante, pertencente a esta classe.
    // Só vai criar a EntityManagerFactory apenas uma vez.
    private static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("loja");
    // Método que devolve um EntityManager.
    // Método static, ou seja, pertence a classe.
    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
