/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mayara Hakner
 */
@Entity
@Table(name = "pedido")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p")})
public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pedido")
    private Integer idPedido;
    @Column(name = "data_pedido")
    @Temporal(TemporalType.DATE)
    private Date dataPedido;
    @Column(name = "horario_entrega")
    private String horarioEntrega;
    @Column(name = "enterga_vem_buscar")
    private boolean entergaVemBuscar;
    @Column(name = "endereco_entrega")
    private String enderecoEntrega;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "desconto_pedido")
    private Double descontoPedido;
    @Column(name = "observacao_pedido")
    private String observacaoPedido;
    @JoinColumn(name = "cliente_codigo_cliente", referencedColumnName = "codigo_cliente")
    @ManyToOne(optional = false)
    private Cliente clienteCodigoCliente;
    @JoinColumn(name = "funcionario_id_funcionario", referencedColumnName = "id_funcionario")
    @ManyToOne(optional = false)
    private Funcionario funcionarioIdFuncionario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<PedidoHasProduto> pedidoHasProdutoList;

    public Pedido() {
    }

    public Pedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(String horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

    public boolean getEntergaVemBuscar() {
        return entergaVemBuscar;
    }

    public void setEntergaVemBuscar(boolean entergaVemBuscar) {
        this.entergaVemBuscar = entergaVemBuscar;
    }

    public String getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(String enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public Double getDescontoPedido() {
        return descontoPedido;
    }

    public void setDescontoPedido(Double descontoPedido) {
        this.descontoPedido = descontoPedido;
    }

    public String getObservacaoPedido() {
        return observacaoPedido;
    }

    public void setObservacaoPedido(String observacaoPedido) {
        this.observacaoPedido = observacaoPedido;
    }

    public Cliente getClienteCodigoCliente() {
        return clienteCodigoCliente;
    }

    public void setClienteCodigoCliente(Cliente clienteCodigoCliente) {
        this.clienteCodigoCliente = clienteCodigoCliente;
    }

    public Funcionario getFuncionarioIdFuncionario() {
        return funcionarioIdFuncionario;
    }

    public void setFuncionarioIdFuncionario(Funcionario funcionarioIdFuncionario) {
        this.funcionarioIdFuncionario = funcionarioIdFuncionario;
    }

    public List<PedidoHasProduto> getPedidoHasProdutoList() {
        return pedidoHasProdutoList;
    }

    public void setPedidoHasProdutoList(List<PedidoHasProduto> pedidoHasProdutoList) {
        this.pedidoHasProdutoList = pedidoHasProdutoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPedido != null ? idPedido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.idPedido == null && other.idPedido != null) || (this.idPedido != null && !this.idPedido.equals(other.idPedido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Pedido[ idPedido=" + idPedido + " ]";
    }
    
}
