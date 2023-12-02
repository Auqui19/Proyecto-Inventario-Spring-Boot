package com.bd.siv.util;

import java.awt.Color;
import java.io.IOException;
import java.util.List;


import com.bd.siv.modelo.Producto;
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

public class ProductoExporterPDF {

	private List<Producto> listaProductos;

	public ProductoExporterPDF(List<Producto> listaProductos) {
		super();
		this.listaProductos = listaProductos;
	}

	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();

		celda.setBackgroundColor(Color.BLUE);
		celda.setPadding(5);
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.WHITE);

		celda.setPhrase(new Phrase("ID", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("NOMBRE", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("CÓDIGO", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("PRECIO", fuente));
		tabla.addCell(celda);
		celda.setPhrase(new Phrase("EXISTENCIA", fuente));
		tabla.addCell(celda);
	}

	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		for (Producto producto : listaProductos) {
			tabla.addCell(String.valueOf(producto.getId()));
			tabla.addCell(producto.getNombre());
			tabla.addCell(producto.getCodigo());
			tabla.addCell(String.valueOf(producto.getPrecio()));
			tabla.addCell(String.valueOf(producto.getExistencia()));
		}
	}

	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		documento.open();
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		fuente.setColor(Color.BLUE);
		fuente.setSize(18);
		Paragraph titulo = new Paragraph("Lista de Productos", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		PdfPTable tabla = new PdfPTable(5);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] { 1f, 5f, 2.3f, 2.3f, 2.3f });
		tabla.setWidthPercentage(110);
		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);
		documento.add(tabla);
		documento.close();
	}
}
