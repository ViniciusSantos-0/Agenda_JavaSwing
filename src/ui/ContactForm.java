package ui;

import business.ContactBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactForm extends JFrame {
    private JPanel rootPanel;
    private JTextField textName;
    private JTextField textFone;
    private JButton buttonCancel;
    private JButton buttonSave;

    private ContactBusiness cb;

    public ContactForm (){
        //mostrando o conteúdo do painel da interface gráfica
        setContentPane(rootPanel);
        setSize(500,250);
        setVisible(true);
        //pega o tamanho do monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // configuração para nascer no meio da tela
        setLocation(dim.width/2 - getSize().width/2, dim.height/2 - getSize().height/2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// caso fechar, parar de executar o programa
        cb = new ContactBusiness();
        //método para chamar os eventos dos botões
        setListeners();
    }
        // salvando o contato
        public void setListeners(){
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

               try {
                   String name = textName.getText();
                   String phone = textFone.getText();

                   cb.save(name, phone);

                   new MainForm();
                   dispose();
               }catch (Exception e){
                   JOptionPane.showMessageDialog(new JFrame(),e.getMessage());
               }
            }
        });
        // caso cancelar voltar para o jframe main
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new MainForm();
                dispose();
            }
        });

        }
}
