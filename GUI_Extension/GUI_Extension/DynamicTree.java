/*     */ package GUI_Extension;
/*     */ 
/*     */ import java.awt.Cursor;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTree;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.event.TreeModelEvent;
/*     */ import javax.swing.event.TreeModelListener;
/*     */ import javax.swing.event.TreeSelectionEvent;
/*     */ import javax.swing.event.TreeSelectionListener;
/*     */ import javax.swing.tree.DefaultTreeModel;
/*     */ import javax.swing.tree.TreePath;
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
/*     */ public class DynamicTree
/*     */   extends JPanel
/*     */ {
/*     */   protected TreeMenuItem rootNode;
/*     */   protected DefaultTreeModel treeModel;
/*     */   protected JTree tree;
/*     */   protected TreeMenuItem selectedTreeItem;
/*     */   protected JComboBox<String> maskKey;
/*     */   protected JComboBox<String> virtualKey;
/*     */   protected JButton button;
/*     */   protected ArrayList<TreeMenuItem> itemlist;
/*  77 */   private Toolkit toolkit = Toolkit.getDefaultToolkit();
/*     */   
/*     */   private JPopupMenu popUp;
/*     */   
/*     */   private JMenuItem mi;
/*     */ 
/*     */   
/*     */   public DynamicTree() {
/*  85 */     super(new GridLayout(1, 0));
/*  86 */     setPopUp();
/*  87 */     this.rootNode = new TreeMenuItem("Menu");
/*  88 */     this.treeModel = new DefaultTreeModel(this.rootNode);
/*     */     
/*  90 */     this.treeModel.addTreeModelListener(new MyTreeModelListener());
/*  91 */     this.tree = new JTree(this.treeModel);
/*  92 */     this.tree.setEditable(true);
/*  93 */     this.tree.getSelectionModel().setSelectionMode(1);
/*     */     
/*  95 */     this.tree.setShowsRootHandles(true);
/*     */ 
/*     */     
/*  98 */     this.tree.addKeyListener(new TreeKeyEvent());
/*  99 */     this.tree.addMouseListener(new MouseAdapter()
/*     */         {
/*     */           public void mouseReleased(MouseEvent e)
/*     */           {
/* 103 */             if (e.isPopupTrigger()) {
/* 104 */               DynamicTree.this.popUp.show((JComponent)e.getSource(), e.getX(), e.getY());
/*     */             }
/*     */           }
/*     */         });
/* 108 */     this.itemlist = new ArrayList<>();
/* 109 */     this.tree.addTreeSelectionListener(new MyTreeSelectionListener());
/* 110 */     this.tree.setCursor(new Cursor(12));
/* 111 */     JScrollPane scrollPane = new JScrollPane(this.tree);
/* 112 */     add(scrollPane);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void expandAll(JTree tree) {
/* 122 */     int row = 0;
/* 123 */     while (row < tree.getRowCount()) {
/* 124 */       tree.expandRow(row);
/* 125 */       row++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getAllObject() {
/* 133 */     this.itemlist.clear();
/* 134 */     int row = 0;
/* 135 */     while (row < this.tree.getRowCount()) {
/* 136 */       TreeMenuItem tm = (TreeMenuItem)this.tree.getPathForRow(row).getLastPathComponent();
/* 137 */       for (TreeMenuItem t : this.itemlist) {
/* 138 */         if (tm.getName().toLowerCase().equals(t.getName().toLowerCase()))
/* 139 */           JOptionPane.showMessageDialog(null, "Node with same name already exist (even in upper or lover case). \nPlease rename it."); 
/*     */       } 
/* 141 */       this.itemlist.add(tm);
/* 142 */       row++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 149 */     this.rootNode.removeAllChildren();
/* 150 */     this.treeModel.reload();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeCurrentNode() {
/* 155 */     TreePath currentSelection = this.tree.getSelectionPath();
/* 156 */     if (currentSelection != null) {
/* 157 */       TreeMenuItem currentNode = (TreeMenuItem)currentSelection.getLastPathComponent();
/*     */       
/* 159 */       TreeMenuItem parent = currentNode.getParent();
/* 160 */       if (parent != null) {
/* 161 */         parent.removeChild(currentNode);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 167 */     this.toolkit.beep();
/*     */   }
/*     */   
/*     */   public TreeMenuItem getCurrentNode() {
/* 171 */     TreePath currentSelection = this.tree.getSelectionPath();
/* 172 */     TreeMenuItem currentNode = null;
/* 173 */     if (currentSelection != null)
/* 174 */       currentNode = (TreeMenuItem)currentSelection.getLastPathComponent(); 
/* 175 */     return currentNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TreePath getCurrentPath() {
/* 183 */     TreePath tp = this.tree.getSelectionPath();
/* 184 */     return tp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TreeMenuItem addObject(TreeMenuItem child) {
/* 191 */     TreeMenuItem parentNode = null;
/* 192 */     TreePath parentPath = this.tree.getSelectionPath();
/*     */     
/* 194 */     if (parentPath == null) {
/* 195 */       parentNode = this.rootNode;
/*     */     } else {
/* 197 */       parentNode = (TreeMenuItem)parentPath.getLastPathComponent();
/*     */     } 
/*     */ 
/*     */     
/* 201 */     return addObject(parentNode, child, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public TreeMenuItem addObject(TreeMenuItem parent, TreeMenuItem child) {
/* 206 */     return addObject(parent, child, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TreeMenuItem addObject(TreeMenuItem parent, TreeMenuItem child, boolean shouldBeVisible) {
/* 215 */     if (parent == null || child == null) {
/* 216 */       parent = this.rootNode;
/*     */     }
/*     */ 
/*     */     
/* 220 */     parent.insertChild(child);
/*     */ 
/*     */     
/* 223 */     if (shouldBeVisible) {
/* 224 */       this.tree.scrollPathToVisible(new TreePath(child));
/*     */     }
/* 226 */     return child;
/*     */   }
/*     */ 
/*     */   
/*     */   class MyTreeModelListener
/*     */     implements TreeModelListener
/*     */   {
/*     */     public void treeNodesChanged(TreeModelEvent e) {}
/*     */ 
/*     */     
/*     */     public void treeNodesInserted(TreeModelEvent e) {}
/*     */ 
/*     */     
/*     */     public void treeNodesRemoved(TreeModelEvent e) {}
/*     */ 
/*     */     
/*     */     public void treeStructureChanged(TreeModelEvent e) {}
/*     */   }
/*     */   
/*     */   class TreeKeyEvent
/*     */     implements KeyListener
/*     */   {
/*     */     public void keyTyped(KeyEvent e) {}
/*     */     
/*     */     public void keyPressed(KeyEvent e) {
/*     */       String name;
/* 252 */       int key = e.getKeyCode();
/* 253 */       switch (key) {
/*     */         case 10:
/* 255 */           name = JOptionPane.showInputDialog("Enter node name");
/*     */           
/* 257 */           if (name == null || name.equals("") || name.length() <= 0) {
/*     */             break;
/*     */           }
/*     */           
/* 261 */           DynamicTree.this.addObject(new TreeMenuItem(name));
/* 262 */           DynamicTree.this.getAllObject();
/* 263 */           DynamicTree.this.reloadAndExpand();
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void keyReleased(KeyEvent e) {
/* 272 */       int key = e.getKeyCode();
/* 273 */       switch (key) {
/*     */         case 127:
/* 275 */           DynamicTree.this.removeCurrentNode();
/* 276 */           DynamicTree.this.reloadAndExpand();
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPopUp() {
/* 288 */     this.popUp = new JPopupMenu();
/* 289 */     this.mi = new JMenuItem("Insert Node");
/* 290 */     this.mi.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 294 */             String name = JOptionPane.showInputDialog("Enter action name");
/* 295 */             if (name == null || name.equals("") || name.length() <= 0) {
/*     */               return;
/*     */             }
/* 298 */             DynamicTree.this.addObject(new TreeMenuItem(name));
/* 299 */             DynamicTree.this.reloadAndExpand();
/* 300 */             DynamicTree.this.getAllObject();
/*     */             
/* 302 */             DynamicTree.this.reloadAndExpand();
/*     */           }
/*     */         });
/* 305 */     this.popUp.add(this.mi);
/* 306 */     this.mi = new JMenuItem("Delete Node");
/* 307 */     this.mi.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 311 */             DynamicTree.this.removeCurrentNode();
/* 312 */             DynamicTree.this.reloadAndExpand();
/*     */           }
/*     */         });
/* 315 */     this.popUp.add(this.mi);
/* 316 */     this.mi = new JMenuItem("Rename");
/* 317 */     this.mi.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 321 */             SwingUtilities.invokeLater(new Runnable() {
/*     */                   public void run() {
/* 323 */                     DynamicTree.this.tree.startEditingAtPath(DynamicTree.this.tree.getSelectionPath());
/*     */                   }
/*     */                 });
/*     */           }
/*     */         });
/* 328 */     this.popUp.add(this.mi);
/* 329 */     this.popUp.setOpaque(true);
/* 330 */     this.popUp.setLightWeightPopupEnabled(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reloadAndExpand() {
/* 338 */     this.treeModel.reload();
/* 339 */     expandAll(this.tree);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class MyTreeSelectionListener
/*     */     implements TreeSelectionListener
/*     */   {
/*     */     public void valueChanged(TreeSelectionEvent e) {
/* 353 */       DynamicTree.this.selectedTreeItem = (TreeMenuItem)DynamicTree.this.tree.getLastSelectedPathComponent();
/* 354 */       if (DynamicTree.this.selectedTreeItem == null)
/* 355 */         return;  DynamicTree.this.maskKey.setSelectedIndex(DynamicTree.this.maskKey.getItemCount() - 1);
/* 356 */       DynamicTree.this.virtualKey.setSelectedIndex(DynamicTree.this.virtualKey.getItemCount() - 1);
/* 357 */       if (DynamicTree.this.selectedTreeItem.isLeaf() && !DynamicTree.this.selectedTreeItem.getName().contains("-")) {
/* 358 */         DynamicTree.this.maskKey.setEnabled(true);
/* 359 */         DynamicTree.this.virtualKey.setEnabled(true);
/* 360 */         DynamicTree.this.button.setEnabled(true);
/*     */         
/* 362 */         if (DynamicTree.this.selectedTreeItem.getKeyAcc()[0] != null || DynamicTree.this.selectedTreeItem.getKeyAcc()[1] != null) {
/* 363 */           DynamicTree.this.maskKey.setSelectedItem(DynamicTree.this.selectedTreeItem.getKeyAcc()[0]);
/* 364 */           DynamicTree.this.virtualKey.setSelectedItem(DynamicTree.this.selectedTreeItem.getKeyAcc()[1]);
/*     */         } 
/*     */       } else {
/*     */         
/* 368 */         DynamicTree.this.maskKey.setEnabled(false);
/* 369 */         DynamicTree.this.virtualKey.setEnabled(false);
/* 370 */         DynamicTree.this.button.setEnabled(false);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String generateMenu() {
/* 382 */     getAllObject();
/* 383 */     String text = "";
/*     */     
/* 385 */     text = text + "\t//method for generate menu\n\tpublic void generateMenu(){\n\t\tmenuBar = new JMenuBar();\n\n";
/*     */     
/*     */     int i;
/* 388 */     for (i = 1; i < this.itemlist.size(); i++) {
/* 389 */       TreeMenuItem tm = this.itemlist.get(i);
/* 390 */       if (!tm.isLeaf()) {
/* 391 */         text = text + "\t\tJMenu " + tm.getName().toLowerCase().replace(" ", "") + " = new JMenu(\"" + tm.getName() + "\");\n";
/*     */       }
/*     */     } 
/*     */     
/* 395 */     text = text + "\n";
/* 396 */     for (i = 1; i < this.itemlist.size(); i++) {
/* 397 */       TreeMenuItem tm = this.itemlist.get(i);
/* 398 */       if (tm.isLeaf() && 
/* 399 */         !tm.getName().equals("-")) {
/*     */         
/* 401 */         text = text + "\t\tJMenuItem " + tm.getName().toLowerCase().replace(" ", "") + " = new JMenuItem(\"" + tm.getName() + "   \");\n";
/* 402 */         if (tm.getKeyAcc()[1] != null || tm.getKeyAcc()[0] != null) {
/* 403 */           text = text + "\t\t" + tm.getName().toLowerCase() + ".setAccelerator(KeyStroke.getKeyStroke(KeyEvent." + tm.getKeyAcc()[1] + ",Event." + tm.getKeyAcc()[0] + "));\n\n";
/*     */         }
/*     */       } 
/*     */     } 
/* 407 */     text = text + "\n";
/*     */     
/* 409 */     for (i = 1; i < this.itemlist.size(); i++) {
/* 410 */       TreeMenuItem tm = this.itemlist.get(i);
/* 411 */       if (tm.isLeaf() && 
/* 412 */         !tm.getName().equals("-") && 
/* 413 */         tm.getAction() != null) {
/* 414 */         text = text + "\t\t//Setings action for menu item\n\t\t//Call defined method\n\t\t" + tm.getName().toLowerCase() + ".addActionListener(new ActionListener() {\n" + "\t\t\tpublic void actionPerformed(ActionEvent evt) {\n" + "\t\t\t\t" + tm.getAction() + "(evt);\n" + "\t\t\t}\n" + "\t\t});\n\n";
/*     */       }
/*     */     } 
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
/* 427 */     text = text + "\n";
/* 428 */     for (i = 1; i < this.itemlist.size(); i++) {
/* 429 */       TreeMenuItem tm = this.itemlist.get(i);
/* 430 */       if (!tm.getParent().getName().equals(this.rootNode.getName())) {
/* 431 */         if (tm.getName().equals("-")) {
/* 432 */           text = text + "\t\t" + tm.getParent().getName().toLowerCase() + ".addSeparator();\n";
/*     */         } else {
/* 434 */           text = text + "\t\t" + tm.getParent().getName().toLowerCase() + ".add(" + tm.getName().toLowerCase() + ");\n";
/*     */         } 
/*     */       }
/*     */     } 
/* 438 */     text = text + "\n";
/* 439 */     for (i = 1; i < this.itemlist.size(); i++) {
/* 440 */       TreeMenuItem tm = this.itemlist.get(i);
/* 441 */       if (!tm.isLeaf() && tm.getParent().getName().equals(this.rootNode.getName())) {
/* 442 */         text = text + "\t\tmenuBar.add(" + tm.getName().toLowerCase() + ");\n";
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 447 */     text = text + "\t}";
/* 448 */     text = text + "\n";
/* 449 */     for (i = 1; i < this.itemlist.size(); i++) {
/* 450 */       TreeMenuItem tm = this.itemlist.get(i);
/* 451 */       if (tm.isLeaf() && 
/* 452 */         !tm.getName().equals("-") && 
/* 453 */         tm.getAction() != null) {
/* 454 */         text = text + "\t//Method for " + tm.getName() + " from menu" + tm.getParent().getName() + " \n" + "\tprivate void " + tm.getAction() + " (ActionEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 463 */     text = text + "\n";
/*     */     
/* 465 */     return text;
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\DynamicTree.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */