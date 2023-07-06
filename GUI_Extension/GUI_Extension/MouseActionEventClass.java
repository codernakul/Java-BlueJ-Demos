/*     */ package GUI_Extension;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.TreeMap;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JColorChooser;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.border.Border;
/*     */ 
/*     */ public final class MouseActionEventClass
/*     */   implements MouseListener {
/*  24 */   private final int COLUMN = 1;
/*     */   private JComponent comp;
/*     */   private Border borderDefault;
/*     */   private ResizableBorder resizableBorder;
/*     */   private Container parent;
/*     */   private TreeMap<String, JComponent> addedComponentsMap;
/*     */   private TreeMap<String, EventsInit> componentEvents;
/*     */   private JPopupMenu popup;
/*     */   private PropertiesTable propertiesTable;
/*     */   private JComboBox<String> comboBox;
/*     */   private JTabbedPane events;
/*     */   private EventsTable eventsTable;
/*  36 */   private int step = -1;
/*     */ 
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
/*     */ 
/*     */   
/*     */   public MouseActionEventClass(JComponent component, TreeMap<String, JComponent> addedComponentsMap, PropertiesTable propertiesTable, JTabbedPane eventPanel, TreeMap<String, EventsInit> componentEvents, EventsTable eventsTable, JComboBox<String> comboBox) {
/*  52 */     this.comp = component;
/*  53 */     this.borderDefault = this.comp.getBorder();
/*  54 */     this.parent = this.comp.getParent();
/*  55 */     this.addedComponentsMap = addedComponentsMap;
/*  56 */     this.componentEvents = componentEvents;
/*  57 */     this.propertiesTable = propertiesTable;
/*  58 */     this.events = eventPanel;
/*  59 */     this.eventsTable = eventsTable;
/*  60 */     this.comboBox = comboBox;
/*  61 */     this.resizableBorder = new ResizableBorder();
/*  62 */     this.panels = new ArrayList<>();
/*  63 */     createPopupMenu();
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
/*     */   public void mouseClicked(MouseEvent e) {
/*  75 */     int action = e.getButton();
/*  76 */     switch (action) {
/*     */       case 1:
/*  78 */         this.comp.setBorder(this.resizableBorder);
/*     */         
/*  80 */         this.comp.requestFocus();
/*     */         try {
/*  82 */           this.comboBox.setSelectedItem(this.comp.getName());
/*  83 */         } catch (Exception ex) {}
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/*  90 */         this.popup.show(e.getComponent(), e.getX(), e.getY());
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
/*     */   public void mouseExited(MouseEvent e) {
/* 102 */     this.comp.setBorder(this.borderDefault);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {
/* 112 */     this.events.setTitleAt(1, "Events - " + this.comp.getName());
/* 113 */     JComponent c = (JComponent)e.getSource();
/* 114 */     this.propertiesTable.setComponentProperties(c);
/* 115 */     this.eventsTable.setComponentName(this.comp.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {
/* 124 */     if (!this.comp.getClass().getSimpleName().equals("JLayeredPane")) {
/* 125 */       this.comp.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
/*     */     }
/*     */   }
/*     */   
/*     */   public void paint(Graphics g) {
/* 130 */     g.setColor(Color.red);
/* 131 */     g.fillRect(this.comp.getWidth() + 30, this.comp.getHeight() + 30, 10, 10);
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
/*     */   
/*     */   public void createPopupMenu() {
/* 146 */     this.popup = new JPopupMenu();
/* 147 */     JMenuItem menuItem = new JMenuItem("Change Background color");
/* 148 */     menuItem.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 152 */             MouseActionEventClass.this.comp.setBackground(JColorChooser.showDialog(MouseActionEventClass.this.comp, "Choose background color for: " + MouseActionEventClass.this.comp.getName(), MouseActionEventClass.this.comp.getBackground()));
/* 153 */             MouseActionEventClass.this.propertiesTable.getTable().setValueAt(MouseActionEventClass.this.comp.getBackground().getRed() + "," + MouseActionEventClass.this.comp.getBackground().getGreen() + "," + MouseActionEventClass.this.comp.getBackground().getBlue(), 3, 1);
/*     */           }
/*     */         });
/* 156 */     this.popup.add(menuItem);
/* 157 */     menuItem = new JMenuItem("Change Foreground color");
/* 158 */     menuItem.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 162 */             MouseActionEventClass.this.comp.setForeground(JColorChooser.showDialog(MouseActionEventClass.this.comp, "Choos foreground color for: " + MouseActionEventClass.this.comp.getName(), MouseActionEventClass.this.comp.getForeground()));
/* 163 */             MouseActionEventClass.this.propertiesTable.getTable().setValueAt(MouseActionEventClass.this.comp.getForeground().getRed() + "," + MouseActionEventClass.this.comp.getForeground().getGreen() + "," + MouseActionEventClass.this.comp.getForeground().getBlue(), 4, 1);
/*     */           }
/*     */         });
/* 166 */     this.popup.add(menuItem);
/* 167 */     this.popup.addSeparator();
/* 168 */     menuItem = new JMenuItem("Delete component");
/* 169 */     menuItem.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 173 */             MouseActionEventClass.this.deleteComponent();
/*     */           }
/*     */         });
/* 176 */     if (!this.comp.getName().equals("GUI_project")) {
/* 177 */       this.popup.add(menuItem);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deleteComponent() {
/* 186 */     findPanels(this.comp);
/* 187 */     if (this.comp instanceof javax.swing.JPanel && (this.comp.getComponents()).length > 0) {
/* 188 */       for (Component c : this.comp.getComponents()) {
/* 189 */         this.addedComponentsMap.remove(c.getName());
/* 190 */         this.componentEvents.remove(c.getName());
/* 191 */         this.comboBox.removeItem(c.getName());
/* 192 */         this.comp.remove(c);
/*     */       } 
/*     */     }
/* 195 */     for (JComponent c : this.panels) {
/* 196 */       for (Component k : c.getComponents()) {
/* 197 */         this.addedComponentsMap.remove(k.getName());
/* 198 */         this.componentEvents.remove(k.getName());
/* 199 */         this.comboBox.removeItem(k.getName());
/* 200 */         c.remove(k);
/*     */       } 
/* 202 */       this.comp.remove(c);
/*     */     } 
/*     */     
/* 205 */     this.addedComponentsMap.remove(this.comp.getName());
/* 206 */     this.componentEvents.remove(this.comp.getName());
/* 207 */     this.comboBox.removeItem(this.comp.getName());
/* 208 */     this.parent.remove(this.comp);
/* 209 */     this.parent.revalidate();
/* 210 */     this.parent.repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void findPanels(JComponent panel) {
/* 223 */     this.step++;
/* 224 */     for (Component comp : panel.getComponents()) {
/* 225 */       if (comp instanceof javax.swing.JPanel) {
/* 226 */         this.panels.add((JComponent)comp);
/*     */       }
/*     */     } 
/* 229 */     if (this.panels.size() > 0 && this.step < this.panels.size())
/* 230 */       findPanels(this.panels.get(this.step)); 
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\MouseActionEventClass.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */