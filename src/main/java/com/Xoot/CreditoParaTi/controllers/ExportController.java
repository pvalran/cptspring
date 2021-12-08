package com.Xoot.CreditoParaTi.controllers;

import com.Xoot.CreditoParaTi.entity.pima.Employee;
import com.Xoot.CreditoParaTi.mapper.FilterTransacionDTO;
import com.Xoot.CreditoParaTi.services.interfaces.ICustomerService;
import com.Xoot.CreditoParaTi.services.interfaces.IEmployeeService;
import com.Xoot.CreditoParaTi.services.interfaces.ITransactionService;
import com.Xoot.CreditoParaTi.services.interfaces.IUserService;
import com.Xoot.CreditoParaTi.services.service.ExportImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/export")
public class ExportController  {
    @Autowired
    private IUserService userService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private ITransactionService transactionService;

    @GetMapping("/leaflet/excel")
    public void exportLeaflet(HttpServletResponse response) throws IOException {
        ExportImpl exportService = new ExportImpl();
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=prospectos_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        exportService.exportLeaflet(response,userService.findAllBoardApp());
    }

    @GetMapping("/enrolments/excel/{id}")
    public void exportEnrolments(HttpServletResponse response,@PathVariable Integer id) throws IOException {
        ExportImpl exportService = new ExportImpl();
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        Employee employee = employeeService.findById(id);
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=enrolados_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        if (employee.getProfileId().equals("2")) {
            exportService.exportEnrolments(response,customerService.getByCustomerTransaction(employee.getIdUser()));
        } else {
            exportService.exportEnrolments(response,customerService.getByCustomerTransaction());
        }
    }

    @PostMapping("/customerstransaction/excel")
    public void exportCustomersTransaction(HttpServletResponse response, @RequestBody FilterTransacionDTO filterTransacionDto) throws IOException {
        ExportImpl exportService = new ExportImpl();
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=transaction_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        exportService.exportCustomerTransaction(response,transactionService.filterDate(filterTransacionDto));
    }
}
