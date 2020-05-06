package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/HospitalsAPI")
public class HospitalsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Hospital hosObj = new Hospital();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HospitalsAPI() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String output = hosObj.insertHopital(null, request.getParameter("hospital_name"), request.getParameter("hospital_address_no"),
				request.getParameter("hospital_address_lane1"), request.getParameter("hospital_address_lane2"),  request.getParameter("hospital_address_lane3"),  request.getParameter("hospital_city"), request.getParameter("Tel"), request.getParameter("Email"));
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Map paras = getParasMap(request);
		String output = hosObj.updateHospital(null, paras.get("hospital_name").toString(), paras.get("hospital_address_no").toString(),
				paras.get("hospital_address_lane1").toString(), paras.get("hospital_address_lane2").toString(), paras.get("hospital_address_lane3").toString(),  paras.get("hospital_city").toString(), paras.get("Tel").toString(), paras.get("Email").toString());
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = hosObj.deleteHospital(paras.get("hospital_id").toString());
		response.getWriter().write(output); 
		
	}

	private static Map getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
		}
		return map;
	}

}
