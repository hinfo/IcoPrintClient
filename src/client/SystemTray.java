package client;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.SwingConstants;

/**
 * * @author Dalí Freire Dias
 */
public class SystemTray extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel jContentPane = null;
    private TrayIcon bandeja = null;
    private JLabel jLabel = null;

    /**
     * * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SystemTray thisClass = new SystemTray();
                thisClass.setVisible(true);
            }
        });
    }

    /**
     * * This is the default constructor
     */
    public SystemTray() {
        super();
        initialize();

        this.bandeja = new TrayIcon(getImagemTray(), "Exemplificando 'System tray'", 
                getPopupMenuTray());
        adicionaEventos();

    }

    /**
     * * This method initializes this * * @return void
     */
    private void initialize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(300, 200);
        this.setLocation((screenSize.width - this.getSize().width) / 2, 
                (screenSize.height - this.getSize().height) / 2);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setContentPane(getJContentPane());
        this.setTitle("System tray");
    }

    /**
     * * Adiciona os eventos
     */
    private void adicionaEventos() {
        bandeja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                botaoAbrir();
            }
        });

        WindowListener sair = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                botaoFechar();
            }
        };
        this.addWindowListener(sair);
    }

    /**
     * * Encerra o sistema
     */
    private void botaoSair() {
        Object[] opcoes = {"Sair", "Cancelar"};
        int opcao = JOptionPane.showOptionDialog(this, "Deseja sair do sistema? \n",
                "Exemplificando 'System tray'", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);

        if (opcao == JOptionPane.YES_OPTION) {

            System.exit(0);

        }
    }

    /**
     * * Coloca o sistema no 'System tray'
     */
    private void botaoFechar() {
        try {
            java.awt.SystemTray.getSystemTray().add(bandeja);
            bandeja.displayMessage("Exemplificando 'System tray'",
                    "A aplicação 'Exemplificando System tray' ainda está em execução. "
                    + "\nDê um clique duplo neste ícone para visualizá-la!",
                    TrayIcon.MessageType.NONE);

            this.setVisible(false);

        } catch (AWTException e1) {

            JOptionPane.showMessageDialog(null, "Falha ao adicionar ícone na bandeja do sistema!", 
                    "", JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
            this.setVisible(true);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Falha ao adicionar ícone na bandeja do sistema!", 
                    "", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
            this.setVisible(true);

        }
    }

    /**
     * * Restaura o sistema
     */
    private void botaoAbrir() {
        java.awt.SystemTray.getSystemTray().remove(bandeja);
        this.setVisible(true);
    }

    /**
     * * Retorna o icone que ficará na bandeja. * @return Image * @throws
     * HeadlessException
     */
    private Image getImagemTray() throws HeadlessException {
        Icon defaultIcon = MetalIconFactory.getTreeComputerIcon();
        Image img = new BufferedImage(defaultIcon.getIconWidth(), defaultIcon
                .getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        defaultIcon.paintIcon(new Panel(), img.getGraphics(), 0, 0);

// Image img = Toolkit.getDefaultToolkit().getImage( getClass().getResource("/imagens/icone_bandeja.gif") );
        return img;
    }

    /**
     * Retorna o menu que é exibido quando clica com o botão direito no ícone da
     * bandeja.
     *
     * @return PopupMenu
     * @throws HeadlessException
     */
    private PopupMenu getPopupMenuTray() throws HeadlessException {
        PopupMenu menu = new PopupMenu();

        MenuItem abrir = new MenuItem("Abrir");
        abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                botaoAbrir();
            }
        });
        MenuItem fechar = new MenuItem("Fechar");
        fechar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                botaoSair();
            }
        });
        menu.add(abrir);
        menu.add(fechar);

        return menu;
    }

    /**
     * This method initializes jLabel
     *
     * @return javax.swing.JLabel
     */
    private JLabel getJLabel() {
        if (jLabel == null) {
            jLabel = new JLabel();
            jLabel.setText("Exemplificando o uso do 'System tray'");
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setBounds(new Rectangle(1, 65, 292, 16));
        }
        return jLabel;
    }

    /**
     * This method initializes jContentPane
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJContentPane() {
        if (jContentPane == null) {
            jContentPane = new JPanel();
            jContentPane.setLayout(null);
            jContentPane.add(getJLabel(), null);
        }
        return jContentPane;
    }

}
