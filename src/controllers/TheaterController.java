package controllers;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import models.Theater;
import tools.ConnectionDB;


public class TheaterController extends HttpServlet {
		
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if(request.getParameter("action")!=null){
				ConnectionDB con = new ConnectionDB(); 
				List<ArrayList> lst;
				List<Theater> Theaterlst = new ArrayList<Theater>();
				
				String action=(String)request.getParameter("action");
				String id = (String)request.getParameter("id");
				
				Gson gson = new Gson();
				response.setContentType("application/json");
				
				if(action.equals("list")){
					try{	
						
					//Fetch Data from Theater Table
						lst = con.getTableByName("theater");
						for (ArrayList<Object> m : lst){
							Theater item = new Theater((Integer)m.get(0),(Integer)m.get(1),(Integer)m.get(2));
							Theaterlst.add(item);
						}
						
					//Get Total Record Count for Pagination
						int userCount=Theaterlst.size();
					//Convert Java Object to Json				
					JsonElement element = gson.toJsonTree(Theaterlst, new TypeToken<List<Theater>>() {}.getType());
					JsonArray jsonArray = element.getAsJsonArray();
					String listData= jsonArray.toString();	
					
					//Return Json in the format required by jTable plugin
					
					listData="{\"Result\":\"OK\",\"Records\":"+listData+",\"TotalRecordCount\":"+userCount+"}";			
					response.getWriter().print(listData);
					}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getMessage()+"}";
						response.getWriter().print(error);
						ex.printStackTrace();
					}				
				}
			
				else if(action.equals("create") || action.equals("update")){
					Theater Theater=new Theater(); //Create object for jsonarray translation
					Map <String, Object> matrr=new HashMap(); //create object hashmap for db insertion/update
					if(request.getParameter("theaterno")!=null){				   
					   String theaterNo =request.getParameter("theaterno");
					   int theaterno=Integer.parseInt(theaterNo);
					   Theater.setTheaterno(theaterno);
					   matrr.put("theaterno", theaterno);
					}
					if(request.getParameter("capacity")!=null){
						String capacityI=(String)request.getParameter("capacity");
						int capacity=Integer.parseInt(capacityI);
						Theater.setCapacity(capacity);
						matrr.put("capacity", capacity);
					}
					if(request.getParameter("cinemaid")!=null){
						String cinemaId=(String)request.getParameter("cinemaid");
						int cinemaid=Integer.parseInt(cinemaId);
						Theater.setCapacity(cinemaid);
						matrr.put("capacity", cinemaid);
					}
					try{											
						if(action.equals("create")){//Create new record
							con.insertIntoTable("theater", matrr);				
							Theaterlst.add(Theater);
							//Convert Java Object to Json				
							String json=gson.toJson(Theater);					
							//Return Json in the format required by jTable plugin
							String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
							response.getWriter().print(listData);
							
						}else if(action.equals("update")){//Update existing record
							con.updateInTable("theater", matrr, "theaterno", String.valueOf(Theater.getTheaterno()));
							String listData="{\"Result\":\"OK\"}";									
							response.getWriter().print(listData);
						}
					}catch(Exception ex){
							String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
							response.getWriter().print(error);
					}
				}else if(action.equals("delete")){//Delete record
					try{
						if(request.getParameter("theaterno")!=null){
							String Theaterno=(String)request.getParameter("theaterno");
							System.out.println(Theaterno);
							con.deleteTuple("theater", "theaterno", Theaterno);
							String listData="{\"Result\":\"OK\"}";								
							response.getWriter().print(listData);
							
						}
					}catch(Exception ex){
					String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
					response.getWriter().print(error);
				}				
			}
				con.closeConnection();
		 }
			
	   }
	}



