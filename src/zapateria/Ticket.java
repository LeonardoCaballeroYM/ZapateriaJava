/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zapateria;

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
public class Ticket {

    private Font fuenteCurrier = new Font(Font.FontFamily.COURIER, 10, Font.BOLD);
    private Font fuenteCurrier2 = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
    private Font fuenteHelvetica = new Font(Font.FontFamily.HELVETICA, 20, Font.BOLD);
    private Font fuenteSymbol = new Font(Font.FontFamily.SYMBOL, 10, Font.BOLD);
    private Font fuenteTimes_Roman = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
    private Font fuenteUndefined = new Font(Font.FontFamily.UNDEFINED, 20, Font.BOLD);
    private Font fuenteZapfdingbats = new Font(Font.FontFamily.ZAPFDINGBATS, 15, Font.NORMAL);

    public void generarTicket(String nom, TicketVo datos, ArrayList<VentasVo> lista) {

        try {
            File salida = new File((nom) + ".pdf");

            Image imagen = Image.getInstance(getClass().getResource("/img/logo.png"));
            imagen.setAbsolutePosition(35f, 725f);
            imagen.scaleAbsolute(40, 40);
            imagen.setAlignment(Element.ALIGN_LEFT);

            //tabla titulo
            PdfPTable tablatitulo = new PdfPTable(1);

            PdfPCell titulo = new PdfPCell();
            titulo.setPhrase(new Phrase("Zapateria Eclipse", fuenteCurrier));
            titulo.setHorizontalAlignment(Element.ALIGN_LEFT);
            titulo.setUseDescender(true);
            titulo.setBorder(0);

            PdfPCell ticket = new PdfPCell();
            ticket.setPhrase(new Phrase("Ticket de compra", fuenteCurrier2));
            ticket.setHorizontalAlignment(Element.ALIGN_LEFT);
            //ticket.setUseDescender(true);
            ticket.setBorder(0);

            PdfPCell vendedor = new PdfPCell();
            vendedor.setPhrase(new Phrase("Vendedor: " + datos.getVendedor(), fuenteCurrier2));
            vendedor.setHorizontalAlignment(Element.ALIGN_LEFT);
//            f.setUseDescender(true);
            vendedor.setBorder(0);

            PdfPCell f = new PdfPCell();
            f.setPhrase(new Phrase("Fecha: " + datos.getFecha(), fuenteCurrier2));
            f.setHorizontalAlignment(Element.ALIGN_LEFT);
//            f.setUseDescender(true);
            f.setBorder(0);

            float[] medidaCeldas = {0x1};

            tablatitulo.setWidths(medidaCeldas);
            tablatitulo.addCell(titulo);
            tablatitulo.addCell(ticket);
            tablatitulo.addCell(vendedor);
            tablatitulo.addCell(f);

            //tabla de articulos vendidos
            PdfPTable tablaVenta = new PdfPTable(5);

            float[] medidaCeldas2 = {0.3f, 0.4f, 0.3f, 0.3f, 2f};

            tablaVenta.setWidths(medidaCeldas2);
            tablaVenta.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell codigo = new PdfPCell();
            codigo.setPhrase(new Phrase("Código", fuenteTimes_Roman));
            codigo.setHorizontalAlignment(Element.ALIGN_LEFT);
            codigo.setUseDescender(true);
            codigo.setBorder(0);

            PdfPCell nombre = new PdfPCell();
            nombre.setPhrase(new Phrase("Producto", fuenteTimes_Roman));
            nombre.setHorizontalAlignment(Element.ALIGN_LEFT);
            nombre.setUseDescender(true);
            nombre.setBorder(0);

            PdfPCell cantidad = new PdfPCell();
            cantidad.setPhrase(new Phrase("Cantidad", fuenteTimes_Roman));
            cantidad.setHorizontalAlignment(Element.ALIGN_LEFT);
            cantidad.setUseDescender(true);
            cantidad.setBorder(0);

            PdfPCell precio = new PdfPCell();
            precio.setPhrase(new Phrase("Precio", fuenteTimes_Roman));
            precio.setHorizontalAlignment(Element.ALIGN_LEFT);
            precio.setUseDescender(true);
            precio.setBorder(0);

            PdfPCell total = new PdfPCell();
            total.setPhrase(new Phrase("Total", fuenteTimes_Roman));
            total.setHorizontalAlignment(Element.ALIGN_LEFT);
            total.setUseDescender(true);
            total.setBorder(0);

            tablaVenta.addCell(codigo);
            tablaVenta.addCell(nombre);
            tablaVenta.addCell(cantidad);
            tablaVenta.addCell(precio);
            tablaVenta.addCell(total);

            VentasVo valor;
            for (int i = 0; i < lista.size(); i++) {
                valor = lista.get(i);
                PdfPCell codigo2 = new PdfPCell();
                codigo2.setPhrase(new Phrase(valor.getCodigo() + "", fuenteTimes_Roman));
                codigo2.setHorizontalAlignment(Element.ALIGN_LEFT);
                codigo2.setUseDescender(true);
                codigo2.setBorder(0);
                tablaVenta.addCell(codigo2);

                PdfPCell nombre2 = new PdfPCell();
                nombre2.setPhrase(new Phrase(valor.getNombre(), fuenteTimes_Roman));
                nombre2.setHorizontalAlignment(Element.ALIGN_LEFT);
                nombre2.setUseDescender(true);
                nombre2.setBorder(0);
                tablaVenta.addCell(nombre2);

                PdfPCell cantidad2 = new PdfPCell();
                cantidad2.setPhrase(new Phrase(valor.getCantidad() + "", fuenteTimes_Roman));
                cantidad2.setHorizontalAlignment(Element.ALIGN_LEFT);
                cantidad2.setUseDescender(true);
                cantidad2.setBorder(0);
                tablaVenta.addCell(cantidad2);

                PdfPCell precio2 = new PdfPCell();
                precio2.setPhrase(new Phrase("$" + valor.getPrecio(), fuenteTimes_Roman));
                precio2.setHorizontalAlignment(Element.ALIGN_LEFT);
                precio2.setUseDescender(true);
                precio2.setBorder(0);
                tablaVenta.addCell(precio2);

                PdfPCell total2 = new PdfPCell();
                total2.setPhrase(new Phrase("$" + valor.getTotal(), fuenteTimes_Roman));
                total2.setHorizontalAlignment(Element.ALIGN_LEFT);
                total2.setUseDescender(true);
                total2.setBorder(0);
                tablaVenta.addCell(total2);

            }

//            PdfPCell importe = new PdfPCell();
//            importe.setPhrase(new Phrase("Importe:", fuenteCurrier2));
//            importe.setHorizontalAlignment(Element.ALIGN_LEFT);
//            importe.setUseDescender(true);
//            importe.setBorder(0);
//            PdfPCell importe2 = new PdfPCell();
//            importe2.setPhrase(new Phrase("$" + datos.getImporte(), fuenteCurrier2));
//            importe2.setHorizontalAlignment(Element.ALIGN_LEFT);
//            importe2.setUseDescender(true);
//            importe2.setBorder(0);
//
//            PdfPCell cambio = new PdfPCell();
//            cambio.setPhrase(new Phrase("Cambio:", fuenteCurrier2));
//            cambio.setHorizontalAlignment(Element.ALIGN_LEFT);
//            cambio.setUseDescender(true);
//            cambio.setBorder(0);
            PdfPCell cambio2 = new PdfPCell();
            cambio2.setPhrase(new Phrase("$" + datos.getCambio(), fuenteCurrier2));
            cambio2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cambio2.setUseDescender(true);
            cambio2.setBorder(0);

//            tablaVenta.addCell(importe);
//            tablaVenta.addCell(importe2);
//            tablaVenta.addCell(cambio);
//            tablaVenta.addCell(cambio2);
            Paragraph totalventa = new Paragraph("Total: $" + datos.getTotal(), fuenteCurrier2);
            totalventa.setAlignment(Element.ALIGN_LEFT);

            Paragraph importe = new Paragraph("Importe: $" + datos.getImporte(), fuenteCurrier2);
            importe.setAlignment(Element.ALIGN_LEFT);

            Paragraph cambio = new Paragraph("Cambio: $" + datos.getCambio(), fuenteCurrier);
            cambio.setAlignment(Element.ALIGN_LEFT);

            Paragraph gra = new Paragraph("      ¡Gracias por su compra!", fuenteCurrier);
            gra.setAlignment(Element.ALIGN_LEFT);

//          formato del documento
            Document document = new Document(PageSize.LETTER, 25, 30, 25, 25);
            PdfWriter.getInstance(document, new FileOutputStream(salida));
            document.open();

            document.add(imagen);
            document.add(tablatitulo);

            document.add(new Paragraph("---------------------------------------------------"));

            document.add(tablaVenta);
            document.add(new Paragraph("---------------------------------------------------"));
            document.add(totalventa);
            document.add(importe);
            document.add(cambio);
            document.add(new Paragraph("---------------------------------------------------"));
            document.add(gra);

            document.close();

            //Abre el documento despues de crearlos
            Desktop.getDesktop().open(salida);

        } catch (DocumentException | IOException e) {
            System.out.println(e);
        }
    }
}
