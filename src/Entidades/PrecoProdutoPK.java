/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mayara Hakner
 */
@Embeddable
public class PrecoProdutoPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "data_preco_produto")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPrecoProduto;
    @Basic(optional = false)
    @Column(name = "produto_id_produto")
    private int produtoIdProduto;

    public PrecoProdutoPK() {
    }

    public PrecoProdutoPK(Date dataPrecoProduto, int produtoIdProduto) {
        this.dataPrecoProduto = dataPrecoProduto;
        this.produtoIdProduto = produtoIdProduto;
    }

    public Date getDataPrecoProduto() {
        return dataPrecoProduto;
    }

    public void setDataPrecoProduto(Date dataPrecoProduto) {
        this.dataPrecoProduto = dataPrecoProduto;
    }

    public int getProdutoIdProduto() {
        return produtoIdProduto;
    }

    public void setProdutoIdProduto(int produtoIdProduto) {
        this.produtoIdProduto = produtoIdProduto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataPrecoProduto != null ? dataPrecoProduto.hashCode() : 0);
        hash += (int) produtoIdProduto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrecoProdutoPK)) {
            return false;
        }
        PrecoProdutoPK other = (PrecoProdutoPK) object;
        if ((this.dataPrecoProduto == null && other.dataPrecoProduto != null) || (this.dataPrecoProduto != null && !this.dataPrecoProduto.equals(other.dataPrecoProduto))) {
            return false;
        }
        if (this.produtoIdProduto != other.produtoIdProduto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.PrecoProdutoPK[ dataPrecoProduto=" + dataPrecoProduto + ", produtoIdProduto=" + produtoIdProduto + " ]";
    }
    
}
