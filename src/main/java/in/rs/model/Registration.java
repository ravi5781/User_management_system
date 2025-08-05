package in.rs.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import jakarta.servlet.http.HttpSession;

public class Registration {
	private Connection con;
	private HttpSession session;
	
	public Registration(HttpSession session) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/user_management","root","tiger");
			this.session=session;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Register method
	public String register(String name, String phone, String email, String password) {
	    String status = "";
	    try {
	        PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE phone = ? OR email = ?");
	        ps.setString(1, phone);
	        ps.setString(2, email);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            status = "existed";
	        } else {
	            ps = con.prepareStatement("INSERT INTO users VALUES(0, ?, ?, ?, ?, NOW())");
	            ps.setString(1, name);
	            ps.setString(2, phone);
	            ps.setString(3, email);
	            ps.setString(4, password);
	            int result = ps.executeUpdate();
	            status = (result > 0) ? "success" : "failure";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        status = "failure";
	    }
	    return status;
	}

	
	
	//Login method
	public String login(String email, String password) {
		String status="";
		try {
			PreparedStatement ps=con.prepareStatement("SELECT * FROM users WHERE email=? AND password=?");
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				session.setAttribute("id", rs.getString("slno"));
				session.setAttribute("uname", rs.getString("name"));
				session.setAttribute("email", rs.getString("email"));
				status="success";
			}
			else {
				status="failure";
			}
		} catch (Exception e) {
			e.printStackTrace();
			status="failure";
		}
		return status;
	}
	
	
	//get user details for editing
	public User getUserById() {
		User user=null;
		try {
			PreparedStatement ps=con.prepareStatement("SELECT * FROM users WHERE slno= ?");
			ps.setString(1, (String) session.getAttribute("id"));
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				user=new User();
					user.setId(rs.getString("slno"));
					user.setName(rs.getString("name"));
					user.setPhone(rs.getString("phone"));
					user.setEmail(rs.getString("email"));
					user.setDate(rs.getString("date"));	
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	//Admin:Delete user by id
	public String delete(int id) {
		try {
			PreparedStatement ps=con.prepareStatement("DELETE FROM users WHERE slno=?");
			ps.setInt(1,id);
			int result=ps.executeUpdate();
			return result>0 ? "success" : "failure";
		} catch (Exception e) {
			e.printStackTrace();
			return "failure";
		}
	}
	
    // Update profile
    public String update(String name, String phone, String email) {
        String status = "";
        try {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE users SET name = ?, phone = ?, email = ? WHERE slno = ?"
            );
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, (String) session.getAttribute("id"));
            int result = ps.executeUpdate();
            if (result > 0) {
                session.setAttribute("uname", name);
                status = "success";
            } else {
                status = "failure";
            }
        } catch (Exception e) {
            e.printStackTrace();
            status = "failure";
        }
        return status;
    }
    
 // Reset password
    public String getPassword(String email, String oldPass) {
        try {
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE email = ? AND password = ?");
            ps.setString(1, email);
            ps.setString(2, oldPass);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? "success" : "failure";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }
    
    
    public String resetPassword(String email, String newPass) {
        try {
            PreparedStatement ps = con.prepareStatement(
                "UPDATE users SET password = ? WHERE email = ?"
            );
            ps.setString(1, newPass);
            ps.setString(2, email);
            int result = ps.executeUpdate();
            return result > 0 ? "success" : "failure";
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }
    
	
 // Admin: Get all users (excluding admin)
    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                "SELECT *, DATE_FORMAT(date, '%b %d, %Y') AS formatted_date FROM users WHERE slno != 1"
            );
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getString("slno"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setDate(rs.getString("formatted_date"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
    // Admin: Search user by ID
    public ArrayList<User> getUserBySearch(String id) {
        ArrayList<User> list = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(
                "SELECT * FROM users WHERE slno = ?"
            );
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getString("slno"));
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setDate(rs.getString("date"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
	
	
	
}
