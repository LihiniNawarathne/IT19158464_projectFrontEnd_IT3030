package servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import recources.PaymentResources;

/**
 * Servlet implementation class paymentAPI
 */
@WebServlet("/paymentAPI")
public class paymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PaymentResources paymentobj = new PaymentResources();
	
	
	  /**
     * @see HttpServlet#HttpServlet()
     */
    public paymentAPI() {
        super();
        // TODO Auto-generated constructor stub
        
        
    }

 

    
    private static Map getParasMap(HttpServletRequest request)
    {
	    Map<String, String> map = new HashMap<String, String>();
	    try
	    {
		    Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		    String queryString = scanner.hasNext() ?
		    scanner.useDelimiter("\\A").next() : "";
		    scanner.close();
		    String[] params = queryString.split("&");
		    for (String param : params)
		    {
		    	String[] p = param.split("=");
		    	 System.out.println("Para :"+ p);
		    	map.put(p[0], p[1]);
		    	System.out.println("p0 :"+p[0]);
				 System.out.println("p1 :"+p[1]);
		    }
		}
	    catch (Exception e)
	    {		    
	    	
	    }
		return map;
    }
	
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ID :"+request.getParameter("userID"));
		String output =paymentobj.insertPayment(request.getParameter("userID"), 
				request.getParameter("billID"), 
				request.getParameter("paid_amount"), 
				request.getParameter("payment_type"), 
				request.getParameter("card_no"));
			
		response.getWriter().write(output);

	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("API");
		Map paras = getParasMap(request); 
		System.out.println("Bill ID :"+paras.get("hidpaymentIDSave").toString());
		 String output = paymentobj.updatePayment(paras.get("hidpaymentIDSave").toString(),
				 paras.get("billID").toString(), 
				 paras.get("paid_amount").toString(), 
				 paras.get("payment_type").toString(), 
				 paras.get("card_no").toString());
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Before Bill ID ");
		Map paras = getParasMap(request);
		System.out.println("Bill ID :"+paras.get("billID").toString());
		String output = paymentobj.deletepayment(paras.get("billID").toString());
		response.getWriter().write(output);
		
	}

}
