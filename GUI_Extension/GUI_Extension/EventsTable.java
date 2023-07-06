/*     */ package GUI_Extension;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.TreeMap;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.table.AbstractTableModel;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventsTable
/*     */ {
/*     */   private JTable table;
/*  26 */   private String component = "";
/*     */ 
/*     */   
/*     */   private TreeMap<String, EventsInit> componentEvents;
/*     */ 
/*     */ 
/*     */   
/*     */   public EventsTable(TreeMap<String, EventsInit> componentEvents) {
/*  34 */     this.componentEvents = componentEvents;
/*  35 */     this.table = new JTable(new JTableModel());
/*  36 */     this.table.setFillsViewportHeight(true);
/*  37 */     TableCellRenderer buttonRenderer = new JTableButtonRenderer();
/*  38 */     this.table.getColumn("Button1").setCellRenderer(buttonRenderer);
/*  39 */     this.table.getColumn("Button1").setMaxWidth(40);
/*     */     
/*  41 */     this.table.addMouseListener(new JTableButtonMouseListener(this.table));
/*  42 */     this.table.setRowHeight(22);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponentName(String name) {
/*  51 */     this.component = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getComponentName() {
/*  58 */     return this.component;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JTable getTable() {
/*  65 */     return this.table;
/*     */   }
/*     */   public TreeMap<String, EventsInit> getTree() {
/*  68 */     return this.componentEvents;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class JTableModel
/*     */     extends AbstractTableModel
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */     
/*  78 */     private final String[] COLUMN_NAMES = new String[] { "Events", "Button1" };
/*  79 */     private final Class<?>[] COLUMN_TYPES = new Class[] { String.class, JButton.class };
/*  80 */     private final String[] TABLE_NAMES = new String[] { "actionPerformed", "keyPressed", "keyReleased", "keyTyped", "mouseClicked", "mouseEntered", "mouseExited", "mousePressed", "mouseRelesed", "mouseDragged", "mouseMoved", "mouseWheelMoved" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getColumnCount() {
/*  91 */       return this.COLUMN_NAMES.length;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getRowCount() {
/*  96 */       return this.TABLE_NAMES.length;
/*     */     }
/*     */ 
/*     */     
/*     */     public String getColumnName(int columnIndex) {
/* 101 */       return this.COLUMN_NAMES[columnIndex];
/*     */     }
/*     */ 
/*     */     
/*     */     public Class<?> getColumnClass(int columnIndex) {
/* 106 */       return this.COLUMN_TYPES[columnIndex];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getValueAt(final int rowIndex, int columnIndex) {
/*     */       final JButton button;
/* 117 */       switch (columnIndex) { case 0:
/* 118 */           return this.TABLE_NAMES[rowIndex];
/* 119 */         case 1: button = new JButton("...");
/* 120 */           button.addActionListener(new ActionListener() {
/*     */                 public void actionPerformed(ActionEvent arg0) {
/* 122 */                   String name = "";
/* 123 */                   boolean exist = true;
/* 124 */                   String name2 = ((EventsInit)EventsTable.this.componentEvents.get(EventsTable.this.component)).getActionValue(rowIndex);
/* 125 */                   name = JOptionPane.showInputDialog("Enter method name", name2);
/*     */                   
/* 127 */                   label19: for (String s : EventsTable.this.componentEvents.keySet()) {
/* 128 */                     for (int i = 0; i < 12; i++) {
/* 129 */                       String actionValue = ((EventsInit)EventsTable.this.componentEvents.get(s)).getActionValue(i);
/* 130 */                       if (actionValue.equals(name) && !actionValue.equals("")) {
/* 131 */                         JOptionPane.showMessageDialog(button, "Method name already exists.\nPlease change name of this method.", "Warning", 2);
/* 132 */                         name = JOptionPane.showInputDialog("Enter method name", ((EventsInit)EventsTable.this.componentEvents.get(EventsTable.this.component)).getActionValue(rowIndex));
/*     */                         break label19;
/*     */                       } 
/*     */                     } 
/*     */                   } 
/* 137 */                   if (name == null)
/* 138 */                     name = name2; 
/* 139 */                   ((EventsInit)EventsTable.this.componentEvents.get(EventsTable.this.component)).setActionValue(rowIndex, name);
/*     */                 }
/*     */               });
/* 142 */           return button; }
/*     */       
/* 144 */       return "Error";
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class JTableButtonRenderer
/*     */     implements TableCellRenderer
/*     */   {
/*     */     private JTableButtonRenderer() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
/* 158 */       JButton button = (JButton)value;
/* 159 */       if (isSelected) {
/* 160 */         button.setForeground(table.getSelectionForeground());
/* 161 */         button.setBackground(table.getSelectionBackground());
/*     */       } else {
/* 163 */         button.setForeground(table.getForeground());
/* 164 */         button.setBackground(UIManager.getColor("Button.background"));
/*     */       } 
/* 166 */       return button;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static class JTableButtonMouseListener
/*     */     extends MouseAdapter
/*     */   {
/*     */     private final JTable table;
/*     */ 
/*     */     
/*     */     public JTableButtonMouseListener(JTable table) {
/* 178 */       this.table = table;
/*     */     }
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {
/* 182 */       int column = this.table.getColumnModel().getColumnIndexAtX(e.getX());
/* 183 */       int row = e.getY() / this.table.getRowHeight();
/*     */       
/* 185 */       if (row < this.table.getRowCount() && row >= 0 && column < this.table.getColumnCount() && column >= 0) {
/* 186 */         Object value = this.table.getValueAt(row, column);
/* 187 */         if (value instanceof JButton)
/* 188 */           ((JButton)value).doClick(); 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\EventsTable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */