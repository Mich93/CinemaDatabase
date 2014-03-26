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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import models.Projection;
import models.Ticket;
import tools.ConnectionDB;


public class TicketController extends HttpServlet {
		
	private static final long serialVersionUID = 1L;
	private String action = null;
	private static String id;
	private ConnectionDB con = new ConnectionDB(); 

//DoGet checks account table for an id and set the response right
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
			
		if(request.getParameter("password") != null){
			String email=(String)request.getParameter("email");
			String passw = (String)request.getParameter("password");
			
		    session.setMaxInactiveInterval(600);
		    
		try {
//			getServletConfig().getServletContext().getRequestDispatcher("index.jsp").forward(request, response);
			id = con.getIdByAccount(email, passw);
			
			if (id != null){
			session.setAttribute("action", "list");	
			session.setAttribute("id", id);
			
			response.sendRedirect("tickets.jsp");
			}
			else{
				response.sendRedirect("tickets.jsp");
			}
				
		} 
		catch (Exception ex) {
				ex.printStackTrace();
			}	
		}
	}
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if(request.getParameter("action")!=null){
				
				List<ArrayList> lst;
				List<Projection> prjctlist = new ArrayList<Projection>();
				
				action=(String)request.getParameter("action");
				
				Gson gson = new Gson();
				response.setContentType("application/json");
				
				if(action.equals("list") && id != null){
					try{	
						
					//Fetch Data from Ticket Table
						lst = con.getTableByNameWithJoin("projection","ticket","projectionid","accountid", id);
						for (ArrayList<Object> m : lst){
							Projection item = new Projection((Integer)m.get(0), m.get(1).toString(), m.get(2).toString(), m.get(3).toString(), m.get(4).toString(), (Integer)m.get(5), m.get(6).toString());
							prjctlist.add(item);
						}
						
					//Get Total Record Count for Pagination
						int userCount=prjctlist.size();
					//Convert Java Object to Json				
					JsonElement element = gson.toJsonTree(prjctlist, new TypeToken<List<Projection>>() {}.getType());
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
				}else if(action.equals("delete")){//Delete record
					try{
						if(request.getParameter("projectionid")!=null){
							String projectionid=(String)request.getParameter("projectionid");
							System.out.println(projectionid);
							con.deleteTuple("projection", "projectionid", projectionid);
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


