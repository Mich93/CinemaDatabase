package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ConnectionDB;
import models.Projection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class ProjectionController extends HttpServlet {
	
	private String action = null;
	private ConnectionDB con; 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("action")!=null){
			con = new ConnectionDB(); 	
			List<ArrayList> lst;
			List<Projection> projectionlst = new ArrayList<Projection>();
			String action=(String)request.getParameter("action");
			
			Gson gson = new Gson();
			response.setContentType("application/json");
			
			if(action.equals("list")){
				try{	
					
				//Fetch Data from User Table
					lst = con.getTableByName("projection");
					for (ArrayList<Object> m : lst){
						Projection item = new Projection((Integer)m.get(0), m.get(1).toString(), m.get(2).toString(), m.get(3).toString(), m.get(4).toString(), (Integer)m.get(5), m.get(6).toString());
						projectionlst.add(item);
					}
					
				//Get Total Record Count for Pagination
					int userCount=projectionlst.size();
				//Convert Java Object to Json				
				JsonElement element = gson.toJsonTree(projectionlst, new TypeToken<List<Projection>>() {}.getType());
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
				Projection proj=new Projection(); //Create object for jsonarray translation
				Map <String, Object> matrr=new HashMap(); //create object hashmap for db insertion/update
				if(request.getParameter("projectionid")!=null){				   
				   int id =Integer.parseInt(request.getParameter("projectionid"));
				   proj.setId(id);
				   matrr.put("projectionid", id);
				   System.out.println(id);
				}
				if(request.getParameter("language")!=null){
					String language=(String)request.getParameter("language");
					proj.setLanguage(language);
					matrr.put("category", language);
					System.out.println(language);
				}
				if(request.getParameter("projectiondate")!=null){
				   String projectiondate=(String)request.getParameter("projectiondate");
				   proj.setDate(projectiondate);
				   matrr.put("projectiondate", projectiondate);
				   System.out.println(projectiondate);
				}
			   if(request.getParameter("starthr")!=null){
					   String starthr=(String)request.getParameter("starthr");
					   proj.setStart(starthr);
					   matrr.put("starthr", starthr);
					   System.out.println(starthr);
					}
			   if(request.getParameter("endhr")!=null){
				   String endhr=(String)request.getParameter("endhr");
				   proj.setEnd(endhr);
				   matrr.put("endhr", endhr);
				   System.out.println(endhr);
				}
			   if(request.getParameter("theatreno")!=null){
				   int theaternr= Integer.parseInt(request.getParameter("theaterno"));
				   proj.setTheaterNr(theaternr);
				   matrr.put("theaterno", theaternr);
				   System.out.println(theaternr);
				}
			   if(request.getParameter("movietitle")!=null){
				   int movietitle= Integer.parseInt(request.getParameter("movietitle"));
				   proj.setTheaterNr(movietitle);
				   matrr.put("movietitle", movietitle);
				   System.out.println(movietitle);
				}
			   
				try{											
					if(action.equals("create")){//Create new record
						con.insertIntoTable("projection", matrr);				
						projectionlst.add(proj);
						//Convert Java Object to Json				
						String json=gson.toJson(proj);					
						//Return Json in the format required by jTable plugin
						String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
						response.getWriter().print(listData);
						
					}else if(action.equals("update")){//Update existing record
						con.updateInTable("projection", matrr, "projectionid", String.valueOf(proj.getId()));
						String listData="{\"Result\":\"OK\"}";									
						response.getWriter().print(listData);
					}
				}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
						response.getWriter().print(error);
				}
			}else if(action.equals("delete")){//Delete record
				try{
					if(request.getParameter("projectionid")!=null){
						String id=(String)request.getParameter("projection");
						System.out.println(id);
						con.deleteTuple("projection", "projectionid", id);
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
