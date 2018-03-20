/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.data.CompanyDao;
import assetc.model.Company;
import assetc.model.Organisation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Laud.Ochei
 */

@Service("companyService")
public class CompanyServiceImpl implements CompanyService{
        CompanyDao companyDao;

	@Autowired
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	@Override
	public Company findByNo(Integer companyno) {
		return companyDao.findByNo(companyno);
	}
        
        @Override
	public List<Company> findAllCompany() {
		return companyDao.findAll();
	}
        
        @Override
	public void saveCompany(Company company) {
            companyDao.saveCompany(company);
	}
       
        @Override
        public int CompanyIDExists(String companyid) {
            return companyDao.CompanyIDExists(companyid);
        }
        
        
        @Override
        public int CompanyNameExists(String companyname) {
            return companyDao.CompanyNameExists(companyname);
        }
        
        @Override
	public void updateCompany(Company company) {
            companyDao.update(company);
	}
        
        
        @Override
        public void delete(Integer companyno) {    
            companyDao.delete(companyno);
        }
        
         @Override
        public int checkForeignKey(String companyid) {
            return companyDao.checkForeignKey(companyid);
        }
    
}
