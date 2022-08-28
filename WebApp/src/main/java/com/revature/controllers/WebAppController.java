package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.*;
import com.revature.services.WebAppService;

public class WebAppController extends HttpServlet{

	private WebAppService was = new WebAppService();
	private ObjectMapper om = new ObjectMapper();
	private Clothing x = new Clothing();
	private Categories y = new Categories();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		
		String URI = request.getRequestURI();
		System.out.println(URI);
		String[] urlSections = URI.split("/");

		//GET INFO OF ALL ITEMS
		if (urlSections.length == 3) {
		@SuppressWarnings("unchecked")
		List<Clothing> list = (List<Clothing>) was.getInventoryOfAll(x.getClass());

		//JAVA STREAM TO SORT LIST BY ITEM NUMBER
		String json = om.writeValueAsString(list.stream().sorted(Comparator.comparing(Clothing::getItemNum)).collect(Collectors.toList()));
		System.out.println(json);

		PrintWriter printWriter = response.getWriter();

		printWriter.print(json);

		response.setStatus(200);//STATUS OK

		response.setContentType("application/json");
		}//GET INFO BY ITEM NUMBER
		else if (urlSections.length==4) {
			try {
				int id = Integer.valueOf(urlSections[3]);
				
				List<?> clothing = was.getInfoByItemNo(x.getClass(), y.getClass(), id);
				
				PrintWriter printWriter = response.getWriter();
				
				String json = om.writeValueAsString(clothing);
				
				printWriter.print(json);
				response.setStatus(200);//STATUS OK
				response.setContentType("application/json");
		
			}catch(NumberFormatException e) {
				response.setStatus(404);//ERROR: NOT FOUND
				return;
			}
		}else {
			response.setStatus(404);//ERROR: NOT FOUND
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String URI = request.getRequestURI();
		System.out.println(URI);
		String[] urlSections = URI.split("/");

		//INSERT NEW ITEM
		if (urlSections.length == 3) {
		StringBuilder sb = new StringBuilder();

		BufferedReader reader = request.getReader();

		String line = reader.readLine();

		while (line != null) {
			sb.append(line);
			line = reader.readLine();
		}

		String json = new String(sb);
		System.out.println(json);

		Clothing clothing = om.readValue(json, Clothing.class);

		was.insertNewItem(clothing);

		response.setStatus(201); //STATUS CREATED
		}else {
			response.setStatus(404);//ERROR: NOT FOUND
		}
		
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException{
		
		String URI = request.getRequestURI();
		System.out.println(URI);
		String[] urlSections = URI.split("/");
		
		//UPDATES ITEM  BY ITEM NUMBER AND ALLOWS USER TO INPUT THE COLUMN AND VALUE TO UPDATE
		if (urlSections.length==6) {
			try {
				int id = Integer.valueOf(urlSections[3]);
				String column = String.valueOf(urlSections[4]);
				String value = String.valueOf(urlSections[5]);
				StringBuilder sb = new StringBuilder();

				BufferedReader reader = request.getReader();

				String line = reader.readLine();

				while (line != null) {
					sb.append(line);
					line = reader.readLine();
				}

				String json = new String(sb);
				System.out.println(json);

				Clothing clothing = om.readValue(json, Clothing.class);

				was.updateByItemNo(clothing, id, column, value);

				response.setStatus(202); //STATUS ACCEPTED
			}catch(NumberFormatException e) {
				response.setStatus(404);//ERROR: NOT FOUND
				return;
			}
		}else {
			response.setStatus(404);//ERROR: NOT FOUND
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		String URI = request.getRequestURI();
		System.out.println(URI);
		String[] urlSections = URI.split("/");
		
		//DELETE ITEM BY ITEM NUMBER
		if (urlSections.length==4) {
			try {
				int id = Integer.valueOf(urlSections[3]);
				
				StringBuilder sb = new StringBuilder();

				BufferedReader reader = request.getReader();

				String line = reader.readLine();

				while (line != null) {
					sb.append(line);
					line = reader.readLine();
				}

				String json = new String(sb);
				System.out.println(json);

				Clothing clothing = om.readValue(json, Clothing.class);

				was.removeByItemNo(clothing, id);

				response.setStatus(202); //STATUS ACCEPTED
			}catch(NumberFormatException e) {
				response.setStatus(404);//ERROR: NOT FOUND
				return;
			}
		}else {
			response.setStatus(404);//ERROR: NOT FOUND
		}
	}
}
