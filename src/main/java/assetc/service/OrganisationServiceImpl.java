/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.data.OrganisationDao;
import assetc.model.Organisation;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("organisationService")
public class OrganisationServiceImpl implements OrganisationService {
	OrganisationDao organisationDao;

	@Autowired
	public void setOrganisationDao(OrganisationDao organisationDao) {
		this.organisationDao = organisationDao;
	}

	@Override
	public Organisation findByNo(Integer orgno) {
		return organisationDao.findByNo(orgno);
	}
        
        @Override
	public List<Organisation> findAllOrganisation() {
		return organisationDao.findAll();
	}
        
        @Override
	public void saveOrganisation(Organisation organisation) {
            organisationDao.saveOrganisation(organisation);
	}
       
        @Override
        public int OrganIDExists(String orgid) {
            return organisationDao.OrganIDExists(orgid);
        }
        
        
        @Override
        public int OrganNameExists(String orgname) {
            return organisationDao.OrganNameExists(orgname);
        }
        
        @Override
	public void updateOrganisation(Organisation organisation) {
            organisationDao.update(organisation);
	}
        
        
        @Override
        public void delete(Integer orgno) {    
            organisationDao.delete(orgno);
        }
        
        @Override
        public int checkForeignKey(String orgid) {
            return organisationDao.checkForeignKey(orgid);
        }
        
    
}
