/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import java.util.ArrayList;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Fernando
 */
public class Report implements java.io.Serializable{
    private ArchiveType type;
    private ArrayList<Record> records;
    private String path;

    public Report(ArchiveType type, ArrayList<Record> records , String Path){
        this.type=type;
        this.records = (ArrayList)records.clone();
        this.path = Path;
    }
    public Report(ArchiveType type, User responsable, String Path){
        this.type=type;
        this.path = Path;
        if (type == ArchiveType.Error) {
            records = responsable.getErrorRecords();
        }else{
            records = responsable.getSaleRecords();
        }
    }
    
    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if(text.trim().equalsIgnoreCase("")){
         cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);
   }
    
    public String generateSaleReport(String option){
        
        Document doc = new Document();
        PdfWriter docWriter = null;
        DecimalFormat df = new DecimalFormat("0");
        
        try{
            Font bfBold12 = new Font(FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
            Font bf12 = new Font(FontFamily.COURIER, 12); 
            Font titleFont = new Font(FontFamily.COURIER,20,Font.BOLDITALIC);
            Font headerFont = new Font(FontFamily.COURIER,10,Font.ITALIC);
            //file path
            String path;
            switch(option){
                case "Day":
                    path = this.path+ "/daily_sales_report_CHILEXPLOX.pdf";
                    break;
                case "Week":
                    path = this.path+ "/weekly_sales_report_CHILEXPLOX.pdf";
                    break;
                case "Month":
                    path = this.path+ "/monthly_sales_report_CHILEXPLOX.pdf";
                    break;
                default:
                    path = this.path+ "/report_CHILEXPLOX.pdf";
                    break;
            }
            docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
            
            doc.addAuthor("ChilExplox2015");
            doc.addCreationDate();
            doc.addCreator("ChilExplox2015");
            doc.addTitle("Report");
            doc.setPageSize(PageSize.LETTER);

            //open document
            doc.open();
            
            Date d = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            
            Paragraph header = new Paragraph("ChilExplox 2015. \nCasa Matriz: "
                    + "Campus San Joaquin PUC. \n" + d.toString(),headerFont);
            Paragraph title;
            Paragraph period;
            
            switch(option){
                case "Day":
                    title = new Paragraph("Reporte Diario de Ventas",titleFont);
                    period = new Paragraph("Dia: "+ 
                            String.valueOf(c.get(Calendar.DATE)) + " de "+
                            (new DateFormatSymbols().getMonths()[c.get(Calendar.MONTH)]) + ", "+
                            String.valueOf(c.get(Calendar.YEAR)),titleFont);
                    break;
                case "Week":
                    title = new Paragraph("Reporte Semanal de Ventas",titleFont);
                    period = new Paragraph("Semana: "+ 
                            String.valueOf(c.get(Calendar.WEEK_OF_MONTH))+ " de "+
                            (new DateFormatSymbols().getMonths()[c.get(Calendar.MONTH)]) + ", "+
                            String.valueOf(c.get(Calendar.YEAR)),titleFont);
                    break;
                case "Month":
                    title = new Paragraph("Reporte Mensual de Ventas",titleFont);
                    period = new Paragraph("Mes: "+
                            (new DateFormatSymbols().getMonths()[c.get(Calendar.MONTH)]) + ", "+
                            String.valueOf(c.get(Calendar.YEAR)),titleFont);
                    break;
                default:
                    title = new Paragraph("Reporte de Ventas",titleFont);
                    period = new Paragraph("",titleFont);
                    break;
            }
            title.setAlignment(Element.ALIGN_CENTER);
            period.setAlignment(Element.ALIGN_CENTER);
            period.setSpacingAfter(30);
            doc.add(header);
            doc.add(title);
            doc.add(period);
            
            Paragraph paragraph = new Paragraph("El siguiente reporte muestra"
                    + "las actividades de ventas de encomiendas en el periodo "
                    + "especificado: ",bf12);
            
           float[] columnWidths = {2f, 2f, 2f, 2f};
           //create PDF table with the given widths
           PdfPTable table = new PdfPTable(columnWidths);
           // set table width a percentage of the page width
           table.setWidthPercentage(90f);

           //insert column headings
           insertCell(table, "ID Encomienda", Element.ALIGN_RIGHT, 1, bfBold12);
           insertCell(table, "ID Pedido", Element.ALIGN_LEFT, 1, bfBold12);
           insertCell(table, "RUT Cliente", Element.ALIGN_LEFT, 1, bfBold12);
           insertCell(table, "Total Encomienda", Element.ALIGN_RIGHT, 1, bfBold12);
           table.setHeaderRows(1);

           //insert an empty row
           insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
           //create section heading by cell merging
           //insertCell(table, "-", Element.ALIGN_LEFT, 4, bfBold12);
           double orderTotal, total = 0;

           
           for(Record r: this.records){

            insertCell(table, r.getParcel().getId(), Element.ALIGN_RIGHT, 1, bf12);
            insertCell(table, r.getParcel().getOrder().getId(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, r.getParcel().getOrder().getClient().getRut(), Element.ALIGN_LEFT, 1, bf12);

            orderTotal = BudgetCalculator.calculateParcel(r.getParcel());
            total = total + orderTotal;
            insertCell(table, df.format(orderTotal), Element.ALIGN_RIGHT, 1, bf12);

           }
           //merge the cells to create a footer for that section
           insertCell(table, " Total:", Element.ALIGN_RIGHT, 3, bfBold12);
           insertCell(table, df.format(total), Element.ALIGN_RIGHT, 1, bfBold12);

           //add the PDF table to the paragraph 
           paragraph.add(table);
           doc.add(paragraph);
           return path;
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (DocumentException dex)
        {
         dex.printStackTrace();
        }
        catch (Exception ex)
        {
         ex.printStackTrace();
        }
        finally
        {
         if (doc != null){
          //close the document
          doc.close();
         }
         if (docWriter != null){
          //close the writer
          docWriter.close();
         }
        }
        return "";
    }
    
    public String generateUserSaleReport(User u){
        
        Document doc = new Document();
        PdfWriter docWriter = null;
        DecimalFormat df = new DecimalFormat("0");
        
        try{
            Font bfBold12 = new Font(FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
            Font bf12 = new Font(FontFamily.COURIER, 12); 
            Font titleFont = new Font(FontFamily.COURIER,20,Font.BOLDITALIC);
            Font headerFont = new Font(FontFamily.COURIER,10,Font.ITALIC);
            //file path
            String path;
            path = this.path+ "/"+u.getUsername()+"user_sale_report_CHILEXPLOX.pdf";

            docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
            
            doc.addAuthor("ChilExplox2015");
            doc.addCreationDate();
            doc.addCreator("ChilExplox2015");
            doc.addTitle("Report");
            doc.setPageSize(PageSize.LETTER);

            //open document
            doc.open();
            
            Date d = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            
            Paragraph header = new Paragraph("ChilExplox 2015. \nCasa Matriz: "
                    + "Campus San Joaquin PUC. \n" + d.toString(),headerFont);
            Paragraph title;
            Paragraph period;
            
            title = new Paragraph("Reporte de Ventas de Usuario",titleFont);
            period = new Paragraph("User: "+ u.getUsername());
            
            title.setAlignment(Element.ALIGN_CENTER);
            period.setAlignment(Element.ALIGN_CENTER);
            period.setSpacingAfter(30);
            doc.add(header);
            doc.add(title);
            doc.add(period);
            
            Paragraph paragraph = new Paragraph("El siguiente reporte muestra"
                    + "las actividades de ventas de encomiendas ingresadas por el"
                    + "usuario especificado: ",bf12);
            
           float[] columnWidths = {2f, 2f, 2f, 2f};
           //create PDF table with the given widths
           PdfPTable table = new PdfPTable(columnWidths);
           // set table width a percentage of the page width
           table.setWidthPercentage(90f);

           //insert column headings
           insertCell(table, "ID Encomienda", Element.ALIGN_RIGHT, 1, bfBold12);
           insertCell(table, "ID Pedido", Element.ALIGN_LEFT, 1, bfBold12);
           insertCell(table, "RUT Cliente", Element.ALIGN_LEFT, 1, bfBold12);
           insertCell(table, "Total Encomienda", Element.ALIGN_RIGHT, 1, bfBold12);
           table.setHeaderRows(1);

           //insert an empty row
           insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
           //create section heading by cell merging
           //insertCell(table, "-", Element.ALIGN_LEFT, 4, bfBold12);
           double orderTotal, total = 0;

           
           for(Record r: this.records){

            insertCell(table, r.getParcel().getId(), Element.ALIGN_RIGHT, 1, bf12);
            insertCell(table, r.getParcel().getOrder().getId(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, r.getParcel().getOrder().getClient().getRut(), Element.ALIGN_LEFT, 1, bf12);

            orderTotal = BudgetCalculator.calculateParcel(r.getParcel());
            total = total + orderTotal;
            insertCell(table, df.format(orderTotal), Element.ALIGN_RIGHT, 1, bf12);

           }
           //merge the cells to create a footer for that section
           insertCell(table, " Total:", Element.ALIGN_RIGHT, 3, bfBold12);
           insertCell(table, df.format(total), Element.ALIGN_RIGHT, 1, bfBold12);

           //add the PDF table to the paragraph 
           paragraph.add(table);
           doc.add(paragraph);
           return path;
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (DocumentException dex)
        {
         dex.printStackTrace();
        }
        catch (Exception ex)
        {
         ex.printStackTrace();
        }
        finally
        {
         if (doc != null){
          //close the document
          doc.close();
         }
         if (docWriter != null){
          //close the writer
          docWriter.close();
         }
        }
        return "";
    }
    public String generateUserErrorReport(User u){
        
        Document doc = new Document();
        PdfWriter docWriter = null;
        DecimalFormat df = new DecimalFormat("0");
        
        try{
            Font bfBold12 = new Font(FontFamily.COURIER, 12, Font.BOLD, new BaseColor(0, 0, 0)); 
            Font bf12 = new Font(FontFamily.COURIER, 12); 
            Font titleFont = new Font(FontFamily.COURIER,20,Font.BOLDITALIC);
            Font headerFont = new Font(FontFamily.COURIER,10,Font.ITALIC);
            //file path
            String path;
            path = this.path+ "/"+u.getUsername()+"user_error_report_CHILEXPLOX.pdf";

            docWriter = PdfWriter.getInstance(doc , new FileOutputStream(path));
            
            doc.addAuthor("ChilExplox2015");
            doc.addCreationDate();
            doc.addCreator("ChilExplox2015");
            doc.addTitle("Report");
            doc.setPageSize(PageSize.LETTER);

            //open document
            doc.open();
            
            Date d = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            
            Paragraph header = new Paragraph("ChilExplox 2015. \nCasa Matriz: "
                    + "Campus San Joaquin PUC. \n" + d.toString(),headerFont);
            Paragraph title;
            Paragraph period;
            
            title = new Paragraph("Reporte de Errores de Usuario",titleFont);
            period = new Paragraph("User: "+ u.getUsername());
            
            title.setAlignment(Element.ALIGN_CENTER);
            period.setAlignment(Element.ALIGN_CENTER);
            period.setSpacingAfter(30);
            doc.add(header);
            doc.add(title);
            doc.add(period);
            
            Paragraph paragraph = new Paragraph("El siguiente reporte muestra"
                    + "las actividades de errores realizados en el ingreso de"
                    + "encomiendas por el "
                    + "usuario especificado: ",bf12);
            
           float[] columnWidths = {2f, 2f, 2f, 5f};
           //create PDF table with the given widths
           PdfPTable table = new PdfPTable(columnWidths);
           // set table width a percentage of the page width
           table.setWidthPercentage(90f);

           //insert column headings
           insertCell(table, "ID Encomienda", Element.ALIGN_RIGHT, 1, bfBold12);
           insertCell(table, "ID Pedido", Element.ALIGN_LEFT, 1, bfBold12);
           insertCell(table, "RUT Cliente", Element.ALIGN_LEFT, 1, bfBold12);
           insertCell(table, "Contenido", Element.ALIGN_RIGHT, 1, bfBold12);
           table.setHeaderRows(1);

           //insert an empty row
           insertCell(table, "", Element.ALIGN_LEFT, 4, bfBold12);
           //create section heading by cell merging
           //insertCell(table, "-", Element.ALIGN_LEFT, 4, bfBold12);
           
           for(Record r: this.records){

            insertCell(table, r.getParcel().getId(), Element.ALIGN_RIGHT, 1, bf12);
            insertCell(table, r.getParcel().getOrder().getId(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, r.getParcel().getOrder().getClient().getRut(), Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, r.getContent(), Element.ALIGN_LEFT, 1, bf12);


           }
           //merge the cells to create a footer for that section

           //add the PDF table to the paragraph 
           paragraph.add(table);
           doc.add(paragraph);
           return path;
            
        }catch (FileNotFoundException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (DocumentException dex)
        {
         dex.printStackTrace();
        }
        catch (Exception ex)
        {
         ex.printStackTrace();
        }
        finally
        {
         if (doc != null){
          //close the document
          doc.close();
         }
         if (docWriter != null){
          //close the writer
          docWriter.close();
         }
        }
        return "";
    }
    
}
