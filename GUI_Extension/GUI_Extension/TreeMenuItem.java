/*     */ package GUI_Extension;
/*     */ 
/*     */ import java.util.Enumeration;
/*     */ import java.util.LinkedList;
/*     */ import javax.swing.tree.MutableTreeNode;
/*     */ import javax.swing.tree.TreeNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TreeMenuItem
/*     */   implements MutableTreeNode
/*     */ {
/*     */   private String name;
/*  17 */   private String[] keyAcceler = new String[2];
/*     */   
/*     */   private String actionMethodName;
/*     */   
/*     */   LinkedList<TreeMenuItem> child;
/*     */   
/*     */   TreeMenuItem parent;
/*     */ 
/*     */   
/*     */   public TreeMenuItem(String name) {
/*  27 */     this.name = name;
/*  28 */     this.child = new LinkedList<>();
/*  29 */     this.parent = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TreeMenuItem() {
/*  35 */     this.child = new LinkedList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  42 */     this.name = name;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  47 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeyAccM(String mask) {
/*  53 */     this.keyAcceler[0] = mask;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKeyAccK(String key) {
/*  58 */     this.keyAcceler[1] = key;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getKeyAcc() {
/*  63 */     return this.keyAcceler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction(String action) {
/*  69 */     this.actionMethodName = action;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAction() {
/*  74 */     return this.actionMethodName;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  78 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TreeNode getChildAt(int childIndex) {
/*  87 */     if (childIndex < 0)
/*  88 */       return null; 
/*  89 */     if (childIndex >= this.child.size())
/*  90 */       return null; 
/*  91 */     return this.child.get(childIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getChildCount() {
/*  99 */     return this.child.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TreeMenuItem getParent() {
/* 107 */     return this.parent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getIndex(TreeNode node) {
/* 116 */     int i = 0;
/* 117 */     if (node == null)
/* 118 */       return -1; 
/* 119 */     for (TreeNode t : this.child) {
/* 120 */       if (t.equals(node)) {
/* 121 */         return i;
/*     */       }
/* 123 */       i++;
/*     */     } 
/* 125 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAllowsChildren() {
/* 133 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLeaf() {
/* 142 */     if (this.child.size() == 0) {
/* 143 */       return true;
/*     */     }
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Enumeration children() {
/* 151 */     return (Enumeration)this.child;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllChildren() {
/* 158 */     this.child.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeChild(TreeMenuItem tmi) {
/* 166 */     this.child.remove(tmi);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertChild(TreeMenuItem tmi) {
/* 174 */     this.child.addLast(tmi);
/* 175 */     tmi.parent = this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insert(MutableTreeNode child1, int index) {
/* 184 */     this.child.add(index, (TreeMenuItem)child1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int index) {
/* 192 */     this.child.remove(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(MutableTreeNode node) {
/* 201 */     this.child.remove(node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUserObject(Object object) {
/* 210 */     this.name = object.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeFromParent() {
/* 215 */     if (this.parent == null) {
/*     */       return;
/*     */     }
/* 218 */     this.parent.remove(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParent(MutableTreeNode newParent) {
/* 226 */     this.parent = (TreeMenuItem)newParent;
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\TreeMenuItem.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */