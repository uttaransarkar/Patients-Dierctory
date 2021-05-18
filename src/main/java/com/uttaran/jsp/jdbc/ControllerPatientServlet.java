package com.uttaran.jsp.jdbc;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class ControllerPatientServlet
 */
@WebServlet("/ControllerPatientServlet")
public class ControllerPatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	PatientDbModel patientDbModel;
	
	//resource injection
	@Resource(name = "jdbc/web_patient_record")
	private DataSource dataSource;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

		//initialize PatientDbModel obj
		try {
			patientDbModel = new PatientDbModel(dataSource);
			
		} catch(Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String opCommand = request.getParameter("command");
			
			if(opCommand == null) 
				opCommand = "list";
			
			switch(opCommand) {
				
				case "list":
					listPatients(request, response);
					break;
					
				case "LOAD":
					loadPatientInfo(request, response);
					break;
				
				case "UPDATE":
					updatePatients(request, response);
					break;
					
				
				default:
					listPatients(request, response);
			}
		
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	private void updatePatients(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//updating the patient info
		Date date = new Date();
		String timeFormat = "HH:mm:ss";
		String time = new SimpleDateFormat(timeFormat).format(date);
		
		//read the updated form fields
		int patientId = Integer.parseInt(request.getParameter("patientId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String pres = request.getParameter("pres");
		String status = request.getParameter("status");
		
		//create a Student object and pass it to pass it to model helper class
		Patient pt = new Patient(patientId, time, firstName, lastName, age, gender, pres, status);
		patientDbModel.updatePatient(pt);
		
		//redirecting to updated list
		listPatients(request, response);
		
	}

	private void loadPatientInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//loading the patient info
		int patientId = Integer.parseInt(request.getParameter("patientId"));
		
		//load data from DB
		Patient loaded = patientDbModel.loadPatientInfo(patientId);
		
		//set data as attribute to request
		request.setAttribute("loaded_patient", loaded);
		
		//request dispatcher and forward this data
		RequestDispatcher dispatcher = request.getRequestDispatcher("/patient-update-form.jsp");
		dispatcher.forward(request, response);
		
	}

	private void listPatients(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//get data from helper model class
		List<Patient> listPatients = patientDbModel.listPatients();
		
		//set data to attribute
		request.setAttribute("list_patients", listPatients);
		
		//request dispatcher and forwarding
		RequestDispatcher dispatcher = request.getRequestDispatcher("/patient-list-form.jsp");
		
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String opCommand = request.getParameter("command");
		
		try {
		switch (opCommand) {
			case "ADD": 
				//add patient
				addPatient(request, response);
				break;
			
			default:
				listPatients(request, response);
			}
		} catch (Exception e) {
			
			throw new ServletException(e);
		}
	}

	private void addPatient(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//getting the time when patient is added to the list
		Date date = new Date();
		String timeFormat = "HH:mm:ss";
		String time = new SimpleDateFormat(timeFormat).format(date);
		
		//read the form fields
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String pres = "";
		String status = "Pending";
		
		
		//create new Patient obj
		Patient ob = new Patient(time, firstName, lastName, age, gender, pres, status);
	
		//add it to DB
		patientDbModel.addPatient(ob);
		
		//redirecting to updated list
		response.sendRedirect(request.getContextPath() + "/ControllerPatientServlet?command=");

	}

}
