/*     */ package GUI_Extension;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DynamicTreeDemo
/*     */   extends JPanel
/*     */   implements ActionListener
/*     */ {
/*  55 */   private int newNodeSuffix = 1;
/*  56 */   private static String ADD_COMMAND = "add";
/*  57 */   private static String REMOVE_COMMAND = "remove";
/*  58 */   private static String CLEAR_COMMAND = "clear";
/*     */ 
/*     */   
/*     */   private DynamicTree treePanel;
/*     */ 
/*     */ 
/*     */   
/*     */   public DynamicTreeDemo() {
/*  66 */     super(new BorderLayout());
/*     */ 
/*     */     
/*  69 */     this.treePanel = new DynamicTree();
/*  70 */     populateTree(this.treePanel);
/*  71 */     this.treePanel.expandAll(this.treePanel.tree);
/*  72 */     JButton addButton = new JButton("Add");
/*  73 */     addButton.setActionCommand(ADD_COMMAND);
/*  74 */     addButton.addActionListener(this);
/*     */     
/*  76 */     JButton removeButton = new JButton("Remove");
/*  77 */     removeButton.setActionCommand(REMOVE_COMMAND);
/*  78 */     removeButton.addActionListener(this);
/*     */     
/*  80 */     JButton clearButton = new JButton("Clear");
/*  81 */     clearButton.setActionCommand(CLEAR_COMMAND);
/*  82 */     clearButton.addActionListener(this);
/*     */ 
/*     */     
/*  85 */     this.treePanel.setPreferredSize(new Dimension(300, 150));
/*  86 */     add(this.treePanel, "Center");
/*     */     
/*  88 */     JPanel panel = new JPanel(new GridLayout(0, 3));
/*  89 */     panel.add(addButton);
/*  90 */     panel.add(removeButton);
/*  91 */     panel.add(clearButton);
/*  92 */     add(panel, "South");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populateTree(DynamicTree treePanel) {
/* 102 */     TreeMenuItem p1 = treePanel.addObject((TreeMenuItem)null, new TreeMenuItem("File"));
/* 103 */     TreeMenuItem p2 = treePanel.addObject((TreeMenuItem)null, new TreeMenuItem("Tools"));
/* 104 */     TreeMenuItem p3 = treePanel.addObject((TreeMenuItem)null, new TreeMenuItem("Help"));
/*     */     
/* 106 */     treePanel.addObject(p1, new TreeMenuItem("Open"));
/* 107 */     treePanel.addObject(p1, new TreeMenuItem("Save"));
/* 108 */     treePanel.addObject(p1, new TreeMenuItem("-"));
/* 109 */     treePanel.addObject(p1, new TreeMenuItem("Exit"));
/*     */     
/* 111 */     treePanel.addObject(p2, new TreeMenuItem("Preferences"));
/* 112 */     treePanel.addObject(p3, new TreeMenuItem("About"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 120 */     String command = e.getActionCommand();
/*     */     
/* 122 */     if (ADD_COMMAND.equals(command)) {
/*     */       
/* 124 */       String name = JOptionPane.showInputDialog("Enter action name");
/* 125 */       if (name == null || name.equals("") || name.length() <= 0) {
/*     */         return;
/*     */       }
/* 128 */       this.treePanel.addObject(new TreeMenuItem(name));
/* 129 */       this.treePanel.reloadAndExpand();
/*     */       
/* 131 */       this.treePanel.reloadAndExpand();
/*     */     }
/* 133 */     else if (REMOVE_COMMAND.equals(command)) {
/*     */       
/* 135 */       this.treePanel.removeCurrentNode();
/* 136 */       this.treePanel.reloadAndExpand();
/* 137 */     } else if (CLEAR_COMMAND.equals(command)) {
/*     */       
/* 139 */       this.treePanel.clear();
/* 140 */       this.treePanel.reloadAndExpand();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DynamicTree getPanel() {
/* 146 */     return this.treePanel;
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\DynamicTreeDemo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */