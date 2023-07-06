/*     */ package GUI_Extension;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.ListSelectionModel;
/*     */ import javax.swing.table.TableCellEditor;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ import javax.swing.table.TableModel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class JTableX
/*     */   extends JTable
/*     */ {
/*     */   protected RowEditorModel rm;
/*     */   
/*     */   public JTableX() {
/*  22 */     this.rm = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JTableX(TableModel tm) {
/*  32 */     super(tm);
/*  33 */     this.rm = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JTableX(TableModel tm, TableColumnModel cm) {
/*  42 */     super(tm, cm);
/*  43 */     this.rm = null;
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
/*     */   public JTableX(TableModel tm, TableColumnModel cm, ListSelectionModel sm) {
/*  55 */     super(tm, cm, sm);
/*  56 */     this.rm = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JTableX(int rows, int cols) {
/*  66 */     super(rows, cols);
/*  67 */     this.rm = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JTableX(Vector rowData, Vector columnNames) {
/*  77 */     super(rowData, columnNames);
/*  78 */     this.rm = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JTableX(Object[][] rowData, Object[] colNames) {
/*  88 */     super(rowData, colNames);
/*  89 */     this.rm = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JTableX(TableModel tm, RowEditorModel rm) {
/* 100 */     super(tm, null, null);
/* 101 */     this.rm = rm;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRowEditorModel(RowEditorModel rm) {
/* 106 */     this.rm = rm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RowEditorModel getRowEditorModel() {
/* 114 */     return this.rm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TableCellEditor getCellEditor(int row, int col) {
/* 125 */     TableCellEditor tmpEditor = null;
/* 126 */     if (this.rm != null)
/* 127 */       tmpEditor = this.rm.getEditor(row); 
/* 128 */     if (tmpEditor != null)
/* 129 */       return tmpEditor; 
/* 130 */     return super.getCellEditor(row, col);
/*     */   }
/*     */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\JTableX.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */