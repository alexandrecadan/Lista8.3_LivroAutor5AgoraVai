/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista8.pkg3_livroautor5agoravai;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author alexandre
 */
public class Lista83_LivroAutor5AgoraVai {

    private AutorDAO autorDAO;
    private LivroDAO livroDAO;

    public Lista83_LivroAutor5AgoraVai() throws Exception {
        autorDAO = new AutorDAO();
        livroDAO = new LivroDAO();
    }

    public static void main(String args[]) throws Exception {
        Lista83_LivroAutor5AgoraVai main = new Lista83_LivroAutor5AgoraVai();
        String opcao = "";
        while (true) {
            try {
                System.out.println("Escolha uma das opções e tecle <ENTER>: ");
                System.out.println("  1 - Incluir Autor");
                System.out.println("  2 - Incluir Livro");
                System.out.println("  3 - Listar Autores");
                System.out.println("  4 - Listar Livro com autores");
                System.out.println("  5 - Listar Autores de um livro");
                System.out.println("  6 - Listar Livros de um autor");
                System.out.println("  7 - Sair");
                Scanner sc = new Scanner(System.in, "ISO-8859-1");
                opcao = sc.nextLine();
                switch (opcao) {
                    case "1":
                        main.incluirAutor();
                        break;
                    case "2":
                        main.incluirLivro();
                        break;
                    case "3":
                        main.listarAutores();
                        break;
                    case "4":
                        main.listarLivroComAutores();
                        break;
                    case "5":
                        main.lerAutores();
                        break;
                    case "6":
                        main.lerLivro();
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
                if (opcao.equals("7")) {
                    break;
                }
            } catch (Exception ex) {
                System.out.println("Falha na operação. Mensagem=" + ex.getMessage());
                //ex.printStackTrace();
            }
        }
    }

    public void incluirAutor() {
        System.out.print("Digite o nome do autor:");
        Scanner sc = new Scanner(System.in, "ISO-8859-1");
        String nome = sc.nextLine();
        //Autor autor = new Autor(nome);
        int numLivros = 1;
        List<Livro> listaLivros = new ArrayList();
        int idLivro;
        do {
            try {
                Scanner sc3 = new Scanner(System.in);
                System.out.print("ID Livro " + numLivros + ":");
                idLivro = sc3.nextInt();
                if (idLivro == -1){
                    break;
                }
                Livro livro = livroDAO.consultarLivro(idLivro);
                if (livro != null) {
                    listaLivros.add(livro);
                    numLivros++;
                } else {
                   System.out.println("Livro não existe!"); 
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("ID livro não é inteiro ou inválido!");
            } 
        } while (true);
        Autor autor = new Autor(nome, listaLivros);
        autorDAO.inserirAutor(autor);
    }

    public void incluirLivro() {
        System.out.print("Digite o título do livro:");
        Scanner sc = new Scanner(System.in, "ISO-8859-1");
        String titulo = sc.nextLine();
        int numAutores = 1;
        List<Autor> listaAutores = new ArrayList();
        int idAutor;
        do {
            try {
                Scanner sc2 = new Scanner(System.in, "ISO-8859-1");
                System.out.print("ID Autor " + numAutores + ":");
                idAutor = sc2.nextInt();
                if (idAutor == -1) {
                    break;
                }
                Autor autor = autorDAO.consultarAutor(idAutor);
                if (autor != null) {
                    listaAutores.add(autor);
                    numAutores++;
                } else {
                    System.out.println("Autor não existe!");
                }
            } catch (Exception ex) {
                System.out.println("ID autor não é inteiro ou inválido!");
            }
        } while (true);

        Livro livro = new Livro(titulo, listaAutores);
        livroDAO.inserirLivro(livro);
    }

    public void listarAutores() throws Exception {
        List<Autor> listaAutores = autorDAO.listarAutores();
        Collections.sort(listaAutores, new Comparator<Autor>() {
            public int compare(Autor arg0, Autor arg1) {
                return arg0.getNome().compareToIgnoreCase(arg1.getNome());
            }
        });
        System.out.println("ID\tNOME");
        for (Autor autor : listaAutores) {
            System.out.println(autor.getId() + " \t" + autor.getNome());
        }
    }

    public void listarLivroComAutores() throws Exception {
        List<Livro> listaLivros = livroDAO.listarLivroComAutores();
        Collections.sort(listaLivros, new Comparator<Livro>() {
            public int compare(Livro arg0, Livro arg1) {
                String titulo = arg0.getTitulo();
                int i = titulo.compareToIgnoreCase(arg1.getTitulo());
                return i;
            }
        });
        System.out.println("ID\tTitulo do Livro\tAutores");
        for (Livro livro : listaLivros) {
            System.out.print(livro.getId() + "\t" + livro.getTitulo() + "\t");
            for (Autor autor : livro.getAutores()) {
                System.out.print(autor.getNome() + ";");
            }
            System.out.print("\n");
        }

    }

    //CASE 5
    public void lerAutores() throws Exception {
        System.out.println("Digite o id do livro");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        Connection con = ConnectionFactory.getConnection();
        for (Autor lista : LivroDAO.lerAutores(id, con)) {
            System.out.println(lista.getNome());
        }
    }

    //CASE 6
    public void lerLivro() throws Exception {
        System.out.println("Digite o id do autor");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        Connection con = ConnectionFactory.getConnection();
        for (Livro lista : AutorDAO.lerLivro(id, con)) {
            System.out.println(lista.getTitulo());
        }
    }

}
