/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista8.pkg3_livroautor5agoravai;

/**
 *
 * @author alexandre
 */
import java.util.ArrayList;
import java.util.List;

public final class Autor {

    private int id;
    private String nome;
    private List<Livro> livros;

    public Autor(String nome) {
        this.nome = nome;
        this.livros = new ArrayList();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setLivros(List<Livro> livros) {
         for(Livro livro: livros){
             this.adicionarLivro(livro);
        }
    }

    public List<Livro> getLivros() {
        return this.livros;
    }

    public void adicionarLivro(Livro livro) {
        if (!this.getLivros().contains(livro)) {
            this.livros.add(livro);
            livro.adicionarAutor(this);
        }
    }

    public void removerLivro(Livro livro) {
        if (this.getLivros().contains(livro)) {
            this.livros.remove(livro);
            livro.removerAutor(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
