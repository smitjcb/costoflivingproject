package com.example.Final.Project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class FinancialController {

    private FinancialDAO financialDAO;

@RequestMapping ("/Financial")
    public List<FinancialDB> retreieveFinancials() {
    return financialDAO.getFinancials();
}

//Add request mapping for specific financial info for one user ie /Financial/'username' (to fill in forms - budget & benefits)
@RequestMapping ("/Financial/{UserID}")
public ResponseEntity<List> retreieveUserFinancials(@PathVariable int UserID) {
    List<FinancialDB> finance = financialDAO.getUserFinancials(UserID);
    if (finance.isEmpty()){
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    else return new ResponseEntity<>(finance,HttpStatus.OK);

}
//Add put mapping to add financial data into databse for one user (other income etc)

    @RequestMapping ("/update")
    public void updateFinancial (@RequestBody FinancialDB requestFinancial) {
//        System.out.println(requestFinancial.getSalary());
//        System.out.println(requestFinancial.getUserName());
    financialDAO.updateSalary(requestFinancial.getSalary(), requestFinancial.getUserName(),requestFinancial.getBenefit());
}
    
@Autowired
public void setFinancialDAO(FinancialDAO financialDAO) {
        this.financialDAO = financialDAO;
    }
}

//URL for all data for Financials help https://localhost:8080/Financial