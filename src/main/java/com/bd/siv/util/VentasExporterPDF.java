package com.bd.siv.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.bd.siv.modelo.ProductoVendido;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class VentasExporterPDF {

	private List<ProductoVendido> listaventas;

	public VentasExporterPDF(List<ProductoVendido> listaventas) {
		super();
		this.listaventas = listaventas;
	}
	
	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();

		celda.setBackgroundColor(Color.BLUE);
		celda.setPadding(5);
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("ID Venta", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Cantidad de Producto", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Código de Producto", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Nombre de Producto", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Precio de Producto", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("Precio de Total", fuente));
		tabla.addCell(celda);
	}
	
	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (ProductoVendido productovendido : listaventas) {
			tabla.addCell(String.valueOf(productovendido.getVenta()));
			tabla.addCell(String.valueOf(productovendido.getCantidad()));
			tabla.addCell(productovendido.getCodigo());
			tabla.addCell(productovendido.getNombre());
			tabla.addCell(String.valueOf(productovendido.getPrecio()));
			tabla.addCell(String.valueOf(productovendido.getTotal()));
		}
	}
	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		documento.open();
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.BLUE);
		fuente.setSize(18);
		Paragraph titulo = new Paragraph("Lista de Ventas Realizadas", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		PdfPTable tabla = new PdfPTable(3);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 1f, 1f, 2.5f, 4f, 2.5f, 2.5f });
		tabla.setWidthPercentage(110);
		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);
		documento.add(tabla);
		documento.close();
	}
}
