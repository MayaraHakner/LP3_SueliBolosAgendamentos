package GUIs;

import DAOs.DAOCliente;
import DAOs.DAOFuncionario;
import DAOs.DAOPedido;
import DAOs.DAOPrecoProduto;
import DAOs.DAOPrecoProdutoPK;
import DAOs.DAOProduto;
import DAOs.DAOSabor;
import DAOs.DAOStatus;
import DAOs.DAOUnMedida;
import Main.CaixaDeFerramentas;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class GUIMenu extends JFrame {

    Container cp;
//    JButton btMais = new JButton("Mais");

    JMenuBar menuGeral1 = new JMenuBar();
    JMenuBar menuGeral2 = new JMenuBar();
    JMenuBar menuGeral3 = new JMenuBar();
    JMenuBar menuGeral4 = new JMenuBar();

    JMenu menuCadastro = new JMenu("Cadastro");
    JMenu menuListagem = new JMenu("Listagem");
    //JMenu menuBuscar = new JMenu("Buscar");

    JMenuItem itemPedido = new JMenuItem("Pedido");
    JMenuItem itemProduto = new JMenuItem("Produto");
    JMenuItem itemCliente = new JMenuItem("Cliente");
    JMenuItem itemFuncionario = new JMenuItem("Funcionário");
    JMenuItem itemSabor = new JMenuItem("Sabor");
    JMenuItem itemUnMedida = new JMenuItem("Unidade de Medida");
    JMenuItem itemPrecoProduto = new JMenuItem("Preço do Produto");
    JMenuItem itemStatus = new JMenuItem("Status");

    JMenuItem listaPedido = new JMenuItem("Pedido");
    JMenuItem listaProduto = new JMenuItem("Produto");
    JMenuItem listaCliente = new JMenuItem("Cliente");
    JMenuItem listaFuncionario = new JMenuItem("Funcionário");
    JMenuItem listaSabor = new JMenuItem("Sabor");
    JMenuItem listaUnMedida = new JMenuItem("Unidade de Medida");
    JMenuItem listaPrecoProduto = new JMenuItem("Preço do Produto");
    JMenuItem listaStatus = new JMenuItem("Status");

    JMenuItem buscarPedido = new JMenuItem("Pedido");
    JMenuItem buscarProduto = new JMenuItem("Produto");
    JMenuItem buscarCliente = new JMenuItem("Cliente");
    JMenuItem buscarFuncionario = new JMenuItem("Funcionário");
    JMenuItem buscarSabor = new JMenuItem("Sabor");
    JMenuItem buscarUnMedida = new JMenuItem("Unidade de Medida");
    JMenuItem buscarPrecoProduto = new JMenuItem("Preço do Produto");
    JMenuItem buscarStatus = new JMenuItem("Status");

    JLabel lbImagem = new JLabel();
    JLabel lbImagem1 = new JLabel();

    JLabel lbTitulo = new JLabel("Sueli Bolos - Agendamentos");

    ImageIcon imagemChocolate = new ImageIcon("naked.jpg");
    Image oficial = imagemChocolate.getImage();
    Image oficialMenor = oficial.getScaledInstance(590, 400, java.awt.Image.SCALE_SMOOTH);

    JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel pnCentro = new JPanel();
    JPanel pnSul = new JPanel();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    String dt = ("17/05/2000");
    Date data;
    DAOPedido daoPedido = new DAOPedido();
    DAOCliente daoCliente = new DAOCliente();
    DAOProduto daoProduto = new DAOProduto();
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    DAOSabor daoSabor = new DAOSabor();
    DAOStatus daoStatus = new DAOStatus();
    DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
    DAOUnMedida daoUnMedida = new DAOUnMedida();

    CaixaDeFerramentas ferramenta = new CaixaDeFerramentas();

    //  btMais.drawImage(new ImageIcon("mais1.jpg").getImage(),0,0,this);
    public GUIMenu() {
        setTitle("Menu de Opções");
        setSize(600, 550);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);
        //  btMais.setBorderPainted(false);
        //btMais.setContentAreaFilled(false);
        menuGeral1.add(menuCadastro);
        menuCadastro.add(itemPedido);
        menuCadastro.add(itemCliente);
        menuCadastro.add(itemProduto);
        menuCadastro.add(itemFuncionario);
        menuCadastro.add(itemSabor);
        menuCadastro.add(itemStatus);
        menuCadastro.add(itemUnMedida);
        menuCadastro.add(itemPrecoProduto);

        menuGeral2.add(menuListagem);
        menuListagem.add(listaPedido);
        menuListagem.add(listaCliente);
        menuListagem.add(listaProduto);
        menuListagem.add(listaFuncionario);
        menuListagem.add(listaSabor);
        menuListagem.add(listaStatus);
        menuListagem.add(listaUnMedida);
        menuListagem.add(listaPrecoProduto);

        /*        menuGeral3.add(menuBuscar);
        menuBuscar.add(buscarPedido);
        menuBuscar.add(buscarCliente);
        menuBuscar.add(buscarProduto);
        menuBuscar.add(buscarFuncionario);
        menuBuscar.add(buscarSabor);
        menuBuscar.add(buscarUnMedida);
        menuBuscar.add(buscarPrecoProduto);*/
        pnNorte.setBackground(Color.PINK);
        pnNorte.add(menuGeral1);
        pnNorte.add(menuGeral2);
        pnNorte.add(menuGeral3);
        pnNorte.add(menuGeral4);
        //       pnNorte.add(btMais);

        lbTitulo.setFont(new Font("Edwardian Script ITC", Font.BOLD, 55));
        pnCentro.add(lbTitulo);
        pnCentro.setBackground(Color.PINK);

        lbImagem.setIcon(new ImageIcon(oficialMenor));
        pnSul.add(lbImagem);

        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

        itemCliente.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new ClienteGUI(new Point(880, 250), new Dimension(600, 400));
            }
        });
        itemFuncionario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new FuncionarioGUI1(new Point(880, 250), new Dimension(800, 600));
            }
        });
        itemSabor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new SaborGUI(new Point(880, 250), new Dimension(800, 600));
            }
        });
        itemProduto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new ProdutoGUI(new Point(880, 250), new Dimension(800, 600));
            }
        });
        itemStatus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                StatusGUI_JTable statusGUI_JTable = new StatusGUI_JTable();
            }
        });
        itemUnMedida.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new UnidadeMedidaGUI(new Point(880, 250), new Dimension(800, 600));
            }
        });
        itemPedido.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new PedidoGUI(new Point(880, 250), new Dimension(800, 600));
            }
        });
        itemPrecoProduto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new GUIPrecoProdutoPK(new Point(880, 250), new Dimension(800, 600));
            }
        });

        listaPedido.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIPedidoListagem guiPedidoListagem = new GUIPedidoListagem(daoPedido.listInOrderId(), getBounds().x, getBounds().y, new Dimension(800, 600));

            }
        });
        listaCliente.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIClienteListagem guiClienteListagem = new GUIClienteListagem(daoCliente.listInOrderId(), getBounds().x, getBounds().y, new Dimension(800, 600));

            }
        });
        listaProduto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIProdutoListagem guiProdutoListagem = new GUIProdutoListagem(daoProduto.listInOrderId(), getBounds().x, getBounds().y, new Dimension(800, 600));
            }
        });
        listaFuncionario.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIFuncionarioListagem guiFuncionarioListagem = new GUIFuncionarioListagem(daoFuncionario.listInOrderId(), getBounds().x, getBounds().y, new Dimension(800, 600));
            }
        });
        listaSabor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUISaborListagem guiSaborListagem = new GUISaborListagem(daoSabor.listInOrderId(), getBounds().x, getBounds().y, new Dimension(800, 600));
            }
        });
        listaStatus.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIStatusListagem guiStatusListagem = new GUIStatusListagem(daoStatus.listInOrderId(), getBounds().x, getBounds().y, new Dimension(800, 600));
            }
        });
        listaUnMedida.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIUnMedidaListagem guiUnMedidaListagem = new GUIUnMedidaListagem(daoUnMedida.listInOrderId(), getBounds().x, getBounds().y, new Dimension(800, 600));
            }
        });
        listaPrecoProduto.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GUIPrecoProdutoPKListagem guiPrecoProdutoPKListagem = new GUIPrecoProdutoPKListagem(daoPrecoProduto.listInOrderId(), getBounds().x, getBounds().y, new Dimension(800, 600));
            }
        });
    }

}
