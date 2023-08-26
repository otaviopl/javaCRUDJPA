package br.edu.ifsp.carlao2005.testes;

import br.edu.ifsp.carlao2005.dao.AlunoDao;
import br.edu.ifsp.carlao2005.modelo.Aluno;
import jakarta.persistence.EntityManager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CadastroDeAluno {

    public static void cadastrarAluno(EntityManager em) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("RA do aluno: ");
        String ra = scanner.nextLine();

        System.out.print("E-mail do aluno: ");
        String email = scanner.nextLine();

        System.out.print("Nota 1: ");
        BigDecimal nota1 = new BigDecimal(scanner.nextLine());

        System.out.print("Nota 2: ");
        BigDecimal nota2 = new BigDecimal(scanner.nextLine());

        System.out.print("Nota 3: ");
        BigDecimal nota3 = new BigDecimal(scanner.nextLine());

        em.getTransaction().begin();

        Aluno alunoCriado = new Aluno();
        alunoCriado.setNome(nome);
        alunoCriado.setRa(ra);
        alunoCriado.setEmail(email);
        alunoCriado.setNota1(nota1);
        alunoCriado.setNota2(nota2);
        alunoCriado.setNota3(nota3);

        AlunoDao dao = new AlunoDao(em);
        dao.cadastrar(alunoCriado);

        em.getTransaction().commit();
        System.out.println("Aluno cadastrado com sucesso!");
    }

    public static void excluirAluno(EntityManager em) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o ID do aluno a ser excluído: ");
        Long id = scanner.nextLong();

        em.getTransaction().begin();

        AlunoDao dao = new AlunoDao(em);
        Aluno aluno = dao.buscarPorId(id);

        if (aluno != null) {
            dao.excluir(aluno);
            System.out.println("Aluno excluído com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
        }

        em.getTransaction().commit();
    }

    //NAO HÁ TRATAMENTO DE EREROS NAS FUNCOES, POR FAVOR SEJA UM USUARIO CONSCIENTE.
    public static void alterarAluno(EntityManager em) {
        AlunoDao dao = new AlunoDao(em);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o ID do aluno a ser alterado: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        Aluno aluno = dao.buscarPorId(id);
        if (aluno != null) {
            System.out.println("Aluno encontrado:");
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("RA: " + aluno.getRa());

            System.out.print("Nova nota 1: ");
            BigDecimal novaNota1 = new BigDecimal(scanner.nextLine());

            System.out.print("Nova nota 2: ");
            BigDecimal novaNota2 = new BigDecimal(scanner.nextLine());

            System.out.print("Nova nota 3: ");
            BigDecimal novaNota3 = new BigDecimal(scanner.nextLine());

            aluno.setNota1(novaNota1);
            aluno.setNota2(novaNota2);
            aluno.setNota3(novaNota3);

            em.getTransaction().begin();
            em.merge(aluno); // Atualiza o aluno no banco de dados
            em.getTransaction().commit();

            System.out.println("Aluno alterado com sucesso!");
        } else {
            System.out.println("Aluno não encontrado.");
        }

    }
    public static void listarTodosAlunos(EntityManager em) {
        AlunoDao dao = new AlunoDao(em);

        List<Aluno> alunos = dao.buscarTodos();

        if (!alunos.isEmpty()) {
            System.out.println("Todos os alunos cadastrados:");
            for (Aluno aluno : alunos) {
                System.out.println("ID: " + aluno.getId());
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("RA: " + aluno.getRa());
                // ... imprimir outras informações relevantes
            }
        } else {
            System.out.println("Nenhum aluno cadastrado.");
        }

    }
    public static void listarAlunosSituacao(EntityManager em) {
        AlunoDao dao = new AlunoDao(em);

        List<Aluno> alunos = dao.buscarTodos();

        if (!alunos.isEmpty()) {
            System.out.println("Situação dos alunos:");
            for (Aluno aluno : alunos) {
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("Situação: " + (aluno.calcularMedia().compareTo(new BigDecimal("6.0")) >= 0 ? "Aprovado" : "Não Aprovado"));
                // ... imprimir outras informações relevantes
            }
        } else {
            System.out.println("Nenhum aluno cadastrado.");
        }
    }
}
