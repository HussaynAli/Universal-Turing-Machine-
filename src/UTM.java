
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Temp
 */
public class UTM extends javax.swing.JFrame {
     private static String [][]simulate;
            //private static Vector<Vector<String>> simulate=new Vector<Vector<String>>();
            public  static int State;
            private static int n;
            private static String blank="";
             private String original="";
             private String start="";
            private static String Input="";
            private static String Symbol="";
            private static String current;
            private static String stop="";
            private  int iter=1;
            private static String output;
            private static BufferedReader cinn = null;
            private  int count;
            private boolean mutex=true;
             boolean Step=false;
              boolean Run=false;
              boolean end;
              boolean status=true;
              StringBuilder cout=new StringBuilder();
    /**
     * Creates new form UTM
     */
              public  void Animate(String tmp[])
            {
                        
                    /*for(int p=0;p<tmp.length;p++)
                        {
                                    if(p==iter)
                                    {// SHOWING HEAD ANIMATION HERE
                                        cout.append('|'+"("+""+tmp[p]+""+")");
                                        display.setText(cout.toString());
                                        cout.delete(0, cout.length());
                                    }
                                    else{
                                       cout.append('|'+""+tmp[p]+"");
                                       display.setText(cout.toString());
                                       cout.delete(0, cout.length());
                                    }
                        }
                        System.out.println('|');*/
                display.setText(""+(tmp.toString()));
            }
                public static String implode(String[] ary, String delim) 
            {// FUCNTION FOR REMOVING ',' AND '(' ')'
                        String out ="";

                        for(int i=0; i<ary.length; i++) {

                            if(i!=0) { out += delim; }
                            out += ary[i];
                    }
                        return out;
            }
    public boolean Step_simulate()
    {
        output="";
        boolean end=false;
        int k=0;
        String temp="";
        String tmp[]=Input.split("");
            for(k=0;k < n && iter < tmp.length ;k++)
            {
                count++;
                // IF CURRRENT STATE AND SYMBOL OF INPUT MATCHES THEN A VALID TRANSITION 
                if(simulate[k][0].equalsIgnoreCase(current) && simulate[k][1].equalsIgnoreCase(tmp[iter]))
                {
                     stepcount.setText(String.valueOf(count));
                    readstate.setText(current);
                    writesymbol.setText(simulate[k][3]);
                    if(simulate[k][1].equals(blank))
                    {
                            Input=implode(tmp,"");
                            Input+=blank;
                            Input=blank+Input;
                            tmp=Input.split("");
                            iter++;
                    }
                    for(int p=0;p<tmp.length;p++)
                   {
                       if(p==iter){// SHOWING HEAD ANIMATION HERE
                           temp+="(";
                           temp+=tmp[p];
                           temp+=")";
                       }                          
                       else
                           temp+=tmp[p];                        
                   }
                    display.setText(temp);
                    temp="";
                    System.out.print("=>");
                   
                    end=false;
                    tmp[iter]=simulate[k][3];
                    if(simulate[k][4].equalsIgnoreCase("R") || simulate[k][4].equalsIgnoreCase("right"))
                      iter++;// MOVING ONE CHAR RIGHT IN INPUT STRING

                    else if(simulate[k][4].equalsIgnoreCase("L") || simulate[k][4].equalsIgnoreCase("left"))
                            iter--; // MOVING ONE CHAR LEFT IN INPUT STRING
                      //ELSE STAYS                
                         current = simulate[k][2];//CURRENT STATE UPDATE
                         Iter.setText(simulate[k][4]);
                         Current.setText(current);
                         System.out.println('('+implode(simulate[k],",")+") = "+ implode(tmp,""));    
                         Input=implode(tmp,"");
                         break;	                 
                }
            }
            //HALTING CONDITION IF STRING ACCPTED
            if(current.equalsIgnoreCase("STOP"))// IF CURRENT STATE IS STOP
            {
                System.out.println("String Accepted Machine Halted");
                output=implode(tmp,"");
                end=true;
            }
            else if(end == true)//IF FOR A CHAR IN INPUT NO TRANSITION FUNCTION FOUND IN THE TABLE THEN STRING REJECTED
            {
                System.out.println("String rejected Machine Trapped");
            }
            
        return true;
    }
    
public  String Simulate() throws InterruptedException // FUNCTION FOR SIMULATING THE LOADED TURING MACHINE
    {
       // stepcount.setText("jnfjnfj");
        output="";

        boolean end=false;
        int k=0;
        
        count=0;
        String tmp[]=Input.split("");
        while(!end)
        {
            end=true;

            for(k=0;k < n && iter < tmp.length ;k++)
            {
                // IF CURRRENT STATE AND SYMBOL OF INPUT MATCHES THEN A VALID TRANSITION 
                if(simulate[k][0].equalsIgnoreCase(current) && simulate[k][1].equalsIgnoreCase(tmp[iter]))
                {
                        count++;
                        
                        System.out.println("");
                       display.setText(""+(tmp.toString()));
                        //Animate(tmp);
                        
                        System.out.print("=>");
                        
                        stepcount.setText(String.valueOf(count));
                        
                        readstate.setText(current);
                        
                        writesymbol.setText(simulate[k][3]);
                        
                        if(simulate[k][1].equals(blank))
                        {
                                Input=implode(tmp,"");
                                Input+=blank;
                                Input=blank+Input;
                                tmp=Input.split("");
                                iter++;
                        }
                        
                        end=false;
                        
                        tmp[iter]=simulate[k][3];
                        
                        if(simulate[k][4].equalsIgnoreCase("R") || simulate[k][4].equalsIgnoreCase("right")){
                        
                            iter++;// MOVING ONE CHAR RIGHT IN INPUT STRING
                        
                            Iter.setText("RIGHT");
                        }

                        else if(simulate[k][4].equalsIgnoreCase("L") || simulate[k][4].equalsIgnoreCase("left")){
                                
                            iter--; // MOVING ONE CHAR LEFT IN INPUT STRING
                          
                            Iter.setText(""+"LEFT");
                        }
                        else
                        {
                          //ELSE STAYS 
                           Iter.setText(""+"STAY");
                        }
                        
                        current = simulate[k][2];//CURRENT STATE UPDATE
                        
                        readstate.setText(""+current);
                        
                        System.out.println('('+implode(simulate[k],",")+") = "+ implode(tmp,""));                                                 
                        Iter.setText(simulate[k][4]);
                        //Thread.sleep(500);
                        
                        break;	                
                }
                //HALTING CONDITION IF STRING ACCPTED
            }

            if(stop.contains(current))// IF CURRENT STATE IS STOP
            {
                System.out.println("String Accepted Machine Halted");
                output=implode(tmp,"");
                end=true;
            }
            else if(end == true)//IF FOR A CHAR IN INPUT NO TRANSITION FUNCTION FOUND IN THE TABLE THEN STRING REJECTED
            {
                System.out.println("String rejected MachineHalted");
            }

        }
        return output;
    }
  public boolean Load(File path) throws FileNotFoundException, IOException
    {
        String []arr=new String[6];
        Vector<String> states=new Vector<String>();
        try{
            cinn = new BufferedReader(new FileReader(path));

            // LOADING INTO A STATES VECTOR FOR TEMP PURPOSE
            while(!(Input=cinn.readLine()).equals(">"))
            {
               states.add(Input);

            }

            current=cinn.readLine(); // Getting Starting State

            stop="STOP"; // getting final State
            
            blank=cinn.readLine();// Getting Blank

            Input=cinn.readLine();// Getting Input
            
            Input+=blank;
            
            Input=blank+Input;
            
            original=Input;
            
            start=current;
            n=states.size();

            simulate=new String[n][5];

            int k=0;

            // FILLING THE SIMULATE ARRAY KNOWN AS TRANSITION TABLE FOR TM
            for (String state : states) {
                arr = state.split("[(,)]+");
                    for(int i=1;i<=5;i++)
                        simulate[k][i-1]=arr[i];
                    k++;

            }
            states.clear();
            cinn.close();
            return true;
         }
        finally{
        }
    }
    public UTM() {
        initComponents();
        //this.jMenuBar2.setName("Universal Turing Machine");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        load = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        run = new javax.swing.JButton();
        step = new javax.swing.JButton();
        stepcount = new javax.swing.JTextField();
        readstate = new javax.swing.JTextField();
        writesymbol = new javax.swing.JTextField();
        Current = new javax.swing.JTextField();
        Iter = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        label5 = new java.awt.Label();
        display = new javax.swing.JFormattedTextField();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        readme = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Universal Turing Machine");

        jPanel1.setBackground(new java.awt.Color(102, 204, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setToolTipText("");
        jPanel1.setNextFocusableComponent(this);

        load.setText("Load");
        load.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });

        Reset.setText("Reset");
        Reset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        run.setText("Run");
        run.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        run.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runActionPerformed(evt);
            }
        });

        step.setText("Step");
        step.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        step.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stepActionPerformed(evt);
            }
        });

        stepcount.setEditable(false);
        stepcount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        stepcount.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        stepcount.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        readstate.setEditable(false);
        readstate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        readstate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        readstate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        writesymbol.setEditable(false);
        writesymbol.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        writesymbol.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        writesymbol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        Current.setEditable(false);
        Current.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Current.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Current.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        Current.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentActionPerformed(evt);
            }
        });

        Iter.setEditable(false);
        Iter.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Iter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Iter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(255, 0, 0));
        label1.setText("Write");

        label2.setAlignment(java.awt.Label.CENTER);
        label2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label2.setForeground(new java.awt.Color(255, 0, 0));
        label2.setText("To State");

        label3.setAlignment(java.awt.Label.CENTER);
        label3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label3.setForeground(new java.awt.Color(255, 0, 0));
        label3.setText("Move");

        label4.setAlignment(java.awt.Label.CENTER);
        label4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label4.setForeground(new java.awt.Color(255, 0, 0));
        label4.setText("Step Count");

        label5.setAlignment(java.awt.Label.CENTER);
        label5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        label5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        label5.setForeground(new java.awt.Color(255, 0, 0));
        label5.setText("On State");

        display.setEditable(false);
        display.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        display.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        display.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        display.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        display.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(stepcount, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(readstate, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(59, 59, 59)
                                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(Iter, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(43, 43, 43))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(writesymbol, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Current, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(159, 159, 159)))
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(display, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(step, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(run, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(load, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(stepcount, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(readstate, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(writesymbol, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Current, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(Iter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addComponent(display, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(run, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(step, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(load, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu3.setText("File");

        jMenu5.setText("Machine Library");
        jMenu3.add(jMenu5);

        readme.setText("About");
        jMenu3.add(readme);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");

        jMenuItem1.setText("Change Input");
        jMenu4.add(jMenuItem1);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed
 try {
                   if(mutex){
                       File temp=null;
                        JFileChooser choose=new JFileChooser ();
                         choose.showOpenDialog(jPanel1);     
                         temp=choose.getSelectedFile();
                        
                         if(temp!=null){
                                   
                             mutex=false;
                            Load(temp);
                            mutex=true;
                        }
                   }
               } catch (IOException ex) {
                   Logger.getLogger(UTM.class.getName()).log(Level.SEVERE, null, ex);
               }        // TODO add your handling code here:
    }//GEN-LAST:event_loadActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
            
            Input=original;
            current=start;
            iter=0;
            stepcount.setText("");
            readstate.setText("");
            writesymbol.setText("");
            Current.setText("");
            Iter.setText("");
            mutex=true;
            count=0;       
            end=false;
            display.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_ResetActionPerformed
 int delay = 1000;
    private void runActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runActionPerformed
        


        /* 
        stepcount.setText("jnfjnfj");
        
        System.out.println("Simulating ........ ");
        
        iter=1;
        int k=0;
        output="";
        stepcount.setText(String.valueOf(count));
        readstate.setText(current);
        writesymbol.setText(simulate[k][3]);

        boolean end=false;
        

        count=0;
        
            String tmp[]=Input.split("");
        
        while(!end)
        {
            end=true;

            for(k=0;k < n && iter < tmp.length ;k++)
            {
                // IF CURRRENT STATE AND SYMBOL OF INPUT MATCHES THEN A VALID TRANSITION 
                if(simulate[k][0].equalsIgnoreCase(current) && simulate[k][1].equalsIgnoreCase(tmp[iter]))
                {
                    try {
                        count++;
                        System.out.println("");
                       Animate(tmp);
                        System.out.print("=>");
                        stepcount.setText(String.valueOf(count));
                        readstate.setText(current);
                        writesymbol.setText(simulate[k][3]);
                        if(simulate[k][1].equals(blank))
                        {
                                Input=implode(tmp,"");
                                Input+=blank;
                                Input=blank+Input;
                                tmp=Input.split("");
                                iter++;
                        }
                        end=false;
                        tmp[iter]=simulate[k][3];
                        if(simulate[k][4].equalsIgnoreCase("R") || simulate[k][4].equalsIgnoreCase("right")){
                          iter++;// MOVING ONE CHAR RIGHT IN INPUT STRING
                        Iter.setText("RIGHT");
                        }

                        else if(simulate[k][4].equalsIgnoreCase("L") || simulate[k][4].equalsIgnoreCase("left")){
                                iter--; // MOVING ONE CHAR LEFT IN INPUT STRING
                           Iter.setText(""+"LEFT");
                        }else{
                          //ELSE STAYS 
                           Iter.setText(""+"STAY");
                             }
                        current = simulate[k][2];//CURRENT STATE UPDATE
                        readstate.setText(""+current);
                        System.out.println('('+implode(simulate[k],",")+") = "+ implode(tmp,""));                                                 
                        Thread.sleep(500);
                        break;	                
                    }
                    //HALTING CONDITION IF STRING ACCPTED
                    catch (InterruptedException ex) {
                        Logger.getLogger(UTM.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    //HALTING CONDITION IF STRING ACCPTED
            }

            if(stop.contains(current))// IF CURRENT STATE IS STOP
            {
                System.out.println("String Accepted Machine Halted");
                output=implode(tmp,"");
                end=true;
            }
            else if(end == true)//IF FOR A CHAR IN INPUT NO TRANSITION FUNCTION FOUND IN THE TABLE THEN STRING REJECTED
            {
                System.out.println("String rejected MachineHalted");
            }

        }*/
        String temp="";
         boolean end=false;
        int k=0;
        
        count=0;
        String tmp[]=Input.split("");
        while(!end)
        {
            end=true;

            for(k=0;k < n && iter < tmp.length ;k++)
            {
                // IF CURRRENT STATE AND SYMBOL OF INPUT MATCHES THEN A VALID TRANSITION 
                if(simulate[k][0].equalsIgnoreCase(current) && simulate[k][1].equalsIgnoreCase(tmp[iter]))
                {
                        count++;
                        
                        System.out.println("");
                       display.setText(""+(tmp.toString()));
                        //Animate(tmp);
                        
                     //   System.out.print("=>");
                        
                        stepcount.setText(String.valueOf(count));
                        
                        readstate.setText(current);
                        
                        writesymbol.setText(simulate[k][3]);
                        
                    for(int p=0;p<tmp.length;p++)
                   {
                       if(p==iter){// SHOWING HEAD ANIMATION HERE
                           temp+="(";
                           temp+=tmp[p];
                           temp+=")";
                       }                          
                       else
                           temp+=tmp[p];                        
                   }
                    display.setText(temp);
                    temp="";
                        if(simulate[k][1].equals(blank))
                        {
                                Input=implode(tmp,"");
                                Input+=blank;
                                Input=blank+Input;
                                tmp=Input.split("");
                                iter++;
                        }
                        
                        end=false;
                        
                        tmp[iter]=simulate[k][3];
                        
                        if(simulate[k][4].equalsIgnoreCase("R") || simulate[k][4].equalsIgnoreCase("right")){
                        
                            iter++;// MOVING ONE CHAR RIGHT IN INPUT STRING
                        
                            Iter.setText("RIGHT");
                        }

                        else if(simulate[k][4].equalsIgnoreCase("L") || simulate[k][4].equalsIgnoreCase("left")){
                                
                            iter--; // MOVING ONE CHAR LEFT IN INPUT STRING
                          
                            Iter.setText(""+"LEFT");
                        }
                        else
                        {
                          //ELSE STAYS 
                           Iter.setText(""+"STAY");
                        }
                        
                        current = simulate[k][2];//CURRENT STATE UPDATE
                        
                        
                        
                        System.out.println('('+implode(simulate[k],",")+") = "+ implode(tmp,""));                                                 
                        
                        Iter.setText(simulate[k][4]);
                         Current.setText(current);
                    /*try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UTM.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
                        
                        break;	                
                }            
            }

            if(stop.contains(current))// IF CURRENT STATE IS STOP
            {
                System.out.println("String Accepted Machine Halted");
                output=implode(tmp,"");
                end=true;
            }
            else if(end == true)//IF FOR A CHAR IN INPUT NO TRANSITION FUNCTION FOUND IN THE TABLE THEN STRING REJECTED
            {
                System.out.println("String rejected MachineHalted");
            }

        }
                        
    }//GEN-LAST:event_runActionPerformed

    private void stepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stepActionPerformed
                Step_simulate();        // TODO add your handling code here:
    }//GEN-LAST:event_stepActionPerformed

    private void displayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_displayActionPerformed

    private void CurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CurrentActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UTM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UTM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UTM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UTM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UTM().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Current;
    private javax.swing.JTextField Iter;
    private javax.swing.JButton Reset;
    private javax.swing.JFormattedTextField display;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private javax.swing.JButton load;
    private javax.swing.JMenu readme;
    private javax.swing.JTextField readstate;
    private javax.swing.JButton run;
    private javax.swing.JButton step;
    private javax.swing.JTextField stepcount;
    private javax.swing.JTextField writesymbol;
    // End of variables declaration//GEN-END:variables
}
