package DAOs;

import Entidades.UnMedida;
import java.util.ArrayList;
import java.util.List;

public class DAOUnMedida extends DAOGenerico<UnMedida> {

private List<UnMedida> lista = new ArrayList<>();    public DAOUnMedida(){
        super(UnMedida.class);
    }

    public int autoIdUnMedida() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idUnMedida) FROM UnMedida e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<UnMedida> listByNome(String nome) {
        return em.createQuery("SELECT e FROM UnMedida e WHERE e.idUnMedida) LIKE :nome").setParameter("nome", "%" + nome + "%").getResultList();
    }

    public List<UnMedida> listById(int id) {
        return em.createQuery("SELECT e FROM UnMedida + e WHERE e.nome= :id").setParameter("id", id).getResultList();
    }

    public List<UnMedida> listInOrderNome() {
        return em.createQuery("SELECT e FROM UnMedida e ORDER BY e.nome").getResultList();
    }

    public List<UnMedida> listInOrderId() {
        return em.createQuery("SELECT e FROM UnMedida e ORDER BY e.idUnMedida").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<UnMedida> lf;
        if (qualOrdem.equals("id")) {
            lf = listInOrderId();
        } else {
            lf = listInOrderNome();
        }

        List<String> ls = new ArrayList<>();
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdUnMedida() + "-" + lf.get(i).getNomeUnidadeMedida());
        }
        return ls;
    }


public static void main(String[] args) {
        DAOUnMedida daoUnMedida = new DAOUnMedida();
        List<UnMedida> listaUnMedida = daoUnMedida.list();
        for (UnMedida produto : listaUnMedida) {
            System.out.println(produto.getIdUnMedida()+"-"+produto.getNomeUnidadeMedida());
        }
    }}