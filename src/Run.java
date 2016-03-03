import javax.swing.*;

public class Run
{
   public static void main(String[] args)
   {
      try
      {
         UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
      }
      catch (Exception ex){}
      
      new FrmMain().setVisible(true);
   }
}
