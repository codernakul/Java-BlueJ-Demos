/*     */ package GUI_Extension;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.util.TreeMap;
/*     */ import javax.swing.DefaultCellEditor;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JEditorPane;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JTextArea;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.event.TableModelEvent;
/*     */ import javax.swing.event.TableModelListener;
/*     */ import javax.swing.table.DefaultTableModel;
/*     */ import javax.swing.table.TableCellEditor;
/*     */ 
/*     */ public class PropertiesTable {
/*  23 */   private final int COLUMN = 1;
/*     */   
/*     */   private JComponent comp;
/*     */   
/*     */   private Container parent;
/*     */   
/*     */   private JTableX table;
/*     */   
/*     */   private TreeMap<String, JComponent> map;
/*     */   
/*     */   private TreeMap<String, EventsInit> componentEvents;
/*     */   private String[] oldValue;
/*     */   private boolean changingValues = false;
/*     */   private TableModelListener tml;
/*     */   private JComboBox<String> combobox;
/*     */   
/*     */   public PropertiesTable(TreeMap<String, JComponent> map, TreeMap<String, EventsInit> componentEvents, JComboBox cb) {
/*  40 */     this.map = map;
/*  41 */     this.oldValue = new String[10];
/*  42 */     this.table = new JTableX();
/*  43 */     this.combobox = this.combobox;
/*  44 */     this.componentEvents = componentEvents;
/*  45 */     String[] col_names = { "Properties", "Value" };
/*  46 */     DefaultTableModel model = new DefaultTableModel((Object[])col_names, 10)
/*     */       {
/*  48 */         String[] row_names = new String[] { "Name", "Location", "Size", "Background", "Foreground", "Parent", "Enable", "Font", "Text", "Visible" };
/*     */         
/*     */         public Object getValueAt(int row, int col) {
/*  51 */           if (col == 0)
/*  52 */             return this.row_names[row]; 
/*  53 */           return super.getValueAt(row, col);
/*     */         }
/*     */         
/*     */         public boolean isCellEditable(int row, int col) {
/*  57 */           if (PropertiesTable.this.comp.getClass().getSimpleName().equals("JLayeredPane") && col == 1 && row == 9) {
/*  58 */             return false;
/*     */           }
/*  60 */           if (PropertiesTable.this.comp.getClass().getSimpleName().equals("JLayeredPane") && col == 1 && row == 1) {
/*  61 */             return false;
/*     */           }
/*  63 */           if (col == 0)
/*  64 */             return false; 
/*  65 */           return true;
/*     */         }
/*     */       };
/*     */ 
/*     */     
/*  70 */     this.table.setModel(model);
/*  71 */     model.addTableModelListener(getListener());
/*  72 */     this.table.setRowHeight(22);
/*  73 */     RowEditorModel rm = new RowEditorModel();
/*  74 */     this.table.setRowEditorModel(rm);
/*     */     
/*  76 */     JCheckBox chb = new JCheckBox();
/*  77 */     DefaultCellEditor ed = new DefaultCellEditor(chb);
/*  78 */     rm.addEditorForRow(6, ed);
/*  79 */     chb = new JCheckBox();
/*  80 */     ed = new DefaultCellEditor(chb);
/*  81 */     rm.addEditorForRow(9, ed);
/*  82 */     JColorChooser cc = new JColorChooser();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JTableX getTable() {
/*  91 */     return this.table;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] getOldValue() {
/*  99 */     return this.oldValue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOldValue() {
/* 105 */     for (int i = 0; i < this.oldValue.length; i++) {
/* 106 */       this.oldValue[i] = this.table.getValueAt(i, 1).toString();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent getComponentForTable() {
/* 115 */     return this.comp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponentForTable(JComponent comp) {
/* 123 */     this.comp = comp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponentProperties(JComponent c) {
/* 131 */     this.changingValues = true;
/*     */     
/* 133 */     TableCellEditor cellEditor = this.table.getCellEditor();
/* 134 */     if (cellEditor != null)
/*     */     {
/* 136 */       if (!cellEditor.stopCellEditing())
/*     */       {
/* 138 */         cellEditor.cancelCellEditing();
/*     */       }
/*     */     }
/* 141 */     this.comp = c;
/* 142 */     this.table.setValueAt(c.getName().toString(), 0, 1);
/* 143 */     this.table.setValueAt((c.getLocation()).x + ";" + (c.getLocation()).y, 1, 1);
/* 144 */     this.table.setValueAt((c.getSize()).width + ";" + (c.getSize()).height, 2, 1);
/* 145 */     this.table.setValueAt(c.getBackground().getRed() + ";" + c.getBackground().getGreen() + ";" + c.getBackground().getBlue(), 3, 1);
/* 146 */     this.table.setValueAt(c.getForeground().getRed() + ";" + c.getForeground().getGreen() + ";" + c.getForeground().getBlue(), 4, 1);
/* 147 */     this.table.setValueAt(c.getParent().getClass().getSimpleName(), 5, 1);
/* 148 */     this.table.setValueAt(Boolean.valueOf(c.isEnabled()), 6, 1);
/* 149 */     this.table.setValueAt(c.getFont().getFamily() + ";" + c.getFont().getStyle() + ";" + c.getFont().getSize(), 7, 1);
/* 150 */     this.table.setValueAt(getTextValue(c.getClass().getSimpleName(), c), 8, 1);
/* 151 */     this.table.setValueAt(Boolean.valueOf(c.isVisible()), 9, 1);
/*     */ 
/*     */     
/* 154 */     for (int i = 0; i < this.oldValue.length; i++) {
/* 155 */       this.oldValue[i] = this.table.getValueAt(i, 1).toString();
/*     */     }
/* 157 */     this.changingValues = false;
/*     */   }
/*     */   
/*     */   public String getTextValue(String className, JComponent c) {
/*     */     JButton b;
/*     */     JCheckBox ch;
/*     */     JTextArea ta;
/*     */     JTextField tf;
/*     */     JLabel lb;
/*     */     JRadioButton rb;
/*     */     JEditorPane ep;
/* 168 */     String text = "";
/* 169 */     switch (className) {
/*     */       case "JButton":
/* 171 */         b = (JButton)c;
/* 172 */         return b.getText();
/*     */       case "JCheckBox":
/* 174 */         ch = (JCheckBox)c;
/* 175 */         return ch.getText();
/*     */       case "JTextArea":
/* 177 */         ta = (JTextArea)c;
/* 178 */         return ta.getText();
/*     */       case "JTextField":
/* 180 */         tf = (JTextField)c;
/* 181 */         return tf.getText();
/*     */       case "JLabel":
/* 183 */         lb = (JLabel)c;
/* 184 */         return lb.getText();
/*     */       case "JRadioButton":
/* 186 */         rb = (JRadioButton)c;
/* 187 */         return rb.getText();
/*     */       case "JEditorPane":
/* 189 */         ep = (JEditorPane)c;
/* 190 */         return ep.getText();
/*     */     } 
/*     */ 
/*     */     
/* 194 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextValue(String className, String text, JComponent c) {
/*     */     JButton b;
/*     */     JCheckBox ch;
/*     */     JTextArea ta;
/*     */     JTextField tf;
/*     */     JLabel lb;
/*     */     JRadioButton rb;
/*     */     JEditorPane ep;
/* 207 */     switch (className) {
/*     */       case "JButton":
/* 209 */         b = (JButton)c;
/* 210 */         b.setText(text);
/*     */         break;
/*     */       case "JCheckBox":
/* 213 */         ch = (JCheckBox)c;
/* 214 */         ch.setText(text);
/*     */         break;
/*     */       case "JTextArea":
/* 217 */         ta = (JTextArea)c;
/* 218 */         ta.setText(text);
/*     */         break;
/*     */       case "JTextField":
/* 221 */         tf = (JTextField)c;
/* 222 */         tf.setText(text);
/*     */         break;
/*     */       case "JLabel":
/* 225 */         lb = (JLabel)c;
/* 226 */         lb.setText(text);
/*     */         break;
/*     */       case "JRadioButton":
/* 229 */         rb = (JRadioButton)c;
/* 230 */         rb.setText(text);
/*     */         break;
/*     */       case "JEditorPane":
/* 233 */         ep = (JEditorPane)c;
/* 234 */         ep.setText(text);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNull() {
/* 243 */     for (int i = 0; i < 10; i++) {
/* 244 */       this.table.setValueAt(null, i, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TableModelListener getListener() {
/* 254 */     this.tml = new TableModelListener()
/*     */       {
/*     */         public void tableChanged(TableModelEvent e) {
/* 257 */           if (PropertiesTable.this.changingValues) {
/*     */             return;
/*     */           }
/* 260 */           int row = e.getFirstRow();
/* 261 */           int column = e.getColumn();
/* 262 */           if (row < 0 || column < 0)
/*     */             return; 
/* 264 */           String cellValue = String.valueOf(PropertiesTable.this.table.getValueAt(row, column));
/* 265 */           String[] value = cellValue.split(";");
/* 266 */           String oldComponentName = PropertiesTable.this.oldValue[0]; try {
/*     */             JComponent component; EventsInit ei; int x; int y; int width; int height; Color nBc; Color nFc; boolean b; Font f; String s;
/*     */             boolean b2;
/* 269 */             switch (row) {
/*     */               case 0:
/* 271 */                 if (PropertiesTable.this.map.containsKey(cellValue)) {
/* 272 */                   JOptionPane.showMessageDialog(null, "Component of this name already exists.");
/* 273 */                   cellValue = oldComponentName;
/*     */                 } 
/* 275 */                 PropertiesTable.this.comp.setName(cellValue);
/* 276 */                 component = (JComponent)PropertiesTable.this.map.get(oldComponentName);
/* 277 */                 PropertiesTable.this.map.remove(oldComponentName);
/* 278 */                 component.setName(cellValue);
/* 279 */                 PropertiesTable.this.map.put(cellValue, component);
/* 280 */                 ei = (EventsInit)PropertiesTable.this.componentEvents.get(oldComponentName);
/* 281 */                 PropertiesTable.this.componentEvents.remove(oldComponentName);
/* 282 */                 PropertiesTable.this.componentEvents.put(cellValue, ei);
/* 283 */                 ((EventsInit)PropertiesTable.this.componentEvents.get(cellValue)).setComponentName(cellValue);
/* 284 */                 PropertiesTable.this.combobox.addItem(cellValue);
/* 285 */                 PropertiesTable.this.combobox.removeItem(oldComponentName);
/* 286 */                 PropertiesTable.this.combobox.setSelectedItem(cellValue);
/* 287 */                 oldComponentName = cellValue;
/*     */                 break;
/*     */ 
/*     */               
/*     */               case 1:
/* 292 */                 x = Integer.parseInt(value[0]);
/* 293 */                 y = Integer.parseInt(value[1]);
/* 294 */                 PropertiesTable.this.comp.setLocation(x, y);
/* 295 */                 ((JComponent)PropertiesTable.this.map.get(oldComponentName)).setLocation(x, y);
/*     */                 break;
/*     */               case 2:
/* 298 */                 width = Integer.parseInt(value[0]);
/* 299 */                 height = Integer.parseInt(value[1]);
/* 300 */                 PropertiesTable.this.comp.setSize(width, height);
/* 301 */                 ((JComponent)PropertiesTable.this.map.get(oldComponentName)).setSize(width, height);
/*     */                 break;
/*     */               
/*     */               case 3:
/* 305 */                 nBc = new Color(Integer.parseInt(value[0]), Integer.parseInt(value[1]), Integer.parseInt(value[2]));
/* 306 */                 PropertiesTable.this.comp.setBackground(nBc);
/* 307 */                 ((JComponent)PropertiesTable.this.map.get(oldComponentName)).setBackground(nBc);
/*     */                 break;
/*     */               case 4:
/* 310 */                 nFc = new Color(Integer.parseInt(value[0]), Integer.parseInt(value[1]), Integer.parseInt(value[2]));
/* 311 */                 PropertiesTable.this.comp.setForeground(nFc);
/* 312 */                 ((JComponent)PropertiesTable.this.map.get(oldComponentName)).setForeground(nFc);
/*     */                 break;
/*     */ 
/*     */ 
/*     */               
/*     */               case 6:
/* 318 */                 b = Boolean.valueOf(cellValue).booleanValue();
/* 319 */                 PropertiesTable.this.comp.setEnabled(b);
/* 320 */                 ((JComponent)PropertiesTable.this.map.get(oldComponentName)).setEnabled(b);
/*     */                 break;
/*     */               case 7:
/* 323 */                 f = new Font(value[0], Integer.parseInt(value[1]), Integer.parseInt(value[2]));
/* 324 */                 PropertiesTable.this.comp.setFont(f);
/* 325 */                 ((JComponent)PropertiesTable.this.map.get(oldComponentName)).setFont(f);
/*     */                 break;
/*     */               case 8:
/* 328 */                 PropertiesTable.this.setTextValue(PropertiesTable.this.comp.getClass().getSimpleName(), cellValue, PropertiesTable.this.comp);
/* 329 */                 s = ((JComponent)PropertiesTable.this.map.get(oldComponentName)).getClass().getSimpleName().toString();
/* 330 */                 PropertiesTable.this.setTextValue(s, cellValue, (JComponent)PropertiesTable.this.map.get(oldComponentName));
/*     */                 break;
/*     */               case 9:
/* 333 */                 b2 = Boolean.valueOf(cellValue).booleanValue();
/* 334 */                 PropertiesTable.this.comp.setVisible(b2);
/* 335 */                 ((JComponent)PropertiesTable.this.map.get(oldComponentName)).setVisible(b2);
/*     */                 break;
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 343 */           } catch (Exception ex) {}
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 351 */     return this.tml;
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\PropertiesTable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */