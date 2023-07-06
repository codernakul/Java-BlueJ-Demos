/*    */ package GUI_Extension;
/*    */ 
/*    */ import java.util.Hashtable;
/*    */ import javax.swing.table.TableCellEditor;
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
/*    */ public class RowEditorModel
/*    */ {
/* 17 */   private Hashtable data = new Hashtable<>();
/*    */ 
/*    */   
/*    */   public void addEditorForRow(int row, TableCellEditor e) {
/* 21 */     this.data.put(new Integer(row), e);
/*    */   }
/*    */   
/*    */   public void removeEditorForRow(int row) {
/* 25 */     this.data.remove(new Integer(row));
/*    */   }
/*    */   
/*    */   public TableCellEditor getEditor(int row) {
/* 29 */     return (TableCellEditor)this.data.get(new Integer(row));
/*    */   }
/*    */ }


/* Location:              D:\MISC\GUI_Extension.jar!\GUI_Extension\RowEditorModel.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */