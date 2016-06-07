<%-- 
    Document   : twitter
    Created on : 07-jun-2016, 16:46:14
    Author     : Siel
--%>

<%@page import="twitter4j.ResponseList"%>
<%@page import="twitter4j.Status"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Twitter Profile</title>
    </head>
    <body>
        <h1>Last Twitter Updates</h1>
        <ol>
            <% ResponseList<Status> tweets=(ResponseList<Status>) request.getAttribute("Tweets"); 
                for (Status tweet: tweets) {    %>
            <li><%= tweet.getText() %> - <%= tweet.getCreatedAt().toString() %></li>
            <%}%>
        </ol>
        <form action="./TwitterClient" method="POST">
            <table>
                <tr>
                    <th>New Tweet</th>
                    <th><input type="text" name="newTweet" placeholder="Write your tweet here..."/></th>
                </tr>
                <tr>
                    <th><input type="submit" name="action" value="Send"></th>
                </tr>
            </table>
                    
        </form>
    </body>
</html>
