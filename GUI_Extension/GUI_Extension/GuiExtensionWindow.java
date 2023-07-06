/*     */ package GUI_Extension;
/*     */ 
/*     */ import bluej.extensions.BlueJ;
/*     */ import bluej.extensions.ProjectNotOpenException;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.datatransfer.DataFlavor;
/*     */ import java.awt.datatransfer.StringSelection;
/*     */ import java.awt.datatransfer.Transferable;
/*     */ import java.awt.datatransfer.UnsupportedFlavorException;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.util.TreeMap;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFileChooser;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JLayeredPane;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSplitPane;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.JTextPane;
/*     */ import javax.swing.KeyStroke;
/*     */ import javax.swing.TransferHandler;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
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
/*     */ public class GuiExtensionWindow
/*     */   extends JFrame
/*     */ {
/*     */   private JPanel leftTopComponentPane;
/*     */   private JPanel rightMainPanel;
/*     */   private JLayeredPane canvas;
/*     */   private JTextPane codePane;
/*     */   private JScrollPane scroll;
/*     */   private JScrollPane codeScroll;
/*     */   private JTabbedPane tabbedPane;
/*     */   private PropertiesTable propertiesTable;
/*     */   private TreeMap<String, JComponent> addedComponents;
/*     */   private TreeMap<String, EventsInit> componentEvents;
/*     */   private JComboBox<String> combobox;
/*     */   private EventsTable eventsTable;
/*     */   private JTabbedPane propertiesEventPane;
/*     */   private JMenuBar menuBar;
/*     */   private BlueJ bluej;
/*     */   private GenerateCode generate;
/*     */   private CanvasAddComponent addComp;
/*     */   private ComponentMover cm;
/*  81 */   private TransferHandler tr = new PanelHandler();
/*  82 */   private int layer = 0;
/*     */   
/*     */   private MenuPanelGenerator mpg;
/*     */   private JFileChooser fc;
/*  86 */   private final String[] COMPONENTS_ = new String[] { "Button.png", "ComboBox.png", "EditorPane.png", "CheckBox.png", "Label.png", "List.png", "Panel.png", "PasswordField.png", "RadioButton.png", "TextArea.png", "TextField.png" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GuiExtensionWindow(BlueJ bluej) {
/*     */     try {
/*  97 */       for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
/*  98 */         if ("Nimbus".equals(info.getName())) {
/*  99 */           UIManager.setLookAndFeel(info.getClassName());
/*     */           break;
/*     */         } 
/*     */       } 
/* 103 */     } catch (Exception e) {
/*     */       try {
/* 105 */         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*     */       }
/* 107 */       catch (Exception ex) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 113 */     setTitle("GUI designer");
/* 114 */     setSize(1024, 768);
/* 115 */     setPreferredSize(new Dimension(800, 600));
/*     */ 
/*     */     
/* 118 */     this.bluej = bluej;
/* 119 */     this.fc = new JFileChooser();
/* 120 */     this.fc.setDialogTitle("Save file as...");
/* 121 */     createMenu();
/* 122 */     setJMenuBar(this.menuBar);
/*     */ 
/*     */     
/* 125 */     this.addedComponents = new TreeMap<>();
/* 126 */     this.componentEvents = new TreeMap<>();
/* 127 */     this.combobox = new JComboBox<>();
/*     */     
/* 129 */     this.eventsTable = new EventsTable(this.componentEvents);
/* 130 */     this.propertiesEventPane = new JTabbedPane();
/* 131 */     this.rightMainPanel = new JPanel(null);
/* 132 */     this.rightMainPanel.setName("panel");
/* 133 */     this.rightMainPanel.setBackground(Color.WHITE);
/*     */     
/* 135 */     this.canvas = new JLayeredPane();
/* 136 */     this.canvas.setName("GUI_project");
/* 137 */     this.canvas.setPreferredSize(new Dimension(500, 400));
/* 138 */     this.canvas.setMaximumSize(new Dimension(3180, 3000));
/* 139 */     this.canvas.setMinimumSize(new Dimension(200, 80));
/* 140 */     this.canvas.setOpaque(true);
/* 141 */     this.canvas.setBackground(Color.LIGHT_GRAY);
/* 142 */     this.canvas.setBorder(BorderFactory.createLineBorder(new Color(193, 225, 252), 2));
/* 143 */     this.canvas.setBounds(10, 60, 500, 400);
/*     */ 
/*     */     
/* 146 */     this.canvas.setTransferHandler(this.tr);
/* 147 */     this.propertiesTable = new PropertiesTable(this.addedComponents, this.componentEvents, this.combobox);
/* 148 */     this.cm = new ComponentMover(this.propertiesTable.getTable());
/* 149 */     this.canvas.addMouseListener(new MouseActionEventClass(this.canvas, this.addedComponents, this.propertiesTable, this.propertiesEventPane, this.componentEvents, this.eventsTable, this.combobox));
/*     */     
/* 151 */     this.canvas.addMouseMotionListener(new MouseMoveEventClass(this.canvas, this.propertiesTable));
/*     */     
/* 153 */     this.combobox.addItemListener(new ComboListener(this.propertiesTable, this.canvas, this.eventsTable, this.propertiesEventPane));
/* 154 */     this.combobox.setName("comboComponents");
/* 155 */     this.combobox.setBounds(10, 20, 120, 30);
/*     */ 
/*     */     
/* 158 */     this.rightMainPanel.add(this.combobox);
/* 159 */     this.rightMainPanel.add(this.canvas);
/* 160 */     this.rightMainPanel.setBorder(BorderFactory.createTitledBorder("Design"));
/*     */ 
/*     */     
/* 163 */     JSplitPane splitPane = new JSplitPane(1);
/* 164 */     JSplitPane splitPaneV = new JSplitPane(0);
/* 165 */     splitPane.setDividerLocation(250);
/* 166 */     splitPaneV.setDividerLocation((getSize()).height / 2);
/* 167 */     GridLayout gridLayout = new GridLayout(0, 2, 10, 10);
/* 168 */     this.leftTopComponentPane = new JPanel();
/* 169 */     this.leftTopComponentPane.setLayout(new BoxLayout(this.leftTopComponentPane, 3));
/* 170 */     this.leftTopComponentPane.setBackground(new Color(212, 208, 200));
/* 171 */     this.leftTopComponentPane.setPreferredSize(new Dimension(200, 350));
/* 172 */     this.leftTopComponentPane.setMaximumSize(new Dimension(3180, 320));
/* 173 */     this.leftTopComponentPane.setMinimumSize(new Dimension(200, 200));
/* 174 */     this.addComp = new CanvasAddComponent();
/* 175 */     setDraggableComponents();
/*     */     
/* 177 */     this.leftTopComponentPane.setDoubleBuffered(true);
/* 178 */     this.scroll = new JScrollPane(this.leftTopComponentPane);
/* 179 */     this.scroll.setHorizontalScrollBarPolicy(30);
/* 180 */     this.scroll.setVerticalScrollBarPolicy(20);
/* 181 */     this.scroll.setPreferredSize(new Dimension(300, 370));
/*     */     
/* 183 */     JPanel leftBottomPane = new JPanel(new BorderLayout());
/*     */ 
/*     */     
/* 186 */     leftBottomPane.setBackground(new Color(236, 236, 236));
/* 187 */     leftBottomPane.setPreferredSize(new Dimension(200, 300));
/* 188 */     leftBottomPane.setMaximumSize(new Dimension(3180, 320));
/* 189 */     leftBottomPane.setMinimumSize(new Dimension(200, 80));
/*     */     
/* 191 */     JScrollPane leftBottomScrollPane = new JScrollPane(leftBottomPane);
/* 192 */     leftBottomScrollPane.setHorizontalScrollBarPolicy(30);
/* 193 */     leftBottomScrollPane.setVerticalScrollBarPolicy(20);
/* 194 */     leftBottomScrollPane.setPreferredSize(new Dimension(300, 370));
/*     */ 
/*     */ 
/*     */     
/* 198 */     JPanel propertiesPane = new JPanel(new BorderLayout());
/* 199 */     propertiesPane.setBackground(Color.red);
/* 200 */     JPanel eventPane = new JPanel(new BorderLayout());
/*     */     
/* 202 */     eventPane.add(this.eventsTable.getTable(), "Center");
/*     */     
/* 204 */     this.propertiesEventPane.addTab("Properties", propertiesPane);
/* 205 */     this.propertiesEventPane.addTab("Events", eventPane);
/*     */ 
/*     */     
/* 208 */     propertiesPane.add(this.propertiesTable.getTable(), "Center");
/* 209 */     leftBottomPane.add(this.propertiesEventPane, "Center");
/*     */ 
/*     */ 
/*     */     
/* 213 */     JPanel codePanel = new JPanel(new BorderLayout());
/* 214 */     this.codePane = new JTextPane();
/* 215 */     this.codePane.setFont(new Font("Courier New", 0, 14));
/* 216 */     this.codeScroll = new JScrollPane(this.codePane);
/* 217 */     this.codeScroll.setVerticalScrollBarPolicy(20);
/*     */     
/* 219 */     this.codeScroll.setPreferredSize(new Dimension(250, 250));
/* 220 */     this.codeScroll.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Code"), BorderFactory.createEmptyBorder(3, 3, 3, 3)), this.codePane.getBorder()));
/*     */     
/* 222 */     codePanel.add(this.codeScroll, "Center");
/* 223 */     this.tabbedPane = new JTabbedPane();
/* 224 */     this.tabbedPane.setPreferredSize(new Dimension(350, 300));
/* 225 */     this.tabbedPane.setMaximumSize(new Dimension(3180, 2000));
/* 226 */     this.tabbedPane.setMinimumSize(new Dimension(350, 720));
/* 227 */     this.tabbedPane.setBorder(BorderFactory.createEmptyBorder(0, 2, 2, 5));
/* 228 */     this.tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=30 marginheight=5>Design</body></html>", this.rightMainPanel);
/* 229 */     this.tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=30 marginheight=5>Code</body></html>", codePanel);
/*     */     
/* 231 */     JSplitPane sp = new JSplitPane(0);
/* 232 */     sp.setDividerLocation(300);
/* 233 */     sp.setTopComponent(new JPanel());
/* 234 */     sp.setBottomComponent(new JComboBox());
/* 235 */     this.mpg = new MenuPanelGenerator(this.componentEvents);
/* 236 */     this.tabbedPane.addTab("<html><body leftmargin=15 topmargin=8 marginwidth=30 marginheight=5>Menu Generator</body></html>", this.mpg);
/*     */     
/* 238 */     this.tabbedPane.addChangeListener(new ChangeListener()
/*     */         {
/*     */           public void stateChanged(ChangeEvent e)
/*     */           {
/* 242 */             GuiExtensionWindow.this.generate = new GenerateCode(GuiExtensionWindow.this.canvas, GuiExtensionWindow.this.codePane, GuiExtensionWindow.this.addedComponents, GuiExtensionWindow.this.componentEvents, GuiExtensionWindow.this.mpg.treedemo.getPanel().generateMenu());
/* 243 */             if (GuiExtensionWindow.this.tabbedPane.getSelectedIndex() == 1 || GuiExtensionWindow.this.tabbedPane.getSelectedIndex() == 2) {
/* 244 */               GuiExtensionWindow.this.generate.generate();
/* 245 */               GuiExtensionWindow.this.propertiesEventPane.setEnabled(false);
/* 246 */               GuiExtensionWindow.this.propertiesTable.getTable().setEnabled(false);
/* 247 */               GuiExtensionWindow.this.eventsTable.getTable().setEnabled(false);
/*     */             } else {
/* 249 */               GuiExtensionWindow.this.propertiesEventPane.setEnabled(true);
/* 250 */               GuiExtensionWindow.this.propertiesTable.getTable().setEnabled(true);
/* 251 */               GuiExtensionWindow.this.eventsTable.getTable().setEnabled(true);
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 257 */     splitPaneV.setTopComponent(this.scroll);
/* 258 */     splitPaneV.setBottomComponent(leftBottomScrollPane);
/* 259 */     splitPaneV.setDividerLocation(200);
/* 260 */     splitPane.setLeftComponent(splitPaneV);
/* 261 */     splitPane.setRightComponent(this.tabbedPane);
/*     */     
/* 263 */     setLocationRelativeTo((Component)null);
/* 264 */     setDefaultCloseOperation(2);
/* 265 */     setContentPane(splitPane);
/*     */     
/* 267 */     pack();
/* 268 */     setVisible(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDraggableComponents() {
/* 279 */     Dimension minSize = new Dimension(5, 3);
/* 280 */     Dimension prefSize = new Dimension(5, 3);
/* 281 */     Dimension maxSize = new Dimension(32767, 3);
/*     */     
/* 283 */     String originalFN = "";
/* 284 */     String newFN = "";
/*     */     
/* 286 */     int fLong = 0;
/*     */ 
/*     */     
/*     */     try {
/* 290 */       for (int i = 0; i < this.COMPONENTS_.length; i++) {
/* 291 */         originalFN = this.COMPONENTS_[i].replace(".png", "");
/* 292 */         fLong = originalFN.length();
/* 293 */         newFN = originalFN;
/* 294 */         if (fLong < 14) {
/* 295 */           for (int j = fLong; j < 14; j++) {
/* 296 */             newFN = newFN + " ";
/*     */           }
/*     */         }
/*     */         
/* 300 */         JLabel label = UIHelper.createLabel(newFN, originalFN);
/* 301 */         label.setTransferHandler(new ComponentHAndler(originalFN));
/*     */         
/* 303 */         label.setAlignmentX(0.0F);
/*     */         
/* 305 */         label.setBorder(BorderFactory.createEtchedBorder(1));
/* 306 */         label.addMouseListener(new MouseHendler());
/* 307 */         label.setDoubleBuffered(true);
/* 308 */         this.leftTopComponentPane.add(label);
/* 309 */         this.leftTopComponentPane.add(new Box.Filler(minSize, prefSize, maxSize));
/*     */       } 
/* 311 */     } catch (NullPointerException e) {
/* 312 */       JOptionPane.showMessageDialog(this, "Wrong path to icons!");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void createMenu() {
/* 323 */     this.menuBar = new JMenuBar();
/*     */ 
/*     */     
/* 326 */     JMenu fileMenu = new JMenu("File");
/*     */ 
/*     */     
/* 329 */     JMenuItem saveAsAction = new JMenuItem("   Save as      ");
/* 330 */     saveAsAction.setAccelerator(KeyStroke.getKeyStroke(83, 1));
/* 331 */     JMenuItem saveAction = new JMenuItem("   Save      ");
/*     */     
/* 333 */     saveAction.setAccelerator(KeyStroke.getKeyStroke(83, 2));
/* 334 */     JMenuItem exitAction = new JMenuItem("   Exit      ");
/*     */     
/* 336 */     exitAction.setAccelerator(KeyStroke.getKeyStroke(88, 2));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 343 */     fileMenu.add(saveAction);
/* 344 */     fileMenu.add(saveAsAction);
/* 345 */     fileMenu.addSeparator();
/* 346 */     fileMenu.add(exitAction);
/* 347 */     this.menuBar.add(fileMenu);
/*     */ 
/*     */     
/* 350 */     exitAction.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/* 352 */             GuiExtensionWindow.this.dispose();
/* 353 */             GuiExtensionWindow.this.dispose();
/*     */           }
/*     */         });
/* 356 */     saveAction.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/* 358 */             GuiExtensionWindow.this.generate = new GenerateCode(GuiExtensionWindow.this.canvas, GuiExtensionWindow.this.codePane, GuiExtensionWindow.this.addedComponents, GuiExtensionWindow.this.componentEvents, GuiExtensionWindow.this.mpg.treedemo.getPanel().generateMenu());
/* 359 */             GuiExtensionWindow.this.generate.generate();
/* 360 */             String path = "";
/*     */             
/*     */             try {
/* 363 */               path = GuiExtensionWindow.this.bluej.getCurrentPackage().getProject().getDir().getPath();
/* 364 */               path = path + "\\" + GuiExtensionWindow.this.canvas.getName() + ".java";
/*     */               try {
/* 366 */                 File file = new File(path + "\\" + GuiExtensionWindow.this.canvas.getName() + ".java");
/* 367 */                 if (file.exists()) {
/* 368 */                   int dialogResult = JOptionPane.showConfirmDialog(null, "File already exists.\nReplace?", "Warning", 0);
/* 369 */                   if (dialogResult == 0)
/*     */                   {
/* 371 */                     GuiExtensionWindow.this.saveFile(path);
/*     */                   }
/*     */                 } else {
/* 374 */                   GuiExtensionWindow.this.saveFile(path);
/*     */                 }
/*     */               
/*     */               }
/* 378 */               catch (Exception ex) {
/* 379 */                 Logger.getLogger(GuiExtensionWindow.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */               }
/*     */             
/* 382 */             } catch (ProjectNotOpenException ex) {}
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 388 */     saveAsAction.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent e)
/*     */           {
/* 392 */             GuiExtensionWindow.this.generate = new GenerateCode(GuiExtensionWindow.this.canvas, GuiExtensionWindow.this.codePane, GuiExtensionWindow.this.addedComponents, GuiExtensionWindow.this.componentEvents, GuiExtensionWindow.this.mpg.treedemo.getPanel().generateMenu());
/* 393 */             GuiExtensionWindow.this.generate.generate();
/* 394 */             File f = new File(GuiExtensionWindow.this.canvas.getName() + ".java");
/* 395 */             GuiExtensionWindow.this.fc.setSelectedFile(f);
/* 396 */             int returnVal = GuiExtensionWindow.this.fc.showSaveDialog(GuiExtensionWindow.this);
/*     */             
/* 398 */             if (returnVal == 0) {
/* 399 */               File file = GuiExtensionWindow.this.fc.getSelectedFile();
/* 400 */               GuiExtensionWindow.this.saveFile(file.getPath());
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveFile(String path) {
/* 411 */     FileWriter fstream = null;
/*     */     try {
/* 413 */       fstream = new FileWriter(path);
/* 414 */       BufferedWriter out = new BufferedWriter(fstream);
/* 415 */       out.write(this.codePane.getText());
/* 416 */       out.close();
/* 417 */       JOptionPane.showMessageDialog(null, "Saved!", "File saved", 1);
/*     */       try {
/* 419 */         this.bluej.getCurrentPackage().reload();
/*     */       }
/* 421 */       catch (ProjectNotOpenException|bluej.extensions.PackageNotFoundException pe) {
/* 422 */         JOptionPane.showMessageDialog(null, "Package reloaded failed!", "Error", 0);
/*     */       }
/*     */     
/* 425 */     } catch (IOException ex) {
/* 426 */       Logger.getLogger(GuiExtensionWindow.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   class ComponentHAndler
/*     */     extends TransferHandler
/*     */   {
/* 434 */     private String text = "";
/*     */     public ComponentHAndler(String text) {
/* 436 */       this.text = text;
/*     */     }
/*     */     
/*     */     protected Transferable createTransferable(JComponent c) {
/* 440 */       return new StringSelection(this.text);
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSourceActions(JComponent c) {
/* 445 */       return 1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class MouseHendler
/*     */     extends MouseAdapter
/*     */   {
/*     */     public void mousePressed(MouseEvent e) {
/* 455 */       JComponent c = (JComponent)e.getSource();
/* 456 */       TransferHandler handle = c.getTransferHandler();
/* 457 */       handle.exportAsDrag(c, e, 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   class PanelHandler
/*     */     extends TransferHandler
/*     */   {
/*     */     public boolean canImport(TransferHandler.TransferSupport info) {
/* 470 */       if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
/* 471 */         return false;
/*     */       }
/* 473 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean importData(TransferHandler.TransferSupport info) {
/* 482 */       if (!info.isDrop()) {
/* 483 */         return false;
/*     */       }
/*     */ 
/*     */       
/* 487 */       if (!info.isDataFlavorSupported(DataFlavor.stringFlavor)) {
/* 488 */         JOptionPane.showMessageDialog(null, "List doesn't accept a drop of this type.", "error", 0);
/* 489 */         return false;
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 494 */         String data = (String)info.getTransferable().getTransferData(DataFlavor.stringFlavor);
/* 495 */         JComponent comp = GuiExtensionWindow.this.addComp.addComponentOnCanvas(data, info.getDropLocation().getDropPoint(), GuiExtensionWindow.this.tr);
/* 496 */         if (comp != null) {
/* 497 */           GuiExtensionWindow.this.cm.registerComponent(new Component[] { comp });
/* 498 */           JComponent p = (JComponent)info.getComponent();
/* 499 */           if (p instanceof JLayeredPane) {
/* 500 */             p.add(comp, new Integer(GuiExtensionWindow.this.layer++));
/*     */           } else {
/* 502 */             p.add(comp);
/*     */           } 
/* 504 */           GuiExtensionWindow.this.propertiesTable.setComponentProperties(comp);
/* 505 */           GuiExtensionWindow.this.addedComponents.put(comp.getName(), comp);
/* 506 */           GuiExtensionWindow.this.combobox.addItem(comp.getName());
/* 507 */           GuiExtensionWindow.this.componentEvents.put(comp.getName(), new EventsInit(comp.getName()));
/* 508 */           GuiExtensionWindow.this.combobox.setSelectedItem(comp.getName());
/* 509 */           comp.addMouseMotionListener(new MouseMoveEventClass(comp, GuiExtensionWindow.this.propertiesTable));
/* 510 */           comp.addMouseListener(new MouseActionEventClass(comp, GuiExtensionWindow.this.addedComponents, GuiExtensionWindow.this.propertiesTable, GuiExtensionWindow.this.propertiesEventPane, GuiExtensionWindow.this.componentEvents, GuiExtensionWindow.this.eventsTable, GuiExtensionWindow.this.combobox));
/* 511 */           comp.addKeyListener(new KeyActionEvent(GuiExtensionWindow.this.propertiesTable, GuiExtensionWindow.this.addedComponents, GuiExtensionWindow.this.componentEvents, GuiExtensionWindow.this.combobox));
/* 512 */           comp.requestFocus();
/* 513 */           p.revalidate();
/* 514 */           p.repaint();
/*     */         } 
/* 516 */       } catch (UnsupportedFlavorException ex) {
/* 517 */         JOptionPane.showMessageDialog(null, "Ecccccccc.", "error", 0);
/* 518 */       } catch (IOException ex) {
/* 519 */         JOptionPane.showMessageDialog(null, "Eccc IO.", "error", 0);
/*     */       } 
/* 521 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\GuiExtensionWindow.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */