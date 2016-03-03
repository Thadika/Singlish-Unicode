import javax.swing.*;
import java.awt.event.*;   //events තිබෙන්නේ මේ package එකේ ය.
import java.awt.*;         //Swing Containers " " "

class FrmMain extends JFrame
{
   Translator myObj = new Singlish();   
   
   FrmMain()
   {
      setTitle("Singlish Unicode");
      setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      setMinimumSize(new Dimension(500,400));
      
      //================= JPanel සෑදීම =================
      JPanel pnlWrap = new JPanel();
      JPanel pnlTop = new JPanel();
      JPanel pnlMid = new JPanel();
      JPanel pnlDwn = new JPanel();
      
      pnlWrap.setLayout(new BoxLayout(pnlWrap,BoxLayout.Y_AXIS));
      pnlTop.setLayout(new FlowLayout(FlowLayout.LEFT));
      pnlMid.setLayout(new BoxLayout(pnlMid,BoxLayout.Y_AXIS));
      pnlDwn.setLayout(new FlowLayout(FlowLayout.LEFT));
      
      //pnlWrap.setBorder(BorderFactory.createLineBorder(Color.blue));
      
      //================= Swing Controls සෑදීම =================
      JTextArea txtIn = new JTextArea();
      JTextArea txtOut = new JTextArea();
      
      JScrollPane scrlIn = new JScrollPane(txtIn);
      JScrollPane scrlOut = new JScrollPane(txtOut);
      
      JButton btnConvrt = new JButton("Convert");
      JButton btnClear = new JButton("Clear");
      
      JButton btnCut = new JButton("Cut");
      JButton btnCopy = new JButton("Copy");
      JButton btnPaste = new JButton("Paste");
      
      txtIn.setRows(7);
      txtOut.setRows(7);
      
      txtOut.setEditable(false);
      txtOut.setFont(new Font("Iskoola Pota", 0, 16));
      
      //Menu Bar
      JMenuBar mnuBr = new JMenuBar();
      JMenu mnuFile = new JMenu("File");
      JMenu mnuEdit = new JMenu("Edit");
      JMenu mnuHelp = new JMenu("Help");
      
      JMenuItem mnItmExit = new JMenuItem("Exit");
      JMenuItem mnItmCopy = new JMenuItem("Copy");
      JMenuItem mnItmPaste = new JMenuItem("Paste");
      JMenuItem mnItmAbout = new JMenuItem("About Singlish");
      
      mnuFile.add(mnItmExit);
      mnuEdit.add(mnItmCopy);
      mnuEdit.add(mnItmPaste);
      mnuHelp.add(mnItmAbout);
      
      mnuBr.add(mnuFile);
      //mnuBr.add(mnuEdit);
      mnuBr.add(mnuHelp);
            
      //Radio button වල panel එක
      JPanel pnlCnvrt = new JPanel();
      pnlCnvrt.setBorder(BorderFactory.createTitledBorder("Convert To"));
      
      ButtonGroup grpConv = new ButtonGroup();
      
      JRadioButton rdiUni = new JRadioButton("Unicode");
      JRadioButton rdiBit = new JRadioButton("Bitmap");
      
      grpConv.add(rdiUni);
      grpConv.add(rdiBit);
      
      pnlCnvrt.add(rdiUni);
      pnlCnvrt.add(rdiBit);
      rdiUni.setSelected(true);
      //Radio button සෑදීම අවසාන යි.
      
            
      //================= Panel වලට components add කිරීම =================
      pnlTop.add(pnlCnvrt);
      
      pnlMid.add(scrlIn);
      pnlMid.add(new JLabel(" "));
      pnlMid.add(scrlOut);
           
      pnlDwn.add(btnConvrt);
      pnlDwn.add(btnClear);
      
      //Main Panel එකට components add කිරීම
      pnlWrap.add(pnlTop);
      pnlWrap.add(pnlMid);
      pnlWrap.add(pnlDwn);
      
      //================= Main Panel එක form එකට add කිරීම =================
      this.add(mnuBr,BorderLayout.NORTH);//වෙනම panel එකකට වඩා හොඳයි.
      this.add(pnlWrap,BorderLayout.CENTER);
      
      
      //Event සඳහා Action Listeners සෑදීම
      /*
         Adapter classes කියන්නේ Listner නම් interfaces වල
       ඇති methods සියල්ල ම implement කරන ලද class වලට යි.
       ඒ නිසා අපට අවශ්‍ය method එක විතරක් override කරලා
       භාවිතා කළ හැකියි.
       නැත්නම් interface එකේ තියෙන ඔක්කොම methods implement
       කරන්න වෙනවා. 
        (Eg: KeyListner හි ඇති keyUp, keyDown, keyReleased යන 3ම.)
      */
      txtIn.addKeyListener(new KeyAdapter()
      {
         public void keyReleased(KeyEvent e)
         {
            txtOut.setText(myObj.startConvert(txtIn.getText()));
            //txtOut.setText(myObj.convertTest(txtIn.getText()));
         }
      });
      
      btnClear.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            txtIn.setText(null);
            txtOut.setText(null);
         }
      });
      
      rdiUni.addItemListener(new ItemListener()
      {
         public void itemStateChanged(ItemEvent e)
         {
            myObj = null;
            //txtIn.setText(null);
            
            if(rdiUni.isSelected()==false)
            {
               myObj=new Bitmap();
               txtOut.setFont(new Font("FMAbhaya", 0, 18));
               txtIn.setFont(new Font("FMAbhaya", 0, 18));
            }else
            {
               myObj=new Singlish();
               //txtOut.setFont(null);
               txtOut.setFont(new Font("Iskoola Pota", 0, 16));
               txtIn.setFont(new Font("Iskoola Pota", 0, 16));
            }
         }
      });
   }
}
