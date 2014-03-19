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

import models.Cinema;
import tools.ConnectionDB;


public class CinemaController extends HttpServlet {
		
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if(request.getParameter("action")!=null){
				ConnectionDB con = new ConnectionDB(); 
				List<ArrayList> lst;
				List<Cinema> Cinemalst = new ArrayList<Cinema>();
				
				String action=(String)request.getParameter("action");
				String id = (String)request.getParameter("id");
				
				Gson gson = new Gson();
				response.setContentType("application/json");
				
				if(action.equals("list")){
					try{	
						
					//Fetch Data from Cinema Table
						lst = con.getTableByName("Cinema");
						for (ArrayList<Object> m : lst){
							Cinema item = new Cinema((Integer)m.get(0), m.get(1).toString(), m.get(2).toString() , m.get(3).toString(), Time.valueOf(m.get(4).toString()),Time.valueOf(m.get(5).toString()));
							Cinemalst.add(item);
						}
						
					//Get Total Record Count for Pagination
						int userCount=Cinemalst.size();
					//Convert Java Object to Json				
					JsonElement element = gson.toJsonTree(Cinemalst, new TypeToken<List<Cinema>>() {}.getType());
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
					Cinema Cinema=new Cinema(); //Create object for jsonarray translation
					Map <String, Object> matrr=new HashMap(); //create object hashmap for db insertion/update
					if(request.getParameter("cinemaid")!=null){				   
					   String cinemaId =request.getParameter("cinemaid");
					   int cinemaid=Integer.parseInt(cinemaId);
					   Cinema.setCinemaid(cinemaid);
					   matrr.put("cinemaid", cinemaid);
					}
					if(request.getParameter("name")!=null){
						String name=(String)request.getParameter("name");
						Cinema.setName(name);
						matrr.put("name", name);
					}
					if(request.getParameter("address")!=null){				   
						   String address =request.getParameter("address");
						   Cinema.setAddress(address);
						   matrr.put("address", address);
						}
					if(request.getParameter("city")!=null){
					   String city=(String)request.getParameter("city");
					   Cinema.setCity(city);
					   matrr.put("city", city);
					}
					if(request.getParameter("openhr")!=null){				   
						   String openHr =request.getParameter("openhr");
						   Time openhr=Time.valueOf(openHr);
						   Cinema.setOpenhr(openhr);
						   matrr.put("openhr", openhr);
						}
					if(request.getParameter("closehr")!=null){				   
						   String closeHr =request.getParameter("closehr");
						   Time closehr=Time.valueOf(closeHr);
						   Cinema.setClosehr(closehr);
						   matrr.put("closehr", closehr);
						}
					try{											
						if(action.equals("create")){//Create new record
							con.insertIntoTable("cinema", matrr);				
							Cinemalst.add(Cinema);
							//Convert Java Object to Json				
							String json=gson.toJson(Cinema);					
							//Return Json in the format required by jTable plugin
							String listData="{\"Result\":\"OK\",\"Record\":"+json+"}";											
							response.getWriter().print(listData);
							
						}else if(action.equals("update")){//Update existing record
							con.updateInTable("cinema", matrr, "cinemaid", String.valueOf(Cinema.getCinemaid()));
							String listData="{\"Result\":\"OK\"}";									
							response.getWriter().print(listData);
						}
					}catch(Exception ex){
							String error="{\"Result\":\"ERROR\",\"Message\":"+ex.getStackTrace().toString()+"}";
							response.getWriter().print(error);
					}
				}else if(action.equals("delete")){//Delete record
					try{
						if(request.getParameter("cinemaid")!=null){
							String Cinemaid=(String)request.getParameter("cinemaid");
							System.out.println(Cinemaid);
							con.deleteTuple("cinema", "cinemaid", Cinemaid);
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


