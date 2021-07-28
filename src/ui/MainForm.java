package ui;

import business.ContactBusiness;
import ui.entity.ContactEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame{

    private JPanel rootPanel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JLabel lblCount;

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
        cb = new ContactBusiness();
        setListeners();

        loadContacts();
    }
    private void loadContacts(){
        List<ContactEntity> cList = cb.getList();

        String [] columnNames = {"Nome", "Telefone"};

        DefaultTableModel model = new DefaultTableModel(new Object[0][0],columnNames);

        for (ContactEntity i : cList){
            Object [] o = new Object[2];
            o[0] = i.getName();
            o[1] = i.getPhone();

            model.addRow(o);
        }
        tableContacts.clearSelection();
        tableContacts.setModel(model);

        lblCount.setText(cb.getCount());

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
