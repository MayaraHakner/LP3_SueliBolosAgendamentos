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
import javax.swing.JCheckBox;
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
public class SaborGUI extends JFrame {

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
    private JLabel labelNome = new JLabel("Nome");
    private JLabel labelStatus = new JLabel("Status");
    private JTextField textFieldId = new JTextField(15);
    private JTextField textFieldNome = new JTextField(15);
    JCheckBox checkBoxStatus = new JCheckBox("Indisponível");
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
            boolean idSabor, boolean nomeSabor, boolean statusSabor
    ) {
        if (idSabor) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEditable(idSabor);
        textFieldNome.setEditable(nomeSabor);
        checkBoxStatus.setEnabled(statusSabor);
    }

    public void zerarAtributos() {
        textFieldNome.setText("");
        checkBoxStatus.setSelected(false);
    }
    Color corPadrao = labelId.getBackground();
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnCentro = new JPanel(new GridLayout(2, 2));
    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private ScrollPane scroll = new ScrollPane();
    private String qualAcao = "";
    CaixaDeFerramentas ferramenta = new CaixaDeFerramentas();
    FerramentasParaData ferramentasParaData = new FerramentasParaData();
    private Sabor sabor = new Sabor();
    private DAOSabor daoSabor = new DAOSabor();

    public SaborGUI(Point posicao, Dimension dimensao) {

        Sabor n;
        setSize(600, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Sabor - GUI");
        atvBotoes(true, true, false, false);
        habilitarAtributos(true, false, false);

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

        textFieldId.setEditable(false);
        textFieldId.setText("");

//--------------- listeners ----------------- 
        textFieldId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
                if (textFieldId.getText().equals("")) {
                    List<String> listaAuxiliar = daoSabor.listInOrderNomeStrings("id");
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
                }
            }
        });
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sabor = new Sabor();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços

                if (textFieldId.getText().equals("")) {
                    List<String> listaAuxiliar = daoSabor.listInOrderNomeStrings("id");
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

                        sabor.setIdSabor(Integer.valueOf(textFieldId.getText()));
                        sabor = daoSabor.obter(sabor.getIdSabor());
                        if (sabor != null) { //se encontrou na lista

                            textFieldId.setText(String.valueOf(sabor.getIdSabor()));
                            textFieldNome.setText(String.valueOf(sabor.getNomeSabor()));
                            checkBoxStatus.setSelected(sabor.getStatus());
                            atvBotoes(true, true, true, true);

                            habilitarAtributos(true, false, false);
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
                textFieldId.setEditable(false);
                textFieldId.setText("");

            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                textFieldId.setText("");
                textFieldId.setEditable(false);
                habilitarAtributos(false, true, true);
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
                    sabor = new Sabor();
                }
                try {
                    // sabor.setIdSabor(Integer.valueOf((textFieldId.getText())));
                } catch (Exception erro2) {
                    // deuRuim = true;
                    //textFieldId.setBackground(Color.red);
                }
                sabor.setNomeSabor(String.valueOf(textFieldNome.getText()));

                try {
                    sabor.setNomeSabor(String.valueOf((textFieldNome.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldId.setBackground(Color.red);
                }
                try {
                    sabor.setStatus(Boolean.valueOf((checkBoxStatus.isSelected())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldId.setBackground(Color.red);
                }
                if (!deuRuim) {
                    if (qualAcao.equals("insert")) {
                        try {
                            sabor.setIdSabor(daoSabor.autoIdSabor());
                        } catch (Exception erro2) {
                            // deuRuim = true;
                            System.out.println("aqui");
                            textFieldId.setBackground(Color.red);
                        }
                        daoSabor.inserir(sabor);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoSabor.atualizar(sabor);
                        labelAviso.setText("Registro alterado.");
                    }

                    habilitarAtributos(true, false, false);
                    mostrarBotoes(true);
                    atvBotoes(true, true, false, false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                }
                textFieldId.setEditable(false);
                textFieldId.setText("");
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                qualAcao = "update";
                mostrarBotoes(false);
                textFieldId.setEditable(false);
                textFieldId.setText("");
                habilitarAtributos(false, true, true);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                mostrarBotoes(true);
                textFieldId.setEditable(false);
                textFieldId.setText("");

                habilitarAtributos(true, false, false);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + sabor.getNomeSabor() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoSabor.remover(sabor);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(true, true, false, false);
                    textFieldNome.requestFocus();
                    textFieldNome.selectAll();
                    textFieldId.setEditable(false);
                    textFieldId.setText("");

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

        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                qualAcao = "list";
                GUISaborListagem guiSaborListagem = new GUISaborListagem(daoSabor.listInOrderId(), getBounds().x, getBounds().y, dimensao);
            }
        });
    }

    public static void main(String[] args) {
        new SaborGUI(new Point(880, 250), new Dimension(800, 600));
    }
}
