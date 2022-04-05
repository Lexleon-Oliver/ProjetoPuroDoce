
package purodoce.model;

import java.util.Objects;

/**
 *
 * @author lex
 */
public class Produto {
    private Long id;
    private String nome;
    private float quantidade;
    private String medida;
    private float preco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void setQuantidade(float qtd, String medida) {
        switch (medida){
            case "kg":
                this.quantidade=qtd;
            break;
            case "unidade":
                this.quantidade=qtd;
            break;
            case "litro":
                this.quantidade=qtd;
            break;
             case "pente 30 unidades":
                this.quantidade=qtd*30;
                this.setMedida("unidade");
            break;
             case "dúzia":
                this.quantidade=qtd*12;
                this.setMedida("unidade");
            break;
             case "caixa com 12 litros":
                this.quantidade=qtd*12;
                this.setMedida("litro");
            break;
             case "lata 800g":
                this.quantidade=qtd*0.8f;
                this.setMedida("kg");
            break;
             case "caixa 200g":
                this.quantidade=qtd*0.2f;
                this.setMedida("kg");
            break;
             case "pacote 5kg":
                this.quantidade=qtd*5;
                this.setMedida("kg");
            break;

        }
    }

    public float getQuantidade() {
        return quantidade;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Produto{" + "id=" + id + ", nome=" + nome + ", quantidade=" + quantidade + ", medida=" + medida + ", preco=" + preco + '}';
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
        final Produto other = (Produto) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    
    
    
}
