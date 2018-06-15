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
import java.text.ParseException;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Mayara
 */
public class ProdutoGUI extends JFrame {

    private Container cp;
    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    ImageIcon iconeRetrieveSabor = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeRetrieveUnidade = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);
    JButton btnRetrieveSabor = new JButton(iconeRetrieveSabor);
    JButton btnRetrieveUnidade = new JButton(iconeRetrieveUnidade);
    private JLabel labelId = new JLabel("Id");
    private JLabel labelNome = new JLabel("Nome");
    private JLabel labelStatus = new JLabel("Status");
    private JLabel labelUnmedida = new JLabel("Unidade Medida");
    private JLabel labelSabor = new JLabel("Sabor");
    private JTextField textFieldId = new JTextField(15);
    private JTextField textFieldNome = new JTextField(15);
    JCheckBox checkBoxStatus = new JCheckBox("Inativo");
    private JTextField textFieldUnmedida = new JTextField(15);
    private JTextField textFieldSabor = new JTextField(15);
    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
    DAOSabor daoSabor1 = new DAOSabor();
    DAOUnMedida daoUnMedida1 = new DAOUnMedida();

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
            boolean idProduto, boolean nomeProduto, boolean statusProduto, boolean unMedidaProduto, boolean saborProduto
    ) {
        if (idProduto) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEditable(idProduto);
        textFieldNome.setEditable(nomeProduto);
        checkBoxStatus.setEnabled(statusProduto);
        textFieldUnmedida.setEditable(unMedidaProduto);
        textFieldSabor.setEditable(saborProduto);
    }

    public void zerarAtributos() {
        textFieldNome.setText("");
        checkBoxStatus.setSelected(false);
        textFieldUnmedida.setText("");
        textFieldSabor.setText("");
    }
    Color corPadrao = labelId.getBackground();
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel(new GridLayout(4, 2));
    private JPanel pnCentroSabor = new JPanel(new GridLayout(1, 4));
    private JPanel pnCentroUnidade = new JPanel(new GridLayout(1, 4));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private ScrollPane scroll = new ScrollPane();
    private String qualAcao = "";
    CaixaDeFerramentas ferramenta = new CaixaDeFerramentas();
    FerramentasParaData ferramentasParaData = new FerramentasParaData();
    private Produto produto = new Produto();
    private DAOProduto daoProduto = new DAOProduto();
    DAOSabor daoSabor = new DAOSabor();
    DAOUnMedida daoUnMedida = new DAOUnMedida();
    Sabor sabor = new Sabor();
    UnMedida unMedida = new UnMedida();
    MaskFormatter mascara;
    JFormattedTextField mascaraHorario;

    public ProdutoGUI(Point posicao, Dimension dimensao) {
        btnRetrieveSabor.setEnabled(false);
        btnRetrieveUnidade.setEnabled(false);
        Produto n;
        setSize(600, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Produto - GUI");
        atvBotoes(true, true, false, false);
        habilitarAtributos(true, false, false, false, false);
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
        pnCentro.add(labelStatus);
        pnCentro.add(checkBoxStatus);
        pnCentro.add(labelUnmedida);
        pnCentroUnidade.add(textFieldUnmedida);
        pnCentroUnidade.add(btnRetrieveUnidade);
        pnCentro.add(pnCentroUnidade);
        pnCentro.add(labelSabor);
        pnCentroSabor.add(textFieldSabor);
        pnCentroSabor.add(btnRetrieveSabor);
        pnCentro.add(pnCentroSabor);
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
        try {
            mascara = new MaskFormatter("##/##/####");
        } catch (ParseException erro) {

        }
        mascaraHorario = new JFormattedTextField(mascara);
        textFieldId.setEditable(false);
//--------------- listeners ----------------- 
        textFieldId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });
        btnRetrieveSabor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoSabor.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldSabor.getBounds().y + textFieldSabor.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldSabor.setText(selectedItem);

                        //preparar para salvar
                        sabor = daoSabor.obter(Integer.valueOf(aux[0]));

                    } else {
                        textFieldSabor.requestFocus();
                        textFieldSabor.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum sabor cadastrado.");
                }
            }
        });
        btnRetrieveUnidade.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoUnMedida.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldUnmedida.getBounds().y + textFieldSabor.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldUnmedida.setText(selectedItem);

                        //preparar para salvar
                        unMedida = daoUnMedida.obter(String.valueOf(aux[0]));

                    } else {
                        textFieldSabor.requestFocus();
                        textFieldSabor.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum sabor cadastrado.");
                }
            }
        });

        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                produto = new Produto();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços

                if (textFieldId.getText().equals("")) {
                    List<String> listaAuxiliar = daoProduto.listInOrderNomeStrings("nome");
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

                        produto.setIdProduto(Integer.valueOf(textFieldId.getText()));
                        produto = daoProduto.obter(produto.getIdProduto());
                        if (produto != null) { //se encontrou na lista

                            textFieldId.setText(String.valueOf(produto.getIdProduto()));
                            textFieldNome.setText(String.valueOf(produto.getNomeProduto()));
                            checkBoxStatus.setSelected((produto.getStatus()));
                            textFieldUnmedida.setText(String.valueOf(produto.getUnMedidaIdUnMedida().getIdUnMedida()));
                            textFieldSabor.setText(String.valueOf(produto.getSaborIdSabor().getIdSabor()));
                            atvBotoes(true, true, true, true);

                            habilitarAtributos(true, false, false, false, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            qualAcao = "encontrou";
                        } else {
                            atvBotoes(true, true, false, false);
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
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();

                habilitarAtributos(false, true, true, false, false);
                textFieldNome.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                qualAcao = "insert";
                textFieldId.setText("");
                textFieldId.setEditable(false);
                btnRetrieveSabor.setEnabled(true);
                btnRetrieveUnidade.setEnabled(true);

            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                btnRetrieveSabor.setEnabled(false);
                btnRetrieveUnidade.setEnabled(false);
                boolean deuRuim = false;
                if (qualAcao.equals("insert")) {
                    produto = new Produto();
                }

                produto.setNomeProduto(String.valueOf(textFieldNome.getText()));
                try {
                    produto.setStatus(((checkBoxStatus.isSelected())));
                } catch (Exception erro2) {
                    deuRuim = true;
                }
                try {
                    produto.setSaborIdSabor(daoSabor.obter(Integer.valueOf(textFieldSabor.getText().split("-")[0])));
                } catch (Exception erro2) {
                    deuRuim = true;
                }

                try {
                   produto.setUnMedidaIdUnMedida(daoUnMedida.obter(String.valueOf(textFieldUnmedida.getText().split("-")[0])));
                } catch (Exception erro2) {
                    deuRuim = true;
                }
                
                if (!deuRuim) {
                    if (qualAcao.equals("insert")) {
                        try {
                            produto.setIdProduto(daoProduto.autoIdProduto());
                            System.out.println("aqui");
                        } catch (Exception erro2) {
                            deuRuim = true;
                            textFieldId.setBackground(Color.red);
                        }
                        daoProduto.inserir(produto);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoProduto.atualizar(produto);
                        labelAviso.setText("Registro alterado.");
                    }

                    habilitarAtributos(true, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(true, true, false, false);
                    btnRetrieveSabor.setEnabled(false);
                    btnRetrieveUnidade.setEnabled(false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                    habilitarAtributos(true, true, true, false, false);
                    btnRetrieveSabor.setEnabled(true);
                    btnRetrieveUnidade.setEnabled(true);
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

                habilitarAtributos(false, true, true, false, false);
                btnRetrieveSabor.setEnabled(true);
                btnRetrieveUnidade.setEnabled(true);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(true, true, false, false);
                mostrarBotoes(true);
                textFieldId.setText("");
                textFieldId.setEditable(false);

                habilitarAtributos(true, false, false, false, false);
                btnRetrieveSabor.setEnabled(false);
                btnRetrieveUnidade.setEnabled(false);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnRetrieveSabor.setEnabled(false);
                btnRetrieveUnidade.setEnabled(false);
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + produto.getNomeProduto() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoProduto.remover(produto);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(true, true, false, false);
                    textFieldNome.requestFocus();
                    textFieldNome.selectAll();
                    textFieldId.setText("");
                    textFieldId.setEditable(false);

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
        textFieldUnmedida.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldUnmedida.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldUnmedida.setBackground(corPadrao);
            }
        });
        textFieldSabor.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldSabor.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldSabor.setBackground(corPadrao);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                qualAcao = "list";
                GUIProdutoListagem guiProdutoListagem = new GUIProdutoListagem(daoProduto.listInOrderId(), getBounds().x, getBounds().y, dimensao);
            }
        });
    }

    public static void main(String[] args) {
        new ProdutoGUI(new Point(880, 250), new Dimension(800, 600));
    }
}
