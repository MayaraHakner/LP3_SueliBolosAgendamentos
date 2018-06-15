package GUIs;

import DAOs.*;
import Entidades.*;
import Ferramentas.FerramentasParaData;
import Main.CaixaDeFerramentas;
import myUtil.JanelaPesquisar;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.text.SimpleDateFormat;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;

/**
 *
 * @author Mayara
 */
public class ClienteGUI extends JFrame {

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
    private JLabel labelCodigo = new JLabel("Codigo");
    private JLabel labelNome = new JLabel("Nome");
    private JLabel labelEndereco = new JLabel("Endereco");
    private JLabel labelObservacao = new JLabel("Observacao");
    private JTextField textFieldCodigo = new JTextField(15);
    private JTextField textFieldNome = new JTextField(15);
    private JTextField textFieldEndereco = new JTextField(15);
    private JTextField textFieldObservacao = new JTextField(15);
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
            boolean codigoCliente, boolean nomeCliente, boolean enderecoCliente, boolean observacaoCliente
    ) {
        if (codigoCliente) {
            textFieldCodigo.requestFocus();
            textFieldCodigo.selectAll();
        }
        textFieldCodigo.setEditable(codigoCliente);
        textFieldNome.setEditable(nomeCliente);
        textFieldEndereco.setEditable(enderecoCliente);
        textFieldObservacao.setEditable(observacaoCliente);
    }

    public void zerarAtributos() {
        textFieldNome.setText("");
        textFieldEndereco.setText("");
        textFieldObservacao.setText("");
    }
    Color corPadrao = labelCodigo.getBackground();
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel(new GridLayout(3, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private ScrollPane scroll = new ScrollPane();
    private String qualAcao = "";
    CaixaDeFerramentas ferramenta = new CaixaDeFerramentas();
    FerramentasParaData ferramentasParaData = new FerramentasParaData();
    private Cliente cliente = new Cliente();
    private DAOCliente daoCliente = new DAOCliente();

    public ClienteGUI(Point posicao, Dimension dimensao) {
        Cliente n;
        setSize(600, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Cliente - GUI");
        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false, false, false);
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
        pnCentro.add(labelNome);
        pnCentro.add(textFieldNome);
        pnCentro.add(labelEndereco);
        pnCentro.add(textFieldEndereco);
        pnCentro.add(labelObservacao);
        pnCentro.add(textFieldObservacao);
        JToolBar Toolabelar1 = new JToolBar();
        Toolabelar1.add(labelCodigo);
        Toolabelar1.add(textFieldCodigo);
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
        textFieldCodigo.setEditable(true);
        setLocationRelativeTo(null);
        setVisible(true);
//--------------- listeners ----------------- 
        textFieldCodigo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                cliente = new Cliente();
                textFieldCodigo.setText(textFieldCodigo.getText().trim());//caso tenham sido digitados espaços

                if (textFieldCodigo.getText().equals("")) {
                    List<String> listaAuxiliar = daoCliente.listInOrderNomeStrings("nome");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldCodigo.setText(aux[0]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldCodigo.requestFocus();
                            textFieldCodigo.selectAll();
                        }
                    }

                    textFieldCodigo.requestFocus();
                    textFieldCodigo.selectAll();
                } else {
                    try {

                        cliente.setCodigoCliente(String.valueOf(textFieldCodigo.getText()));
                        cliente = daoCliente.obter(cliente.getCodigoCliente());
                        if (cliente != null) { //se encontrou na lista

                            textFieldCodigo.setText(String.valueOf(cliente.getCodigoCliente()));
                            textFieldNome.setText(String.valueOf(cliente.getNomeCliente()));
                            textFieldEndereco.setText(String.valueOf(cliente.getEnderecoCliente()));
                            textFieldObservacao.setText(String.valueOf(cliente.getObservacaoCliente()));
                            atvBotoes(false, true, true, true);

                            habilitarAtributos(false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            qualAcao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                        }
                        textFieldCodigo.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldCodigo.setOpaque(true);
                        textFieldCodigo.selectAll();
                        textFieldCodigo.requestFocus();
                        textFieldCodigo.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();

                habilitarAtributos(false, true, true, true);
                textFieldNome.requestFocus();
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
                    cliente = new Cliente();
                }
                try {
                    cliente.setCodigoCliente(String.valueOf((textFieldCodigo.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldCodigo.setBackground(Color.red);
                }
                cliente.setNomeCliente(String.valueOf(textFieldNome.getText()));

                try {
                    cliente.setCodigoCliente(String.valueOf((textFieldCodigo.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldCodigo.setBackground(Color.red);
                }
                try {
                    cliente.setNomeCliente(String.valueOf((textFieldNome.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldCodigo.setBackground(Color.red);
                }
                try {
                    cliente.setEnderecoCliente(String.valueOf((textFieldEndereco.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldCodigo.setBackground(Color.red);
                }
                try {
                    cliente.setObservacaoCliente(String.valueOf((textFieldObservacao.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldCodigo.setBackground(Color.red);
                }
                if (!deuRuim) {
                    if (qualAcao.equals("insert")) {
                        daoCliente.inserir(cliente);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoCliente.atualizar(cliente);
                        labelAviso.setText("Registro alterado.");
                    }

                    habilitarAtributos(true, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                qualAcao = "update";
                mostrarBotoes(false);

                habilitarAtributos(false, true, true, true);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                mostrarBotoes(true);

                habilitarAtributos(true, false, false, false);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + cliente.getNomeCliente() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoCliente.remover(cliente);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldNome.requestFocus();
                    textFieldNome.selectAll();
                }
            }
        });
        textFieldCodigo.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldCodigo.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldCodigo.setBackground(corPadrao);
            }
        });
        textFieldNome.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldNome.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldNome.setBackground(corPadrao);
            }
        });
        textFieldEndereco.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldEndereco.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldEndereco.setBackground(corPadrao);
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
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                qualAcao = "list";
                GUIClienteListagem guiClienteListagem = new GUIClienteListagem(daoCliente.listInOrderId(), getBounds().x, getBounds().y, dimensao);
            }
        });
    }

    public static void main(String[] args) {
        new ClienteGUI(new Point(880, 250), new Dimension(800, 600));
    }
}
