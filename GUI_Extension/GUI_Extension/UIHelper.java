/*    */ package GUI_Extension;
/*    */ 
/*    */ import java.awt.Toolkit;
/*    */ import javax.swing.ImageIcon;
/*    */ import javax.swing.JButton;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UIHelper
/*    */ {
/*    */   public static JButton createButton(String text) {
/* 17 */     JButton button = new JButton(text);
/* 18 */     button.setFocusPainted(true);
/* 19 */     button.setBorderPainted(true);
/* 20 */     button.setContentAreaFilled(true);
/* 21 */     return button;
/*    */   }
/*    */ 
/*    */   
/*    */   public static JButton createButton(String text, String icon) {
/* 26 */     return createButton(text, icon, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public static JButton createButton(String text, String icon, boolean flat) {
/* 31 */     ImageIcon iconNormal = readImageIcon(icon + ".png");
/* 32 */     ImageIcon iconHighlight = readImageIcon(icon + ".png");
/* 33 */     ImageIcon iconPressed = readImageIcon(icon + ".png");
/*    */     
/* 35 */     JButton button = new JButton(text, iconNormal);
/* 36 */     button.setFocusPainted(!flat);
/* 37 */     button.setBorderPainted(!flat);
/* 38 */     button.setContentAreaFilled(!flat);
/* 39 */     if (iconHighlight != null) {
/*    */       
/* 41 */       button.setRolloverEnabled(true);
/* 42 */       button.setRolloverIcon(iconHighlight);
/*    */     } 
/* 44 */     if (iconPressed != null)
/* 45 */       button.setPressedIcon(iconPressed); 
/* 46 */     return button;
/*    */   }
/*    */ 
/*    */   
/*    */   public static JLabel createLabel(String text, String icon) {
/* 51 */     ImageIcon iconNormal = readImageIcon(icon + ".png");
/* 52 */     JLabel label = new JLabel(text, iconNormal, 4);
/* 53 */     return label;
/*    */   }
/*    */ 
/*    */   
/*    */   public static ImageIcon readImageIcon(String filename) {
/* 58 */     return new ImageIcon(Toolkit.getDefaultToolkit().getImage(UIHelper.class.getResource("images/" + filename)));
/*    */   }
/*    */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\UIHelper.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */