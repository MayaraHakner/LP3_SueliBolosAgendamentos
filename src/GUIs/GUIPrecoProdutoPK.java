package GUIs;

import DAOs.*;
import Entidades.*;
import Ferramentas.FerramentasParaData;
import java.awt.Dimension;
import java.util.List;
import java.awt.Point;
import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import myUtil.JanelaPesquisar;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Date;

import myUtil.UsarGridBagLayout;

public class GUIPrecoProdutoPK extends JDialog {

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

    JLabel labelProdutoIdProduto = new JLabel("ProdutoIdProduto");
    JTextField textFieldProdutoIdProduto = new JTextField(5);
    JLabel labelDataPrecoProduto = new JLabel("DataPrecoProduto");
    JTextField textFieldDataPrecoProduto = new JTextField(10);
    JLabel labelPrecoProduto = new JLabel("PrecoProduto");
    JTextField textFieldPrecoProduto = new JTextField(20);

    JPanel pnAvisos = new JPanel();
    JLabel labelAviso = new JLabel("");

    String acao = "";//variavel para facilitar insert e update
    DAOPrecoProdutoPK daoPrecoProdutoPK = new DAOPrecoProdutoPK();
    DAOProduto daoProduto = new DAOProduto();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    MaskFormatter mascara;
    DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
    PrecoProduto precoProduto;
    DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
    FerramentasParaData ferramentasParaData = new FerramentasParaData();
    Date dataDatanasc;
    SimpleDateFormat sdfDatanasc = new SimpleDateFormat("dd/MM/yyyy");
    Produto produto = new Produto();

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

    private void habilitarAtributos(boolean produtoIdProduto, boolean dataPrecoProduto) {
        if (produtoIdProduto) {
            textFieldProdutoIdProduto.requestFocus();
            textFieldProdutoIdProduto.selectAll();
        }
        textFieldProdutoIdProduto.setEnabled(produtoIdProduto);
        textFieldProdutoIdProduto.setEditable(produtoIdProduto);
        textFieldDataPrecoProduto.setEditable(produtoIdProduto);
        textFieldPrecoProduto.setEditable(dataPrecoProduto);

    }

    public void zerarAtributos() {
        textFieldPrecoProduto.setText("");
    }
    Color corPadrao = labelProdutoIdProduto.getBackground();

    public GUIPrecoProdutoPK(Point posicao, Dimension dimensao) {
        try {
            mascara = new MaskFormatter("##/##/####");
        } catch (ParseException erro) {

        }
        textFieldDataPrecoProduto = new JFormattedTextField(mascara);

        dimensao.height = 150;
        setTitle("CRUD - PrecoProdutoPK");
        setSize(700, 150);//tamanho da janela
        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado
        setBackground(Color.CYAN);//cor do fundo da janela
        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes

        atvBotoes(false, true, false, false);
        habilitarAtributos(true, false);
        btnCreate.setToolTipText("Inserir novo registro");
        btnRetrieve.setToolTipText("Pesquisar por chave");
        btnUpdate.setToolTipText("Alterar");
        btnDelete.setToolTipText("Excluir");
        btnList.setToolTipText("Listar todos");
        btnSave.setToolTipText("Salvar");
        btnCancel.setToolTipText("Cancelar");
        JToolBar Toolbar1 = new JToolBar();
        Toolbar1.add(labelProdutoIdProduto);
        Toolbar1.add(textFieldProdutoIdProduto);
        Toolbar1.add(labelDataPrecoProduto);
        Toolbar1.add(textFieldDataPrecoProduto);
        Toolbar1.add(btnRetrieve);
        Toolbar1.add(btnCreate);
        Toolbar1.add(btnUpdate);
        Toolbar1.add(btnDelete);
        Toolbar1.add(btnSave);
        Toolbar1.add(btnCancel);
        Toolbar1.add(btnList);
        btnSave.setVisible(false);
        btnCancel.setVisible(false);

//atritubos não chave, todos no painel centro
        JPanel centro = new JPanel();

        UsarGridBagLayout usarGridBagLayout = new UsarGridBagLayout(centro);
        usarGridBagLayout.add(labelPrecoProduto, textFieldPrecoProduto, corPadrao);
        pnAvisos.add(labelAviso);
        pnAvisos.setBackground(Color.yellow);
        cp.add(Toolbar1, BorderLayout.NORTH);
        cp.add(centro, BorderLayout.CENTER);
        cp.add(pnAvisos, BorderLayout.SOUTH);
        textFieldProdutoIdProduto.requestFocus();
        textFieldProdutoIdProduto.selectAll();
        textFieldProdutoIdProduto.setBackground(Color.GREEN);
        labelAviso.setText("Digite um ProdutoIdProduto e clic [Pesquisar]");

//        try {
//            //esse código é para facilitar os testes
//            textFieldProdutoIdProduto.setText("1");
//            textFieldDataPrecoProduto.setText(sdf.format(sdf.parse("10/06/2018")));
//
//        } catch (Exception e) {
//        }
//--------------- listeners ----------------- 
        textFieldProdutoIdProduto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textFieldProdutoIdProduto.getText().equals("")) {
                    // DAOProduto daoProduto = new DAOProduto();
                    List<String> listaAuxiliar = daoProduto.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldProdutoIdProduto.setText(aux[0]);
                        } else {
                            textFieldProdutoIdProduto.requestFocus();
                            textFieldProdutoIdProduto.selectAll();
                        }
                    }

                    textFieldProdutoIdProduto.requestFocus();
                    textFieldProdutoIdProduto.selectAll();
                }
            }
        });

//-----------------------------  btnRetrieve ------------------------------------------
        btnRetrieve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                
                precoProduto = new PrecoProduto();
                textFieldProdutoIdProduto.setText(textFieldProdutoIdProduto.getText().trim());//caso tenham sido digitados espaços
                DAOPrecoProduto daoPrecoProduto1 = new DAOPrecoProduto();

                try {
                    sdf.setLenient(false);

                    Date dtPreco = sdf.parse(textFieldDataPrecoProduto.getText());
                    

                } catch (Exception errroooo1) {

                    //textFieldProdutoIdProduto.setBackground(Color.red);
                    textFieldDataPrecoProduto.setBackground(Color.red);
                }
                if (textFieldProdutoIdProduto.getText().equals("")) {
                    // DAOProduto daoProduto = new DAOProduto();
                    List<String> listaAuxiliar = daoPrecoProduto.listInOrderNomeStrings("id");
                    if (listaAuxiliar.size() > 0) {
                        Point lc = btnRetrieve.getLocationOnScreen();
                        lc.x = lc.x + btnRetrieve.getWidth();
                        String selectedItem = new JanelaPesquisar(listaAuxiliar,
                                lc.x,
                                lc.y).getValorRetornado();
                        if (!selectedItem.equals("")) {
                            String[] aux = selectedItem.split("-");
                            textFieldProdutoIdProduto.setText(aux[0]);
                            textFieldDataPrecoProduto.setText(aux[2]);
                            btnRetrieve.doClick();
                        } else {
                            textFieldProdutoIdProduto.requestFocus();
                            textFieldProdutoIdProduto.selectAll();
                        }
                    }

                    textFieldProdutoIdProduto.requestFocus();
                    textFieldProdutoIdProduto.selectAll();
                } else {
                    try {
                        PrecoProdutoPK precoProdutoPK = new PrecoProdutoPK();
                        precoProdutoPK.setProdutoIdProduto(Integer.valueOf(textFieldProdutoIdProduto.getText()));
                        
                        
                        precoProdutoPK.setDataPrecoProduto(sdf.parse((textFieldDataPrecoProduto.getText())));
                        try {
                            sdf.setLenient(false);
                            int idProd = Integer.valueOf(textFieldProdutoIdProduto.getText());
                            Date dtPreco = sdf.parse(textFieldDataPrecoProduto.getText());
                            precoProduto.setPrecoProdutoPK(new PrecoProdutoPK(dtPreco, idProd));

                        } catch (Exception errroooo) {

                            textFieldProdutoIdProduto.setBackground(Color.red);
                            textFieldDataPrecoProduto.setBackground(Color.red);
                        }
                        DAOPrecoProduto daoPrecoProduto = new DAOPrecoProduto();
                        precoProduto = daoPrecoProduto.obter(precoProdutoPK);

                        if (precoProduto != null) { //se encontrou na lista                            
                            textFieldPrecoProduto.setText(String.valueOf(precoProduto.getPreco()));
                            atvBotoes(false, true, true, true);
                            habilitarAtributos(true, false);
                            labelAviso.setText("Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]");
                            acao = "encontrou";
                        } else {  //não achou na lista
                            try {
                                produto = daoProduto.obter(Integer.valueOf(textFieldProdutoIdProduto.getText()));
                                if (produto ==null) {
                                    labelAviso.setText("Produto não cadastrado " );
                                atvBotoes(false, true, false, false);
                                }
                                else{
                                    atvBotoes(true, true, false, false);
                            zerarAtributos();
                            labelAviso.setText("Não cadastrado - clic [Inserir] ou digite outra id [Pesquisar]");
                                }
                                
                            } catch (Exception oooo) {
                                
                            }
                        }
                        textFieldProdutoIdProduto.setBackground(Color.green);
                        textFieldDataPrecoProduto.setBackground(Color.green);
                    } catch (Exception x) {
                        textFieldProdutoIdProduto.setOpaque(true);
                        textFieldProdutoIdProduto.selectAll();
                        textFieldProdutoIdProduto.requestFocus();
                        textFieldProdutoIdProduto.setBackground(Color.red);
                        textFieldDataPrecoProduto.setBackground(Color.red);
                        labelAviso.setText("Tipo errado - " + x.getMessage());
                    }
                }
            }
        });

        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                habilitarAtributos(false, true);
                textFieldPrecoProduto.requestFocus();
                mostrarBotoes(false);
                labelAviso.setText("Preencha os campos e clic [Salvar] ou clic [Cancelar]");
                acao = "insert";
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                zerarAtributos();
                atvBotoes(false, true, false, false);
                mostrarBotoes(true);
                textFieldPrecoProduto.setEditable(false);
                habilitarAtributos(true, false);
                textFieldDataPrecoProduto.setBackground(corPadrao);
            
                textFieldProdutoIdProduto.setBackground(corPadrao);
                }
        });

//-----------------------------  SAVE ------------------------------------------
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                boolean deuRuim = false;
                if (acao.equals("insert")) {
                    precoProduto = new PrecoProduto();
                }
                try {
                    sdf.setLenient(false);
                    int idProd = Integer.valueOf(textFieldProdutoIdProduto.getText());
                    Date dtPreco = sdf.parse(textFieldDataPrecoProduto.getText());
                    precoProduto.setPrecoProdutoPK(new PrecoProdutoPK(dtPreco, idProd));

                } catch (Exception erro2) {
                    deuRuim = true;
                    textFieldProdutoIdProduto.setBackground(Color.red);
                    textFieldDataPrecoProduto.setBackground(Color.red);
                }
                try {
                    precoProduto.setPreco(Double.valueOf((textFieldPrecoProduto.getText())));
                    textFieldPrecoProduto.setBackground(corPadrao);

                } catch (Exception erro3) {
                    textFieldPrecoProduto.setBackground(Color.red);
                    deuRuim = true;
                }
                if (!deuRuim) {
                    if (acao.equals("insert")) {
                        daoPrecoProduto.inserir(precoProduto);
                        labelAviso.setText("Registro inserido.");
                    } else {
                        daoPrecoProduto.atualizar(precoProduto);
                        labelAviso.setText("Registro alterado.");
                    }
                    habilitarAtributos(true, false);
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
                acao = "update";
                mostrarBotoes(false);
                habilitarAtributos(false, true);
            }
        });
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,
                        "Confirma a exclusão do registro?\n "
                        + daoProduto.obter(precoProduto.getPrecoProdutoPK().getProdutoIdProduto()).getNomeProduto() + "\n"
                        + sdf.format(precoProduto.getPrecoProdutoPK().getDataPrecoProduto()) + "\n"
                        + "R$" + precoProduto.getPreco() + "\n",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {
                    labelAviso.setText("Registro excluído...");
                    daoPrecoProduto.remover(precoProduto);
                    zerarAtributos();
                    mostrarBotoes(true);
                    atvBotoes(false, true, false, false);
                    textFieldDataPrecoProduto.requestFocus();
                    textFieldDataPrecoProduto.selectAll();
                    textFieldProdutoIdProduto.setBackground(corPadrao);
                    textFieldDataPrecoProduto.setBackground(corPadrao);
                    textFieldDataPrecoProduto.setText("");
                    textFieldProdutoIdProduto.setText("");
                   
                }
            }
        });
        btnList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                GUIPrecoProdutoPKListagem guiPrecoProdutoPKListagem = new GUIPrecoProdutoPKListagem(daoPrecoProduto.listInOrderId(), getBounds().x, getBounds().y, new Dimension(800, 600));
            }
        });
        textFieldDataPrecoProduto.addFocusListener(new FocusListener() { //ao receber o foco, fica verde
            @Override
            public void focusGained(FocusEvent fe) {
                textFieldDataPrecoProduto.setBackground(Color.GREEN);
            }

            @Override
            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco
                textFieldDataPrecoProduto.setBackground(corPadrao);
            }
        });
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Sai   
                dispose();
            }
        });

        //  pack();
        setModal(true);
        setLocationRelativeTo(null);
        setVisible(true);//faz a janela ficar visível  
    }

    public static void main(String[] args) {
        new GUIPrecoProdutoPK(new Point(880, 250), new Dimension(800, 600));
    }
}
