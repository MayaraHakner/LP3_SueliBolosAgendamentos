package Ferramentas;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import oracle.jrockit.jfr.events.DataStructureDescriptor;

/**
 * Componente power para entrada de data/hora. Possuir calendário opcional ao
 * pressionar F12 e recurso de validar e completar data inválida
 *
 * @author junior
 *
 */
public class JDateTextField extends JFormattedTextField {

    // este formatador faz a máscara de entrada de dados " /  / "
    public static MaskFormatter formatador;
    // constantes usadas para completar a data caso usuário deixe parte sem preencher
    private final String mesAtual = new SimpleDateFormat("MM").format(new Date());
    private final String anoAtual = new SimpleDateFormat("yyyy").format(new Date());

    // necessário a inicialização estática da variável maskformater pq ela é chamada no construtor (super())
    static {
        try {
            formatador = new MaskFormatter("##/##/####");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    JDateTextField() {
        super(formatador);
        // fonte mono espaçada para visualmente ficar bem melhor
        setFont(new Font("Courier", Font.PLAIN, 12));
        // define um tamanho máximo 13 colunas 12 para conteúdo + 1 para não ficar "justo"
        setColumns(13);
        // adiciona um listener para foco, quando "perder" o foco vamos completar/validar a data
        addFocusListener(new DateFieldFocusListener());
        // adiciona um listener para teclado, + e - para mudar a data e F12 para calendário
        addKeyListener(new DateFieldKeyListener());

    }

    /**
     * Retorna a data já convertida para Date
     *
     * @return O conteúdo da JDateTextField em formato java.util.Date
     */
    public Date getDate() {
        // não pega a parte que tem o dia da semana
        String dataStr = getText().substring(0, 10);
        // vazio? tchau
        if (dataStr.equals("  /  /    ")) {
            return null;
        }
        // setLenient é para não aceitar datas inválidas, 
        // senão o java converte para data válida mais próxima
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);

        try {
            Date d = df.parse(dataStr);
            // atualiza conteúdo do campo com o dia da semana
            setText(new SimpleDateFormat("dd/MM/yyyy").format(d));
            d = null;
            return df.parse(dataStr);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "O valor de data digitado não é válido", "Erro de conversão", JOptionPane.ERROR_MESSAGE);
            // atualiza conteúdo do campo com o dia da semana
            setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            requestFocus();
            return null;
        }
        
    }

    /**
     * Modifica o conteúdo da JDateTextField para uma Data que o programa quiser
     *
     * @param data entra como valor a ser "setado" na tela
     */
   /* public void setDate(Date data) {
        setText(new SimpleDateFormat("dd/MM/yyyy").format(data));
    }*/

    /**
     * Método privado para completar a data quando estiver parcialmente
     * preenchida pelo usuario após completar, também valida a fim de a data não
     * ser digitada errada
     */
    private void completaData() {

        String[] partes = getText().split("/");
        String dataStr = getText().substring(0, 10);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        // primeira parte, dia
        if (partes[0].equals("  ")) {
            dataStr = df.format(new Date());
        } else // mes
        {
            if (partes[1].equals("  ")) {
                dataStr = partes[0] + "/" + mesAtual + "/" + anoAtual;
            } else // ano
            {
                if (partes[2].substring(0, 2).equals("  ")) // note o substring por causa do dia da semana
                {
                    dataStr = partes[0] + "/" + partes[1] + "/" + anoAtual;
                }
            }
        }

        // não converter datas erradas para valor mais aproximado válido (setLenient)
        df.setLenient(false);
        try {
            Date d = df.parse(dataStr);
            // atualiza conteúdo do campo com o dia da semana
            setText(new SimpleDateFormat("dd/MM/yyyy").format(d));
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "O valor de data digitado não é válido", "Erro de conversão", JOptionPane.ERROR_MESSAGE);
            // atualiza conteúdo do campo com o dia da semana
            setText(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            requestFocus();
        }
    }

    /**
     * classe interna privata para controle de foco, quando detectado a perda de
     * foco o programa chama a função completaData()
     *
     * @author junior
     * @see completaData()
     */
    public class DateFieldFocusListener implements FocusListener {

        public void focusGained(FocusEvent e) {
        }

        public void focusLost(FocusEvent e) {
            completaData();
        }
    }

    /**
     * classe interna priavata para controle de teclado, no qual o usuário usa +
     * e - ou então chama um calendário pressionando F12
     *
     * @author junior
     *
     */
     public class DateFieldKeyListener implements KeyListener {

        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                // pega posição do JTextField em relação a tela do S.O.
                Point p = getLocationOnScreen();
                // chama um DataChooser posicionando logo abaixo da JTextField
                DateChooser dc = new DateChooser((JFrame) null, "Escolha uma data", p.x, p.y + getHeight());
                Date newDate = dc.select(getDate());
                if (newDate != null) {
                    setText(new SimpleDateFormat("dd/MM/yyyy").format(newDate));
                }
                // envia o foco para o início da edit
                setSelectionStart(0);
                setSelectionEnd(0);
            }

        }

        public void keyReleased(KeyEvent e) {
        }

        public void keyTyped(KeyEvent e) {
        }
        //public void keyPressed(KeyEvent e) {}
        //public void DateFieldKeyListene(){}

    }

    public void keyPressed() {
    }

}
