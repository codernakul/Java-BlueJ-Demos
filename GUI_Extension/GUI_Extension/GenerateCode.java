/*     */ package GUI_Extension;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.util.ArrayList;
/*     */ import java.util.TreeMap;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JEditorPane;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JLayeredPane;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.StyledDocument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenerateCode
/*     */ {
/*     */   private TreeMap<String, JComponent> addedComponens;
/*     */   private TreeMap<String, EventsInit> componentEvents;
/*     */   private JTextPane textPane;
/*     */   private String header;
/*     */   private String classGenerator;
/*     */   private String constructor;
/*     */   private String main;
/*     */   private String lookAndFeel;
/*     */   private String generateMenu;
/*     */   private StyledDocument doc;
/*  38 */   private String[] componentsType = new String[] { "JButton", "JTextArea", "JCheckBox", "JTextField", "JLabel", "JRadioButton", "JEditorPane" };
/*     */   private int r;
/*     */   private int g;
/*     */   private int b;
/*  42 */   private int step = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<JComponent> panels;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GenerateCode(JLayeredPane canvas, JTextPane component, TreeMap<String, JComponent> listComponents, TreeMap<String, EventsInit> componentEvents, String generateMenu) {
/*  55 */     Color color = canvas.getBackground();
/*  56 */     this.panels = new ArrayList<>();
/*  57 */     this.r = color.getRed();
/*  58 */     this.g = color.getGreen();
/*  59 */     this.b = color.getBlue();
/*  60 */     this.textPane = component;
/*  61 */     this.textPane.setContentType("text");
/*  62 */     this.addedComponens = listComponents;
/*  63 */     this.componentEvents = componentEvents;
/*  64 */     this.doc = this.textPane.getStyledDocument();
/*  65 */     findPanels(canvas);
/*  66 */     this.header = "/**\n*Text genereted by Simple GUI Extension for BlueJ\n*/\nimport javax.swing.UIManager.LookAndFeelInfo;\nimport java.awt.*;\nimport java.awt.event.ActionEvent;\nimport java.awt.event.ActionListener;\nimport java.awt.event.KeyAdapter;\nimport java.awt.event.KeyEvent;\nimport java.awt.event.MouseAdapter;\nimport java.awt.event.MouseEvent;\nimport java.awt.event.MouseWheelEvent;\nimport java.awt.event.MouseWheelListener;\nimport javax.swing.border.Border;\nimport javax.swing.*;\n\n\n";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     this.classGenerator = "public class " + canvas.getName() + " extends JFrame {\n\n" + "\tprivate JMenuBar menuBar;\n";
/*     */     
/*  82 */     this.lookAndFeel = "\t\t/**Nastavení výchozího vzhledu aplikace\n\t\t*pro nastavení systémového vzhledu použijte:\n\t\t*UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());\n\t\t*/\n\t\ttry{\n\t\t\tfor (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {\n\t\t\t\tif(\"Nimbus\".equals(info.getName())) {\n\t\t\t\t\tUIManager.setLookAndFeel(info.getClassName());\n\t\t\t\t\tbreak;\n\t\t\t\t}\n\t\t\t}\n\t\t}\n\t\tcatch(Exception e){\n\t\t\ttry{\n\t\t\t\tUIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());\n\t\t\t}\n\t\tcatch(Exception ex){}\n\t\t}\n\n";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     this.constructor = "\n\t//Constructor \n\tpublic " + canvas.getName() + "(){\n\n" + "\t\tthis.setTitle(\"" + canvas.getName() + "\");\n" + "\t\tthis.setSize(" + (canvas.getSize()).width + "," + (canvas.getSize()).height + ");\n" + "\t\t//menu generate method\n" + "\t\tgenerateMenu();\n" + "\t\tthis.setJMenuBar(menuBar);\n\n" + "\t\t//pane with null layout" + "\n\t\tJPanel contentPane = new JPanel(null);\n" + "\t\tcontentPane.setPreferredSize(new Dimension(" + (canvas.getSize()).width + "," + (canvas.getSize()).height + "));\n" + "\t\tcontentPane.setBackground(new Color(" + this.r + "," + this.g + "," + this.b + "));\n\n";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     this.main = "\n\n\t public static void main(String[] args){\n\t\tSystem.setProperty(\"swing.defaultlaf\", \"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel\");\n\t\tjavax.swing.SwingUtilities.invokeLater(new Runnable() {\n\t\t\tpublic void run() {\n\t\t\t\tnew " + canvas.getName() + "();\n" + "\t\t\t}\n" + "\t\t});\n" + "\t}\n";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     this.generateMenu = generateMenu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generate() {
/* 130 */     this.step = -1;
/*     */     try {
/* 132 */       this.textPane.setText("");
/* 133 */       this.doc.insertString(this.doc.getLength(), this.header, null);
/* 134 */       this.doc.insertString(this.doc.getLength(), this.classGenerator, null);
/*     */       
/* 136 */       for (String key : this.addedComponens.keySet()) {
/* 137 */         JComponent c = this.addedComponens.get(key);
/* 138 */         String className = c.getClass().getSimpleName();
/* 139 */         Color colorB = c.getBackground();
/* 140 */         Color colorF = c.getForeground();
/* 141 */         Font f = c.getFont();
/* 142 */         String text = "\tprivate " + className + " " + key + ";\n";
/*     */         
/* 144 */         if (c instanceof javax.swing.JPanel) {
/* 145 */           this.constructor += "\n\t\t" + key + " = new " + className + "(null);\n";
/* 146 */           this.constructor += "\t\t" + key + ".setBorder(BorderFactory.createEtchedBorder(1));\n";
/*     */         } else {
/*     */           
/* 149 */           this.constructor += "\n\t\t" + key + " = new " + className + "();\n";
/*     */         } 
/* 151 */         this.constructor += "\t\t" + key + ".setBounds(" + (c.getLocation()).x + "," + (c.getLocation()).y + "," + c.getWidth() + "," + c.getHeight() + ");\n";
/* 152 */         this.constructor += "\t\t" + key + ".setBackground(new Color(" + colorB.getRed() + "," + colorB.getGreen() + "," + colorB.getBlue() + "));\n";
/* 153 */         this.constructor += "\t\t" + key + ".setForeground(new Color(" + colorF.getRed() + "," + colorF.getGreen() + "," + colorF.getBlue() + "));\n";
/* 154 */         this.constructor += "\t\t" + key + ".setEnabled(" + c.isEnabled() + ");\n";
/* 155 */         this.constructor += "\t\t" + key + ".setFont(new Font(\"" + f.getName() + "\"," + f.getStyle() + "," + f.getSize() + "));\n";
/* 156 */         for (String s : this.componentsType) {
/* 157 */           if (s.equals(className)) {
/* 158 */             this.constructor += "\t\t" + key + ".setText(\"" + getTextValue(className, c) + "\");\n";
/* 159 */             if (s.equals("JTextArea") || s.equals("JEditorPane")) {
/* 160 */               this.constructor += "\t\t" + key + ".setBorder(BorderFactory.createBevelBorder(1));\n";
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 165 */         this.constructor += "\t\t" + key + ".setVisible(" + c.isVisible() + ");\n";
/* 166 */         this.constructor += ((EventsInit)this.componentEvents.get(key)).getActionPerformed();
/* 167 */         this.constructor += ((EventsInit)this.componentEvents.get(key)).getKeyEvents();
/* 168 */         this.constructor += ((EventsInit)this.componentEvents.get(key)).getMouseEvents();
/* 169 */         this.constructor += ((EventsInit)this.componentEvents.get(key)).getMouseMoveEvents();
/* 170 */         this.constructor += ((EventsInit)this.componentEvents.get(key)).getMouseWheelEvent();
/*     */ 
/*     */         
/* 173 */         this.doc.insertString(this.doc.getLength(), text, null);
/*     */       } 
/*     */ 
/*     */       
/* 177 */       this.constructor += "\n\t\t//adding components to contentPane panel";
/* 178 */       for (String key : this.addedComponens.keySet()) {
/* 179 */         if (((JComponent)this.addedComponens.get(key)).getParent() instanceof JLayeredPane) {
/* 180 */           this.constructor += "\n\t\tcontentPane.add(" + key + ");"; continue;
/*     */         } 
/* 182 */         this.constructor += "\n\t\t" + ((JComponent)this.addedComponens.get(key)).getParent().getName() + ".add(" + key + ");";
/*     */       } 
/*     */       
/* 185 */       this.constructor += "\n\n\t\t//adding panel to JFrame and seting of window position and close operation\n\t\tthis.add(contentPane);\n\t\tthis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n\t\tthis.setLocationRelativeTo(null);\n\t\tthis.pack();\n\t\tthis.setVisible(true);\n\t}\n\n";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       this.doc.insertString(this.doc.getLength(), this.constructor, null);
/* 193 */       for (String s : this.componentEvents.keySet()) {
/* 194 */         this.doc.insertString(this.doc.getLength(), ((EventsInit)this.componentEvents.get(s)).getMethods(), null);
/*     */       }
/*     */ 
/*     */       
/* 198 */       this.doc.insertString(this.doc.getLength(), this.generateMenu, null);
/*     */       
/* 200 */       this.doc.insertString(this.doc.getLength(), this.main, null);
/* 201 */       this.doc.insertString(this.doc.getLength(), "\n}", null);
/* 202 */     } catch (BadLocationException ex) {
/* 203 */       System.err.println("Couldn't insert initial text into text pane.");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getTextValue(String className, JComponent c) {
/*     */     JButton b;
/*     */     JCheckBox ch;
/*     */     JTextArea ta;
/*     */     JTextField tf;
/*     */     JLabel lb;
/*     */     JRadioButton rb;
/*     */     JEditorPane ep;
/* 216 */     String text = "";
/* 217 */     switch (className) {
/*     */       case "JButton":
/* 219 */         b = (JButton)c;
/* 220 */         return b.getText();
/*     */       case "JCheckBox":
/* 222 */         ch = (JCheckBox)c;
/* 223 */         return ch.getText();
/*     */       case "JTextArea":
/* 225 */         ta = (JTextArea)c;
/* 226 */         return ta.getText();
/*     */       case "JTextField":
/* 228 */         tf = (JTextField)c;
/* 229 */         return tf.getText();
/*     */       case "JLabel":
/* 231 */         lb = (JLabel)c;
/* 232 */         return lb.getText();
/*     */       case "JRadioButton":
/* 234 */         rb = (JRadioButton)c;
/* 235 */         return rb.getText();
/*     */       case "JEditorPane":
/* 237 */         ep = (JEditorPane)c;
/* 238 */         return ep.getText();
/*     */     } 
/*     */ 
/*     */     
/* 242 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void findPanels(JComponent panel) {
/* 250 */     this.step++;
/* 251 */     for (Component comp : panel.getComponents()) {
/* 252 */       if (comp instanceof javax.swing.JPanel) {
/* 253 */         this.panels.add((JComponent)comp);
/*     */       }
/*     */     } 
/* 256 */     if (this.panels.size() > 0 && this.step < this.panels.size())
/* 257 */       findPanels(this.panels.get(this.step)); 
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\GenerateCode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */