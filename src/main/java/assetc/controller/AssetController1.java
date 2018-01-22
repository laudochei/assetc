/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assetc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import assetc.model.Asset;
import assetc.model.Task;
import assetc.service.AssetService;
import assetc.validator.AssetFormValidator;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/assets")
public class AssetController1 {

	private final Logger logger = LoggerFactory.getLogger(AssetController1.class);

	@Autowired
	AssetFormValidator assetFormValidator;
	
        //Set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(assetFormValidator);
	}

	private AssetService assetService;

	@Autowired
	public void setAssetService(AssetService assetService) {
		this.assetService = assetService;
	}
    
        // list page
        @RequestMapping(value = "/assetlist", method=GET)
        public String showAllLocation(Model model) {
            model.addAttribute("assets", assetService.findAllAsset());
            return "assetlist";
        }
        
        
        // show asset
	@RequestMapping(value = "/{assetno}", method = RequestMethod.GET)
	public String showAsset(@PathVariable("assetno") Integer assetno, Model model) {

		logger.debug("showAsset() assetno: {}", assetno);
		Asset asset = assetService.findByNo(assetno);
		if (asset == null) {
			model.addAttribute("css", "danger");
			model.addAttribute("msg", "Asset not found");
		}
		model.addAttribute("asset", asset);
		return "assetprofile";
	}
        
        
        
        // show add aaset form
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String showAddAssetForm(Model model) {

		logger.debug("showAddAssetForm()");
		Asset asset = new Asset();
                                
		// set default value
                  asset.setAssetid("100000000011");
                  asset.setLocationid("ID80-003-BP-OLS-PEI-X-00");
                  asset.setDescription("PPNG, HZDOUS SOLID WASTE TREATMENT");
                  asset.setLongdescription("PPNG, HZDOUS SOLID WASTE TREATMENT");
                  asset.setEquipmenttype("type1");
                  asset.setFailurecode("FA_PEG");
                  asset.setPhysicallocation("Aberdeen");
                  asset.setManufacturer("PHILIPS");
                  asset.setPartnum("00000");
                  asset.setModelnum("eLLK92036-36");
                  asset.setSerialnum("00000");
                  asset.setCustomfield("00000");
                  
		model.addAttribute("assetForm", asset);	    
                return "assetForm"; 
	}
     
        
        // add location
        @RequestMapping(value="/add", method = RequestMethod.POST)
        public String addAsset(@ModelAttribute("assetForm") @Validated Asset asset, BindingResult result, Model model,final RedirectAttributes redirectAttributes) {
            
                Task task = new Task(); 
                int actionNo = 1; // set add action to 1
                task.setCreate(actionNo);
                //locationService.checkAction(actionNo);
                
            logger.debug("addAsset() : {}", asset);
            //check for errors in the form
              if (result.hasErrors()) 
              {
                return "assetForm";
              } else { // save new location
                  redirectAttributes.addFlashAttribute("css", "success");
                  redirectAttributes.addFlashAttribute("msg", "Asset added successfully!");
                  assetService.saveAsset(asset);
                  return "redirect:/assets/" + asset.getAssetno();
                
              }
        }
        
        
        
        
        // show update form
	@RequestMapping(value = "/{assetno}/update", method = RequestMethod.GET)
	public String showUpdateAssetForm(@PathVariable("assetno") int assetno, Model model) {
		logger.debug("showUpdateAssetForm() : {}", assetno);
                Asset asset = assetService.findByNo(assetno);
		model.addAttribute("assetForm", asset);	    
                return "assetForm";
	}
        
        
        // update location
    @RequestMapping(value = "/{assetno}/update", method = RequestMethod.POST)
    public String updateAsset(@ModelAttribute("assetForm") @Validated Asset asset, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {
               
        logger.debug("updateAsset() : {}", asset);
        if (result.hasErrors()) {
            return "assetForm";
        } else {
            redirectAttributes.addFlashAttribute("css", "success");
            redirectAttributes.addFlashAttribute("msg", "Asset updated successfully!");
            assetService.updateAsset(asset);
            return "redirect:/assets/" + asset.getAssetno();
        }  
    }
        
        
    
    // delete location
    @RequestMapping(value = "/{assetno}/delete", method = RequestMethod.GET)
    public String deleteAsset(@PathVariable("assetno") Integer assetno, final RedirectAttributes redirectAttributes) {
	logger.debug("deleteAsset() : {}", assetno);
	assetService.delete(assetno);
	redirectAttributes.addFlashAttribute("css", "success");
	redirectAttributes.addFlashAttribute("msg", "Asset is deleted!");
        return "redirect:/assets/assetlist";
    }   
        
        
}
