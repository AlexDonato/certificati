package com.alessandrodonato.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alessandrodonato.webapp.model.Fornitore;
import com.alessandrodonato.webapp.service.FornitoreManager;

/**
 * Implementation of <strong>SimpleFormController</strong> that interacts with
 * the {@link UserManager} to retrieve/persist values to the database.
 *
 * <p><a href="UserFormController.java.html"><i>View Source</i></a>
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
@Controller
@RequestMapping("/fornitoreform*")
public class FornitoreFormController extends BaseFormController {
    
		@Autowired
    private FornitoreManager fornitoreManager;

    public FornitoreFormController() {
        setCancelView("redirect:/mainMenu");
        setSuccessView("redirect:/certificati/fornitore");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Fornitore fornitore, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
            throws Exception {
    	
    		System.out.println("il fornitore: "  + fornitore);
    		System.out.println("il manager: "  + fornitoreManager);
    		
        if (request.getParameter("cancel") != null) {
            if (!StringUtils.equals(request.getParameter("from"), "list")) {
                return getCancelView();
            } else {
                return getSuccessView();
            }
        }

//        if (validator != null) { // validator is null during testing
//            validator.validate(user, errors);
//
//            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
//                return "userform";
//            }
//        }

        log.debug("entering 'onSubmit' method...");

        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
        	fornitoreManager.remove(fornitore.getId());
            
        	saveMessage(request, getText("fornitore.deleted", fornitore.getRagioneSociale(), locale));

        	return getSuccessView();
        	
        } else {

            try {
                fornitoreManager.save(fornitore);
            } catch (AccessDeniedException ade) {
                // thrown by UserSecurityAdvice configured in aop:advisor userManagerSecurity
                log.warn(ade.getMessage());
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }

            // Se l'inserimento non arriva dalla lista ...
            if (!StringUtils.equals(request.getParameter("from"), "list")) {
                saveMessage(request, getText("fornitore.saved", fornitore.getRagioneSociale(), locale));

                // return to main Menu
                return getCancelView();
            } else {
                if (StringUtils.isBlank(request.getParameter("version"))) {
                    saveMessage(request, getText("fornitore.added", fornitore.getRagioneSociale(), locale));

                    return getSuccessView();
                } else {
                    saveMessage(request, getText("fornitore.updated", fornitore.getRagioneSociale(), locale));
                }
            }
        }

        return "fornitoreform";
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Fornitore showForm(HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        if (!isFormSubmission(request)) {
            String fornitoreId = request.getParameter("id");

            // if user logged in with remember me, display a warning that they can't change passwords
            

            if (!StringUtils.isBlank(fornitoreId) && !"".equals(request.getParameter("version"))) {
            	log.debug("restituisco il fornitore con id " + fornitoreId);
            	return fornitoreManager.getFornitoreById(Long.parseLong(fornitoreId));
            } else {
            	return new Fornitore();
            }
            
        } else {
            // populate user object from database, so all fields don't need to be hidden fields in form
            return fornitoreManager.get(Long.getLong(request.getParameter("id")));
        }
    }

    private boolean isFormSubmission(HttpServletRequest request) {
        return request.getMethod().equalsIgnoreCase("post");
    }

    protected boolean isAdd(HttpServletRequest request) {
        String method = request.getParameter("method");
        return (method != null && method.equalsIgnoreCase("add"));
    }

}
