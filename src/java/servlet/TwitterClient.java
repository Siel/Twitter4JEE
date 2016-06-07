/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 *
 * @author Siel
 */
// ruta http://localhost:8080/Rest_Twitter_Lab/TwitterClient
public class TwitterClient extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, TwitterException {
        
        String consumerKey="acdi5Tpjda5dshPSgsNdc2YuP";
        String consumerSecret="5mZuFFShjXdwTCbk1hRZ9c47BJz9vKwoPFDPDHyG9xdxLGdZRi";
        String accessToken="224428346-hU7uwq12mUSvxT7jClIEnpCN5Lj6Rp1YgnQiwG6h";
        String accessTokenSecret="8kPqai6ciArmiWinkBOGvxHfYbif2OL7N96NbQYvEHfiH";
        Twitter twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(consumerKey,consumerSecret);
        AccessToken accessTok = new AccessToken(accessToken, accessTokenSecret);
        twitter.setOAuthAccessToken(accessTok);
        
        String action = (request.getParameter("action")!=null)?request.getParameter("action"):"fresh";
        if(action.equalsIgnoreCase("Send")){
            String latestStatus = request.getParameter("newTweet");
            Status status = twitter.updateStatus(latestStatus);
        }
        //cargamos todos los tweets
        
        ResponseList<Status> a = twitter.getUserTimeline(new Paging(1,20));
        
        request.setAttribute("Tweets", a);
        //mandamos los tweets en una lista a la vista
        request.getRequestDispatcher("twitter.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (TwitterException ex) {
            Logger.getLogger(TwitterClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
