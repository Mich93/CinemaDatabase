package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tools.ConnectionDB;
import models.Projection;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

public class ProjectionController {
	
	private String action = null;
	private static String id;
	private ConnectionDB con; 



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
					for (ArrayList<?> m : lst){
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
				if(request.getParameter("title")!=null){				   
				   String title =request.getParameter("title");
				   proj.setTitle(title);
				   matrr.put("title", title);
				   System.out.println(title);
				}
				if(request.getParameter("category")!=null){
					String category=(String)request.getParameter("category");
					proj.setCategory(category);
					matrr.put("category", category);
					System.out.println(category);
				}
				if(request.getParameter("directorname")!=null){
				   String directorname=(String)request.getParameter("directorname");
				   proj.setDirectorname(directorname);
				   matrr.put("directorname", directorname);
				   System.out.println(directorname);
				}
				try{											
					if(action.equals("create")){//Create new record
						con.insertIntoTable("movie", matrr);				
						projectionlst.add(proj);
						//Convert Java Object to Json				
						String json=gson.toJson(proj);					
						//Return Json in the format required by jTable plugin
						String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
						response.getWriter().print(listData);
						
					}else if(action.equals("update")){//Update existing record
						con.updateInTable("movie", matrr, "title", proj.getTitle());
						String listData="{\"Result\":\"OK\"}";									
						response.getWriter().print(listData);
					}
				}catch(Exception ex){
						String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
						response.getWriter().print(error);
				}
			}else if(action.equals("delete")){//Delete record
				try{
					if(request.getParameter("title")!=null){
						String title=(String)request.getParameter("title");
						System.out.println(title);
						con.deleteTuple("movie", "title", title);
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
