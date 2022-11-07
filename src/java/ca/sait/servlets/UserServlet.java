/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ca.sait.servlets;

import ca.sait.models.Role;
import ca.sait.models.User;
import ca.sait.services.RoleService;
import ca.sait.services.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phamh
 */
public class UserServlet extends HttpServlet {


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
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        ArrayList<User> users = userService.getAll();
        ArrayList<Role> roles = roleService.getAll();
        request.setAttribute("users", users);
        request.setAttribute("roles", roles);
        request.setAttribute("roleService", roleService);
        
        String action = request.getParameter("action");
        
        if (action != null && action.equals("edit")) {
            String userEmail = request.getParameter("user").replaceAll("\\s+", "+");
            request.setAttribute("editEnable", userEmail);
            User user = userService.get(userEmail);
            request.setAttribute("editUser", user);
        } else if (action != null && action.equals("delete")) {
            String userEmail = request.getParameter("user").replaceAll("\\s+", "+");
            User user= userService.get(userEmail);
            userService.delete(user);
            response.sendRedirect("user");
            return;
        } else if (action != null && action.equals("cancel")) {
            request.setAttribute("editEnable", null);
        } else if(action != null && action.equals("add")) {
            request.setAttribute("addEnable", "enable");
        }
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        UserService userService = new UserService();
        RoleService roleService = new RoleService();
        ArrayList<User> users = userService.getAll();
        ArrayList<Role> roles = roleService.getAll();
        int inputActiveInt;
        
        if (action != null && action.equals("addUser")) {

            String inputEmail = request.getParameter("inputEmail");
            String inputPassword = request.getParameter("inputPassword");
            String inputFirstName = request.getParameter("inputFirstName");
            String inputLastName = request.getParameter("inputLastName");
            String inputRole = request.getParameter("inputRole");
            String inputActive = request.getParameter("inputActive");

            // Converts inputActive field to boolean
            if(inputActive != null && inputActive.equals("1")) {
                inputActiveInt = 1;
            } else {
                inputActiveInt = 0;
            }

            // Converts inputRole to Role.
            int newRole = 0;
            for (int i = 1; i <= roles.size(); i++) {
                if (roleService.get(i).equals(inputRole)) {
                    newRole = i;
                }
            }
            User user = new User(inputEmail, inputActiveInt, inputFirstName, inputLastName, inputPassword, newRole);
            userService.add(user);
            response.sendRedirect("user");
            return;
        } else if (action != null && action.equals("update")) {
            String email = request.getParameter("email");
            User user = userService.get(email);

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String active = request.getParameter("active");
            
            if(active != null && active.equals("1")) {
                inputActiveInt = 1;
            } else {
                inputActiveInt = 0;
            }

            int newRole = 0;
            if (role.startsWith("Current:")) {
                newRole = user.getRole();
            } else {
                for (int i = 1; i <= roles.size(); i++) {
                if (roleService.get(i).equals(role)) {
                    newRole = i;
                }
            }
            }
            
            

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(password);
            user.setRole(newRole);
            user.setActive(inputActiveInt);

            userService.update(user);
            request.setAttribute("editEnable", null);
            response.sendRedirect("user");
            return;
        }
        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
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
