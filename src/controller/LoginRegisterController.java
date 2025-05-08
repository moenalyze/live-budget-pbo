/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import helper.UserDAO;

/**
 *
 * @author HP
 */
public class LoginRegisterController {
    private final UserDAO userDAO;
            
    public LoginRegisterController() {
        userDAO = new UserDAO();
    }
    
    // Handle Login
    public boolean handleLogin(String username, String password) {
        // Mencegah form input kosong
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            System.out.println("Username dan password tidak boleh kosong");
            return false;
        }
        
        boolean isLogin = userDAO.login(username, password);
        if (isLogin) {
            System.out.println("YATTAAAA");
        } else {
            System.out.println("BEGO");
        }
        return isLogin;
    }
    
    public boolean handleRegister(String username, String password) {
        // Mencegah form input kosong
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            System.out.println("Username dan password tidak boleh kosong");
            return false;
        }
        
        boolean isRegister = userDAO.register(username, password);
        if (isRegister) {
            System.out.println("YATTAAAA");
        } else {
            System.out.println("Username sudah ada");
        }
        return isRegister;
    }
}
