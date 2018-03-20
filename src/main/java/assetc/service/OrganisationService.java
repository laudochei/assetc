/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.model.Organisation;
import java.util.List;

/**
 *
 * @author Laud.Ochei
 */
public interface OrganisationService {
    Organisation findByNo(Integer orgno);
    List<Organisation> findAllOrganisation();
    void saveOrganisation(Organisation organisation);
    void updateOrganisation(Organisation organisation);
    void delete(Integer orgno);
    int OrganIDExists(String orgid);
    int OrganNameExists(String orgname);
    int checkForeignKey(String orgid);
}
