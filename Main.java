import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.event.*;
import java.util.*;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.table.*;
import javax.swing.Timer;
public class Main extends JFrame implements ActionListener
{
    JFrame f = new JFrame("RAVIS");
    JLabel cpanelbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/cpanelbg.png")));
    JLabel adminpanelbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/Login BG.png")));
    JLabel bgupdate = new JLabel(new ImageIcon(getClass().getResource("resources/Main/updatebg.png")));
    JLabel addbookspanelbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/addpanelbg.png")));
    JLabel editbookspanelbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/editpanelbg.png")));
    JLabel deletebookspanelbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/deletepanelbg.png")));
    JLabel lendbookspanelbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/lendpanelbg.png")));
    JLabel returnbookspanelbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/returnpanelbg.png")));
    JLabel categorypanelbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/categorypanelbg.png")));
    JLabel searchpanelbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/searchpanelbg.png")));
    JLabel bsearchpanelbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/searchpanelbg.png")));
    JLabel searchoptionbg = new JLabel(new ImageIcon(getClass().getResource("resources/Main/searchoptionbg.png")));
    
    JButton admin, login, register, about, log, fgot;
    JPanel mainpanel,adminpanel,cpanel,updatepanel,searchpanel,addBooks,editBooks,deleteBooks,lendBooks,returnBooks,loadingpanel,addCategory,searchBooks,bsearchBooks,searchOption;
    Font font = new Font("Serif", Font.BOLD, 20);
    Main()
    {        
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                Frame();
            }});
    }
    
    Dimension screen;
    
    String dbUsername, dbPassword;
    JOptionPane  J = new JOptionPane();
    void Frame()
    {
        screen = Toolkit.getDefaultToolkit().getScreenSize();
        f.setVisible(true);
        f.setSize(1024,795);
        f.setLayout(null);
        f.setLocation(screen.width/2,screen.height/2);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("resources/Main/icon.png")));
        AdminPanel();
    }
    
    
    JButton sbook, sborrower,sback;
    void searchOption()
    {
        searchOption = new JPanel();
        searchOption.setLayout(null);
        
        sbook = new JButton(new ImageIcon(getClass().getResource("resources/Main/sbook (1).png")));
        sbook.setFocusPainted(false);
        sbook.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/sbook (2).png")));
        sbook.addActionListener(this);
        sbook.setContentAreaFilled(false);
        sbook.setFocusPainted(false);
        sbook.setBorderPainted(false);
        
        sborrower = new JButton(new ImageIcon(getClass().getResource("resources/Main/sborrow (1).png")));
        sborrower.setFocusPainted(false);
        sborrower.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/sborrow (2).png")));
        sborrower.addActionListener(this);
        sborrower.setContentAreaFilled(false);
        sborrower.setFocusPainted(false);
        sborrower.setBorderPainted(false);
        
        sback = new JButton(new ImageIcon(getClass().getResource("resources/Main/back (1).png")));
        sback.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/back (2).png")));
        sback.setBorderPainted(false);
        sback.setOpaque(false);
        sback.setContentAreaFilled(false);
        sback.setFocusPainted(false);
        sback.addActionListener(this);
        
        a(searchOption,sbook,400,250,214,64);
        a(searchOption,sborrower,400,310,214,64);
        a(searchOption,sback,400,370,214,64);
        
        a(f,searchOption,0,0,1024,768);
        a(searchOption,searchoptionbg,0,0,1024,768);
    }
     
    private JTextField auser;
    private JPasswordField apass;
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null; 
    JLabel pinfo;
    void AdminPanel()
    {
        auser = new JTextField();
        auser.setOpaque(false);
        auser.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        auser.setHorizontalAlignment(JTextField.CENTER);
        apass = new JPasswordField();
        apass.setOpaque(false);
        apass.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        apass.setHorizontalAlignment(JPasswordField.CENTER);
        apass.addActionListener(this);
        
        log = new JButton(new ImageIcon(getClass().getResource("resources/Main/login button.png")));
        log.setFocusPainted(false);
        log.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/login button2.png")));
        log.addActionListener(this);
        log.setContentAreaFilled(false);
        log.setFocusPainted(false);
        log.setBorderPainted(false);
        
        pinfo = new JLabel();
        pinfo.setForeground(Color.WHITE);
        pinfo.setHorizontalAlignment(JLabel.CENTER);
       
        adminpanel = new JPanel();
        adminpanel.setLayout(null);
        
        a(adminpanel,auser,250,368,150,30);
        a(adminpanel,apass,250,405,150,30);
        a(adminpanel,log,250,440,150,40);
        a(adminpanel,pinfo,250,470,150,30);
        a(f,adminpanel,0,0,1024,768);
        a(adminpanel,adminpanelbg,0,0,1024,768);

    }
    
    /** Update Book **/
    
    JButton addbooks, deletebooks, editbooks, addcategory, addback;
    
    void updateBook()
    {
        updatepanel = new JPanel();
        updatepanel.setLayout(null);
        updatepanel.setVisible(true);
        
        addbooks = new JButton(new ImageIcon(getClass().getResource("resources/Main/addbook (1).png")));
        addbooks.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/addbook (2).png")));
        addbooks.setBorderPainted(false);
        addbooks.setOpaque(false);
        addbooks.setContentAreaFilled(false);
        addbooks.setFocusPainted(false);
        addbooks.addActionListener(this);
        
        deletebooks = new JButton(new ImageIcon(getClass().getResource("resources/Main/deletebook (1).png")));
        deletebooks.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/deletebook (2).png")));
        deletebooks.setBorderPainted(false);
        deletebooks.setOpaque(false);
        deletebooks.setContentAreaFilled(false);
        deletebooks.setFocusPainted(false);
        deletebooks.addActionListener(this);
        
        editbooks = new JButton(new ImageIcon(getClass().getResource("resources/Main/editbook (1).png")));
        editbooks.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/editbook (2).png")));
        editbooks.setBorderPainted(false);
        editbooks.setOpaque(false);
        editbooks.setContentAreaFilled(false);
        editbooks.setFocusPainted(false);
        editbooks.addActionListener(this);
        
        addcategory = new JButton(new ImageIcon(getClass().getResource("resources/Main/addcategory (1).png")));
        addcategory.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/addcategory (2).png")));
        addcategory.setBorderPainted(false);
        addcategory.setOpaque(false);
        addcategory.setContentAreaFilled(false);
        addcategory.setFocusPainted(false);
        addcategory.addActionListener(this);
        
        addback = new JButton(new ImageIcon(getClass().getResource("resources/Main/back (1).png")));
        addback.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/back (2).png")));
        addback.setBorderPainted(false);
        addback.setOpaque(false);
        addback.setContentAreaFilled(false);
        addback.setFocusPainted(false);
        addback.addActionListener(this);
        
        a(updatepanel,addbooks,400,250,214,64);
        a(updatepanel,deletebooks,400,310,214,64);
        a(updatepanel,editbooks,400,370,214,64);
        a(updatepanel,addcategory,400,430,214,64);
        a(updatepanel,addback,400,490,214,64);
        
        a(f,updatepanel,0,0,1024,768);
        a(updatepanel,bgupdate,0,0,1024,768);
    }
    
    /** Add Book **/
    
    JButton back, submit, clear;
    JTextField title,   author, description,isbn, date_published, no_of_copies,catalog_no;
    JComboBox<String> category;
    void addBooks()
    {
        addBooks = new JPanel();
        addBooks.setLayout(null);
        
        title = new JTextField();
        title.setOpaque(false);
        title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        title.setFont(font);
        title.setHorizontalAlignment(JTextField.CENTER);
        
        author = new JTextField();
        author.setOpaque(false);
        author.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        author.setFont(font);
        author.setHorizontalAlignment(JTextField.CENTER);
        
        description = new JTextField();
        description.setOpaque(false);
        description.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        description.setFont(font);
        description.setHorizontalAlignment(JTextField.CENTER);
        
        isbn = new JTextField();
        isbn.setOpaque(false);
        isbn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        isbn.setFont(font);
        isbn.setHorizontalAlignment(JTextField.CENTER);
        
        category = new JComboBox<String>();
        category.setMaximumRowCount(20);
        try
        {
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
            String query = "SELECT * FROM library.book_classification;";
            ResultSet rs=stmt.executeQuery(query);
            while (rs.next()) 
            {  
                category.addItem(rs.getString("classifications"));  
            }
        }catch(Exception se)
        {
        }
        
        date_published = new JTextField();
        date_published.setOpaque(false);
        date_published.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        date_published.setFont(font);
        date_published.setHorizontalAlignment(JTextField.CENTER);
        
        no_of_copies = new JTextField();
        no_of_copies.setOpaque(false);
        no_of_copies.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        no_of_copies.setFont(font);
        no_of_copies.setHorizontalAlignment(JTextField.CENTER);
        
        catalog_no = new JTextField();
        catalog_no.setOpaque(false);
        catalog_no.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        catalog_no.setFont(font);
        catalog_no.setHorizontalAlignment(JTextField.CENTER);
        catalog_no.setEditable(false);
        try
        {
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
            String query = "SELECT * FROM library.book_classification;";
            ResultSet rs=stmt.executeQuery(query);
            while (rs.next()) 
            {  
                category.addItem(rs.getString("classifications"));  
            }
        }catch(Exception se)
        {
        }               
        
        back  = new JButton(new ImageIcon(getClass().getResource("resources/Main/a_back (1).png")));
        back.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/a_back (2).png")));
        back.setBorderPainted(false);
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setFocusPainted(false);
        back.addActionListener(this);
        
        submit  = new JButton(new ImageIcon(getClass().getResource("resources/Main/submit (1).png")));
        submit.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/submit (2).png")));
        submit.setBorderPainted(false);
        submit.setOpaque(false);
        submit.setContentAreaFilled(false);
        submit.setFocusPainted(false);
        submit.addActionListener(this);
        
        clear  = new JButton(new ImageIcon(getClass().getResource("resources/Main/clear (1).png")));
        clear.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/clear (2).png")));
        clear.setBorderPainted(false);
        clear.setOpaque(false);
        clear.setContentAreaFilled(false);
        clear.setFocusPainted(false);
        clear.addActionListener(this);
        
        a(addBooks,back,20,20,150,40);
        a(addBooks,submit,55,660,114,64);
        a(addBooks,clear,169,660,114,64);
        
        a(addBooks,title,505,210,454,40);
        a(addBooks,author,575,292,390,40);
        a(addBooks,description,643,370,322,40);
        a(addBooks,isbn,491,447,472,40);
        a(addBooks,date_published,708,525,256,40);
        a(addBooks,no_of_copies,670,602,293,40);
        a(addBooks,catalog_no,650,677,312,40);
        a(addBooks,category,80,260,180,30);
        
        a(f,addBooks,0,0,1024,768);
        a(addBooks,addbookspanelbg,0,0,1024,768);
    }
    
    /** Edit Book **/
    
    JButton e_back, e_editRecord;
    JTextField e_title, e_author, e_description, e_isbn, e_date_published, e_no_of_copies, e_catalog_no;
    JComboBox<String> e_category;
    void editBooks()
    {
        editBooks = new JPanel();
        editBooks.setLayout(null);
        
        e_back = new JButton(new ImageIcon(getClass().getResource("resources/Main/e_back (1).png")));
        e_back.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/e_back (2).png")));
        e_back.setFocusPainted(false);
        e_back.setBorderPainted(false);
        e_back.setOpaque(false);
        e_back.setContentAreaFilled(false);
        e_back.addActionListener(this);
        
        e_editRecord  = new JButton(new ImageIcon(getClass().getResource("resources/Main/editbook (1).png")));
        e_editRecord.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/editbook (2).png")));
        e_editRecord.setBorderPainted(false);
        e_editRecord.setOpaque(false);
        e_editRecord.setContentAreaFilled(false);
        e_editRecord.setFocusPainted(false);
        e_editRecord.addActionListener(this);
        
        e_title = new JTextField();
        e_title.setOpaque(false);
        e_title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        e_title.setFont(font);
        e_title.setHorizontalAlignment(JTextField.CENTER);
        
        e_author = new JTextField();
        e_author.setOpaque(false);
        e_author.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        e_author.setFont(font);
        e_author.setHorizontalAlignment(JTextField.CENTER);
        
        e_description = new JTextField();
        e_description.setOpaque(false);
        e_description.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        e_description.setFont(font);
        e_description.setHorizontalAlignment(JTextField.CENTER);
        
        e_isbn = new JTextField();
        e_isbn.setOpaque(false);
        e_isbn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        e_isbn.setFont(font);
        e_isbn.setHorizontalAlignment(JTextField.CENTER);
        
        e_category = new JComboBox<String>();
        
        e_date_published = new JTextField();
        e_date_published.setOpaque(false);
        e_date_published.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        e_date_published.setFont(font);
        e_date_published.setHorizontalAlignment(JTextField.CENTER);
        
        e_no_of_copies = new JTextField();
        e_no_of_copies.setOpaque(false);
        e_no_of_copies.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        e_no_of_copies.setFont(font);
        e_no_of_copies.setHorizontalAlignment(JTextField.CENTER);
        
        e_catalog_no = new JTextField();
        e_catalog_no.setOpaque(false);
        e_catalog_no.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        e_catalog_no.setFont(font);
        e_catalog_no.setHorizontalAlignment(JTextField.CENTER);
        e_catalog_no.addActionListener(this);
        JLabel jtitle= new JLabel("*Title: ");
        jtitle.setForeground(Color.WHITE);
        jtitle.setHorizontalAlignment(JTextField.CENTER);
        
        a(editBooks,e_back,20,20,150,40);
        a(editBooks,e_editRecord,55,660,228,64);
        
        a(editBooks,e_title,500,213,455,40);
        a(editBooks,e_author,570,294,387,40);
        a(editBooks,e_description,638,372,320,40);
        a(editBooks,e_isbn,486,448,472,40);
        a(editBooks,e_date_published,703,527,254,40);
        a(editBooks,e_no_of_copies,665,604,291,40);
        a(editBooks,e_catalog_no,70,270,205,50);
        a(editBooks,e_category,580,680,362,45);
        
        a(f,editBooks,0,0,1024,768);
        a(editBooks,editbookspanelbg,0,0,1024,768);
    }
    
    /** Delete Book **/
    JButton d_back, d_deleteRecord;
    JTextField d_title,d_author, d_description, d_isbn, d_date_published, d_no_of_copies, d_catalog_no, d_category;

    void deleteBooks()
    {
        deleteBooks = new JPanel();
        deleteBooks.setLayout(null);
        
        d_back = new JButton(new ImageIcon(getClass().getResource("resources/Main/d_back (1).png")));
        d_back.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/d_back (2).png")));
        d_back.setFocusPainted(false);
        d_back.setBorderPainted(false);
        d_back.setOpaque(false);
        d_back.setContentAreaFilled(false);
        d_back.addActionListener(this);
        
        d_deleteRecord  = new JButton(new ImageIcon(getClass().getResource("resources/Main/deletebook (1).png")));
        d_deleteRecord.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/deletebook (2).png")));
        d_deleteRecord.setBorderPainted(false);
        d_deleteRecord.setOpaque(false);
        d_deleteRecord.setContentAreaFilled(false);
        d_deleteRecord.setFocusPainted(false);
        d_deleteRecord.addActionListener(this);
        
        d_title = new JTextField();
        d_title.setOpaque(false);
        d_title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        d_title.setFont(font);
        d_title.setHorizontalAlignment(JTextField.CENTER);
        d_title.setEditable(false);
        
        d_author = new JTextField();
        d_author.setOpaque(false);
        d_author.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        d_author.setFont(font);
        d_author.setHorizontalAlignment(JTextField.CENTER);
        d_author.setEditable(false);
        
        d_description = new JTextField();
        d_description.setOpaque(false);
        d_description.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        d_description.setFont(font);
        d_description.setHorizontalAlignment(JTextField.CENTER);
        d_description.setEditable(false);
        
        d_isbn = new JTextField();
        d_isbn.setOpaque(false);
        d_isbn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        d_isbn.setFont(font);
        d_isbn.setHorizontalAlignment(JTextField.CENTER);
        d_isbn.setEditable(false);
        
        d_category = new JTextField();
        d_category.setOpaque(false);
        d_category.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        d_category.setFont(font);
        d_category.setHorizontalAlignment(JTextField.CENTER);
        d_category.setEditable(false);
        
        d_date_published = new JTextField();
        d_date_published.setOpaque(false);
        d_date_published.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        d_date_published.setFont(font);
        d_date_published.setHorizontalAlignment(JTextField.CENTER);
        d_date_published.setEditable(false);
        
        d_no_of_copies = new JTextField();
        d_no_of_copies.setOpaque(false);
        d_no_of_copies.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        d_no_of_copies.setFont(font);
        d_no_of_copies.setHorizontalAlignment(JTextField.CENTER);
        d_no_of_copies.setEditable(false);
        
        d_catalog_no = new JTextField();
        d_catalog_no.setOpaque(false);
        d_catalog_no.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        d_catalog_no.setFont(font);
        d_catalog_no.setHorizontalAlignment(JTextField.CENTER);
        d_catalog_no.addActionListener(this);
                
        a(deleteBooks,d_back,20,20,150,40);
        a(deleteBooks,d_deleteRecord,55,660,228,64);
      
        a(deleteBooks,d_title,500,213,455,40);
        a(deleteBooks,d_author,570,294,387,40);
        a(deleteBooks,d_description,638,372,320,40);
        a(deleteBooks,d_isbn,486,448,472,40);
        a(deleteBooks,d_date_published,703,527,254,40);
        a(deleteBooks,d_no_of_copies,665,604,291,40);
        a(deleteBooks,d_catalog_no,70,270,205,50);
        a(deleteBooks,d_category,580,680,362,45);
        
        a(f,deleteBooks,0,0,1024,768);
        a(deleteBooks,deletebookspanelbg,0,0,1024,768);
    }   
    
    /** Lend Book **/
    JButton l_back,l_confirm;
    JComboBox<String> l_category;
    JComboBox<String> l_title;
    JTextField l_student_no,l_firstname,l_lastname,l_borrowersID,l_author,l_catalog_no,l_date_borrowed,l_return_date;  
    void lendBooks()
    {
        lendBooks = new JPanel();
        lendBooks.setLayout(null);
        
        l_back = new JButton(new ImageIcon(getClass().getResource("resources/Main/l_back (1).png")));
        l_back.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/l_back (2).png")));
        l_back.setFocusPainted(false);
        l_back.setBorderPainted(false);
        l_back.setOpaque(false);
        l_back.setContentAreaFilled(false);
        l_back.addActionListener(this);
        
        l_confirm  = new JButton(new ImageIcon(getClass().getResource("resources/Main/l_confirm (1).png")));
        l_confirm.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/l_confirm (2).png")));
        l_confirm.setBorderPainted(false);
        l_confirm.setOpaque(false);
        l_confirm.setContentAreaFilled(false);
        l_confirm.setFocusPainted(false);
        l_confirm.addActionListener(this);
        l_confirm.setEnabled(false);
         
        l_category = new JComboBox<String>();
        l_category.setOpaque(false);
        l_category.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l_category.setFont(font);
        l_category.addActionListener(this);  
        
        l_title = new JComboBox<String>();
        l_title.setOpaque(false);
        l_title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l_title.setFont(font);
        l_title.addActionListener(this);
        
        l_student_no = new JTextField();
        l_student_no.setOpaque(false);
        l_student_no.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l_student_no.setFont(font);
        l_student_no.setHorizontalAlignment(JTextField.CENTER);
        l_student_no.addActionListener(this);
        
        
        l_firstname = new JTextField();
        l_firstname.setOpaque(false);
        l_firstname.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l_firstname.setFont(font);
        l_firstname.setHorizontalAlignment(JTextField.CENTER);
        l_firstname.setEnabled(false);
        
        l_lastname = new JTextField();
        l_lastname.setOpaque(false);
        l_lastname.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l_lastname.setFont(font);
        l_lastname.setHorizontalAlignment(JTextField.CENTER);
        l_lastname.setEnabled(false);
        
        l_borrowersID = new JTextField();
        l_borrowersID.setOpaque(false);
        l_borrowersID.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l_borrowersID.setFont(font);
        l_borrowersID.setHorizontalAlignment(JTextField.CENTER);
        l_borrowersID.setEnabled(false);
        
        l_author = new JTextField();
        l_author.setOpaque(false);
        l_author.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l_author.setFont(font);
        l_author.setHorizontalAlignment(JTextField.CENTER);
        l_author.setEnabled(false);
        
        l_catalog_no = new JTextField();
        l_catalog_no.setOpaque(false);
        l_catalog_no.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l_catalog_no.setFont(font);
        l_catalog_no.setHorizontalAlignment(JTextField.CENTER);
        l_catalog_no.setEnabled(false);
        
        l_date_borrowed = new JTextField();
        l_date_borrowed.setOpaque(false);
        l_date_borrowed.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l_date_borrowed.setFont(font);
        l_date_borrowed.setHorizontalAlignment(JTextField.CENTER);
        l_date_borrowed.setEnabled(false);
        
        l_return_date = new JTextField();
        l_return_date.setOpaque(false);
        l_return_date.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        l_return_date.setFont(font);
        l_return_date.setHorizontalAlignment(JTextField.CENTER);
        l_return_date.setEnabled(false);
        
        a(lendBooks,l_confirm,65,670,228,64);
        a(lendBooks,l_back,20,20,150,40);
        
        a(lendBooks,l_student_no,60,260,250,40);
        a(lendBooks,l_firstname,55,427,250,30);
        a(lendBooks,l_lastname,55,515,250,30);
        a(lendBooks,l_borrowersID,55,615,250,40);
        
        a(lendBooks,l_category,650,218,300,40);
        a(lendBooks,l_title,650,295,300,40);
        a(lendBooks,l_author,650,370,300,40);
        a(lendBooks,l_catalog_no,650,447,300,40);
        
        a(lendBooks,l_date_borrowed,715,562,240,40);
        a(lendBooks,l_return_date,715,642,240,40);
        
        a(f,lendBooks,0,0,1024,768);
        a(lendBooks,lendbookspanelbg,0,0,1024,768);
    }
    
    /** Return Book **/
    JButton r_back,r_return;
    JTextField r_student_no,r_firstname,r_lastname,r_borrowersID,r_author,r_catalog_no,r_date_borrowed,r_return_date,r_penaltyfee,r_classification,r_title,r_date_returned;  
    void returnBooks()
    {
        returnBooks = new JPanel();
        returnBooks.setLayout(null);
        
        r_back = new JButton(new ImageIcon(getClass().getResource("resources/Main/r_back (1).png")));
        r_back.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/r_back (2).png")));
        r_back.setFocusPainted(false);
        r_back.setBorderPainted(false);
        r_back.setOpaque(false);
        r_back.setContentAreaFilled(false);
        r_back.addActionListener(this);
        
        r_return  = new JButton(new ImageIcon(getClass().getResource("resources/Main/r_return (1).png")));
        r_return.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/r_return (2).png")));
        r_return.setBorderPainted(false);
        r_return.setOpaque(false);
        r_return.setContentAreaFilled(false);
        r_return.setFocusPainted(false);
        r_return.addActionListener(this);
        r_return.setEnabled(false);
        
        r_borrowersID = new JTextField();
        r_borrowersID.setOpaque(false);
        r_borrowersID.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_borrowersID.setFont(font);
        r_borrowersID.setHorizontalAlignment(JTextField.CENTER);
        r_borrowersID.addActionListener(this);
        
        r_student_no = new JTextField();
        r_student_no.setOpaque(false);
        r_student_no.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_student_no.setFont(font);
        r_student_no.setHorizontalAlignment(JTextField.CENTER);
        r_student_no.setEnabled(false);
        
        r_firstname = new JTextField();
        r_firstname.setOpaque(false);
        r_firstname.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_firstname.setFont(font);
        r_firstname.setHorizontalAlignment(JTextField.CENTER);
        r_firstname.setEnabled(false);
        
        r_lastname = new JTextField();
        r_lastname.setOpaque(false);
        r_lastname.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_lastname.setFont(font);
        r_lastname.setHorizontalAlignment(JTextField.CENTER);
        r_lastname.setEnabled(false);
        
        r_catalog_no = new JTextField();
        r_catalog_no.setOpaque(false);
        r_catalog_no.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_catalog_no.setFont(font);
        r_catalog_no.setHorizontalAlignment(JTextField.CENTER);
        r_catalog_no.setEnabled(false);
        
        r_title = new JTextField();
        r_title.setOpaque(false);
        r_title.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_title.setFont(font);
        r_title.setHorizontalAlignment(JTextField.CENTER);
        r_title.setEnabled(false);
        
        r_author = new JTextField();
        r_author.setOpaque(false);
        r_author.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_author.setFont(font);
        r_author.setHorizontalAlignment(JTextField.CENTER);
        r_author.setEnabled(false);
        
        
        r_date_borrowed = new JTextField();
        r_date_borrowed.setOpaque(false);
        r_date_borrowed.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_date_borrowed.setFont(font);
        r_date_borrowed.setHorizontalAlignment(JTextField.CENTER);
        r_date_borrowed.setEnabled(false);
        
        r_return_date = new JTextField();
        r_return_date.setOpaque(false);
        r_return_date.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_return_date.setFont(font);
        r_return_date.setHorizontalAlignment(JTextField.CENTER);
        r_return_date.setEnabled(false);
        
        r_date_returned = new JTextField();
        r_date_returned.setOpaque(false);
        r_date_returned.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_date_returned.setFont(font);
        r_date_returned.setHorizontalAlignment(JTextField.CENTER);
        r_date_returned.setEnabled(false);
        
        r_penaltyfee = new JTextField();
        r_penaltyfee.setOpaque(false);
        r_penaltyfee.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        r_penaltyfee.setFont(font);
        r_penaltyfee.setHorizontalAlignment(JTextField.CENTER);
        r_penaltyfee.setEnabled(false);
               
        a(returnBooks,r_return,65,670,228,64);
        a(returnBooks,r_back,20,20,150,40);
        
        a(returnBooks,r_borrowersID,60,260,250,40);
        a(returnBooks,r_firstname,55,412,250,30);
        a(returnBooks,r_lastname,55,507,250,30);
        a(returnBooks,r_student_no,55,607,250,30);
        
        a(returnBooks,r_catalog_no,648,220,300,40);
        a(returnBooks,r_title,648,295,300,40);
        a(returnBooks,r_author,648,370,300,40);
        
        a(returnBooks,r_date_borrowed,710,450,240,40);
        a(returnBooks,r_return_date,710,525,240,40);
        a(returnBooks,r_date_returned,710,600,240,40);
        a(returnBooks,r_penaltyfee,710,678,240,40);
        
        a(f,returnBooks,0,0,1024,768);
        returnBooks.setBackground(Color.black);
        a(returnBooks,returnbookspanelbg,0,0,1024,768);
    }
    
    /** Admin Process **/
    
    private static final int BUFFER_SIZE = 4096;
    String fname,lname;
    BufferedImage image;
    InputStream inputStream = null;
    void AdminProcess()
    {
        try
        {
            con = ConnectionManager.getConnection();
            stmt = con.createStatement();
            String query = "SELECT * FROM librarians;";
            stmt.executeQuery(query);
            ResultSet rs1 = stmt.getResultSet();
            String username=auser.getText();
            String password=new String(apass.getPassword());
            while(rs1.next()){
                dbUsername = rs1.getString("username");
                dbPassword = rs1.getString("password");
                fname = rs1.getString("First_Name");
                lname = rs1.getString("Last_Name");
                if(dbUsername.equalsIgnoreCase(username) && dbPassword.equals(password)){
                    pinfo.setText("Login Success!"); 
                    String sql = "SELECT photo FROM librarians WHERE first_name=? AND last_name=?";
                    PreparedStatement statement = con.prepareStatement(sql);
                    statement.setString(1, fname);
                    statement.setString(2, lname);
                     
                    ResultSet result = statement.executeQuery();
                    if (result.next()) 
                    {
                        Blob blob = result.getBlob("photo");
                        inputStream = blob.getBinaryStream();
                       // int bytesRead = -1;
                        byte[] buffer = new byte[BUFFER_SIZE];
                        inputStream.close();
                    }
                    image = ImageIO.read(inputStream);
                    con.close();
                    adminpanel.setVisible(false);
                    cpanel();
                }
                else if(!dbUsername.equalsIgnoreCase(username) && !dbPassword.equals(password))
                {
                    SwingUtilities.invokeLater(new Runnable()
                    {
                        public void run()
                        {
                            pinfo.setText("Wrong Username or Password");
                        }});
                }
            }
        }catch(Exception se)
        {
        }
    }
    
    /** Add Category **/
    JButton ac_addCategory, ac_back;
    JTextField ac_Category, ac_Categoryid;
    
    void addCategory()
    {
        addCategory = new JPanel();
        addCategory.setLayout(null);
       
        ac_back = new JButton(new ImageIcon(getClass().getResource("resources/Main/ac_back (1).png")));
        ac_back.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/ac_back (2).png")));
        ac_back.setFocusPainted(false);
        ac_back.setBorderPainted(false);
        ac_back.setOpaque(false);
        ac_back.setContentAreaFilled(false);
        ac_back.addActionListener(this);
        
        ac_addCategory = new JButton(new ImageIcon(getClass().getResource("resources/Main/ac_addCategory (1).png")));
        ac_addCategory.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/ac_addCategory (2).png")));
        ac_addCategory.setFocusPainted(false);
        ac_addCategory.setBorderPainted(false);
        ac_addCategory.setOpaque(false);
        ac_addCategory.setContentAreaFilled(false);
        ac_addCategory.addActionListener(this);
        
        ac_Category = new JTextField();
        ac_Category.setOpaque(false);
        ac_Category.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ac_Category.setFont(font);
        ac_Category.setHorizontalAlignment(JTextField.CENTER);
        
        ac_Categoryid = new JTextField();
        ac_Categoryid.setOpaque(false);
        ac_Categoryid.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ac_Categoryid.setFont(font);
        ac_Categoryid.setHorizontalAlignment(JTextField.CENTER);
        ac_Categoryid.setEditable(false);
                
        a(addCategory,ac_back,20,20,150,40);
        a(addCategory,ac_addCategory,375,475,245,64);
        
        a(addCategory,ac_Category,375,265,235,40);
        a(addCategory,ac_Categoryid,380,405,230,40);
        
        a(f,addCategory,0,0,1024,768);
        a(addCategory,categorypanelbg,0,0,1024,768);
    }    
    
    /** Search Books **/
    JTable table;
    JTextField tsearch;
    JButton s_back, s_button;
    JTable searchtable;
    JScrollPane scrollPane;
    DefaultTableModel tableModel = new DefaultTableModel();
    
    void searchBooks()
    {
        table = new JTable(tableModel);
        table.setOpaque(false);
        table.setShowGrid(false);
        table.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setOpaque(false);
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(0,0,0,0.6f));
        table.getTableHeader().setForeground(Color.white);
        
        searchBooks = new JPanel();
        searchBooks.setLayout(null);
        
        JScrollPane sp = new JScrollPane(table);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        sp.setVisible(false);
        
        s_back = new JButton(new ImageIcon(getClass().getResource("resources/Main/d_back (1).png")));
        s_back.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/d_back (2).png")));
        s_back.setFocusPainted(false);
        s_back.setBorderPainted(false);
        s_back.setOpaque(false);
        s_back.setContentAreaFilled(false);
        s_back.addActionListener(this);
        
        
        tsearch = new JTextField();
        tsearch.setOpaque(false);
        tsearch.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        tsearch.getDocument().addDocumentListener(new DocumentListener()
        {
            public void changedUpdate(DocumentEvent arg0) 
            {
                sp.setVisible(true);
                loadData();
            }
            public void insertUpdate(DocumentEvent arg0) 
            {   
                sp.setVisible(true);
                loadData();
            }
            public void removeUpdate(DocumentEvent arg0) 
            {
                sp.setVisible(true);
                loadData();
            }
        });
        
        a(searchBooks,s_back,20,20,150,40);
        a(searchBooks,tsearch,440,205,260,30); 
        a(searchBooks,sp,50,300,922,420); 
        a(f,searchBooks,0,0,1024,768);
        a(searchBooks,searchpanelbg,0,0,1024,768);
    }
    
    private void loadData() {
        try 
        {
            Connection conn = ConnectionManager.getConnection();
            Statement stmt = conn.createStatement(); 
            String option = tsearch.getText();
            ResultSet rs = stmt.executeQuery("select catalog_no, title, author, no_of_copies from books  WHERE title like '%"+option+"%' or Author like'%"+option+"%' or No_of_copies like'%"+option+"%'" );
            ResultSetMetaData metaData = rs.getMetaData();

            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }

            tableModel.setDataVector(data, columnNames);
        } catch (Exception e) 
        {
        }
    }
    
    /** Search Borrowers **/
    
    JTable btable;
    JTextField btsearch;
    JButton bs_back, bs_button;
    JTable bsearchtable;
    JScrollPane bscrollPane;
    DefaultTableModel btableModel = new DefaultTableModel();
    
    void bsearchBooks()
    {
        btable = new JTable(btableModel);
        btable.setOpaque(false);
        btable.setShowGrid(false);
        btable.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ((DefaultTableCellRenderer)btable.getDefaultRenderer(Object.class)).setOpaque(false);
        btable.getTableHeader().setOpaque(false);
        btable.getTableHeader().setBackground(new Color(0,0,0,0.6f));
        btable.getTableHeader().setForeground(Color.white);
        
        bsearchBooks = new JPanel();
        bsearchBooks.setLayout(null);
        
        JScrollPane bsp = new JScrollPane(btable);
        bsp.setOpaque(false);
        bsp.getViewport().setOpaque(false);
        bsp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        bs_back = new JButton(new ImageIcon(getClass().getResource("resources/Main/d_back (1).png")));
        bs_back.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/d_back (2).png")));
        bs_back.setFocusPainted(false);
        bs_back.setBorderPainted(false);
        bs_back.setOpaque(false);
        bs_back.setContentAreaFilled(false);
        bs_back.addActionListener(this);
        
        
        btsearch = new JTextField("BORROWERS");
        btsearch.setHorizontalAlignment(JTextField.CENTER);
        btsearch.setOpaque(false);
        btsearch.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btsearch.setEditable(false);
        btsearch.setFont(font);
        loadDatab();
        
        a(bsearchBooks,bs_back,20,20,150,40);
        a(bsearchBooks,btsearch,440,203,260,30); 
        a(bsearchBooks,bsp,50,300,922,420); 
        a(f,bsearchBooks,0,0,1024,768);
        a(bsearchBooks,bsearchpanelbg,0,0,1024,768);
    }
    
    private void loadDatab() {
        try 
        {
            Connection conn = ConnectionManager.getConnection();
            Statement stmt = conn.createStatement(); 
            String option = btsearch.getText();
            ResultSet rs = stmt.executeQuery("select BorrowersId as `Borrowers ID`, borrowers.Catalog_no as `Catalog No` ,First_Name as `First Name`, last_name as `Last Name`, Title, Date_Borrowed as `Date Borrowed` from borrowers, student, books where Date_Returned is null and borrowers.Student_no = student.Student_no and borrowers.Catalog_no = books.Catalog_no;");
            ResultSetMetaData metaData = rs.getMetaData();
            Vector<String> columnNames = new Vector<String>();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }
            Vector<Vector<Object>> data = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> vector = new Vector<Object>();
                for (int i = 1; i <= columnCount; i++) {
                    vector.add(rs.getObject(i));
                }
                data.add(vector);
            }
            btableModel.setDataVector(data, columnNames);
        } catch (Exception e) 
        {
        }
    }
    
    /** CPANEL Buttons **/
    
    JButton update, search , lend, returnb, Logout;
        
    void cpanel()
    {
        cpanel = new JPanel();
        cpanel.setLayout(null);
        
        update = new JButton(new ImageIcon(getClass().getResource("resources/Main/update1.png")));
        update.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/update2.png")));
        update.setFocusPainted(false);
        update.setBorderPainted(false);
        update.setOpaque(false);
        update.setContentAreaFilled(false);
        update.addActionListener(this);
        
        search = new JButton(new ImageIcon(getClass().getResource("resources/Main/search (1).png")));
        search.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/search (2).png")));
        search.setBorderPainted(false);
        search.setOpaque(false);
        search.setContentAreaFilled(false);
        search.setFocusPainted(false);
        search.addActionListener(this);
     
        lend = new JButton(new ImageIcon(getClass().getResource("resources/Main/lend (1).png")));
        lend.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/lend (2).png")));
        lend.setBorderPainted(false);
        lend.setOpaque(false);
        lend.setContentAreaFilled(false);
        lend.setFocusPainted(false);
        lend.addActionListener(this);
        
        returnb = new JButton(new ImageIcon(getClass().getResource("resources/Main/return (1).png")));
        returnb.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/return (2).png")));
        returnb.setBorderPainted(false);
        returnb.setOpaque(false);
        returnb.setContentAreaFilled(false);
        returnb.setFocusPainted(false);
        returnb.addActionListener(this);

        Logout = new JButton(new ImageIcon(getClass().getResource("resources/Main/logout (1).png")));
        Logout.setRolloverIcon(new ImageIcon(getClass().getResource("resources/Main/logout (2).png")));
        Logout.setBorderPainted(false);
        Logout.setOpaque(false);
        Logout.setContentAreaFilled(false);
        Logout.setFocusPainted(false);
        Logout.addActionListener(this);
        
        JLabel firstname = new JLabel(fname + " " + lname);
        firstname.setFont(font);
        firstname.setHorizontalAlignment(JTextField.CENTER);
        JLabel photo = new JLabel(new ImageIcon(image));
        
        a(cpanel,firstname,725,325,230,30);
        a(cpanel,photo,790,220,100,100);
        
        a(cpanel,update,732,360,214,64);
        a(cpanel,search,732,420,214,64);
        a(cpanel,lend,732,480,214,64);
        a(cpanel,returnb,732,540,214,64);
        a(cpanel,Logout,732,600,214,64);

        a(f,cpanel,0,0,1024,768);
        a(cpanel,cpanelbg,0,0,1024,768);
    }
    /** Category Process **/
    void categoryprocess()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    con = ConnectionManager.getConnection();
                    stmt = con.createStatement();
                    String item = (String)l_category.getSelectedItem();
                    ResultSet r = stmt.executeQuery("select ClassificationID as classification from book_classification where Classifications='"+item+"'");
                    r.next();
                    classification= r.getString("classification");
                    r = stmt.executeQuery("Select Title from books where (ClassificationID='"+classification+"' AND No_of_Copies>0)");
                    r.next();
                     while (r.next()) 
                    {  
                        l_title.addItem(r.getString("Title"));  
                    }
                }catch(SQLException E)
                {
                    J.showMessageDialog(null,"Error category", "Warning", J.WARNING_MESSAGE);
                }
            }});
    }
    /** Title Process **/
     void titleprocess()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    java.util.Date currentDate = new java.util.Date();
                    java.sql.Date sql_date1 = new java.sql.Date(currentDate.getTime());
                    Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE,2);
                    currentDate = cal.getTime();
                    java.sql.Date sql_date2 = new java.sql.Date(currentDate.getTime());
                    con = ConnectionManager.getConnection();
                    stmt = con.createStatement();
                    String item = (String)l_title.getSelectedItem();
                    ResultSet r = stmt.executeQuery("select Author,Catalog_no from books where Title='"+item+"'");
                    r.next();
                    l_author.setText(r.getString("Author"));
                    l_catalog_no.setText(r.getString("Catalog_no"));
                    l_date_borrowed.setText(""+sql_date1);
                    l_return_date.setText(""+sql_date2);
                    stmt.close();
                    con.close();
                }catch(SQLException E)
                {
                }
            }});
    }
    
    int count;
    String classification;
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==log)
        {
            AdminProcess();
        }
        
        if(e.getSource()==auser)
        {
            AdminProcess();
        }
        
        if(e.getSource()==apass)
        {
            AdminProcess();
        }
        
        if(e.getSource()==update)
        {
            cpanel.setVisible(false);
            updateBook();
        }
        
        if(e.getSource()==addbooks)
        {
            updatepanel.setVisible(false);
            try{
                addBooks();
                 con = ConnectionManager.getConnection();
                stmt = con.createStatement();
                 ResultSet r = stmt.executeQuery("select max(number) from books");
                 r.next();
                 count = r.getInt("max(number)")+1;
                 stmt.close();
                 r.close();
                 catalog_no.setText("CN"+count);
            }catch(SQLException Ee)
            {
                J.showMessageDialog(null,"Check SQL Connection!", "Warning", J.WARNING_MESSAGE);
            }
           
        }
        
        if(e.getSource()==addcategory)
        {
            String tot=null;
            updatepanel.setVisible(false);
            try
            {
                addCategory();
                con = ConnectionManager.getConnection();
                stmt = con.createStatement();
                ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM book_classification");
                r.next();
                count = r.getInt("rowcount")+ 1 ;
                r.close();
                ac_Categoryid.setText("CD"+count);
            }catch(SQLException Ee)
            {
                J.showMessageDialog(null,"Check SQL Connection! addcategory", "Warning", J.WARNING_MESSAGE);
            }
        }

        if(e.getSource()==deletebooks)
        {
            updatepanel.setVisible(false);
            deleteBooks();
        }
        
        if(e.getSource()==editbooks)
        {
            updatepanel.setVisible(false);
            editBooks();
        }

        if(e.getSource()==r_back)
        {
            cpanel.setVisible(true);
            returnBooks.setVisible(false);
        }
        
        if(e.getSource()==search)
        {
            cpanel.setVisible(false);
            searchOption();
        }
        
        if(e.getSource()==sbook)
        {
            searchOption.setVisible(false);
            searchBooks();
        }
        
        if(e.getSource()==sback)
        {
            searchOption.setVisible(false);
            cpanel();
        }
        
        if(e.getSource()==sborrower)
        {
            searchOption.setVisible(false);
            bsearchBooks();
        }
        
        if(e.getSource()==s_back)
        {
            searchOption.setVisible(true);
            searchBooks.setVisible(false);
        }
        
        if(e.getSource()==bs_back)
        {
            searchOption.setVisible(true);
            bsearchBooks.setVisible(false);
        }
        
        if(e.getSource()==addback)
        {
            updatepanel.setVisible(false);
            cpanel.setVisible(true);
        }
        
        if(e.getSource()==lend)
        {
            cpanel.setVisible(false);
            lendBooks();
        }

        if(e.getSource()==returnb)
        {
            cpanel.setVisible(false);
            returnBooks();
        }
        
        if(e.getSource()==Logout)
        {
            cpanel.setVisible(false);
            AdminPanel();
            try
            {
                con.close();
                stmt.close();
            }catch(Exception ee)
            {
            }
        }
        
        /** Add Books Buttons **/
        
        if(e.getSource()==back)
        {
            addBooks.setVisible(false);
            updatepanel.setVisible(true);
        }
        
        if(e.getSource()==submit)
        {
           try
           {
               con = ConnectionManager.getConnection();
               stmt = con.createStatement();
               ResultSet r = stmt.executeQuery("select ClassificationID as classification from book_classification where Classifications='"+category.getSelectedItem()+"'");
               r.next();
               classification= r.getString("classification");
               String query = "insert into books (Catalog_no,ClassificationID,ISBN,Title,Description,Author,Date_Published,No_of_Copies,number) values('"+catalog_no.getText()+
               "','"+classification+"','"+isbn.getText()+"','"+title.getText()+"','"+description.getText()+"','"+author.getText()+"','"+date_published.getText()+"','"+
               no_of_copies.getText()+"','"+count+"');";
               stmt.executeUpdate(query);
               con.close();
               stmt.close();
                J.showMessageDialog(null,"Book Added Successfully!","Success!!",JOptionPane.INFORMATION_MESSAGE);
               updatepanel.setVisible(true);
               addBooks.setVisible(false);
            }catch(SQLException E)
            {
                J.showMessageDialog(null,"Invalid Input!","Error!!",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(e.getSource()==clear)
        {
            title.setText("");
            author.setText("");
            description.setText("");
            isbn.setText("");
            date_published.setText("");
            no_of_copies.setText("");
        }
        
        /**Delete Books Buttons **/
        
        if(e.getSource()==d_back)
        {
            updatepanel.setVisible(true);
            deleteBooks.setVisible(false);
        }
        
        if(e.getSource()==d_catalog_no)
        {
            try
            {
                con = ConnectionManager.getConnection();
                stmt = con.createStatement();
                ResultSet r = stmt.executeQuery("Select * from books where Catalog_no='"+d_catalog_no.getText()+"'");
                r.next();
                d_title.setText(r.getString("Title"));
                d_author.setText(r.getString("Author"));
                d_description.setText(r.getString("Description"));
                d_isbn.setText(r.getString("ISBN"));
                d_date_published.setText(r.getString("Date_Published"));
                d_no_of_copies.setText(r.getString("No_of_Copies"));
                classification = r.getString("ClassificationID");
                r = stmt.executeQuery("Select Classifications from book_classification where ClassificationID='"+classification+"'");
                r.next();
                d_category.setText(r.getString("Classifications"));
                con.close();
                stmt.close();
            }catch(SQLException E)
            {
                J.showMessageDialog(null,"Check SQL Connection! dcatalog", "Warning", J.WARNING_MESSAGE);
            }
        }
        
        if(e.getSource()==d_deleteRecord)
        {
           try
            {
                con = ConnectionManager.getConnection();
                stmt = con.createStatement();
                ResultSet r =stmt.executeQuery("Select Date_Returned from borrowers where Catalog_no ='"+d_catalog_no.getText()+"'");
                String restrictions="";
                while(r.next())
                {    
                    restrictions=r.getString("Date_Returned")+"a";
                    if(restrictions.equals("nulla"))
                    {
                        break;
                    }
                }
                
                if(restrictions.equals("nulla"))
                {
                    JOptionPane.showMessageDialog(null,"Books cannot be deleted \n\r book is still on lend","Error!",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                  String query = "Delete from books where Catalog_no='"+d_catalog_no.getText()+"'";
                  stmt.executeUpdate(query);
                  con.close();
                  stmt.close();
                  J.showMessageDialog(null,"Book Records Delited Successfully!","Success!!",JOptionPane.INFORMATION_MESSAGE);
                  updatepanel.setVisible(true);
                  deleteBooks.setVisible(false);
                }
            }catch(SQLException E)
            {
                E.printStackTrace();
            }
        }
        
        /**  Edit Buttons **/
        
        if(e.getSource()==e_back)
        {
            updatepanel.setVisible(true);
            editBooks.setVisible(false);
        }
            
        if(e.getSource()==e_catalog_no)
        {
            try
            {
                con = ConnectionManager.getConnection();
                stmt = con.createStatement();
               
                ResultSet r = stmt.executeQuery("Select * from books where Catalog_no='"+e_catalog_no.getText()+"'");
                r.next();
                e_title.setText(r.getString("Title"));
                e_author.setText(r.getString("Author"));
                e_description.setText(r.getString("Description"));
                e_isbn.setText(r.getString("ISBN"));
                e_date_published.setText(r.getString("Date_Published"));
                e_no_of_copies.setText(r.getString("No_of_Copies"));
                classification = r.getString("ClassificationID");
                String query = "SELECT * FROM library.book_classification;";
                r=stmt.executeQuery(query);
                while (r.next()) 
                {  
                    e_category.addItem(r.getString("classifications"));  
                }
                con.close();
                stmt.close();
            }catch(SQLException E)
            {
                J.showMessageDialog(null,"Catalog Number not found!", "Warning", J.WARNING_MESSAGE);
            }
        }
        
        if(e.getSource()==e_editRecord)
        { 
            try
            {
                con = ConnectionManager.getConnection();
                stmt = con.createStatement();
                ResultSet r = stmt.executeQuery("Select ClassificationID from book_classification where Classifications='"+e_category.getSelectedItem()+"'");
                r.next();
                classification=r.getString("ClassificationID");
                String query = "Update books set ClassificationID='"+classification+"',ISBN='"+e_isbn.getText()+"',Title='"+e_title.getText()+
                "',Description='"+e_description.getText()+"',Author='"+e_author.getText()+"',Date_Published='"+e_date_published.getText()+
                "',No_of_Copies='"+e_no_of_copies.getText()+"' where Catalog_no ='"+e_catalog_no.getText()+"'";
                stmt.executeUpdate(query);
                 J.showMessageDialog(null,"Book Records Edited Successfully!","Success!!",JOptionPane.INFORMATION_MESSAGE);
                updatepanel.setVisible(true);
                editBooks.setVisible(false);
                con.close();
                stmt.close();
            }catch(SQLException E)
            {
                J.showMessageDialog(null, "Input correct values first!", "Error!", J.WARNING_MESSAGE);
            }
        }
        
        /** Add Category Buttons **/
        
        if(e.getSource()==ac_back)
        {
            updatepanel.setVisible(true);
            addCategory.setVisible(false);
        }
        
        if(e.getSource()==ac_addCategory)
        {
            try
            {
                con = ConnectionManager.getConnection();
                stmt = con.createStatement();
                String category=ac_Category.getText();
                String categoryid=ac_Categoryid.getText();
                String query = "insert into book_classification (`ClassificationID`,`Classifications`,`Location`) values ("+"'"+categoryid+"','"+category+"'"+",'Section "+count+"');";
                stmt.executeUpdate(query);
                 J.showMessageDialog(null,"Book Category Added Successfully!","Success!!",JOptionPane.INFORMATION_MESSAGE);
                addCategory.setVisible(false);
                updatepanel.setVisible(true);
            }catch(SQLException E)
            {
                J.showMessageDialog(null,"Check SQL Connection!", "Warning", J.WARNING_MESSAGE);
            }
        }
        
        /**Lend Books Buttons **/
        
        if(e.getSource()==l_back)
        {
            cpanel.setVisible(true);
            lendBooks.setVisible(false);
        }
        
        if(e.getSource()==l_student_no)
        {
            String checker = l_student_no.getText();
            
            int stdnum = 0;
            try{
                stdnum = Integer.parseInt(checker);
            }catch(NumberFormatException ee){}
            if(stdnum>=1000000 && stdnum<=9999999)
            {
                try
                {
                    con = ConnectionManager.getConnection();
                    stmt = con.createStatement();
                    ResultSet r =stmt.executeQuery("Select Date_Returned from borrowers where Student_no ='"+l_student_no.getText()+"'");
                    String restrictions="";
                    if(l_student_no.getText().equals(null))
                    {
                        JOptionPane.showMessageDialog(null,"Student Number not Found","Student Number Error!",JOptionPane.ERROR_MESSAGE);
                    }
                   
                    while(r.next())
                    {    
                        restrictions=r.getString("Date_Returned")+"a";
                        if(restrictions.equals("nulla"))
                        {
                            break;
                        }
                    }
                    
                    if(restrictions.equals("nulla"))
                    {
                        JOptionPane.showMessageDialog(null,"You Have Existing Transaction \n\r You are temporarily not allowed to borrow books","Error!",JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        l_confirm.setEnabled(true);
                        r = stmt.executeQuery("Select First_Name,Last_Name from student where Student_no='"+l_student_no.getText()+"'");
                        r.next();
                        l_firstname.setText(r.getString("First_Name"));
                        l_lastname.setText(r.getString("Last_Name"));
                        r = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM borrowers");
                        r.next();
                        count = r.getInt("rowcount")+ 1 ;
                        l_borrowersID.setText("BR"+count);
                        String query = "SELECT * FROM library.book_classification;";
                        r= stmt.executeQuery(query);
                    }
                    while (r.next()) 
                    {  
                        l_category.addItem(r.getString("classifications"));  
                    }
                }catch(SQLException E)
                {
                }
            }else J.showMessageDialog(null,"Invalid Student Number!", "Warning", J.WARNING_MESSAGE);
        }
        
        if(e.getSource()==l_category)
        {  
            l_title.removeAllItems();
            categoryprocess();
        }
        
        if(e.getSource()==l_title)
        {
            titleprocess();
        }
        
        if(e.getSource()==l_confirm)
        {
            
            
            try
            {
                con = ConnectionManager.getConnection();
                stmt = con.createStatement();
                ResultSet r = stmt.executeQuery("Select ClassificationID from book_classification where Classifications='"+l_category.getSelectedItem()+"'");
                r.next();
                classification=r.getString("ClassificationID");
                String query = "insert into borrowers (BorrowersId,Student_no,Catalog_no,Date_Borrowed,Return_date) values ("
                +"'"+l_borrowersID.getText()+"','"+l_student_no.getText()+"','"+l_catalog_no.getText()+"','"+l_date_borrowed.getText()+"','"+l_return_date.getText()+"')";
                stmt.executeUpdate(query);
                stmt.executeUpdate("update books set No_of_Copies=No_of_Copies-1 where Catalog_no ='"+l_catalog_no.getText()+"'");
                 J.showMessageDialog(null,"Lend Transaction Successful!","Success!!",JOptionPane.INFORMATION_MESSAGE);
                cpanel.setVisible(true);
                lendBooks.setVisible(false);
                stmt.close();
                con.close();
            }catch(SQLException E)
            {
                 J.showMessageDialog(null,"Check SQL Connection!", "Warning", J.WARNING_MESSAGE);
            }
            
        }
        /**Return Books Buttons **/
        
        if(e.getSource()==r_back)
        {
            cpanel.setVisible(true);
            returnBooks.setVisible(false);
        }
        
        if(e.getSource()==r_borrowersID)
        {
            try
            {
                con = ConnectionManager.getConnection();
                stmt = con.createStatement();
                String dr="", p="";
                ResultSet r = stmt.executeQuery("Select Date_Returned,Penalty from borrowers where BorrowersId='"+r_borrowersID.getText()+"'");
                r.next();
                dr= r.getString("Date_Returned")+"a";
                p = r.getString("Penalty")+"a";
                if(dr.equals("nulla")&p.equals("nulla"))
                {
                    r_return.setEnabled(true);
                    java.util.Date currentDate = new java.util.Date();
                    java.sql.Date sql_date1 = new java.sql.Date(currentDate.getTime());
                    java.sql.Date sql_date2;
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DATE, 2);
                    r = stmt.executeQuery("Select * from borrowers where BorrowersId='"+r_borrowersID.getText()+"'");
                    r.next();
                    String sn = r.getString("Student_no");
                    String cn = r.getString("Catalog_no");
                    r_student_no.setText(sn);
                    r_catalog_no.setText(cn);
                    sql_date2 = new java.sql.Date(r.getDate("Return_date").getTime());
                    r_date_borrowed.setText(r.getString("Date_Borrowed"));
                    r= stmt.executeQuery("Select First_Name,Last_Name from student where Student_no='"+sn+"'");
                    r.next();
                    r_firstname.setText(r.getString("First_Name"));
                    r_lastname.setText(r.getString("Last_Name"));
                    r= stmt.executeQuery("Select Title,Author from books where Catalog_no='"+cn+"'");
                    r.next();
                    r_title.setText(r.getString("Title"));
                    r_author.setText(r.getString("Author"));
                    r_return_date.setText(""+sql_date2);
                    r_date_returned.setText(""+sql_date1);  
                    long c = ((sql_date1.getTime() - sql_date2.getTime())/(60*60*1000))-24;
                    if(c<0)
                    {
                        r_penaltyfee.setText("0");
                    }
                    else
                    {
                        r_penaltyfee.setText(""+c);
                    }
                    stmt.close();
                    con.close();
                }
                else
                {
                   JOptionPane.showMessageDialog(null,"Please Enter an Active BorrowersID","Transaction Error!",JOptionPane.ERROR_MESSAGE);
                }
            }catch(SQLException E)
            {
                JOptionPane.showMessageDialog(null,"Invalid BorrowersID","Error!",JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if(e.getSource()==r_return)
        {
               try
               {
                   con = ConnectionManager.getConnection();
                   stmt = con.createStatement();
                   stmt.executeUpdate("update borrowers set Date_Returned='"+r_date_returned.getText()+"' ,Penalty='"+r_penaltyfee.getText()+
                   "' where BorrowersId='"+r_borrowersID.getText()+"'");
                   stmt.executeUpdate("update books set No_of_Copies=No_of_Copies+1 where Catalog_no ='"+r_catalog_no.getText()+"'");
                   stmt.close();
                   con.close();
                   J.showMessageDialog(null,"Return Transaction Successful!","Success!!",JOptionPane.INFORMATION_MESSAGE);
                   cpanel.setVisible(true);
                   returnBooks.setVisible(false);
               }catch(SQLException E)
               {
                   J.showMessageDialog(null,"Check SQL Connection! r_return", "Warning", J.WARNING_MESSAGE);
               }
        }
    }
    
    public void a(Container con,Component  c,int x,int y,int w, int h)
    {
        c.setBounds(x,y,w,h);
        con.add(c);
    }
}
