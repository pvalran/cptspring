package com.Xoot.CreditoParaTi.services.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.Xoot.CreditoParaTi.dto.CustomerTransactionDTO;
import com.Xoot.CreditoParaTi.entity.Employee;
import com.Xoot.CreditoParaTi.entity.Usuario;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExportImpl {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExportImpl() {
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine(String name, List<String> titles) {
        sheet = workbook.createSheet(name);
        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(12);
        style.setFont(font);

        Integer column = 0;
        for (String title:titles){
            createCell(row, column, title, style);
            column++;
        }
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Date) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLeaflet(List<Usuario> Users) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        for (Usuario user : Users) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, user.getIdUser(), style);
            createCell(row, columnCount++, user.getName(), style);
            createCell(row, columnCount++, user.getPaternalLastName(), style);
            createCell(row, columnCount++, user.getMotherLastName(), style);
            createCell(row, columnCount++, user.getEmail(), style);
            if (user.getStatus_flag() == 0) {
                createCell(row, columnCount++, "NO", style);
            } else {
                createCell(row, columnCount++, "SI", style);
            }
        }
    }



    public void exportLeaflet(HttpServletResponse response,List<Usuario> users) throws IOException {
        List<String> titles = new ArrayList<String>();
        titles.add("ID");
        titles.add("Nombres");
        titles.add("Apellido paterno");
        titles.add("Apellido materno");
        titles.add("Email");
        titles.add("Activo");
        writeHeaderLine("Prospectos",titles);
        writeDataLeaflet(users);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportEnrolments(HttpServletResponse response, List<CustomerTransactionDTO> customters) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String> titles = new ArrayList<String>();
        titles.add("FOLIO");
        titles.add("FECHA DE SOLICITUD");
        titles.add("PROMOTOR");
        titles.add("ENROLAMIENTO");
        titles.add("NOMBRES");
        titles.add("APELLIDO PATERNO");
        titles.add("APELLIDO MATERNO");
        titles.add("ESTATUS");
        titles.add("CAPA DE DOCUMENTO");
        titles.add("CAPA DE SELFIE");
        writeHeaderLine("Prospectos",titles);
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        for (CustomerTransactionDTO customer : customters) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, customer.getCustomer().getCreditId(), style);

            createCell(row, columnCount++, dateFormatter.format(customer.getCrtd_on()), style);
            createCell(row, columnCount++, customer.getCrtd_by(), style);
            createCell(row, columnCount++, customer.getEnrolment(), style);
            createCell(row, columnCount++, customer.getCustomer().getName(), style);
            createCell(row, columnCount++, customer.getCustomer().getPaternalLastName(), style);
            createCell(row, columnCount++, customer.getCustomer().getMotherLastName(), style);
            createCell(row, columnCount++, customer.getStatus(), style);
            createCell(row, columnCount++, customer.getLayerDocument(), style);
            createCell(row, columnCount++, customer.getLayerGobernment(), style);
        }
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }

    public void exportCustomerTransaction(HttpServletResponse response, List<CustomerTransactionDTO> customters) throws IOException {
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        List<String> titles = new ArrayList<String>();
        titles.add("FOLIO");
        titles.add("FECHA DE SOLICITUD");
        titles.add("PROMOTOR");
        titles.add("EMAIL");
        titles.add("CELULAR");
        titles.add("ENROLAMIENTO");
        titles.add("ESTATUS");
        titles.add("CAPA DE DOCUMENTO");
        titles.add("CAPA DE SELFIE");
        titles.add("CAPA DE GOBIERNO");
        writeHeaderLine("Transaciones",titles);
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(10);
        style.setFont(font);

        for (CustomerTransactionDTO customer : customters) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, customer.getCustomer().getCreditId(), style);
            createCell(row, columnCount++, customer.getCrtd_by(), style);
            createCell(row, columnCount++, dateFormatter.format(customer.getCrtd_on()), style);
            createCell(row, columnCount++, customer.getCustomer().getEmail(), style);
            createCell(row, columnCount++, customer.getMobile(), style);
            createCell(row, columnCount++, customer.getEnrolment(), style);
            createCell(row, columnCount++, customer.getStatus(), style);
            createCell(row, columnCount++, customer.getLayerDocument(), style);
            createCell(row, columnCount++, customer.getLayerBiometric(), style);
            createCell(row, columnCount++, customer.getLayerGobernment(), style);
        }
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
