package ui;

import business.ContactBusiness;
import ui.entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame{
    //variáveis dos componentes da interface gráfica
    private JPanel rootPanel;
    private JButton buttonNewContact;
    private JButton buttonRemove;
    private JTable tableContacts;
    private JLabel lblCount;
    //Variáveis necessárias
    private ContactBusiness cb;
    private String nName = "";
    private String nPhone = "";

    public MainForm (){
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
        //método para carregar os contatos na tabela
        loadContacts();
    }
    //método para carregar os contatos
    private void loadContacts(){
        //Array com tipo do contato, pegará a lista e irá atribuir nesse array
        List<ContactEntity> cList = cb.getList();

        // preparando a tabela para receber os atributos do contato
        String [] columnNames = {"Nome", "Telefone"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0],columnNames);
        // imprimindo na tabela
        for (ContactEntity i : cList){
            Object [] o = new Object[2];
            o[0] = i.getName();
            o[1] = i.getPhone();

            model.addRow(o);
        }
        //tirando a seleção de um item, para não ficar marcando como selecionado sem ter recebido click
        tableContacts.clearSelection();
        //setando o modelo da tabela que está como padrão
        tableContacts.setModel(model);
        // colocando a quantidade de contatos existentes
        lblCount.setText(cb.getCount());

    }
    // botao de adicionar
    private void setListeners (){
        buttonNewContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //chamando o formulario de adicionar e fechando o main
                new ContactForm();
                dispose();
            }
        });
        // pegando as informações da linha selecionada
        tableContacts.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()){
                    if(tableContacts.getSelectedRow() != -1){
                        nName = tableContacts.getValueAt(tableContacts.getSelectedRow(),0).toString();
                        nPhone = tableContacts.getValueAt(tableContacts.getSelectedRow(),1).toString();
                    }
                }
            }
        });
        // pegando as informações para deletar
        buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{

                    cb.delete(nName,nPhone);
                    loadContacts();

                    nName = "";
                    nPhone = "";

                }catch(Exception e){
                    JOptionPane.showMessageDialog(new JFrame(),e.getMessage());
                }
            }
        });
    }
}
