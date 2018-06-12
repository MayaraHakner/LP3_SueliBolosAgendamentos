package DAOs;

import Entidades.Sabor;
import java.util.ArrayList;
import java.util.List;

public class DAOSabor extends DAOGenerico<Sabor> {

private List<Sabor> lista = new ArrayList<>();    public DAOSabor(){
        super(Sabor.class);
    }

    public int autoIdSabor() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idSabor) FROM Sabor) e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Sabor> listByNome(String nome) {
        return em.createQuery("SELECT e FROM Sabor e WHERE e.idSabor) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<Sabor> listById(int id) {
        return em.createQuery("SELECT e FROM Sabor + e WHERE e.nome= :id").setParameter("id", id).getResultList();
    }

    public List<Sabor> listInOrderNome() {
        return em.createQuery("SELECT e FROM Sabor e ORDER BY e.nomeSabor").getResultList();
    }

    public List<Sabor> listInOrderId() {
        return em.createQuery("SELECT e FROM Sabor e ORDER BY e.idSabor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Sabor> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdSabor() + "-" + lf.get(i).getNomeSabor());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOSabor daoSabor = new DAOSabor();
        List<Sabor> listaSabor = daoSabor.list();
        for (Sabor produto : listaSabor) {
            System.out.println(produto.getIdSabor()+"-"+produto.getNomeSabor());
        }
    }}