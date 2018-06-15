package GUIs;

import DAOs.*;
import Entidades.*;
import Ferramentas.DateChooser;
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
import java.awt.Image;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import myUtil.CopiaImagem;

/**
 *
 * @author Mayara
 */
public class FuncionarioGUI1 extends JFrame {

    private Container cp;
    ImageIcon iconeCreate = new ImageIcon(getClass().getResource("/icones/create.png"));
    ImageIcon iconeRetrieve = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeRetrieveFK = new ImageIcon(getClass().getResource("/icones/retrieve.png"));
    ImageIcon iconeUpdate = new ImageIcon(getClass().getResource("/icones/update.png"));
    ImageIcon iconeDelete = new ImageIcon(getClass().getResource("/icones/delete.png"));
    ImageIcon iconeSave = new ImageIcon(getClass().getResource("/icones/save.png"));
    ImageIcon iconeCancel = new ImageIcon(getClass().getResource("/icones/cancel.png"));
    ImageIcon iconeListar = new ImageIcon(getClass().getResource("/icones/list.png"));
    JButton btnCreate = new JButton(iconeCreate);
    JButton btnRetrieve = new JButton(iconeRetrieve);
    JButton btnRetrieveFK = new JButton(iconeRetrieveFK);
    JButton btnUpdate = new JButton(iconeUpdate);
    JButton btnDelete = new JButton(iconeDelete);
    JButton btnSave = new JButton(iconeSave);
    JButton btnCancel = new JButton(iconeCancel);
    JButton btnList = new JButton(iconeListar);
    private JLabel labelId = new JLabel("Id");
    private JLabel labelNome = new JLabel("Nome");
    private JLabel labelDatanasc = new JLabel("Datanasc");
    private JLabel labelTelefone = new JLabel("Telefone");
    private JLabel labelEndereco = new JLabel("Endereco");
    private JLabel labelStatus = new JLabel("Status");
    private JLabel labelFoto = new JLabel();
    private JTextField textFieldId = new JTextField(15);
    private JTextField textFieldNome = new JTextField(15);
    private JTextField textFieldTelefone = new JTextField(15);
    private JTextField textFieldEndereco = new JTextField(15);
    private JTextField textFieldStatus = new JTextField(40);
    boolean m = false;
    JDateTextField textFieldDatanasc = new JDateTextField();
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
            boolean idFuncionario, boolean nomeFuncionario, boolean dataNascFuncionario, boolean telefoneFuncionario, boolean enderecoFuncionario, boolean statusFuncionario, boolean fotoFuncionario
    ) {
        if (idFuncionario) {
            textFieldId.requestFocus();
            textFieldId.selectAll();
        }
        textFieldId.setEditable(idFuncionario);
        textFieldNome.setEditable(nomeFuncionario);
        textFieldDatanasc.setEditable(dataNascFuncionario);
        textFieldTelefone.setEditable(telefoneFuncionario);
        textFieldEndereco.setEditable(enderecoFuncionario);
        textFieldStatus.setEditable(statusFuncionario);
    }

    public void zerarAtributos() {
        textFieldNome.setText("");
        textFieldDatanasc.setText("");
        textFieldTelefone.setText("");
        textFieldEndereco.setText("");
        textFieldStatus.setText("");
        try {
            //carrega uma imagem e ajusta seu tamanho
            String caminho = "/imagens/silhueta.jpg";
            Image imagemAux;
            ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(200, 200, Image.SCALE_FAST));

            labelFoto.setIcon(icone);
        } catch (Exception err) {
            System.out.println("erro " + err.getLocalizedMessage());

        }
    }

    Color corPadrao = labelId.getBackground();
    private JPanel pnNorte = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JPanel pnImagem = new JPanel(new GridLayout(1, 1));
    private JPanel pnCentro = new JPanel(new GridLayout(1, 2));
    private JPanel pnCentro1 = new JPanel(new GridLayout(5, 2));
    private JPanel pnCentroFK = new JPanel(new GridLayout(1, 4));

    private JPanel pnSul = new JPanel(new GridLayout(1, 1));
    private ScrollPane scroll = new ScrollPane();
    private String qualAcao = "";
    CaixaDeFerramentas ferramenta = new CaixaDeFerramentas();
    FerramentasParaData ferramentasParaData = new FerramentasParaData();
    Date dataDatanasc;

    SimpleDateFormat sdfDatanasc = new SimpleDateFormat("dd/MM/yyyy");
    private Funcionario funcionario = new Funcionario();
    private DAOFuncionario daoFuncionario = new DAOFuncionario();
    private DAOStatus daoStatus = new DAOStatus();
    Status status = new Status();

    public FuncionarioGUI1(Point posicao, Dimension dimensao) {
        try {
            //carrega uma imagem e ajusta seu tamanho
            String caminho = "/imagens/silhueta.jpg";
            Image imagemAux;
            ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
            imagemAux = icone.getImage();
            icone.setImage(imagemAux.getScaledInstance(200, 200, Image.SCALE_FAST));

            labelFoto.setIcon(icone);
        } catch (Exception err) {
            System.out.println("erro " + err.getLocalizedMessage());

        }
        btnRetrieveFK.setEnabled(false);
        Funcionario n;
        setSize(750, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CRUD - Funcionario - GUI");
        atvBotoes(true, true, false, false);
        habilitarAtributos(true, false, false, false, false, false, false);
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
        pnCentro.add(pnCentro1);
        pnCentro.add(pnImagem);
        pnCentro1.add(labelNome);
        pnCentro1.add(textFieldNome);
        pnCentro1.add(labelDatanasc);
        pnCentro1.add(textFieldDatanasc);
        pnCentro1.add(labelTelefone);
        pnCentro1.add(textFieldTelefone);
        pnCentro1.add(labelEndereco);
        pnCentro1.add(textFieldEndereco);
        /*pnCentro1.add(labelStatus);
        pnCentro1.add(textFieldStatus);
        pnCentro1.add(btnRetrieveFK);*/
        pnImagem.add(labelFoto);

        pnCentro1.add(labelStatus);
        pnCentroFK.add(textFieldStatus);
        pnCentroFK.add(btnRetrieveFK);
        pnCentro1.add(pnCentroFK);
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
//--------------- listeners ----------------- 
        textFieldId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnRetrieve.doClick();
            }
        });
        //String salvar = "";
        btnRetrieveFK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                List<String> listaAuxiliar = daoStatus.listInOrderNomeStrings("id");
                if (listaAuxiliar.size() > 0) {
                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5,
                            textFieldStatus.getBounds().y + textFieldStatus.getHeight()).getValorRetornado();
                    if (!selectedItem.equals("")) {
                        String[] aux = selectedItem.split("-");
                        textFieldStatus.setText(selectedItem);

                        //preparar para salvar
                        status = daoStatus.obter(Integer.valueOf(aux[0]));

                    } else {
                        textFieldStatus.requestFocus();
                        textFieldStatus.selectAll();
                    }
                } else {
                    JOptionPane.showMessageDialog(cp, "Não há nenhum produto cadastrado.");
                }
            }
        });

        labelFoto.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (m == false) {
                } else {
                    JFileChooser fc = new JFileChooser();
                    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

                    if (fc.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                        File img = fc.getSelectedFile();
                        String caminho = fc.getSelectedFile().getAbsolutePath();
                        System.out.println(caminho);
                        funcionario.setFotoFuncionario(caminho);
                        caminho = "";
                        try {
                            ImageIcon icone = new javax.swing.ImageIcon(img.getAbsolutePath());
                            Image imagemAux;
                            imagemAux = icone.getImage();
                            icone.setImage(imagemAux.getScaledInstance(200, 200, Image.SCALE_FAST));
                            labelFoto.setIcon(icone);

                        } catch (Exception ex) {
                        }

                    }

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                funcionario = new Funcionario();
                textFieldId.setText(textFieldId.getText().trim());//caso tenham sido digitados espaços

                if (textFieldId.getText().equals("")) {
                    List<String> listaAuxiliar = daoFuncionario.listInOrderNomeStrings("id");
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

                        funcionario.setIdFuncionario(Integer.valueOf(textFieldId.getText()));
                        funcionario = daoFuncionario.obter(funcionario.getIdFuncionario());
                        if (funcionario != null) { //se encontrou na lista

                            textFieldId.setText(String.valueOf(funcionario.getIdFuncionario()));
                            textFieldNome.setText(String.valueOf(funcionario.getNomeFuncionario()));
                            textFieldDatanasc.setText(ferramentasParaData.converteDeDateParaString(funcionario.getDataNascFuncionario()));
                            textFieldTelefone.setText(String.valueOf(funcionario.getTelefoneFuncionario()));
                            textFieldEndereco.setText(String.valueOf(funcionario.getEndereçoFuncionario()));
                            textFieldStatus.setText(String.valueOf(funcionario.getStatusIdStatus().getIdStatus() + "-" + funcionario.getStatusIdStatus().getNomeStatus()));
                            try {
                                //carrega uma imagem e ajusta seu tamanho
                                // String caminho = "/imagens/" + tfId.getText() + ".jpg";
                                String caminho = "/imagens/" + textFieldId.getText() + ".jpg";
                                Image imagemAux;
                                ImageIcon icone = new ImageIcon(getClass().getResource(caminho));
                                imagemAux = icone.getImage();
                                icone.setImage(imagemAux.getScaledInstance(200, 200, Image.SCALE_FAST));

                                labelFoto.setIcon(icone);
                            } catch (Exception err) {
                                String caminho = "/imagens/silhueta.jpg";

                            }
                            atvBotoes(true, true, true, true);

                            habilitarAtributos(true, false, false, false, false, false, false);
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
                textFieldId.setText("");
                textFieldId.setEditable(false);
            }
        });
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                m = true;
                habilitarAtributos(false, true, true, true, true, false, true);
                textFieldNome.requestFocus();
                textFieldId.setText("");
                textFieldId.setEditable(false);
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                qualAcao = "insert";
                btnRetrieveFK.setEnabled(true);
            }
        });
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                textFieldId.setText("");
                textFieldId.setEditable(false);
                btnRetrieveFK.setEnabled(false);
                boolean deuRuim = false;
                m = false;
                if (qualAcao.equals("insert")) {
                    funcionario = new Funcionario();
                }
                /*try {
                    funcionario.setIdFuncionario(Integer.valueOf((textFieldId.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldId.setBackground(Color.red);
                }*/
                funcionario.setNomeFuncionario(String.valueOf(textFieldNome.getText()));

                try {
                    funcionario.setNomeFuncionario(String.valueOf((textFieldNome.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldId.setBackground(Color.red);
                }
                try {
                    funcionario.setDataNascFuncionario(sdfDatanasc.parse((textFieldDatanasc.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldId.setBackground(Color.red);
                }
                try {
                    funcionario.setTelefoneFuncionario(String.valueOf((textFieldTelefone.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldId.setBackground(Color.red);
                }
                try {
                    funcionario.setEndereçoFuncionario(String.valueOf((textFieldEndereco.getText())));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldId.setBackground(Color.red);
                }
                try {
                    funcionario.setStatusIdStatus(daoStatus.obter(Integer.valueOf(textFieldStatus.getText().split("-")[0])));
                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldId.setBackground(Color.red);
                }

                CopiaImagem copiaImagem = new CopiaImagem();
                try {

                    CopiaImagem.copiar(funcionario.getFotoFuncionario(), "C:\\Users\\Mayara Hakner\\Documents\\NetBeansProjects\\SueliBolosAgendamentosGUIUp\\src\\imagens\\" + textFieldId.getText() + ".jpg");
                } catch (Exception erro) {
                }
                if (!deuRuim) {
                    if (qualAcao.equals("insert")) {
                        try {
                            funcionario.setIdFuncionario(Integer.valueOf(daoFuncionario.autoIdFuncionario()));
                        } catch (Exception erro2) {
                            //deuRuim = true;
                            textFieldId.setBackground(Color.red);
                        }
                        daoFuncionario.inserir(funcionario);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoFuncionario.atualizar(funcionario);
                        labelAviso.setText("Registro alterado.");
                    }

                    habilitarAtributos(true, false, false, false, false, false, false);
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    btnRetrieveFK.setEnabled(false);
                }//!deu ruim
                else {
                    labelAviso.setText("Erro nos dados - corrija");
                    labelAviso.setBackground(Color.red);
                    btnRetrieveFK.setEnabled(true);
                    habilitarAtributos(false, true, true, true, true, false, true);

                }
            }
        });
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                qualAcao = "update";
                mostrarBotoes(false);
                m = true;
                habilitarAtributos(false, true, true, true, true, false, true);
                btnRetrieveFK.setEnabled(true);
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(true, true, false, false);
                mostrarBotoes(true);
                m = false;
                textFieldId.setText("");
                textFieldId.setEditable(false);
                habilitarAtributos(true, false, false, false, false, false, false);
                btnRetrieveFK.setEnabled(false);

            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                m = false;
                btnRetrieveFK.setEnabled(false);
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro <ID = " + funcionario.getNomeFuncionario() + ">?", "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoFuncionario.remover(funcionario);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(true, true, false, false);
                    textFieldNome.requestFocus();
                    textFieldNome.selectAll();
                }
                textFieldId.setText("");
                textFieldId.setEditable(false);
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
        textFieldDatanasc.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDatanasc.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDatanasc.setBackground(corPadrao);
            }
        });
        textFieldTelefone.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldTelefone.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldTelefone.setBackground(corPadrao);
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
        textFieldStatus.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldStatus.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldStatus.setBackground(corPadrao);
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                m = false;
                qualAcao = "list";
                GUIFuncionarioListagem guiFuncionarioListagem = new GUIFuncionarioListagem(daoFuncionario.listInOrderId(), getBounds().x, getBounds().y, dimensao);
            }
        });
    }

    public static void main(String[] args) {
        new FuncionarioGUI1(new Point(880, 250), new Dimension(800, 600));
    }
}
