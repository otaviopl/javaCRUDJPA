package br.edu.ifsp.carlao2005;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import br.edu.ifsp.carlao2005.testes.CadastroDeAluno;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("loja");
        EntityManager em = emf.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu de Opções:");
            System.out.println("1. Adicionar Aluno no Banco de Dados");
            System.out.println("2. Excluir Aluno do Banco de Dados");
            System.out.println("3. Alterar Aluno já existente no Bando de Dados");
            System.out.println("4. Listar Aluno pelo nome");
            System.out.println("5. Listar alunos (com status de aprovação)");
            System.out.println("6. Encerrar programa");
            System.out.print("Escolha uma opção: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> CadastroDeAluno.cadastrarAluno(em);
                case 2 -> CadastroDeAluno.excluirAluno(em);
                case 3 -> CadastroDeAluno.alterarAluno(em);
                case 4 -> CadastroDeAluno.listarTodosAlunos(em);
                case 5 -> CadastroDeAluno.listarAlunosSituacao(em);
                case 6 -> System.out.println("Encerrando o programa...");
                default -> System.out.println("Opção inválida. Escolha novamente.");}
        } while (choice != 6);

        em.close();
        emf.close();}
}
