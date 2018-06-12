package DAOs;

import Entidades.PedidoHasProduto;
import java.util.ArrayList;
import java.util.List;

public class DAOPedidoHasProduto extends DAOGenerico<PedidoHasProduto> {

private List<PedidoHasProduto> lista = new ArrayList<>();    public DAOPedidoHasProduto(){
        super(PedidoHasProduto.class);
    }

    public int autoIdPedidoHasProduto() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPedidoHasProduto) FROM PedidoHasProduto) e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<PedidoHasProduto> listByNome(String nome) {
        return em.createQuery("SELECT e FROM PedidoHasProduto e WHERE e.idPedidoHasProduto) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<PedidoHasProduto> listById(int id) {
        return em.createQuery("SELECT e FROM PedidoHasProduto + e WHERE e.dataCadastro= :id").setParameter("id", id).getResultList();
    }

    public List<PedidoHasProduto> listInOrderNome() {
        return em.createQuery("SELECT e FROM PedidoHasProduto e ORDER BY e.dataCadastro").getResultList();
    }

    public List<PedidoHasProduto> listInOrderId() {
        return em.createQuery("SELECT e FROM PedidoHasProduto e ORDER BY e.idPedidoHasProduto").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<PedidoHasProduto> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getPedidoHasProdutoPK()+ "-" + lf.get(i).getPedidoHasProdutoPK());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOPedidoHasProduto daoPedidoHasProduto = new DAOPedidoHasProduto();
        List<PedidoHasProduto> listaPedidoHasProduto = daoPedidoHasProduto.list();
        for (PedidoHasProduto produto : listaPedidoHasProduto) {
            System.out.println(produto.getPedidoHasProdutoPK()+"-"+produto.getPedidoHasProdutoPK());
        }
    }}