
package purodoce.model;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author lex
 */
public class Orcamento {
    private Cliente cliente;
    private LocalDate dataEntrega;
    private String numPessoas;
    private String forma;
    private String dimensao;
    private List<Produtos> listaProd;
    private float precoProdutos;
    private String maoObra;
    private float precoVenda;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    
//
    public String getNumPessoas() {
        return numPessoas;
    }

    public void setNumPessoas(String numPessoas) {
        this.numPessoas = numPessoas;
    }
//
    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }
//
    public List<Produtos> getListaProd() {
        return listaProd;
    }

    public void setListaProd(List<Produtos> listaProd) {
        this.listaProd = listaProd;
    }

    public float getPrecoProdutos() {
        return precoProdutos;
    }

    public void setPrecoProdutos(List<Produtos> produtos) {
        precoProdutos=0;
        for(Produtos prods: produtos){
            precoProdutos = precoProdutos+prods.getPrecoTotal();
        }
    }
//
    public String getMaoObra() {
        return maoObra;
    }

    public void setMaoObra(String maoObra) {
        this.maoObra = maoObra;
    }
//
    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

  
       
}
