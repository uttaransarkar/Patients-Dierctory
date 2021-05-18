package com.uttaran.jsp.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PatientDbModel {
	
	private DataSource dataSource;

	public PatientDbModel(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public List<Patient> listPatients() throws Exception{
		//list all patients in DB
		List<Patient> listPatients = new ArrayList<Patient>();
		Connection conn = null;
		Statement stm = null;
		ResultSet res = null;
		
		try {			
			//get connection
			conn = dataSource.getConnection();
			
			//sql query and create statement
			String sql = "select * from patient order by status desc, time";
			stm = conn.createStatement();
			
			//execute the statement
			res = stm.executeQuery(sql);
			
			//process the result set
			while(res.next()) {
				int id = res.getInt("id");
				String time = res.getString("time");
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				int age = res.getInt("age");
				String gender = res.getString("gender");
				String pres = res.getString("prescription");
				String status = res.getString("status");
				
				Patient st = new Patient(id, time, firstName, lastName, age, gender, pres, status);
				listPatients.add(st);
			}
		
		} finally {
			//cleaning jdbc obj
			close(conn,stm,res);
		}
		
		return listPatients;
	}

	private void close(Connection conn, Statement stm, ResultSet res) {
		try {
			if(res != null)
				res.close();
			if(stm != null)
				stm.close();
			if(conn != null)
				conn.close();
			
		} catch (Exception e) {
				e.printStackTrace();
		}

	}

	public void addPatient (Patient ob) throws Exception {
		
		//adding patient to DB
		Connection conn = null;
		PreparedStatement prepstm = null;
		
		try {
			//get connection
			conn = dataSource.getConnection();
			
			//sql insert query and prepare statement
			String sql = "insert into patient (time, first_name, last_name, age, gender, prescription, status) "
					+ "values (?, ?, ?, ?, ?, ?, ?)";
			
			prepstm = conn.prepareStatement(sql);
			
			//setting the params
			prepstm.setString(1, ob.getTime());
			prepstm.setString(2, ob.getFirstName());
			prepstm.setString(3, ob.getLastName());
			prepstm.setInt(4, ob.getAge());
			prepstm.setString(5, ob.getGender());
			prepstm.setString(6, ob.getPres());
			prepstm.setString(7, ob.getStatus());
			
			//execute the statement
			prepstm.execute();
			
		} finally {
			//cleaning jdbc obj
			close(conn, prepstm, null);
		}
		
	}

	public Patient loadPatientInfo(int patientId) throws Exception {
		//load patient info from DB
		Connection conn = null;
		PreparedStatement prepstm = null;
		ResultSet res = null;
		Patient loaded = null;
		
		try {
			//get connection
			conn = dataSource.getConnection();
			
			//sql insert query and prepare statement
			String sql = "select * from patient where id=?";
			
			prepstm = conn.prepareStatement(sql);
			
			//setting the params
			prepstm.setInt(1, patientId);
			
			//executing the statement
			res = prepstm.executeQuery();
			
			//process the result set
			if(res.next()) {
				String time = res.getString("time");
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				int age = res.getInt("age");
				String gender = res.getString("gender");
				String pres = res.getString("prescription");
				String status = res.getString("status");
				
				loaded = new Patient(patientId, time, firstName, lastName, age, gender, pres, status);
			}
		} finally {
			//cleaning jdbc obj
			close(conn, prepstm, res);
		}
			
		return loaded;
	}

	public void updatePatient(Patient pt) throws Exception{
		//updating patient info in the DB
		Connection conn = null;
		PreparedStatement prepstm = null;
		
		try {
			//get connection
			conn = dataSource.getConnection();
			
			//sql insert query and prepare statement
			String sql = "update patient set first_name=?, last_name=?, age=?, gender=?,"
					+ " prescription=?, status=? where id=?";
			
			prepstm = conn.prepareStatement(sql);
			
			//setting the params
			prepstm.setString(1, pt.getFirstName());
			prepstm.setString(2, pt.getLastName());
			prepstm.setInt(3, pt.getAge());
			prepstm.setString(4, pt.getGender());
			prepstm.setString(5, pt.getPres());
			prepstm.setString(6, pt.getStatus());
			prepstm.setInt(7, pt.getId());
			
			//executing the statement
			prepstm.execute();
		
		} finally {
			//cleaning
			close(conn, prepstm, null);
		}
		
	}

}
