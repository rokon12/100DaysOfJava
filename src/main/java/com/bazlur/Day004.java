package com.bazlur;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day004 {
  private static final String OUTPUT_FOLDER = "exports/";
  private static final String IMAGE_FOLDER = "images/";
  private static final String IMAGE_EXTENSION = ".jpg";

  public static void main(String[] args) throws IOException, DocumentException {
    var imageSourcePath = Path.of(IMAGE_FOLDER);
    var document = new Document(PageSize.A4, 20.0f, 20.0f, 20.0f, 150.0f);

    PdfWriter.getInstance(document, new FileOutputStream(OUTPUT_FOLDER + "/export.pdf"));
    document.open();
    Files.walk(imageSourcePath)
            .filter(path -> path.toString().endsWith(IMAGE_EXTENSION))
            .forEach(path -> addImageToDocument(document, path));
    document.close();
  }

  private static void addImageToDocument(Document document, Path path) {
    try {
      var image = Image.getInstance(path.toUri().toURL());
      document.setPageSize(image);
      document.newPage();
      image.setAbsolutePosition(0, 0);
      document.add(image);
    } catch (IOException | DocumentException e) {
      throw new RuntimeException(e);
    }
  }
}
