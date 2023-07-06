/*     */ package GUI_Extension;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.TreeMap;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class KeyActionEvent
/*     */   implements KeyListener
/*     */ {
/*  22 */   private final int COLUMN = 1;
/*     */   
/*     */   private JComponent comp;
/*     */   private JComponent parent;
/*     */   private PropertiesTable propertiesTable;
/*     */   private int currX;
/*     */   private int currY;
/*     */   private TreeMap<String, JComponent> addedComponentsMap;
/*     */   private TreeMap<String, EventsInit> componentEvents;
/*  31 */   private int step = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ArrayList<JComponent> panels;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JComboBox<String> combobox;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public KeyActionEvent(PropertiesTable propertiesTable, TreeMap<String, JComponent> addedComponentsMap, TreeMap<String, EventsInit> componentEvents, JComboBox<String> combobox) {
/*  47 */     this.propertiesTable = propertiesTable;
/*  48 */     this.addedComponentsMap = addedComponentsMap;
/*  49 */     this.componentEvents = componentEvents;
/*  50 */     this.combobox = combobox;
/*  51 */     this.panels = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent e) {
/*  66 */     JComponent c = (JComponent)e.getSource();
/*  67 */     this.currX = (c.getLocation()).x;
/*  68 */     this.currY = (c.getLocation()).y;
/*  69 */     int key = e.getKeyCode();
/*  70 */     switch (key) {
/*     */       case 39:
/*  72 */         c.setLocation(++this.currX, this.currY);
/*  73 */         this.propertiesTable.getTable().setValueAt((c.getLocation()).x + ";" + (c.getLocation()).y, 1, 1);
/*     */         break;
/*     */       case 37:
/*  76 */         c.setLocation(--this.currX, this.currY);
/*  77 */         this.propertiesTable.getTable().setValueAt((c.getLocation()).x + ";" + (c.getLocation()).y, 1, 1);
/*     */         break;
/*     */       case 38:
/*  80 */         c.setLocation(this.currX, --this.currY);
/*  81 */         this.propertiesTable.getTable().setValueAt((c.getLocation()).x + ";" + (c.getLocation()).y, 1, 1);
/*     */         break;
/*     */       case 40:
/*  84 */         c.setLocation(this.currX, ++this.currY);
/*  85 */         this.propertiesTable.getTable().setValueAt((c.getLocation()).x + ";" + (c.getLocation()).y, 1, 1);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent e) {
/*  97 */     this.comp = (JComponent)e.getSource();
/*  98 */     this.parent = (JComponent)this.comp.getParent();
/*  99 */     if (e.getKeyCode() == 127) {
/* 100 */       deleteComponent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteComponent() {
/* 111 */     findPanels(this.comp);
/* 112 */     if (this.comp instanceof javax.swing.JPanel && (this.comp.getComponents()).length > 0) {
/* 113 */       for (Component c : this.comp.getComponents()) {
/* 114 */         this.addedComponentsMap.remove(c.getName());
/* 115 */         this.componentEvents.remove(c.getName());
/* 116 */         this.combobox.removeItem(c.getName());
/* 117 */         this.comp.remove(c);
/*     */       } 
/*     */     }
/* 120 */     for (JComponent c : this.panels) {
/* 121 */       for (Component k : c.getComponents()) {
/* 122 */         this.addedComponentsMap.remove(k.getName());
/* 123 */         this.componentEvents.remove(k.getName());
/* 124 */         this.combobox.removeItem(k.getName());
/* 125 */         c.remove(k);
/*     */       } 
/* 127 */       this.comp.remove(c);
/*     */     } 
/*     */     
/* 130 */     this.addedComponentsMap.remove(this.comp.getName());
/* 131 */     this.componentEvents.remove(this.comp.getName());
/* 132 */     this.combobox.removeItem(this.comp.getName());
/* 133 */     this.parent.remove(this.comp);
/* 134 */     this.parent.revalidate();
/* 135 */     this.parent.repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void findPanels(JComponent panel) {
/* 144 */     this.step++;
/* 145 */     for (Component comp : panel.getComponents()) {
/* 146 */       if (comp instanceof javax.swing.JPanel) {
/* 147 */         this.panels.add((JComponent)comp);
/*     */       }
/*     */     } 
/* 150 */     if (this.panels.size() > 0 && this.step < this.panels.size())
/* 151 */       findPanels(this.panels.get(this.step)); 
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\KeyActionEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */