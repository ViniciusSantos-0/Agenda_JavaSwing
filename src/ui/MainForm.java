package ui;

import business.ContactBusiness;
import ui.entity.ContactEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame{

    private JPanel rootPanel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;

    private ContactBusiness cb;

    public MainForm (){
        setContentPane(rootPanel);
       setSize(500,250);
       setVisible(true);
        //pega o tamanho do monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // configuração para nascer no meio da tela
        setLocation(dim.width/2 - getSize().width/2, dim.height/2 - getSize().height/2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setListeners();
        cb = new ContactBusiness();
        loadContacts();
    }
    private void loadContacts(){
      List<ContactEntity> cList = cb.getList();
    }

    private void setListeners (){
        buttonNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ContactForm();
                dispose();
            }
        });
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

}
