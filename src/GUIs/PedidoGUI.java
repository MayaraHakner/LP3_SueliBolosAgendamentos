package GUIs;

import DAOs.*;
import Entidades.*;
import Ferramentas.FerramentasParaData;
import myUtil.JanelaPesquisar;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Date;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import Main.CaixaDeFerramentas;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import javax.swing.JCheckBox;

/**
 *
 * @author Mayara
 */
public class PedidoGUI extends JFrame {

    private Container cp;
    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);
    private JLabel labelId = new JLabel("Id");
    private JLabel labelData = new JLabel("Data");
    private JLabel labelHorario = new JLabel("Horário");
    private JLabel labelEntregavembuscar = new JLabel("Vem Buscar");
    private JLabel labelEnderecoentrega = new JLabel("Endereço de Entrega");
    private JLabel labelDesconto = new JLabel("Desconto");
    private JLabel labelObservacao = new JLabel("Observação");
    private JLabel labelCodigocliente = new JLabel("Código do Cliente");
    private JLabel labelIdfuncionario = new JLabel("Código do Funcionário");
    private JTextField textFieldId = new JTextField(15);
    private JTextField textFieldHorario = new JTextField(15);
    JCheckBox checkBoxEntregavembuscar = new JCheckBox(" ");
    private JTextField textFieldEnderecoentrega = new JTextField(15);
    private JTextField textFieldDesconto = new JTextField(15);
    private JTextField textFieldObservacao = new JTextField(15);
    private JTextField textFieldCodigocliente = new JTextField(15);
    private JTextField textFieldIdfuncionario = new JTextField(15);
    JDateTextField textFieldData = new JDateTextField();
    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    private void atvBotoes(boolean c, boolean r, boolean u, boolean d) {
        btnCreate.setEnabled(c);
        btnRetrieve.setEnabled(r);
        btnUpdate.setEnabled(u);
        btnDelete.setEnabled(d);
        btnList.setEnabled(r);
    }

    public void mostrarBotoes(boolean visivel) {
        btnCreate.setVisible(visivel);
        btnRetrieve.setVisible(visivel);
        btnUpdate.setVisible(visivel);
        btnDelete.setVisible(visivel);
        btnList.setVisible(visivel);
        btnSave.setVisible(!visivel);
        btnCancel.setVisible(!visivel);
    }

    private void habilitarAtributos(
            boolean idPedido, boolean dataPedido, boolean horarioPedido, boolean entregaVemBuscarPedido, boolean enderecoEntregaPedido, boolean descontoPedido, boolean observacaoPedido, boolean codigoClientePedido, boolean idFuncionarioPedido
    ) {
        if (idPedido) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEditable(idPedido);
        textFieldData.setEditable(dataPedido);
        textFieldHorario.setEditable(horarioPedido);
        checkBoxEntregavembuscar.setEnabled(entregaVemBuscarPedido);
        textFieldEnderecoentrega.setEditable(enderecoEntregaPedido);
        textFieldDesconto.setEditable(descontoPedido);
        textFieldObservacao.setEditable(observacaoPedido);
        textFieldCodigocliente.setEditable(codigoClientePedido);
        textFieldIdfuncionario.setEditable(idFuncionarioPedido);
    }

    public void zerarAtributos() {
        textFieldData.setText("");
        textFieldHorario.setText("");
        checkBoxEntregavembuscar.setSelected(false);
        textFieldEnderecoentrega.setText("");
        textFieldDesconto.setText("");
        textFieldObservacao.setText("");
        textFieldCodigocliente.setText("");
        textFieldIdfuncionario.setText("");
    }
    Color corPadrao = labelId.getBackground();
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel(new GridLayout(8, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private ScrollPane scroll = new ScrollPane();
    private String qualAcao = "";
    CaixaDeFerramentas ferramenta = new CaixaDeFerramentas();
    FerramentasParaData ferramentasParaData = new FerramentasParaData();
    Date dataData;
    SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    private Pedido pedido = new Pedido();
    private DAOPedido daoPedido = new DAOPedido();
    DAOCliente daoCliente = new DAOCliente();
    DAOFuncionario daoFuncionario = new DAOFuncionario();
    Cliente cliente = new Cliente();
    Funcionario funcionario = new Funcionario();

    public PedidoGUI(Point posicao, Dimension dimensao) {
        Pedido n;
        setSize(1000, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Pedido - GUI");
        atvBotoes(true, true, false, false);
        habilitarAtributos(true, false, false, false, false, false, false, false, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        pnCentro.add(labelData);
        pnCentro.add(textFieldData);
        pnCentro.add(labelHorario);
        pnCentro.add(textFieldHorario);
        pnCentro.add(labelEntregavembuscar);
        pnCentro.add(checkBoxEntregavembuscar);
        pnCentro.add(labelEnderecoentrega);
        pnCentro.add(textFieldEnderecoentrega);
        pnCentro.add(labelDesconto);
        pnCentro.add(textFieldDesconto);
        pnCentro.add(labelObservacao);
        pnCentro.add(textFieldObservacao);
        pnCentro.add(labelCodigocliente);
        pnCentro.add(textFieldCodigocliente);
        pnCentro.add(labelIdfuncionario);
        pnCentro.add(textFieldIdfuncionario);
        JToolBar Toolabelar1 = new JToolBar();
        Toolabelar1.add(labelId);
        Toolabelar1.add(textFieldId);
        Toolabelar1.add(btnRetrieve);
        Toolabelar1.add(btnCreate);
        Toolabelar1.add(btnUpdate);
        Toolabelar1.add(btnDelete);
        Toolabelar1.add(btnSave);
        Toolabelar1.add(btnCancel);
        Toolabelar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolabelar1, BorderLayout.NORTH);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        labelAviso.setText("Digite um IdProduto e clic [Pesquisar]");
        textFieldId.setEditable(true);
        setLocationRelativeTo(null);
        setVisible(true);

        textFieldId.setText("");
        textFieldId.setEditable(false);
//--------------- listeners ----------------- 
        textFieldId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                pedido = new Pedido();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços

                if (textFieldId.getText().equals("")) {
                    List<String> listaAuxiliar = daoPedido.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldId.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldId.requestFocus();
                            textFieldId.selectAll();
                        }
                    }

                    textFieldId.requestFocus();
                    textFieldId.selectAll();
                } else {
                    try {

                        pedido.setIdPedido(Integer.valueOf(textFieldId.getText()));
                        pedido = daoPedido.obter(pedido.getIdPedido());
                        if (pedido != null) { //se encontrou na lista

                            textFieldId.setText(String.valueOf(pedido.getIdPedido()));
                            textFieldData.setText(ferramentasParaData.converteDeDateParaString(pedido.getDataPedido()));
                            textFieldHorario.setText(String.valueOf(pedido.getHorarioEntrega()));
                            checkBoxEntregavembuscar.setSelected((pedido.getEntergaVemBuscar()));
                            textFieldEnderecoentrega.setText(String.valueOf(pedido.getEnderecoEntrega()));
                            textFieldDesconto.setText(decimalFormat.format(pedido.getDescontoPedido()));
                            textFieldObservacao.setText(String.valueOf(pedido.getObservacaoPedido()));
                            textFieldCodigocliente.setText(String.valueOf(pedido.getClienteCodigoCliente().getCodigoCliente()+ "-" + pedido.getClienteCodigoCliente().getNomeCliente()));
                            textFieldIdfuncionario.setText(String.valueOf(pedido.getFuncionarioIdFuncionario().getIdFuncionario()+ "-" + pedido.getFuncionarioIdFuncionario().getNomeFuncionario()));
                            //textFieldIdfuncionario.setText(String.valueOf(pedido.getFuncionarioIdFuncionario()));
                            atvBotoes(true, true, true, true);

                            habilitarAtributos(true, false, false, false, false, false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            qualAcao = "encontrou";
                        } else {
                            atvBotoes(true, true, true, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldId.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldId.setOpaque(true);
                        textFieldId.selectAll();
                        textFieldId.requestFocus();
                        textFieldId.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
                textFieldId.setText("");
                textFieldId.setEditable(false);
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                textFieldId.setText("");
                textFieldId.setEditable(false);
                habilitarAtributos(false, true, true, true, true, true, true, true, true);
                textFieldData.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                qualAcao = "insert";
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (qualAcao.equals("insert")) {
                    pedido = new Pedido();
                }
                try {
                    pedido.setIdPedido(daoPedido.autoIdPedido());
                    System.out.println("foi");
                } catch (Exception erro2) {
                    
                    textFieldId.setBackground(Color.red);
                    System.out.println("não foi");
                }

                try {
                    pedido.setDataPedido(sdfData.parse((textFieldData.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    System.out.println("1");
                    textFieldId.setBackground(Color.red);
                }
                try {
                    pedido.setHorarioEntrega(String.valueOf((textFieldHorario.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    System.out.println("2");
                    textFieldId.setBackground(Color.red);
                }
                try {
                    pedido.setEntergaVemBuscar((checkBoxEntregavembuscar.isSelected()));
                } catch (Exception erro2) {
                    deuRuim = true;
                    System.out.println("3");
                }
                try {
                    pedido.setEnderecoEntrega(String.valueOf((textFieldEnderecoentrega.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldId.setBackground(Color.red);
                    System.out.println("4");
                }
                try {
                    pedido.setDescontoPedido(Double.valueOf(decimalFormat.format(textFieldDesconto.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    System.out.println("5");
                    textFieldId.setBackground(Color.red);
                }
                try {
                    pedido.setObservacaoPedido(String.valueOf((textFieldObservacao.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    System.out.println("6");
                    textFieldId.setBackground(Color.red);
                }
                try {
                    //pedido.setClienteCodigoCliente(daoCliente.obter((textFieldCodigocliente.getText())));
                    pedido.setClienteCodigoCliente(daoCliente.obter(String.valueOf(textFieldCodigocliente.getText().split("-")[0])));
                } catch (Exception erro2) {
                    deuRuim = true;
                    System.out.println("7");
                    textFieldId.setBackground(Color.red);
                }
                try {
                    pedido.setFuncionarioIdFuncionario(daoFuncionario.obter(Integer.valueOf(textFieldIdfuncionario.getText().split("-")[0])));
                } catch (Exception erro2) {
                    System.out.println("8");
                    deuRuim = true;
                    textFieldId.setBackground(Color.red);
                }
                if (!deuRuim) {
                    if (qualAcao.equals("insert")) {
                        daoPedido.inserir(pedido);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoPedido.atualizar(pedido);
                        labelAviso.setText("Registro alterado.");
                    }

                    habilitarAtributos(true, false, false, false, false, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                }
                textFieldId.setText("");
                textFieldId.setEditable(false);

            }

        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                qualAcao = "update";
                mostrarBotoes(false);
                textFieldId.setText("");
                textFieldId.setEditable(false);
                habilitarAtributos(false, true, true, true, true, true, true, true, true);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                mostrarBotoes(true);
                textFieldId.setText("");
                textFieldId.setEditable(false);
                habilitarAtributos(true, false, false, false, false, false, false, false, false);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + pedido.getDataPedido() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoPedido.remover(pedido);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldData.requestFocus();
                    textFieldData.selectAll();
                    textFieldId.setText("");
                    textFieldId.setEditable(false);
                }
            }
        });
        // ----------------   Janela Pesquisar para FKs -----------------
        textFieldCodigocliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoCliente.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldCodigocliente.getBounds().y + textFieldCodigocliente.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldCodigocliente.setText(selectedItem);

                        //preparar para salvar
                        cliente = daoCliente.obter(String.valueOf(aux[0]));

                    } else {
                        textFieldCodigocliente.requestFocus();
                        textFieldCodigocliente.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });
        textFieldIdfuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoFuncionario.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldIdfuncionario.getBounds().y + textFieldIdfuncionario.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldIdfuncionario.setText(selectedItem);

                        //preparar para salvar
                        funcionario = daoFuncionario.obter(Integer.valueOf(aux[0]));

                    } else {
                        textFieldIdfuncionario.requestFocus();
                        textFieldIdfuncionario.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum funcionario cadastrado.");
                }
            }
        });
        textFieldId.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldId.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldId.setBackground(corPadrao);
            }
        });
        textFieldData.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldData.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldData.setBackground(corPadrao);
            }
        });
        textFieldHorario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldHorario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldHorario.setBackground(corPadrao);
            }
        });
        textFieldEnderecoentrega.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldEnderecoentrega.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldEnderecoentrega.setBackground(corPadrao);
            }
        });
        textFieldDesconto.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDesconto.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDesconto.setBackground(corPadrao);
            }
        });
        textFieldObservacao.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldObservacao.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldObservacao.setBackground(corPadrao);
            }
        });
        textFieldCodigocliente.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldCodigocliente.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldCodigocliente.setBackground(corPadrao);
            }
        });
        textFieldIdfuncionario.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldIdfuncionario.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldIdfuncionario.setBackground(corPadrao);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textFieldId.setText("");
                textFieldId.setEditable(false);
                qualAcao = "list";
                GUIPedidoListagem guiPedidoListagem = new GUIPedidoListagem(daoPedido.listInOrderId(), getBounds().x, getBounds().y, dimensao);
            }
        });
    }

    public static void main(String[] args) {
        new PedidoGUI(new Point(880, 250), new Dimension(800, 600));
    }
}
