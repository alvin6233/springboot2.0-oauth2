package cn.merryyou.security.controller;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: dongxiaozhuang/alvin
 * @datetime: 2019-02-21 13:36
 * @description: new world begin.
 */
@Controller
@SessionAttributes({ "authorizationRequest" })
public class AuthController {

    @GetMapping("/auth/confirm_page")
    public String handleAccessConfirmation(Map<String, Object> model,
    HttpServletRequest request) {
        Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ?
        model.get("scopes") : request.getAttribute("scopes"));
        List<String> scopeList = new ArrayList<String>();
        for (String scope : scopes.keySet()) {
        scopeList.add(scope);
        }
        model.put("scopeList", scopeList);
        return "auth_confirm";
    }

    @GetMapping("/auth/error")
    public String handleError(Map<String, Object> model, HttpServletRequest request) {
        Object error = request.getAttribute("error");
        String errorSummary;
        if (error instanceof OAuth2Exception) {
            OAuth2Exception oauthError = (OAuth2Exception) error;
            errorSummary = HtmlUtils.htmlEscape(oauthError.getSummary());
        } else {
            errorSummary = "Unknown error";
        }
        model.put("errorSummary", errorSummary);
        return "auth_error";
    }
}
