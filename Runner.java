import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.ActionListener;
public class Runner extends JWindow
{
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private  int count;
    private  Timer timer1;
    Runner() 
    {
        JWindow splash = new JWindow();
        splash.setSize(800,600);
        splash.setLayout(null);
        splash.setLocation(screen.width/2,screen.height/2);
        splash.setLocationRelativeTo(null);
        JLabel Loading = new JLabel(new ImageIcon(getClass().getResource("/resources/Splash/splash screen.png")));
        JProgressBar load = new JProgressBar();
        load.setStringPainted(true);
        load.setForeground(Color.DARK_GRAY);
        add(splash,load,156,447,496,31);
        add(splash,Loading,0,0,800,600);
         splash.setVisible(true);
        try 
        { 
             ActionListener al = new ActionListener() 
             {
                 public void actionPerformed(java.awt.event.ActionEvent evt) {
                     count++;
                     load.setValue(count);
                     if (count == 120) {
                         try {
                             splash.dispose();
                             timer1.stop();
                             new Main();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                };
                timer1 = new Timer(20, al); 
                timer1.start();
        }catch(Exception e) 
        {
        }
    }
    
    public void add(Container con,Component  c,int x,int y,int w, int h)
    {
        c.setBounds(x,y,w,h);
        con.add(c);
    }
    
     public static void main(String[] args) throws Exception
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new Runner();
    }
}