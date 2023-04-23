/* 
  Name: Asma Ahmed
  Course: CNT 4714 – Spring 2023 – Project Four
  Assignment title: A Three-Tier Distributed Web-Based Application
  Date: April 23, 2023
 
  Class:  addJobRecord servlet class 
  data entry for job of user to records
*/ 

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.UnavailableException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@SuppressWarnings({ "serial"})
public class addJobRecord extends HttpServlet{
	private Connection database_Connection;
    private Statement database_statement;

    @Override
    public void init(ServletConfig s_conf) throws ServletException {
        super.init(s_conf);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            database_Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/project4", "root", "password");
            database_statement = database_Connection.createStatement();

        } //end initialize

        catch (Exception e) {
            e.printStackTrace();
            throw new UnavailableException(e.getMessage());
        }//end catch
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Snum = request.getParameter("jnu");
        String Snam = request.getParameter("jna");
        String Stat = request.getParameter("nW");
        String Scity = request.getParameter("jcity");
        
        String result = null;

            try {
                result = do_update(Snum, Snam, Stat, Scity);
            } //end try

            catch(SQLException e) {
                result = "<span>" + e.getMessage() + "</span>";
                e.printStackTrace();
            } //end catch
        
        HttpSession session = request.getSession();
        session.setAttribute("result", result);
        session.setAttribute("textbox", Snum);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dataentryHome.jsp");
        dispatcher.forward(request, response);
    }//end doGet

    protected void doPost(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGet(request, response);
    } //end do post

    public String do_select(String input) throws SQLException {
        int n_column = 0, i = 1;
        String res_out = "<div><div><div><table><thead><tr>",  close_table = "</table></div></div></div>";

        ResultSet R_Set = database_statement.executeQuery(input);
        ResultSetMetaData data = R_Set.getMetaData();
        n_column = data.getColumnCount();

        while(i <= n_column) {
            res_out += "<th scope = 'col'>" + data.getColumnName(i) + "</th>";
            i++;
        } //end while loop
        
        res_out = res_out + "</tr></thead><tbody>";

        while(R_Set.next() != false) {
            res_out += "<tr>";
            for (i = 1; i <= n_column; i++) {
                if(i != 1) {
                    res_out += "<td>" + R_Set.getString(i) + "</th>";
                } //end if

                else {
                    res_out += "<td scope'row'>" + R_Set.getString(i) + "</th>";
                }//end else
            }//end for loop
            res_out += "</tr>";
        }//end while
        
        res_out += "</tbody>";
        res_out += close_table;
        return res_out;
    }//end do_select

    private String do_update(String snum, String sname, String status, String city) throws SQLException {
        String res_out = "<div> The statement executed successfully.</div><div>";
        int row_updates = 0;
        row_updates = database_statement.executeUpdate("insert into jobs values("+ "'"  +snum + "'" + "," + "'"+sname+"'"+ ","+"" +status+"" + ","+"'" +city+"'"+ ")");
        res_out += row_updates + " row(s) affected</div><div>";
        return res_out;
    } //end do_update
} //end addJobRecord
