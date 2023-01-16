/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapateria;

import Utilidades.Fecha;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Belen
 */
public class ReportePorDia {
    private Font fuenteCurrier = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
    private Font fuenteCurrier2 = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
    private Font fuenteCurrier3 = new Font(Font.FontFamily.COURIER, 15, Font.NORMAL);
    private Font fuenteHelvetica = new Font(Font.FontFamily.HELVETICA, 15, Font.BOLD);
    private Font fuenteTimes_Roman = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

    Fecha fecha = new Fecha();

    public void generarTicket(String nom, ReporteVo datos, ArrayList<VentasVo> lista) {

        try {
            File salida = new File((nom) + ".pdf");

            Image imagen = Image.getInstance(getClass().getResource("/img/logo.png"));
            imagen.setAbsolutePosition(50f, 700f);
            imagen.scaleAbsolute(55, 55);
            imagen.setAlignment(Element.ALIGN_LEFT);

            //tabla titulo
            PdfPTable tablatitulo = new PdfPTable(1);

            PdfPCell titulo = new PdfPCell();
            titulo.setPhrase(new Phrase("Zapateria Eclipse", fuenteCurrier));
            titulo.setHorizontalAlignment(Element.ALIGN_CENTER);
            titulo.setUseDescender(true);
            titulo.setBorder(0);

            PdfPCell ticket = new PdfPCell();
            ticket.setPhrase(new Phrase("Reporte Por Día", fuenteCurrier3));
            ticket.setHorizontalAlignment(Element.ALIGN_CENTER);
            //ticket.setUseDescender(true);
            ticket.setBorder(0);

            PdfPCell f = new PdfPCell();
            f.setPhrase(new Phrase("Fecha: " + fecha.getFechaActual(), fuenteCurrier2));
            f.setHorizontalAlignment(Element.ALIGN_CENTER);
//            f.setUseDescender(true);
            f.setBorder(0);

            float[] medidaCeldas = {0x1};

            tablatitulo.setWidths(medidaCeldas);
            tablatitulo.addCell(titulo);
            tablatitulo.addCell(ticket);
            tablatitulo.addCell(f);
            
            
            Paragraph ganancias = new Paragraph("Ganancias: $" + datos.getGanancias(), fuenteCurrier2);
            ganancias.setAlignment(Element.ALIGN_LEFT);

            Paragraph totalVentas = new Paragraph("Total de ventas: " + datos.getTotalVentas(), fuenteCurrier2);
            totalVentas.setAlignment(Element.ALIGN_LEFT);

            Paragraph Pares = new Paragraph("Total de pares vendidos: " + datos.getParesVendidos(), fuenteCurrier2);
            Pares.setAlignment(Element.ALIGN_LEFT);

            //tabla de articulos vendidos
            PdfPTable tablaVenta = new PdfPTable(6);

            float[] medidaCeldas2 = {2f, 2f, 2f, 2f, 2f, 2f};

            tablaVenta.setWidths(medidaCeldas2);
//            tablaVenta.setHorizontalAlignment(Element.ALIGN_CENTER);

            PdfPCell Id_Venta = new PdfPCell();
            Id_Venta.setPhrase(new Phrase("Id_Venta", fuenteCurrier2));
            Id_Venta.setHorizontalAlignment(Element.ALIGN_LEFT);
            Id_Venta.setUseDescender(true);
            Id_Venta.setBorder(0);

            PdfPCell Producto = new PdfPCell();
            Producto.setPhrase(new Phrase("Producto", fuenteCurrier2));
            Producto.setHorizontalAlignment(Element.ALIGN_LEFT);
            Producto.setUseDescender(true);
            Producto.setBorder(0);

            PdfPCell Codigo = new PdfPCell();
            Codigo.setPhrase(new Phrase("Código", fuenteCurrier2));
            Codigo.setHorizontalAlignment(Element.ALIGN_LEFT);
            Codigo.setUseDescender(true);
            Codigo.setBorder(0);

            PdfPCell precio = new PdfPCell();
            precio.setPhrase(new Phrase("Precio", fuenteCurrier2));
            precio.setHorizontalAlignment(Element.ALIGN_LEFT);
            precio.setUseDescender(true);
            precio.setBorder(0);

            PdfPCell Cantidad = new PdfPCell();
            Cantidad.setPhrase(new Phrase("Cantidad", fuenteCurrier2));
            Cantidad.setHorizontalAlignment(Element.ALIGN_LEFT);
            Cantidad.setUseDescender(true);
            Cantidad.setBorder(0);

            PdfPCell total = new PdfPCell();
            total.setPhrase(new Phrase("Total", fuenteCurrier2));
            total.setHorizontalAlignment(Element.ALIGN_LEFT);
            total.setUseDescender(true);
            total.setBorder(0);

            tablaVenta.addCell(Id_Venta);
            tablaVenta.addCell(Producto);
            tablaVenta.addCell(Codigo);
            tablaVenta.addCell(precio);
            tablaVenta.addCell(Cantidad);
            tablaVenta.addCell(total);

            VentasVo valor;
            for (int i = 0; i < lista.size(); i++) {
                valor = lista.get(i);

                PdfPCell id = new PdfPCell();
                id.setPhrase(new Phrase(valor.getId() + "", fuenteTimes_Roman));
                id.setHorizontalAlignment(Element.ALIGN_LEFT);
                id.setUseDescender(true);
                id.setBorder(0);
                tablaVenta.addCell(id);

                PdfPCell nombre2 = new PdfPCell();
                nombre2.setPhrase(new Phrase(valor.getNombre(), fuenteTimes_Roman));
                nombre2.setHorizontalAlignment(Element.ALIGN_LEFT);
                nombre2.setUseDescender(true);
                nombre2.setBorder(0);
                tablaVenta.addCell(nombre2);

                PdfPCell codigo2 = new PdfPCell();
                codigo2.setPhrase(new Phrase(valor.getCodigo() + "", fuenteTimes_Roman));
                codigo2.setHorizontalAlignment(Element.ALIGN_LEFT);
                codigo2.setUseDescender(true);
                codigo2.setBorder(0);
                tablaVenta.addCell(codigo2);

                PdfPCell precio2 = new PdfPCell();
                precio2.setPhrase(new Phrase("$" + valor.getPrecio(), fuenteTimes_Roman));
                precio2.setHorizontalAlignment(Element.ALIGN_LEFT);
                precio2.setUseDescender(true);
                precio2.setBorder(0);
                tablaVenta.addCell(precio2);

                PdfPCell cantidad2 = new PdfPCell();
                cantidad2.setPhrase(new Phrase(valor.getCantidad() + "", fuenteTimes_Roman));
                cantidad2.setHorizontalAlignment(Element.ALIGN_LEFT);
                cantidad2.setUseDescender(true);
                cantidad2.setBorder(0);
                tablaVenta.addCell(cantidad2);

                PdfPCell total2 = new PdfPCell();
                total2.setPhrase(new Phrase("$" + valor.getTotal(), fuenteTimes_Roman));
                total2.setHorizontalAlignment(Element.ALIGN_LEFT);
                total2.setUseDescender(true);
                total2.setBorder(0);
                tablaVenta.addCell(total2);
            }

//          formato del documento
            Document document = new Document(PageSize.LETTER, 30, 25, 25, 25);
            PdfWriter.getInstance(document, new FileOutputStream(salida));
            document.open();

            document.add(imagen);
            document.add(tablatitulo);
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            
            document.add(Pares);
            document.add(totalVentas);
            document.add(ganancias);
            
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(tablaVenta);
            
            document.close();

            //Abre el documento despues de crearlos
            Desktop.getDesktop().open(salida);

        } catch (DocumentException | IOException e) {
            System.out.println(e);

        }
    }
}
