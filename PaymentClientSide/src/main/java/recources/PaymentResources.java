package recources;

import java.sql.*;

import util.DBConnection;


public class PaymentResources {

		//DBConnection object to connect to database
		DBConnection dbConnect = new DBConnection();
		String dbErrorMessage = "Database Connection failed!!";
	
		
		//get all payment details based on the user id
				public String readPayment(){
					String outPut = "";
					try{
						Connection con = dbConnect.connect();
						if (con == null){
							return "Error while connecting to the database for reading."; 	
						}
						// Prepare the table to display payment details
						outPut ="<center><h3>Payment Details</h3><center>"+ 
								"<table border='1'><tr><th>Payment ID</th><th>User ID</th>" +
						"<th>Bill ID</th>" +
						"<th>Toatl Amount</th>" +
						"<th>Paid Amount</th>" +
						"<th>Balance</th>" +
						"<th>Month</th>" +
						"<th>Payment Type</th>" +
						"<th>Card no.</th>" +
						"<th>Paid Date</th>" +
						"<th>Update</th><th>Delete</th></tr>";
						
						//query to get all the payment details
						String query = "select * from payment ";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						// binding values

						ResultSet rs = preparedStmt.executeQuery(query);
						
						// iterate through the rows in the result set
						while (rs.next()){
							String paymentID = Integer.toString(rs.getInt("paymentID"));
							String userID = rs.getString("userID");
							String billID = rs.getString("billID");
							String total_amount = Double.toString(rs.getDouble("total_amount"));
							String paid_amount = Double.toString(rs.getDouble("paid_amount"));
							String balance = Double.toString(rs.getDouble("balance"));
							String month =rs.getString("month");
							String payment_type =rs.getString("payment_type");
							String card_no =rs.getString("card_no");
							String paid_Date =rs.getString("paid_Date");
						
							// Add into the html table
							outPut += "<tr><td>" + paymentID + "</td>";
							outPut += "<td>" + userID + "</td>";
							outPut += "<td>" + billID + "</td>";
							outPut += "<td>" + total_amount + "</td>";
							outPut += "<td>" + paid_amount + "</td>";
							outPut += "<td>" + balance + "</td>";
							outPut += "<td>" + month + "</td>";
							outPut += "<td>" + payment_type + "</td>";
							outPut += "<td>" + card_no + "</td>";
							outPut += "<td>" + paid_Date + "</td>";
						
							// button for backing a concept
							outPut += "<td><input name='btnUpdate' type='button' value='Update' "
									+ "class='btnUpdate btn btn-secondary' data-userid='" + paymentID + "'></td>"
									+ "<td><input name='btnRemove' id ='btnRemove' type='button' value='Remove' "
									+ "class='btnRemove btn btn-danger' data-userid='" + billID + "'></td></tr>";
							
						} 
						 
								 
						con.close();
						
						// Complete the html table
						outPut += "</table>";
						
					}catch (Exception e){
						outPut = "Error while reading the items.";
						System.err.println(e.getMessage());	
					}
					return outPut;
					
				}
				
				//read one specific user's payment details
				public String readOneUserPayment(String user_ID)
				{
					String outPut = "";
					try
					{
						Connection con = dbConnect.connect();
						if (con == null)
						{
							return "Error while connecting to the database for reading.";
						}
						System.out.println("userId = "+user_ID);
						
						//get month and amount of bill
						String q1 = "SELECT name from electrogrid.users where userID = '"+ user_ID +"'";
						Statement stmt2 = con.createStatement();
						ResultSet rs1 = stmt2.executeQuery(q1);
						rs1.next();
						System.out.println("No user details for"+rs1.getString(1));
						//check whether the given user id exists on database
						if(!rs1.next()) {
							// Displaying the read concepts
							outPut = "<center><h3>Payment Details</h3><center>"+
									"<table border='1'><tr><th>Payment ID</th>" +
									"<th>Bill ID</th>" +
									"<th>Toatl Amount</th>" +
									"<th>Paid Amount</th>" +
									"<th>Balance</th>" +
									"<th>Month</th>" +
									"<th>Payment Type</th>" +
									"<th>Card no.</th>" +
									"<th>Paid Date</th>" +
									"<th>Update</th><th>Delete</th></tr>";
							
							// Retrieving the concepts launched by a particular researcher
							String query ="SELECT * from payment where userID = '"+ user_ID+"'";
							Statement createStmt = con.createStatement();
							
							ResultSet rs = createStmt.executeQuery(query);
							
							// iterate through the rows in the result set
							while (rs.next())
							{
								String payment_ID = Integer.toString(rs.getInt("paymentID"));
								String userID = rs.getString("userID");
								String billID = rs.getString("billID");
								String total_amount = Double.toString(rs.getDouble("total_amount"));
								String paid_amount = Double.toString(rs.getDouble("paid_amount"));
								String balance = Double.toString(rs.getDouble("balance"));
								String month =rs.getString("month");
								String payment_type =rs.getString("payment_type");
								String card_no =rs.getString("card_no");
								String paid_Date =rs.getString("paid_Date");
								
								
								// Add into the html table
								outPut += "<tr><td>" + payment_ID + "</td>";
								outPut += "<td>" + billID + "</td>";
								outPut += "<td>" + total_amount + "</td>";
								outPut += "<td>" + paid_amount + "</td>";
								outPut += "<td>" + balance + "</td>";
								outPut += "<td>" + month + "</td>";
								outPut += "<td>" + payment_type + "</td>";
								outPut += "<td>" + card_no + "</td>";
								outPut += "<td>" + paid_Date + "</td>";
							
								// button for backing a concept
								outPut += "<td><input name='btnUpdate' type='button' value='Update' "
										+ "class='btnUpdate btn btn-secondary' data-itemid='" + billID + "'></td>"
										+ "<td><input name='btnRemove' type='button' value='Remove' "
										+ "class='btnRemove btn btn-danger' data-itemid='" + billID + "'></td></tr>";
								 } 
	
							con.close();
							
							// Complete the html table
							outPut += "</table>";
						}
							
						else {
							System.out.println("No user details for"+rs1.getString(1));
							return outPut ="The user does not exist in the database";
							
						}
						}
						catch (Exception e)
						{
							outPut ="<center>Error while retrieving payment details!!</center>";
							System.out.println(e.getMessage());
						}
						return outPut;
				}
		
		
		
		//Insert new payment details to the table
		public String insertPayment(String userID,String billID,  String p_amount,String payment_type,String card_no){
			String outPut = "";		

			//check whether the input fields are empty or not
			if(userID.isEmpty() && billID.isEmpty() && p_amount.isEmpty()  && payment_type.isEmpty() && card_no.isEmpty()) {
			
				System.out.println("Check2");
				return "Fields cannot be empty";
			}
			else {
				System.out.println("Check1");
				
					try{
		
						Connection con = dbConnect.connect();
						if (con == null){
							return "Error while connecting to the database for inserting."; 	
						}
						System.out.println("Before Converted billID "+billID);
						System.out.println("Class billID "+billID.getClass());
						//convert to relevant data type
						int bill_ID = Integer.parseInt(billID);
						System.out.println("Converted billID "+bill_ID);
						double paid_amount= Double.parseDouble(p_amount);
						
						// create a prepared statement
						String query = " insert into payment(paymentID,userID,billID,total_amount,paid_amount,balance,month,payment_type,card_no,paid_Date) VALUES (?, ?, ?, ?, ?, ?,?, ?, ?, ?)";						
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
						
						//get month and amount of bill
						String q1 = "SELECT * from billing where bill_ID = '"+ bill_ID +"'";
						Statement stmt2 = con.createStatement();
						ResultSet rs1 = stmt2.executeQuery(q1);
						rs1.next();
						
						String month =rs1.getString(6);
						String total_amount =rs1.getString(9);
		
						System.out.println(month+" "+total_amount);
						
						
						// binding values for payment table
						preparedStmt.setInt(1, 0);
						preparedStmt.setString(2, userID);
						preparedStmt.setInt(3, bill_ID);
						preparedStmt.setDouble(4, Double.parseDouble(total_amount));
						
						
						//get month and amount of bill
						String q2 = "SELECT paid_amount,balance from payment where billID = '"+ bill_ID +"'order by paymentID desc limit 1";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(q2);
						
						//check whether a payment record already exited to the given bill id
						if(!rs.next()) {//There is no record can be found in the payment table for the given Bill ID

							// binding values for payment table
							preparedStmt.setDouble(5, paid_amount);
							
							//get balance (if user paid less than the total amount, balance will be a negative value) 
							double balance =  paid_amount - Double.parseDouble(total_amount);
							preparedStmt.setDouble(6, balance);
							
							
						}
						else{//There is/are record can be found in the payment table for the given Bill ID
						double pamount = Double.parseDouble(rs.getString(1))+paid_amount;
						double pbalance = pamount - Double.parseDouble(total_amount);
						// binding values for payment table
						preparedStmt.setDouble(5, pamount);
						preparedStmt.setDouble(6, pbalance);
						
						}
						
		
						preparedStmt.setString(7, month);
						preparedStmt.setString(8, payment_type);
						preparedStmt.setString(9, card_no);
						
						//get current date
						long currentDate=System.currentTimeMillis();  
						java.sql.Date paid_Date=new java.sql.Date(currentDate);
						preparedStmt.setString(10, paid_Date.toString());
						
						
						
						// execute the statement
						preparedStmt.execute();
						
						con.close();
						String newPayment = readPayment(); 
						outPut = "{\"status\":\"success\", \"data\": \"" +newPayment + "\"}"; 
						 //Successful message when inserting payment
						
						
					}catch (Exception e){
						outPut = "{\"status\":\"error\", \"data\":\"Error while inserting the Payment.\"}";
						System.err.println(e.getMessage());
					}
						
					return outPut;
			}
		}
		
		
		//Update payment details
		public String updatePayment(String payID,String billID,String new_paid,String payment_type,String card_no) {
	
			
			//check whether the input fields are empty or not
			if(payID.isEmpty() && billID.isEmpty()   && payment_type.isEmpty() && card_no.isEmpty() && new_paid.isEmpty())
				return "Fields cannot be empty";
			
			
			else {
				String outPut = "";
				int paymentID = Integer.parseInt(payID);
				int bID = Integer.parseInt(billID);
				double paid_amount =Double.parseDouble(new_paid) ;
				
				
				try{
					
					Connection con = dbConnect.connect();
					if (con == null){
						return "Error while connecting to the database for updating."; 	
					}
					
					//get payment id from payment table
					String q2 = "SELECT paymentID,total_amount from payment where paymentID = '"+ paymentID +"'";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(q2);
		
					//check whether a payment record already exited to the given bill id
					if(rs.next()) {//There is a record can be found in the payment table for the given payment ID

						//get billid of bill
						String q3 = "SELECT * from billing where bill_ID = '"+ bID +"'";
						Statement stmt2 = con.createStatement();
						ResultSet rs2 = stmt2.executeQuery(q3);
						
						if(rs2.next()) {//There is a record can be found in the billing table for the given bill ID
						
							String total_amount =rs2.getString(9);
							double balance  = paid_amount - Double.parseDouble(total_amount);
							
							// create a update statement
							String query ="Update payment set paid_amount='"+paid_amount+"',payment_type='"+payment_type+"',balance='"+balance+"',billID='"+ bID 
									+"',card_no='"+ card_no+"'"+"where paymentID='"+paymentID+"'";
					
							//create statement
							Statement st = con.createStatement();
							st.executeUpdate(query);
						
							con.close();
							//outPut = "Payment updated successfully";
							
							String newItems = readPayment(); 
							outPut = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}"; 
						}
						else {
							outPut = "Entered bill ID is not available.";
						}
						
					}
					else {
						outPut = "Entered Payment ID is not available.";
					}
					
				
				}catch (Exception e){
					outPut = "Error while updating the payment.";
					System.err.println(e.getMessage());
				}
				
				return outPut;
				
			}
		}
			
			//Delete payment by payment id
			public String deletepayment(String billID){
				String outPut = "";
				System.out.println("Hiii "+billID);
				int bill_ID = Integer.parseInt(billID);
				
				try{
					
					Connection con = dbConnect.connect();
					if (con == null){
						return "Error while connecting to the database for deleting."; 	
					}
					
					//get month and amount of bill
					String q2 = "SELECT balance from payment where billID = '"+ bill_ID +"'order by paymentID desc limit 1";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(q2);
					
					//check whether a payment record has happened before to the given bill id
					if(rs.next()) {
						// get balance of the given bill
						double balance =   Double.parseDouble(rs.getString(1));
						
						if(balance == 0.00) {
							// create a prepared statement
							String query = "DELETE FROM payment WHERE billID='"+bill_ID+"'";
							Statement preparedStmt = con.createStatement();
							
							//execute the statement
							preparedStmt.execute(query);
			
							con.close();
							outPut = "Payment records of Bill ID "+ bill_ID +" Deleted successfully";
							
							String newItems = readPayment(); 
							outPut = "{\"status\":\"success\", \"data\": \"" +newItems + "\"}"; 
							//Successful message when deleting payment row
						}
						else
							//outPut = "Bill payment records cannot be deleted since it has a balance.";
							outPut= "{\"status\":\"error\", \"data\":\"Error while deleting the item. Since the record has ongoing balance\"}";
					}
					
					else
						outPut ="Entered bill doesnot have any payment details";
					
				}catch (Exception e){
					outPut = "Error while deleting the payment record.";
					System.out.println(e.getMessage());
				}
				
				return outPut;
		}
		
		
}
