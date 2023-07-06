/*    */ package GUI_Extension;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Insets;
/*    */ import javax.swing.border.Border;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResizableBorder
/*    */   implements Border
/*    */ {
/* 19 */   private int dist = 8;
/* 20 */   private final int CORECTION = 1;
/*    */ 
/*    */ 
/*    */   
/*    */   public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
/* 25 */     g.setColor(Color.black);
/*    */     
/* 27 */     g.drawRect(x, y, width - 1, height - 1);
/* 28 */     g.fillRect(x + width - this.dist, y + height - this.dist, this.dist, this.dist);
/*    */   }
/*    */ 
/*    */   
/*    */   public Insets getBorderInsets(Component c) {
/* 33 */     return new Insets(this.dist, this.dist, this.dist, this.dist);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isBorderOpaque() {
/* 38 */     return true;
/*    */   }
/*    */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\ResizableBorder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */