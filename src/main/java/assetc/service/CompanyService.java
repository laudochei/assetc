/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.model.Company;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface CompanyService {
    Company findByNo(Integer companyno);
    List<Company> findAllCompany();
    void saveCompany(Company company);
    void updateCompany(Company company);
    void delete(Integer companyno);
    int CompanyIDExists(String companyid);
    int CompanyNameExists(String companyname);
    int checkForeignKey(String companyid);
    
}
