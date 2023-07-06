/*    */ package GUI_Extension;
/*    */ 
/*    */ import java.awt.Container;
/*    */ import java.awt.Cursor;
/*    */ import java.awt.Point;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseMotionListener;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.SwingUtilities;
/*    */ import javax.swing.border.Border;
/*    */ 
/*    */ public class MouseMoveEventClass
/*    */   implements MouseMotionListener {
/* 15 */   private final int COLUMN = 1;
/* 16 */   final int RESIZE_AREA = 5;
/* 17 */   final int BORDER_SIZE = 1;
/* 18 */   final int ANCHOR_LOC = 2;
/*    */   
/*    */   private JComponent comp;
/*    */   
/*    */   private JComponent parent;
/*    */   
/*    */   private Border borderDefault;
/*    */   
/*    */   private Border resizableBorder;
/*    */   
/*    */   private Rectangle rect;
/*    */   
/*    */   private boolean isMoveable;
/*    */   
/*    */   private PropertiesTable table;
/*    */   
/*    */   private Point startPos;
/*    */   
/*    */   public MouseMoveEventClass(JComponent component, PropertiesTable table) {
/* 37 */     this.comp = component;
/* 38 */     this.comp.setDoubleBuffered(true);
/* 39 */     this.borderDefault = this.comp.getBorder();
/* 40 */     this.rect = this.comp.getBounds();
/* 41 */     this.resizableBorder = new ResizableBorder();
/* 42 */     this.parent = (JComponent)this.comp.getParent();
/* 43 */     this.table = table;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseMoved(MouseEvent e) {
/* 55 */     int x = (this.comp.getBounds()).width;
/* 56 */     int y = (this.comp.getBounds()).height;
/* 57 */     if (e.getX() > x - 5 && e.getX() <= x + 5 && e.getY() > y - 5 && e.getY() < y + 5) {
/* 58 */       this.comp.setCursor(Cursor.getPredefinedCursor(5));
/* 59 */       this.comp.setBorder(this.resizableBorder);
/*    */     } else {
/*    */       
/* 62 */       this.comp.setCursor(Cursor.getDefaultCursor());
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
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseDragged(MouseEvent e) {
/* 77 */     if (SwingUtilities.isLeftMouseButton(e)) {
/*    */ 
/*    */       
/* 80 */       int x = (this.comp.getBounds()).width;
/* 81 */       int y = (this.comp.getBounds()).height;
/* 82 */       Container parentComponent = this.comp.getParent();
/* 83 */       Point p = parentComponent.getMousePosition();
/*    */       try {
/* 85 */         if (e.getX() > x - 5 && e.getX() <= p.x + 5 && e.getY() > y - 5 && e.getY() < p.y + 5) {
/* 86 */           this.comp.setSize(e.getX(), e.getY());
/* 87 */           this.table.getTable().setValueAt((this.comp.getSize()).width + ";" + (this.comp.getSize()).height, 2, 1);
/*    */           
/* 89 */           this.parent.repaint();
/*    */         } 
/* 91 */       } catch (NullPointerException ex) {}
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\MouseMoveEventClass.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */