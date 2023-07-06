/*    */ package GUI_Extension;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.event.ItemEvent;
/*    */ import java.awt.event.ItemListener;
/*    */ import java.util.ArrayList;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JLayeredPane;
/*    */ import javax.swing.JTabbedPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ComboListener
/*    */   implements ItemListener
/*    */ {
/*    */   private PropertiesTable table;
/*    */   private JLayeredPane canvas;
/*    */   private EventsTable eventsTable;
/*    */   private JTabbedPane eventsPane;
/*    */   private ArrayList<JComponent> panels;
/* 24 */   private int krok = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ComboListener(PropertiesTable table, JLayeredPane canvas, EventsTable eventsTable, JTabbedPane eventsPane) {
/* 34 */     this.table = table;
/* 35 */     this.canvas = canvas;
/* 36 */     this.eventsTable = eventsTable;
/* 37 */     this.eventsPane = eventsPane;
/* 38 */     this.panels = new ArrayList<>();
/* 39 */     this.panels.add(canvas);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void itemStateChanged(ItemEvent e) {
/* 49 */     JComponent c = null;
/* 50 */     if (e.getStateChange() == 1) {
/* 51 */       String selected = (String)e.getItem();
/*    */       
/* 53 */       while (c == null) {
/* 54 */         c = findComponent(this.panels.get(this.krok), selected);
/* 55 */         this.krok++;
/*    */       } 
/* 57 */       this.krok = 0;
/* 58 */       if (c != null) {
/* 59 */         this.table.setComponentProperties(c);
/* 60 */         this.eventsTable.setComponentName(c.getName());
/* 61 */         this.eventsPane.setTitleAt(1, "Events - " + c.getName());
/*    */       } else {
/*    */         
/* 64 */         this.table.setNull();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JComponent findComponent(JComponent c, String selected) {
/* 77 */     for (Component co : c.getComponents()) {
/* 78 */       if (co instanceof javax.swing.JPanel) {
/* 79 */         this.panels.add((JComponent)co);
/*    */       }
/* 81 */       if (co.getName().equals(selected)) {
/* 82 */         return (JComponent)co;
/*    */       }
/*    */     } 
/*    */     
/* 86 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\ComboListener.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */