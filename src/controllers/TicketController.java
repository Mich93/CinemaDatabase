package controllers;

import java.io.IOException;
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

import models.Ticket;
import tools.ConnectionDB;


public class TicketController extends HttpServlet {
		
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if(request.getParameter("action")!=null){
				ConnectionDB con = new ConnectionDB(); 
				List<ArrayList> lst;
				List<Ticket> ticketlst = new ArrayList<Ticket>();
				
				String action=(String)request.getParameter("action");
				String id = (String)request.getParameter("id");
				
				Gson gson = new Gson();
				response.setContentType("application/json");
				
				if(action.equals("list")){
					try{	
						
					//Fetch Data from Ticket Table
						lst = con.getTableByName("ticket");
						for (ArrayList<Object> m : lst){
							Ticket item = new Ticket((Integer)m.get(0), m.get(1).toString(), (Integer)m.get(2) , m.get(3).toString(), (Integer)m.get(4));
							ticketlst.add(item);
						}
						
					//Get Total Record Count for Pagination
						int userCount=ticketlst.size();
					//Convert Java Object to Json				
					JsonElement element = gson.toJsonTree(ticketlst, new TypeToken<List<Ticket>>() {}.getType());
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
					Ticket ticket=new Ticket(); //Create object for jsonarray translation
					Map <String, Object> matrr=new HashMap(); //create object hashmap for db insertion/update
					if(request.getParameter("title")!=null){				   
					   String title =request.getParameter("title");
					   ticket.setTitle(title);
					   matrr.put("title", title);
					}
					if(request.getParameter("category")!=null){
						String category=(String)request.getParameter("category");
						ticket.setCategory(category);
						matrr.put("category", category);
					}
					if(request.getParameter("directorname")!=null){
					   String directorname=(String)request.getParameter("directorname");
					   ticket.setDirectorname(directorname);
					   matrr.put("directorname", directorname);
					}
					try{											
						if(action.equals("create")){//Create new record
							con.insertIntoTable("movie", matrr);				
							ticketlst.add(ticket);
							//Convert Java Object to Json				
							String json=gson.toJson(ticket);					
							//Return Json in the format required by jTable plugin
							String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
							response.getWriter().print(listData);
							
						}else if(action.equals("update")){//Update existing record
							con.updateInTable("movie", matrr, "title", ticket.getTitle());
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

}
