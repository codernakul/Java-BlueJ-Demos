/*     */ package GUI_Extension;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Insets;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.SwingUtilities;
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
/*     */ public class ComponentMover
/*     */   extends MouseAdapter
/*     */ {
/*  27 */   private Insets dragInsets = new Insets(0, 0, 0, 0);
/*  28 */   private Dimension snapSize = new Dimension(1, 1);
/*  29 */   private Insets edgeInsets = new Insets(5, 5, 5, 5);
/*     */   
/*     */   private boolean changeCursor = true;
/*     */   
/*     */   private boolean autoLayout = false;
/*     */   
/*     */   private Class destinationClass;
/*     */   
/*     */   private Component destinationComponent;
/*     */   private Component destination;
/*     */   private Component source;
/*     */   private Point pressed;
/*     */   private Point location;
/*     */   private Cursor originalCursor;
/*     */   private boolean autoscrolls;
/*     */   private boolean potentialDrag;
/*  45 */   private final int RESIZE_AREA = 6;
/*     */ 
/*     */ 
/*     */   
/*     */   private JTableX table;
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentMover() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public ComponentMover(JTableX table) {
/*  58 */     this.table = table;
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
/*     */   public ComponentMover(Class destinationClass, Component... components) {
/*  72 */     this.destinationClass = destinationClass;
/*  73 */     registerComponent(components);
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
/*     */   public ComponentMover(Component destinationComponent, Component... components) {
/*  86 */     this.destinationComponent = destinationComponent;
/*  87 */     registerComponent(components);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAutoLayout() {
/*  97 */     return this.autoLayout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAutoLayout(boolean autoLayout) {
/* 107 */     this.autoLayout = autoLayout;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChangeCursor() {
/* 117 */     return this.changeCursor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChangeCursor(boolean changeCursor) {
/* 128 */     this.changeCursor = changeCursor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Insets getDragInsets() {
/* 138 */     return this.dragInsets;
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
/*     */   public void setDragInsets(Insets dragInsets) {
/* 151 */     this.dragInsets = dragInsets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Insets getEdgeInsets() {
/* 161 */     return this.edgeInsets;
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
/*     */   public void setEdgeInsets(Insets edgeInsets) {
/* 174 */     this.edgeInsets = edgeInsets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deregisterComponent(Component... components) {
/* 184 */     for (Component component : components) {
/* 185 */       component.removeMouseListener(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerComponent(Component... components) {
/* 195 */     for (Component component : components) {
/* 196 */       component.addMouseListener(this);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Dimension getSnapSize() {
/* 206 */     return this.snapSize;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSnapSize(Dimension snapSize) {
/* 216 */     if (snapSize.width < 1 || snapSize.height < 1)
/*     */     {
/* 218 */       throw new IllegalArgumentException("Snap sizes must be greater than 0");
/*     */     }
/* 220 */     this.snapSize = snapSize;
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
/*     */   public void mousePressed(MouseEvent e) {
/* 234 */     this.source = e.getComponent();
/* 235 */     int width = (this.source.getSize()).width - this.dragInsets.left - this.dragInsets.right;
/* 236 */     int height = (this.source.getSize()).height - this.dragInsets.top - this.dragInsets.bottom;
/* 237 */     Rectangle r = new Rectangle(this.dragInsets.left, this.dragInsets.top, width - 6, height - 6);
/*     */     
/* 239 */     if (r.contains(e.getPoint())) {
/* 240 */       setupForDragging(e);
/*     */     }
/*     */   }
/*     */   
/*     */   private void setupForDragging(MouseEvent e) {
/* 245 */     this.source.addMouseMotionListener(this);
/* 246 */     this.potentialDrag = true;
/*     */ 
/*     */ 
/*     */     
/* 250 */     if (this.destinationComponent != null) {
/*     */       
/* 252 */       this.destination = this.destinationComponent;
/*     */     }
/* 254 */     else if (this.destinationClass == null) {
/*     */       
/* 256 */       this.destination = this.source;
/*     */     }
/*     */     else {
/*     */       
/* 260 */       this.destination = SwingUtilities.getAncestorOfClass(this.destinationClass, this.source);
/*     */     } 
/*     */     
/* 263 */     this.pressed = e.getLocationOnScreen();
/* 264 */     this.location = this.destination.getLocation();
/*     */     
/* 266 */     if (this.changeCursor) {
/*     */       
/* 268 */       this.originalCursor = this.source.getCursor();
/* 269 */       this.source.setCursor(Cursor.getPredefinedCursor(13));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 275 */     if (this.destination instanceof JComponent) {
/*     */       
/* 277 */       JComponent jc = (JComponent)this.destination;
/* 278 */       this.autoscrolls = jc.getAutoscrolls();
/* 279 */       jc.setAutoscrolls(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseDragged(MouseEvent e) {
/* 290 */     Point dragged = e.getLocationOnScreen();
/* 291 */     int dragX = getDragDistance(dragged.x, this.pressed.x, this.snapSize.width);
/* 292 */     int dragY = getDragDistance(dragged.y, this.pressed.y, this.snapSize.height);
/*     */     
/* 294 */     int locationX = this.location.x + dragX;
/* 295 */     int locationY = this.location.y + dragY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     while (locationX < this.edgeInsets.left) {
/* 302 */       locationX += this.snapSize.width;
/*     */     }
/* 304 */     while (locationY < this.edgeInsets.top) {
/* 305 */       locationY += this.snapSize.height;
/*     */     }
/* 307 */     Dimension d = getBoundingSize(this.destination);
/*     */     
/* 309 */     while (locationX + (this.destination.getSize()).width + this.edgeInsets.right > d.width) {
/* 310 */       locationX -= this.snapSize.width;
/*     */     }
/* 312 */     while (locationY + (this.destination.getSize()).height + this.edgeInsets.bottom > d.height) {
/* 313 */       locationY -= this.snapSize.height;
/*     */     }
/*     */ 
/*     */     
/* 317 */     this.destination.setLocation(locationX, locationY);
/* 318 */     this.table.setValueAt((this.destination.getLocation()).x + ";" + (this.destination.getLocation()).y, 1, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getDragDistance(int larger, int smaller, int snapSize) {
/* 327 */     int halfway = snapSize / 2;
/* 328 */     int drag = larger - smaller;
/* 329 */     drag += (drag < 0) ? -halfway : halfway;
/* 330 */     drag = drag / snapSize * snapSize;
/*     */     
/* 332 */     return drag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Dimension getBoundingSize(Component source) {
/* 340 */     if (source instanceof java.awt.Window) {
/*     */       
/* 342 */       GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
/* 343 */       Rectangle bounds = env.getMaximumWindowBounds();
/* 344 */       return new Dimension(bounds.width, bounds.height);
/*     */     } 
/*     */ 
/*     */     
/* 348 */     return source.getParent().getSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {
/* 358 */     if (!this.potentialDrag)
/*     */       return; 
/* 360 */     this.source.removeMouseMotionListener(this);
/* 361 */     this.potentialDrag = false;
/*     */     
/* 363 */     if (this.changeCursor) {
/* 364 */       this.source.setCursor(this.originalCursor);
/*     */     }
/* 366 */     if (this.destination instanceof JComponent)
/*     */     {
/* 368 */       ((JComponent)this.destination).setAutoscrolls(this.autoscrolls);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 373 */     if (this.autoLayout)
/*     */     {
/* 375 */       if (this.destination instanceof JComponent) {
/*     */         
/* 377 */         ((JComponent)this.destination).revalidate();
/*     */       }
/*     */       else {
/*     */         
/* 381 */         this.destination.validate();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\ComponentMover.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */