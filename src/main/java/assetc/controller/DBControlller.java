/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 *
 * @author Laud.Ochei
 */
@Configuration
@Controller
@RequestMapping(value = "/db")

public class DBControlller {
    
	// db display
	@RequestMapping(value = "/showdb", method=GET)
        public String showDb(Model model) {
            return "testdb11";
        }
        
        // db display
	@RequestMapping(value = "/createdb", method=GET)
        public String createDb(Model model) {
            return "testdb33";
        }
        
}






