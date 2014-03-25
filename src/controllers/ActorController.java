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

import models.Actor;
import tools.ConnectionDB;


public class ActorController extends HttpServlet {
		
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if(request.getParameter("action")!=null){
				ConnectionDB con = new ConnectionDB(); 
				List<ArrayList> lst;
				List<Actor> actorlst = new ArrayList<Actor>();
				
				String action=(String)request.getParameter("action");
				String id = (String)request.getParameter("id");
				
				Gson gson = new Gson();
				response.setContentType("application/json");
				
				if(action.equals("list")){
					try{	
						
					//Fetch Data from actor Table
						lst = con.getTableByName("actor");
						for (ArrayList<Object> m : lst){
							Actor item = new Actor((Integer)m.get(0), m.get(1).toString() , m.get(2).toString() );
							actorlst.add(item);
						}
						
					//Get Total Record Count for Pagination
						int userCount=actorlst.size();
					//Convert Java Object to Json				
					JsonElement element = gson.toJsonTree(actorlst, new TypeToken<List<Actor>>() {}.getType());
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
					Actor actor=new Actor(); //Create object for jsonarray translation
					Map <String, Object> matrr=new HashMap(); //create object hashmap for db insertion/update
					if(request.getParameter("actorid")!=null){				   
					   String actorId =request.getParameter("actorid");
					   int actorid=Integer.parseInt(actorId);
					   actor.setActorid(actorid);
					   matrr.put("actorid", actorid);
					}
					if(request.getParameter("name")!=null){
						String name=(String)request.getParameter("name");
						actor.setName(name);
						matrr.put("name", name);
					}
					if(request.getParameter("surname")!=null){				   
						   String surname =request.getParameter("surname");
						   actor.setSurname(surname);
						   matrr.put("surname", surname);
						}
					
						
					try{											
						if(action.equals("create")){//Create new record
							con.insertIntoTable("actor", matrr);				
							actorlst.add(actor);
							//Convert Java Object to Json				
							String json=gson.toJson(actor);					
							//Return Json in the format required by jTable plugin
							String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
							response.getWriter().print(listData);
							
						}else if(action.equals("update")){//Update existing record
							con.updateInTable("actor", matrr, "actorid", String.valueOf(actor.getActorid()));
							String listData="{\"Result\":\"OK\"}";									
							response.getWriter().print(listData);
							
						}
					}catch(Exception ex){
							String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
							response.getWriter().print(error);
					}
				}else if(action.equals("delete")){//Delete record
					try{
						if(request.getParameter("actorid")!=null){
							String actorid=(String)request.getParameter("actorid");
							System.out.println(actorid);
							con.deleteTuple("actor", "actorid", actorid);
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



