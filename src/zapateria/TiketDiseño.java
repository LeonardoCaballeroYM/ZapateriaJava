/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapateria;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import static com.itextpdf.text.PageSize.A7;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 *
 * @author Belen
 */
public class TiketDiseño {
    private Font fuentebold = new Font(Font.FontFamily.COURIER,8,Font.BOLD);
    private Font fuenteNormal = new Font(Font.FontFamily.COURIER,5,Font.NORMAL);
    private Font fuenteItalic = new Font(Font.FontFamily.COURIER,5,Font.ITALIC);
    
    public void generarTiket(String header, String codigo, String nombre, String precio,
            String fecha, String cantidad, String total,String importe, String cambio,
            String rutaimagen,String Salida) {
        try {
            Document document =new Document(PageSize.A7, 36, 36,10,10);
            PdfWriter.getInstance(document, new FileOutputStream(Salida));
            document.open();
            document.add(getHeader(header));
            Image imagen = Image.getInstance(rutaimagen);
            imagen.scaleAbsolute(25,25);
            imagen.setAlignment(Element.ALIGN_CENTER);
            document.add(imagen);
            document.add(getCodigo(codigo));
            document.add(getNombre(nombre));
            document.add(getPrecio(precio));
            document.add(getFecha(fecha));
            document.add(getCantidad(cantidad));
            document.add(getTotal(total));
            document.add(getImporte(importe));
            document.add(getCambio(cambio));
            document.close();
        } catch (DocumentException | IOException e) {
        }
    }
    
    private Paragraph getHeader(String texto){
        Paragraph p = new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuentebold);
        p.add(c);
        return p;
    } 
    private Paragraph getCodigo(String texto){
        Paragraph p = new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    private Paragraph getNombre(String texto){
        Paragraph p = new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    private Paragraph getPrecio(String texto){
        Paragraph p = new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    private Paragraph getFecha(String texto){
        Paragraph p = new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    private Paragraph getCantidad(String texto){
        Paragraph p = new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    private Paragraph getTotal(String texto){
        Paragraph p = new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    private Paragraph getImporte(String texto){
        Paragraph p = new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
    private Paragraph getCambio(String texto){
        Paragraph p = new Paragraph();
        Chunk c=new Chunk();
        p.setAlignment(Element.ALIGN_CENTER);
        c.append(texto);
        c.setFont(fuenteNormal);
        p.add(c);
        return p;
    }
}
