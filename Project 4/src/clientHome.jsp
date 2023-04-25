<!--
Name: Asma Ahmed
  Course: CNT 4714 – Spring 2023 – Project Four
  Assignment title: A Three-Tier Distributed Web-Based Application
  Date: April 23, 2023
 
  Class:  clientHome.jsp
-->

<!DOCTYPE html>

<%
	String textBox = (String) session.getAttribute("textBox");
    String result = (String) session.getAttribute("result");

    if (result == null) 
    {
	    result = " ";
    }
    if (textBox == null) 
    {
        textBox = " ";
    }
    
%>

<html lang="en">
    <head>
      <title> clientHome </title>
      <link rel="stylesheet" type="text/css" href="../src/style.css" />

      <script src = "https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
        <script type = "text/javascript">
            function eraseText() {
                // <!-- next line illustrates a straight JavaScript technique
                // document.getElementById("cmd").innerHTML=" ";
                // next line illustrates a JQuery technique
                $("#cmd").html("");
            }
        </script>
        <script type = "text/javascript"> 
            function eraseData() {
                // next line illustrates a straight JavaScript technique
                // document. getElementByID ("data") , innerHTML = .:
                // next line illustrates a JQuery technique
                $("#data").remove();
            }
        </script>
    </head>
    
    <body>
    
      <div align="center">
        <h1 style="font-size: x-large; font-weight: lighter;">
          Spring 2023 Project 4
        </h1>
        <h2 style="font-size: xx-large;">
          Enterprise System
        </h2>
        <h3 style="font-style: italic; font-size: medium;">
          A Servlet/JSP Based Multi-Tiered Enterprise Application
        </h3>
        <br>
      </div>
    
      <div class="client" align="center">
        <form action="clientUserApp.java" method="post" style="margin-top: 15px;">
          <div>
            <p style="margin-left: 10px; margin-right: 10px;">
              You are connected to the Project 4 Enterprise System Database as a [CLIENT] level user. <br><br>
              <b>Please enter a valid SQL query or update command.</b>
            </p>
          </div>
          <div>
            <div>
              <textarea name="textBox" id="textBox" rows="5" cols="55"> <%=textBox%> </textarea>
            </div>
          </div>
    
          <button style="margin-bottom: 15px;" id="console" type="submit">
            Execute Command
          </button>
          <button style="margin-bottom: 15px;" id="console" type="reset">
            Reset Form
          </button>
          <button style="margin-bottom: 15px;" id="console" type="clear" value="clear">
            Clear Form
          </button>
    
          <div>All execution results will appear below.</div>
      </div>
    
      <hr style="width: 50%;">
    
      <div class="client" align="center">
        Database Results:
        <div>
          <%=result%>
        </div>
      </div>
    
    </body>
</html>