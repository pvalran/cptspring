package com.Xoot.CreditoParaTi.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.FileOutputStream;


public class pdfSolicitud {
    public void solicitud() {

        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        PdfWriter.getInstance(document, new FileOutputStream(filePath));
        document.open();




        Paragraph chapterTitle = new Paragraph("java生成pdf文件", redFont);//设置内容
        chapterTitle.setAlignment(1);
        document.add(new Paragraph("表格信息", cusTitleFont));





        document.close();
    }

}
