/*     */ package GUI_Extension;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventsInit
/*     */ {
/*  11 */   private String component = "";
/*     */   
/*  13 */   private String[][] events = new String[][] { { "actionPerformed", "" }, { "keyPressed", "" }, { "keyReleased", "" }, { "keyTyped", "" }, { "mouseClicked", "" }, { "mouseEntered", "" }, { "mouseExited", "" }, { "mousePressed", "" }, { "mouseRelesed", "" }, { "mouseDragged", "" }, { "mouseMoved", "" }, { "mouseWheelMoved", "" } };
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
/*     */   public EventsInit(String name) {
/*  25 */     this.component = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActionValue(int action, String name) {
/*  34 */     this.events[action][1] = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getActionValue(int action) {
/*  44 */     return this.events[action][1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComponentName(String component) {
/*  53 */     this.component = component;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getComponentName() {
/*  59 */     return this.component;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getActionPerformed() {
/*  70 */     String text = "";
/*  71 */     if (!this.events[0][1].equals("")) {
/*  72 */       text = text + "\t\t//Set action for button click\n\t\t//Call defined method\n\t\t" + this.component + ".addActionListener(new ActionListener() {\n" + "\t\t\tpublic void actionPerformed(ActionEvent evt) {\n" + "\t\t\t\t" + this.events[0][1] + "(evt);\n" + "\t\t\t}\n" + "\t\t});\n\n";
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
/*  83 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getKeyEvents() {
/*  91 */     String text = "";
/*  92 */     if (!this.events[1][1].equals("") || !this.events[2][1].equals("") || !this.events[3][1].equals("")) {
/*  93 */       text = text + "\t\t//Set action for key events\n\t\t//Call defined method\n\t\t" + this.component + ".addKeyListener(new KeyAdapter() {\n";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       if (!this.events[1][1].equals("")) {
/*  99 */         text = text + "\t\t\tpublic void keyPressed(KeyEvent evt) {\n\t\t\t\t" + this.events[1][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 103 */       if (!this.events[2][1].equals("")) {
/* 104 */         text = text + "\t\t\tpublic void keyReleased(KeyEvent evt){\n\t\t\t\t" + this.events[2][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 108 */       if (!this.events[3][1].equals("")) {
/* 109 */         text = text + "\t\t\tpublic void keyTyped(KeyEvent evt){\n\t\t\t\t" + this.events[3][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 113 */       text = text + "\t\t});\n\n";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 118 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMouseEvents() {
/* 126 */     String text = "";
/* 127 */     if (!this.events[4][1].equals("") || !this.events[5][1].equals("") || !this.events[6][1].equals("") || !this.events[7][1].equals("") || !this.events[8][1].equals("")) {
/* 128 */       text = text + "\t\t//Set methods for mouse events\n\t\t//Call defined methods\n\t\t" + this.component + ".addMouseListener(new MouseAdapter() {\n";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 133 */       if (!this.events[4][1].equals("")) {
/* 134 */         text = text + "\t\t\tpublic void mouseClicked(MouseEvent evt) {\n\t\t\t\t" + this.events[4][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 138 */       if (!this.events[5][1].equals("")) {
/* 139 */         text = text + "\t\t\tpublic void mouseEntered(MouseEvent evt){\n\t\t\t\t" + this.events[5][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 143 */       if (!this.events[6][1].equals("")) {
/* 144 */         text = text + "\t\t\tpublic void mouseExited(MouseEvent evt){\n\t\t\t\t" + this.events[6][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 148 */       if (!this.events[7][1].equals("")) {
/* 149 */         text = text + "\t\t\tpublic void mousePressed(MouseEvent evt{\n\t\t\t\t" + this.events[7][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 153 */       if (!this.events[8][1].equals("")) {
/* 154 */         text = text + "\t\t\tpublic void mouseReleased(MouseEvent evt){\n\t\t\t\t" + this.events[8][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 158 */       text = text + "\t\t});\n\n";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 163 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMouseMoveEvents() {
/* 172 */     String text = "";
/* 173 */     if (!this.events[9][1].equals("") || !this.events[10][1].equals("")) {
/* 174 */       text = text + "\t\t//Set methods for mouse motion events\n\t\t//Call defined method\n\t\t" + this.component + ".addMouseMotionListener(new MouseAdapter() {\n";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 179 */       if (!this.events[4][1].equals("")) {
/* 180 */         text = text + "\t\t\tpublic void mouseDragged(MouseEvent evt) {\n\t\t\t\t" + this.events[9][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 184 */       if (!this.events[5][1].equals("")) {
/* 185 */         text = text + "\t\t\tpublic void mouseMoved(MouseEvent evt){\n\t\t\t\t" + this.events[10][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 189 */       text = text + "\t\t});\n\n";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 194 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMouseWheelEvent() {
/* 202 */     String text = "";
/* 203 */     if (!this.events[11][1].equals("")) {
/* 204 */       text = text + "\t\t//Set methods for mouse wheel events\n\t\t//Call defined method\n\t\t" + this.component + ".addMouseWheelListener(new MouseWheelListener() {\n";
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 209 */       if (!this.events[11][1].equals("")) {
/* 210 */         text = text + "\t\t\tpublic void mouseWheelMoved(MouseWheelEvent evt) {\n\t\t\t\t" + this.events[11][1] + "(evt);\n" + "\t\t\t}\n";
/*     */       }
/*     */ 
/*     */       
/* 214 */       text = text + "\t\t});\n\n";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 219 */     return text;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMethods() {
/* 227 */     String text = "";
/*     */     
/* 229 */     for (int i = 0; i < this.events.length; i++) {
/* 230 */       if (!this.events[i][1].equals("")) {
/* 231 */         switch (i) {
/*     */           case 0:
/* 233 */             text = text + "\t//Method " + this.events[0][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[0][1] + " (ActionEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 1:
/* 239 */             text = text + "\t//Method " + this.events[1][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[1][1] + " (KeyEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 2:
/* 245 */             text = text + "\t//Method " + this.events[2][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[2][1] + " (KeyEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 3:
/* 251 */             text = text + "\t//Method " + this.events[3][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[3][1] + " (KeyEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 4:
/* 257 */             text = text + "\t//Method " + this.events[4][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[4][1] + " (MouseEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 5:
/* 263 */             text = text + "\t//Method " + this.events[5][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[5][1] + " (MouseEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 6:
/* 269 */             text = text + "\t//Method " + this.events[6][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[6][1] + " (MouseEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 7:
/* 275 */             text = text + "\t//Method " + this.events[7][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[7][1] + " (MouseEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 8:
/* 281 */             text = text + "\t//Method " + this.events[8][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[8][1] + " (MouseEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 9:
/* 287 */             text = text + "\t//Method " + this.events[9][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[9][1] + " (MouseEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 10:
/* 293 */             text = text + "\t//Method " + this.events[10][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[10][1] + " (MouseEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 11:
/* 299 */             text = text + "\t//Method " + this.events[11][0] + " for " + this.component + "\n" + "\tprivate void " + this.events[11][1] + " (MouseWheelEvent evt) {\n" + "\t\t\t//TODO\n" + "\t}\n\n";
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */     } 
/* 310 */     return text;
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\EventsInit.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */