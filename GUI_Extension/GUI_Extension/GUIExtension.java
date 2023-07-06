/*     */ package GUI_Extension;
/*     */ 
/*     */ import bluej.extensions.BPackage;
/*     */ import bluej.extensions.BlueJ;
/*     */ import bluej.extensions.Extension;
/*     */ import bluej.extensions.MenuGenerator;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GUIExtension
/*     */   extends Extension
/*     */ {
/*     */   private BlueJ bluej;
/*     */   
/*     */   public void startup(BlueJ bluej) {
/*  22 */     this.bluej = bluej;
/*  23 */     bluej.setMenuGenerator(new MenuBuilder());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCompatible() {
/*  32 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVersion() {
/*  39 */     return "v. 1.1 - 06.2013";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  46 */     return "Simple GUI Extension";
/*     */   }
/*     */   
/*     */   public void terminate() {
/*  50 */     System.out.println("Simple GUI Extension terminates");
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  54 */     return "Simple GUI Extension";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getURL() {
/*     */     try {
/*  63 */       return new URL("http://home.pf.jcu.cz/~gbluej");
/*  64 */     } catch (Exception eee) {
/*     */       
/*  66 */       System.out.println("Simple extension: getURL: Exception=" + eee.getMessage());
/*  67 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class MenuBuilder
/*     */     extends MenuGenerator
/*     */   {
/*  77 */     private ToolsAction aToolsAction = new ToolsAction("Simple GUI Extension");
/*     */ 
/*     */     
/*     */     public JMenuItem getToolsMenuItem(BPackage aPackage) {
/*  81 */       return new JMenuItem(this.aToolsAction);
/*     */     }
/*     */     
/*     */     class ToolsAction
/*     */       extends AbstractAction {
/*     */       public ToolsAction(String menuName) {
/*  87 */         putValue("Name", menuName);
/*     */       }
/*     */       public void actionPerformed(ActionEvent anEvent) {
/*  90 */         SwingUtilities.invokeLater(new Runnable() {
/*     */               public void run() {
/*  92 */                 new GuiExtensionWindow(GUIExtension.this.bluej);
/*     */               }
/*     */             });
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) throws IOException {
/* 104 */     System.setProperty("swing.defaultlaf", "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
/* 105 */     SwingUtilities.invokeLater(new Runnable() {
/*     */           public void run() {
/* 107 */             new GuiExtensionWindow(null);
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\GUIExtension.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */