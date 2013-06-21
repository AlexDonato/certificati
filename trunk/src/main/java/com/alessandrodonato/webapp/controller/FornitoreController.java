/**
 * 
 */
package com.alessandrodonato.webapp.controller;

import javax.annotation.Resource;

import org.appfuse.dao.GenericDao;
import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.appfuse.service.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alessandrodonato.webapp.model.Fornitore;
import com.alessandrodonato.webapp.service.FornitoreManager;

/**
 * @author Alessandro Donato
 *
 * 09/giu/2013
 */

@Controller
@RequestMapping("/certificati/fornitore*")
public class FornitoreController extends BaseFormController {

  private FornitoreManager fornitoreManager;

//  @Autowired
//  public void setFornitoreManager (@Qualifier ("fornitoreManager") GenericManager<Fornitore, Long> fornitoreManager) {
//      this.fornitoreManager = fornitoreManager;
//  }
  
  @Resource (name="fornitoreManager")
  public void setFornitoreManager (FornitoreManager fornitoreManager) {
    this.fornitoreManager = fornitoreManager;
  }
  
  public FornitoreController() {
      setCancelView("redirect:/mainMenu");
      setSuccessView("redirect:/admin/users");
  }
  
  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView handleRequest(@RequestParam(required = false, value = "q") String query) throws Exception {
      Model model = new ExtendedModelMap();
      try {
      	if (query == null || query.trim().equals("")) {
      		model.addAttribute("fornitoreList", fornitoreManager.getAll());
      	} else {
      		model.addAttribute("fornitoreList", fornitoreManager.getFornitoreByName(query));
      	}
      } catch (SearchException se) {
          model.addAttribute("searchError", se.getMessage());
          model.addAttribute(null);
      }
      return new ModelAndView("certificati/fornitoreList", model.asMap());
  }
}
