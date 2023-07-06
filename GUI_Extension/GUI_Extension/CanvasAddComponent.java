/*     */ package GUI_Extension;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Point;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JEditorPane;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class CanvasAddComponent {
/*  16 */   private final int START = 1;
/*  17 */   private final Dimension PANES_DIMENSION = new Dimension(150, 100);
/*  18 */   private final Dimension BUTTONS_DIMENSION = new Dimension(90, 35);
/*  19 */   private final Border BORDER = BorderFactory.createBevelBorder(1);
/*     */   
/*  21 */   private int buttonName = 1;
/*  22 */   private int checkBoxName = 1;
/*  23 */   private int comboBoxName = 1;
/*  24 */   private int editorPaneName = 1;
/*  25 */   private int labelName = 1;
/*  26 */   private int listName = 1;
/*  27 */   private int menuName = 1;
/*  28 */   private int panelName = 1;
/*  29 */   private int radioButtonName = 1;
/*  30 */   private int tabbedPanelName = 1;
/*  31 */   private int tableName = 1;
/*  32 */   private int textAreaName = 1;
/*  33 */   private int textBoxName = 1;
/*  34 */   private int textFieldName = 1;
/*  35 */   private int passwordFieldName = 1;
/*     */   
/*     */   private String name;
/*     */ 
/*     */   
/*     */   public JComponent addComponentOnCanvas(String component, Point point, TransferHandler tr) {
/*     */     JButton button;
/*     */     JCheckBox checkBox;
/*     */     JComboBox<String> comboBox;
/*     */     JEditorPane editorPane;
/*     */     JLabel label;
/*     */     JList<String> list;
/*     */     DefaultListModel<String> lm;
/*     */     JPasswordField passwordField;
/*     */     JRadioButton radioButton;
/*     */     JTextArea textArea;
/*     */     JTextField textField;
/*     */     JPanel panel;
/*  53 */     switch (component) {
/*     */       
/*     */       case "Button":
/*  56 */         button = new JButton("Button" + this.buttonName);
/*  57 */         button = (JButton)setComponents(button, point, this.BUTTONS_DIMENSION);
/*  58 */         this.name = component + "" + this.buttonName;
/*  59 */         setComponentInCanvas(button, this.name);
/*  60 */         this.buttonName++;
/*  61 */         return button;
/*     */       case "CheckBox":
/*  63 */         checkBox = new JCheckBox("CheckBox");
/*  64 */         checkBox = (JCheckBox)setComponents(checkBox, point, this.BUTTONS_DIMENSION);
/*  65 */         this.name = component + "" + this.checkBoxName;
/*  66 */         setComponentInCanvas(checkBox, this.name);
/*  67 */         this.checkBoxName++;
/*  68 */         return checkBox;
/*     */       case "ComboBox":
/*  70 */         comboBox = new JComboBox<>();
/*  71 */         comboBox = (JComboBox<String>)setComponents(comboBox, point, this.BUTTONS_DIMENSION);
/*  72 */         this.name = component + "" + this.comboBoxName;
/*  73 */         setComponentInCanvas(comboBox, this.name);
/*  74 */         this.comboBoxName++;
/*  75 */         return comboBox;
/*     */       case "EditorPane":
/*  77 */         editorPane = new JEditorPane();
/*  78 */         editorPane = (JEditorPane)setComponents(editorPane, point, this.PANES_DIMENSION);
/*  79 */         editorPane.setBorder(this.BORDER);
/*  80 */         editorPane.setText("JEditorPane");
/*     */         
/*  82 */         this.name = component + "" + this.editorPaneName;
/*  83 */         setComponentInCanvas(editorPane, this.name);
/*  84 */         this.editorPaneName++;
/*  85 */         return editorPane;
/*     */       case "Label":
/*  87 */         label = new JLabel("label");
/*  88 */         label = (JLabel)setComponents(label, point, this.BUTTONS_DIMENSION);
/*  89 */         this.name = component + "" + this.labelName;
/*  90 */         setComponentInCanvas(label, this.name);
/*  91 */         this.labelName++;
/*  92 */         return label;
/*     */       case "List":
/*  94 */         list = new JList<>();
/*  95 */         list = (JList<String>)setComponents(list, point, this.PANES_DIMENSION);
/*  96 */         list.setBorder(this.BORDER);
/*  97 */         lm = new DefaultListModel();
/*  98 */         lm.addElement("Jlist");
/*  99 */         list.setModel(lm);
/* 100 */         this.name = component + "" + this.listName;
/* 101 */         setComponentInCanvas(list, this.name);
/* 102 */         this.listName++;
/* 103 */         return list;
/*     */       case "PasswordField":
/* 105 */         passwordField = new JPasswordField();
/* 106 */         passwordField = (JPasswordField)setComponents(passwordField, point, this.BUTTONS_DIMENSION);
/* 107 */         passwordField.setText("PasswordField");
/* 108 */         this.name = component + "" + this.passwordFieldName;
/* 109 */         setComponentInCanvas(passwordField, this.name);
/* 110 */         this.passwordFieldName++;
/* 111 */         return passwordField;
/*     */       case "RadioButton":
/* 113 */         radioButton = new JRadioButton("JRadioButton");
/* 114 */         radioButton = (JRadioButton)setComponents(radioButton, point, this.BUTTONS_DIMENSION);
/* 115 */         this.name = component + "" + this.radioButtonName;
/* 116 */         setComponentInCanvas(radioButton, this.name);
/* 117 */         this.radioButtonName++;
/* 118 */         return radioButton;
/*     */       case "TextArea":
/* 120 */         textArea = new JTextArea();
/* 121 */         textArea = (JTextArea)setComponents(textArea, point, this.PANES_DIMENSION);
/* 122 */         textArea.setText("JTextArea");
/* 123 */         textArea.setBorder(this.BORDER);
/* 124 */         textArea.setBackground(Color.WHITE);
/* 125 */         this.name = component + "" + this.textAreaName;
/* 126 */         setComponentInCanvas(textArea, this.name);
/* 127 */         this.textAreaName++;
/* 128 */         return textArea;
/*     */       case "TextField":
/* 130 */         textField = new JTextField("JTextField");
/* 131 */         textField = (JTextField)setComponents(textField, point, this.BUTTONS_DIMENSION);
/* 132 */         this.name = component + "" + this.textFieldName;
/* 133 */         setComponentInCanvas(textField, this.name);
/* 134 */         this.textFieldName++;
/* 135 */         return textField;
/*     */       case "Menu":
/* 137 */         return null;
/*     */       case "Panel":
/* 139 */         panel = new JPanel(null);
/* 140 */         panel = (JPanel)setComponents(panel, point, this.PANES_DIMENSION);
/* 141 */         panel.setBorder(BorderFactory.createEtchedBorder(1));
/* 142 */         this.name = component + "" + this.panelName;
/* 143 */         setComponentInCanvas(panel, this.name);
/* 144 */         panel.setTransferHandler(tr);
/* 145 */         this.panelName++;
/* 146 */         return panel;
/*     */     } 
/* 148 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponentInCanvas(JComponent comp, String name) {
/* 160 */     comp.setName(name.toLowerCase());
/*     */   }
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
/*     */   public JComponent setComponents(JComponent comp, Point p, Dimension size) {
/* 174 */     Point newP = new Point(p.x - size.width / 2, p.y - size.height / 2);
/* 175 */     comp.setBounds(newP.x, newP.y, size.width, size.height);
/* 176 */     return comp;
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\CanvasAddComponent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */