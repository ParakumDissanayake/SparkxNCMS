package lk.sparkx.ncms.controllers;


import com.google.gson.Gson;
import lk.sparkx.ncms.models.Gender;
import lk.sparkx.ncms.models.Patient;
import lk.sparkx.ncms.models.SeverityLevel;
import lk.sparkx.ncms.service.PatientService;
import lk.sparkx.ncms.utils.Utility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

import static lk.sparkx.ncms.utils.Utility.sendResponse;

@WebServlet(name = "Patient")
public class PatientController extends HttpServlet {

    private final PatientService patientService = new PatientService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Command = request.getParameter("command");

            switch (Command) {
                case "REGISTER_PATIENT":
                    registerPatient(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Command = request.getParameter("command");

            switch (Command) {
                case "GET_PATIENT":
                    getPatient(request, response);
                    break;
                case "GET_ALL_ACTIVE_PATIENTS":
                    getAllActivePatients(request, response);
                default:
                    getAllActivePatients(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String Command = request.getParameter("command");

            switch (Command) {
                case "ADMIT_PATIENT":
                    admitPatient(request, response);
                    break;
                case "DISCHARGE_PATIENT":
                    dischargePatient(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dischargePatient(HttpServletRequest request, HttpServletResponse response) {
        String resp;
        try{
            String patientId = request.getParameter("patient_id");
            String dischargeDateStr = request.getParameter("discharge_date");
            String dischargedBy = request.getParameter("discharged_by");

            java.util.Date _dischargeDate = new SimpleDateFormat("yyyy-MM-dd").parse(dischargeDateStr);
            java.sql.Date dischargeDate = new java.sql.Date(_dischargeDate.getTime());

            Patient patient = patientService.getPatient(patientId);

            patient.setSeverityLevel(SeverityLevel.RECOVERED);
            patient.setDischargeDate(dischargeDate);
            patient.setDischargedBy(Integer.parseInt(dischargedBy));

            boolean status = patientService.dischargePatient(patient);

            if(status){
                resp = "Patient discharged successfully";
            }
            else{
                resp = "Something went wrong";
            }
        }
        catch (Exception e){
            resp = "Something went wrong";
            e.printStackTrace();
        }
        Utility.sendResponse(resp, response);
    }

    private void admitPatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String resp;
        try {
            String patientId = request.getParameter("patient_id");
            String severityLevel = request.getParameter("severity_level");
            String admitDateStr = request.getParameter("admit_date");
            String admittedBy = request.getParameter("admitted_by");

            java.util.Date _admitDate = new SimpleDateFormat("yyyy-MM-dd").parse(admitDateStr);
            java.sql.Date admitDate = new java.sql.Date(_admitDate.getTime());

            Patient patient = patientService.getPatient(patientId);

            patient.setSeverityLevel(severityLevel.valueOf(severityLevel));
            patient.setAdmitDate(admitDate);
            patient.setAdmittedBy(Integer.parseInt(admittedBy));

            boolean status = patientService.admitPatient(patient);

            if (status) {
                resp = "Patient admitted successfully";
            } else {
                resp = "Something went wrong";
            }
        } catch (Exception e) {
            resp = "Something went wrong";
            e.printStackTrace();
        }
        sendResponse(resp, response);


    }

    private void getAllActivePatients(HttpServletRequest request, HttpServletResponse response) {
        String patientList = new Gson().toJson(patientService.getAllPatients());
        Utility.sendResponse(patientList, response);
    }

    private void getPatient(HttpServletRequest request, HttpServletResponse response) {
        String patientIdOrSerialNo = request.getParameter("patientId");
        String responseString = new Gson().toJson(patientService.getPatient(patientIdOrSerialNo));
        Utility.sendResponse(responseString, response);
    }

    private void registerPatient(HttpServletRequest request, HttpServletResponse response) {
        try{
            String patientFName = request.getParameter("patientFName");
            String patientLName = request.getParameter("patientLName");
            String district = request.getParameter("district");
            String locationX = request.getParameter("locationX");
            String locationY = request.getParameter("locationY");
            String severityLevel = request.getParameter("severityLevel");
            String gender = request.getParameter("gender");
            String contact = request.getParameter("contact");
            String email = request.getParameter("email");
            String age = request.getParameter("age");

            Patient patient = new Patient();
            patient.setFirstName(patientFName);
            patient.setLastName(patientLName);
            patient.setDistrict(district);
            patient.setLocationX(Integer.parseInt(locationX));
            patient.setLocationY(Integer.parseInt(locationY));
            patient.setSeverityLevel(severityLevel.valueOf(severityLevel));
            patient.setGender(Gender.valueOf(gender));
            patient.setContact(contact);
            patient.setEmail(email);
            patient.setAge(Integer.parseInt(age));

            boolean status = patientService.registerPatient(patient);

            String resp;

            if (status){
                resp = "Patient Registered Successfully";
            }
            else{
                resp = "Something went wrong";
            }

            sendResponse(resp, response);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}