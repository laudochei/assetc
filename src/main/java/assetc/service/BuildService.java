/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.service;

import assetc.model.Build;

/**
 *
 * @author Laud.Ochei
 */
public interface BuildService {
    
    Build findByNo(Integer buildno);
    
}
