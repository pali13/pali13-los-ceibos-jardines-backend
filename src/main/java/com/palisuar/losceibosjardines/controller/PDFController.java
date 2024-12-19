package com.palisuar.losceibosjardines.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.palisuar.losceibosjardines.request.PDFRequest;
import com.palisuar.losceibosjardines.request.Task;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "http://localhost:8081")
public class PDFController {

    @PostMapping("/generate")
    public ResponseEntity<byte[]> generatePDF(@RequestBody PDFRequest request) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            document.open();

            PdfContentByte canvas = writer.getDirectContent();

            // Cargar imagen del encabezado
            Image headerImage = Image.getInstance(new ClassPathResource("static/images/encabezado.png").getURL());
            headerImage.scaleToFit(595, 300); // Ajusta el tamaño según sea necesario
            headerImage.setAbsolutePosition(0, 650); // Ajusta X e Y para mover la imagen más arriba
            canvas.addImage(headerImage);

            // Añadir un espacio en blanco antes del título para asegurar que se muestre
            document.add(new Paragraph(" ")); // Espacio en blanco adicional

            // Agregar el título
            Paragraph title = new Paragraph(request.getTitle(),
                    new Font(Font.FontFamily.HELVETICA, 23, Font.BOLD, new BaseColor(0, 104, 56)));
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingBefore(150); // Añade espacio antes de la tabla
            title.setSpacingAfter(50); // Añade un poco de espacio debajo del título
            document.add(title);

            // Crear tabla para las tareas
            PdfPTable taskTable = new PdfPTable(5);
            taskTable.setWidthPercentage(95);
            taskTable.setWidths(new float[] { 2, 7, 4, 2, 2 });

            // Añadir encabezados
            PdfPCell header1 = new PdfPCell(new Phrase("Cantidad", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            PdfPCell header2 = new PdfPCell(
                    new Phrase("Descripción", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            PdfPCell header3 = new PdfPCell(
                    new Phrase("Precio x unidad", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            PdfPCell header4 = new PdfPCell(
                    new Phrase("UYU", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            PdfPCell header5 = new PdfPCell(new Phrase("USD", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            header1.setBorder(Rectangle.NO_BORDER);
            header1.setHorizontalAlignment(Element.ALIGN_CENTER);
            header2.setBorder(Rectangle.NO_BORDER);
            header2.setHorizontalAlignment(Element.ALIGN_CENTER);
            header3.setBorder(Rectangle.NO_BORDER);
            header3.setHorizontalAlignment(Element.ALIGN_CENTER);
            header4.setBorder(Rectangle.NO_BORDER);
            header4.setHorizontalAlignment(Element.ALIGN_CENTER);
            header5.setBorder(Rectangle.NO_BORDER);
            header5.setHorizontalAlignment(Element.ALIGN_CENTER);
            taskTable.addCell(header1);
            taskTable.addCell(header2);
            taskTable.addCell(header3);
            taskTable.addCell(header4);
            taskTable.addCell(header5);

            // Añadir filas de tareas
            for (Task task : request.getTasks()) {
                PdfPCell cellQuantity = new PdfPCell(new Phrase(
                        String.valueOf(task.getQuantity()).equals("0") ? "-" : String.valueOf(task.getQuantity())));
                PdfPCell cellDescription = new PdfPCell(new Phrase(task.getDescription()));
                PdfPCell cellPricePerUnity = new PdfPCell(new Phrase(
                        String.valueOf(task.getPricePerUnity()).equals("0") ? "-"
                                : String.valueOf(task.getPricePerUnity())));
                PdfPCell cellUyu = new PdfPCell(
                        new Phrase(task.getTypeCurrency().equals("UYU") ? "$ " + Math.round(task.getPrice()) : "-"));
                PdfPCell cellUsd = new PdfPCell(
                        new Phrase(task.getTypeCurrency().equals("USD") ? "U$S " + Math.round(task.getPrice()) : "-"));
                cellQuantity.setBorder(Rectangle.NO_BORDER);
                cellQuantity.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellDescription.setBorder(Rectangle.NO_BORDER);
                cellDescription.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellPricePerUnity.setBorder(Rectangle.NO_BORDER);
                cellPricePerUnity.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellUyu.setBorder(Rectangle.NO_BORDER);
                cellUyu.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellUsd.setBorder(Rectangle.NO_BORDER);
                cellUsd.setHorizontalAlignment(Element.ALIGN_CENTER);
                taskTable.addCell(cellQuantity);
                taskTable.addCell(cellDescription);
                taskTable.addCell(cellPricePerUnity);
                taskTable.addCell(cellUyu);
                taskTable.addCell(cellUsd);

            }

            // Añadir tabla al documento
            taskTable.setSpacingAfter(50); // Añadir espacio después de las tareas
            document.add(taskTable);

            double totalUYU = 0;
            double totalUSD = 0;

            for (Task task : request.getTasks()) {
                switch (task.getTypeCurrency()) {
                    case "UYU" -> totalUYU += task.getPrice();
                    case "USD" -> totalUSD += task.getPrice();
                }
            }

            PdfPTable totalTable = new PdfPTable(5);
            totalTable.setWidthPercentage(95);
            totalTable.setWidths(new float[] { 2, 7, 4, 2, 2 });

            // Añadir fila de total
            PdfPCell cellTotalLabel = new PdfPCell(
                    new Phrase("Total:", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            cellTotalLabel.setColspan(3);
            cellTotalLabel.setBorder(Rectangle.NO_BORDER);
            cellTotalLabel.setHorizontalAlignment(Element.ALIGN_RIGHT);
            PdfPCell cellTotalValueUyu = new PdfPCell(new Phrase(String.format("$ %.0f", totalUYU)));
            PdfPCell cellTotalValueUsd = new PdfPCell(new Phrase(String.format("U$S %.0f", totalUSD)));
            cellTotalValueUyu.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellTotalValueUsd.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalTable.addCell(cellTotalLabel);
            totalTable.addCell(cellTotalValueUyu);
            totalTable.addCell(cellTotalValueUsd);
            totalTable.setSpacingAfter(10);

            // Añadir tabla de totales al documento
            document.add(totalTable);

            PdfPTable paragraphTable = new PdfPTable(1);
            paragraphTable.setWidthPercentage(50); // Ajusta el porcentaje del ancho de la tabla
            paragraphTable.setSpacingBefore(20); // Espacio antes del párrafo
            paragraphTable.setSpacingAfter(20); // Espacio después del párrafo

            // Crear el contenido del párrafo
            Paragraph extra = new Paragraph(
                    "Mantenimiento mensual incluye: visita una vez por semana, matayuyo general y selectivo, mantenimiento de podas, limpieza de canteros y espacios de tierra",
                    new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, new BaseColor(0, 0, 0)));

            // Crear una celda para contener el párrafo
            PdfPCell cellExtra = new PdfPCell();
            cellExtra.addElement(extra);
            paragraphTable.setHorizontalAlignment(Element.ALIGN_LEFT); // Alinea la tabla a la izquierda

            cellExtra.setBorder(Rectangle.NO_BORDER); // Sin bordes para que luzca como un párrafo normal
            cellExtra.setPadding(10); // Ajustar el relleno dentro de la celda para mejor apariencia

            // Añadir la celda a la tabla
            paragraphTable.addCell(cellExtra);

            // Añadir la tabla al documento
            document.add(paragraphTable);

            // Pie
            Image footerImage = Image.getInstance("src/main/resources/static/images/pie.png");
            footerImage.scaleToFit(595, 200); // Ajusta el tamaño según sea necesario
            footerImage.setAbsolutePosition(0, 10); // Ajusta X e Y para posicionarla en el final de la página
            canvas.addImage(footerImage);

            document.close();

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=presupuesto.pdf");
            headers.add("Content-Type", "application/pdf");
            return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
