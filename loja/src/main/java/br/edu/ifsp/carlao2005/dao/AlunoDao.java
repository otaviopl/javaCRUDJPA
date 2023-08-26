package br.edu.ifsp.carlao2005.dao;
import br.edu.ifsp.carlao2005.modelo.Aluno;
import jakarta.persistence.EntityManager;

import java.util.List;

public class AlunoDao {
    // EntityManager, que será usado por todos os métodos:
    private EntityManager em;
    // Construtor que já recebe o EntityManager criado:
    public AlunoDao(EntityManager em) {
        this.em = em;
    }
    // Método para gravar um produto no BD:
    public void cadastrar(Aluno alunoCriado) {
        this.em.persist(alunoCriado);
    }

    public Aluno buscarPorId(Long id) {
        return em.find(Aluno.class, id);
    }

    public void excluir(Aluno aluno) {
        em.remove(aluno);
    }
    public List<Aluno> buscarTodos() {
        String jpql = "SELECT a FROM Aluno a";
        return em.createQuery(jpql, Aluno.class).getResultList();
    }
}