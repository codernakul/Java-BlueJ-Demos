/*     */ package GUI_Extension;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.TreeMap;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSplitPane;
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
/*     */ public class MenuPanelGenerator
/*     */   extends JSplitPane
/*     */ {
/*     */   private JComboBox<String> maskKey;
/*     */   private JComboBox<String> virtualKey;
/*     */   private Class cls;
/*     */   private Field[] fields;
/*     */   private String[] key;
/*     */   protected DynamicTreeDemo treedemo;
/*     */   private TreeMap<String, EventsInit> componentEvents;
/*     */   
/*     */   public MenuPanelGenerator(TreeMap<String, EventsInit> componentEvents) {
/*  40 */     setOrientation(0);
/*  41 */     setDividerLocation(300);
/*  42 */     this.componentEvents = componentEvents;
/*  43 */     this.treedemo = new DynamicTreeDemo();
/*  44 */     String[] maskKeyF = { "ALT_MASK", "CTRL_MASK", "SHIFT_MASK", "Select key mask" };
/*  45 */     this.cls = KeyEvent.class;
/*  46 */     this.fields = this.cls.getFields();
/*  47 */     this.key = new String[37];
/*  48 */     int i = 0;
/*  49 */     for (Field f : this.fields) {
/*  50 */       if (f.getName().length() == 4) {
/*  51 */         this.key[i] = f.getName();
/*  52 */         i++;
/*     */       } 
/*     */     } 
/*  55 */     this.key[36] = "Select key";
/*     */     
/*  57 */     MyComboListener cl = new MyComboListener();
/*  58 */     this.maskKey = new JComboBox<>(maskKeyF);
/*  59 */     this.maskKey.setSelectedIndex(this.maskKey.getItemCount() - 1);
/*  60 */     this.maskKey.setName("mask");
/*  61 */     this.maskKey.addItemListener(cl);
/*     */     
/*  63 */     this.virtualKey = new JComboBox<>(this.key);
/*  64 */     this.virtualKey.setSelectedIndex(this.virtualKey.getItemCount() - 1);
/*  65 */     this.virtualKey.setName("key");
/*  66 */     this.virtualKey.addItemListener(cl);
/*  67 */     (this.treedemo.getPanel()).maskKey = this.maskKey;
/*  68 */     (this.treedemo.getPanel()).virtualKey = this.virtualKey;
/*  69 */     JPanel panel = new JPanel();
/*  70 */     JButton button = new JButton("Add action");
/*  71 */     button.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/*  75 */             TreeMenuItem tmi = (MenuPanelGenerator.this.treedemo.getPanel()).selectedTreeItem;
/*     */             try {
/*  77 */               if (tmi.isLeaf()) {
/*  78 */                 String name = JOptionPane.showInputDialog("Enter action name", tmi.getAction());
/*     */                 
/*  80 */                 if (name != null)
/*  81 */                   tmi.setAction(name); 
/*     */               } 
/*  83 */             } catch (NullPointerException ex) {
/*  84 */               JOptionPane.showMessageDialog(null, "Node is not selected.");
/*     */             } 
/*     */           }
/*     */         });
/*  88 */     (this.treedemo.getPanel()).button = button;
/*  89 */     panel.add(this.maskKey);
/*  90 */     panel.add(this.virtualKey);
/*  91 */     panel.add(button);
/*     */ 
/*     */     
/*  94 */     setTopComponent(this.treedemo);
/*  95 */     setBottomComponent(panel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class MyComboListener
/*     */     implements ItemListener
/*     */   {
/*     */     public void itemStateChanged(ItemEvent e) {
/* 107 */       TreeMenuItem tmi = (MenuPanelGenerator.this.treedemo.getPanel()).selectedTreeItem;
/* 108 */       if (e.getStateChange() == 1) {
/* 109 */         JComboBox<String> cb = (JComboBox<String>)e.getSource();
/*     */         try {
/* 111 */           if (tmi.isLeaf()) {
/* 112 */             switch (cb.getName()) {
/*     */               case "mask":
/* 114 */                 if (!cb.getSelectedItem().toString().equals("Select key mask"))
/* 115 */                   tmi.setKeyAccM(cb.getSelectedItem().toString()); 
/*     */                 break;
/*     */               case "key":
/* 118 */                 if (!cb.getSelectedItem().toString().equals("Select key")) {
/* 119 */                   tmi.setKeyAccK(cb.getSelectedItem().toString());
/*     */                 }
/*     */                 break;
/*     */             } 
/*     */           }
/* 124 */         } catch (NullPointerException ex) {
/*     */ 
/*     */           
/* 127 */           MenuPanelGenerator.this.virtualKey.setSelectedIndex(MenuPanelGenerator.this.virtualKey.getItemCount() - 1);
/* 128 */           MenuPanelGenerator.this.maskKey.setSelectedIndex(MenuPanelGenerator.this.maskKey.getItemCount() - 1);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\MenuPanelGenerator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */