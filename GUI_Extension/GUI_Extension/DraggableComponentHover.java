/*    */ package GUI_Extension;
/*    */ 
/*    */ import java.awt.event.MouseAdapter;
/*    */ import java.awt.event.MouseEvent;
/*    */ import javax.swing.BorderFactory;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.border.Border;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DraggableComponentHover
/*    */   extends MouseAdapter
/*    */ {
/*    */   JComponent comp;
/*    */   Border borderBlack;
/*    */   Border defaultBorder;
/*    */   
/*    */   public DraggableComponentHover(JComponent component) {
/* 25 */     this.comp = component;
/* 26 */     this.borderBlack = BorderFactory.createBevelBorder(0);
/* 27 */     this.defaultBorder = this.comp.getBorder();
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseEntered(MouseEvent e) {
/* 32 */     this.comp.setBorder(this.borderBlack);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseExited(MouseEvent e) {
/* 37 */     this.comp.setBorder(this.defaultBorder);
/*    */   }
/*    */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\DraggableComponentHover.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */