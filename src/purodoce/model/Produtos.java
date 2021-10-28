package purodoce.model;

import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author lex
 */
public class Produtos{
    private float quantidade;
    private String nome;
    private float preco;
    private float precoTotal;
    private float estoque;

    public Produtos(Produto p) {
      setNome(p.getNome());
      setPreco(p.getPreco());
      setEstoque(p.getQuantidade());
    }

    public Produtos() {
    }
    

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    private float getPreco() {
        return preco;
    }

    private void setPreco(float preco) {
        this.preco = preco;
    }

    public float getEstoque() {
        return estoque;
    }

    private void setEstoque(float estoque) {
        this.estoque = estoque;
    }

     public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        if (quantidade <= getEstoque()){
            this.quantidade = quantidade;
            setPrecoTotal(getPreco(),quantidade);
        }else{
            JOptionPane.showMessageDialog(null, "Você não possui estoque o suficiente. Faça compras antes!","Estoque insuficiente",JOptionPane.ERROR_MESSAGE);
        } 
    }

    public float getPrecoTotal() {
        return precoTotal;
    }

    private void setPrecoTotal(float preco, float qtd) {
        precoTotal = preco * qtd;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produtos other = (Produtos) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Produtos{" + "quantidade=" + quantidade + ", nome=" + nome + ", precoTotal=" + precoTotal + '}';
    }
    
    
    
    
    
    
}
