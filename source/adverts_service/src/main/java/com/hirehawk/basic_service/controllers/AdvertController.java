package com.hirehawk.basic_service.controllers;

import java.security.Principal;

import com.hirehawk.basic_service.testmongo.DummyAdvert;
import org.apache.http.HttpResponse;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import com.hirehawk.basic_service.testmongo.Advert;
import com.hirehawk.basic_service.testmongo.AdvertRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/manageAdverts")
public class AdvertController {

    @Autowired
    private AdvertRepository advertsRepository;

    @RequestMapping(value = "/")
    public String start() {
        return "<a href=\"/create\">Create Add</a> \\n" +
                "<a href=\"/logout\">Logout</a>\\";
    }

    @RequestMapping(value = "/logout")
    public void logout(HttpServletRequest request) throws ServletException {
        request.logout();

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public DummyAdvert createAdvert(Principal principal, @RequestBody Advert advert) {
        KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
        KeycloakPrincipal pr = (KeycloakPrincipal) token.getPrincipal();
        KeycloakSecurityContext session = pr.getKeycloakSecurityContext();
        AccessToken at = session.getToken();
        String id = at.getOtherClaims().get("user_id").toString();
        advert.setUsersId(id);
        advertsRepository.save(advert);
        Advert added = advertsRepository.findTopByOrdOrderByDateDesc();
        DummyAdvert toSend = new DummyAdvert(added);
        try {
            sendToSolr(toSend);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toSend;
    }

    public void sendToSolr(DummyAdvert advert) {
        {
            String url = "http://176.37.65.30:8213/advertSearch/indexAdvert?";
            String params = "id=" + advert.getId() +
                    "&name=" + advert.getName() +
                    "&category=" + advert.getCategory() +
                    "&info=" + advert.getInfo() +
                    "&location=" + advert.getLocation() +
                    "&price=" + advert.getPrice() +
                    "&num_of_hours=" + advert.getNumb_of_hours() +
                    "&usersId=" + advert.getUsersId();
            url += params;
            url = url.replace(" ", "%20");
            try {
                HttpResponse<String> response = Unirest.get(url)
                        .basicAuth("api", "Vac")
                        .header("cache-control", "no-cache")
                        .asString();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }
    }


//    public void sendToSolr(DummyAdvert advert) throws Exception {
//        URL url = new URL("http://176.37.65.30:8213/advertSearch/indexAdvert");
//
//        Map<String, Object> params = new LinkedHashMap<>();
//
//        params.put("id", advert.getId());
//        params.put("name", advert.getName());
//        params.put("category", advert.getCategory());
//        params.put("info", advert.getInfo());
//        params.put("location", advert.getLocation());
//        params.put("price", advert.getPrice());
//        params.put("numb_of_hours", advert.getNumb_of_hours());
//        params.put("usersID", advert.getUsersId());
//
//        StringBuilder postData = new StringBuilder();
//        for (Map.Entry<String, Object> param : params.entrySet()) {
//            if (postData.length() != 0) postData.append('&');
//            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
//            postData.append('=');
//            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
//        }
//
//        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
//
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
//        conn.setDoOutput(true);
//        conn.getOutputStream().write(postDataBytes);
//    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public DummyAdvert getAdvertById(@PathVariable("id") ObjectId id) {
        Advert temp = advertsRepository.findById(id);
        DummyAdvert toSend = new DummyAdvert(temp);
        return toSend;
    }

    @RequestMapping(value = "/getChosen", method = RequestMethod.GET)
    public List<DummyAdvert> getChosenAdverts(String neededIds) {
        List<DummyAdvert> toSend = new ArrayList<>();
        String []id = neededIds.split(",");
        for (int i = 0; i < id.length; i++) {
            Advert advert = advertsRepository.findById(new ObjectId(id[i]));
            DummyAdvert temp = new DummyAdvert(advert);
            toSend.add(temp);
        }
        return toSend;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<DummyAdvert> getAllAdverts() {
        List<DummyAdvert> toSend = new ArrayList<>();
        List<Advert> adverts = advertsRepository.findAll();
        for (int i = 0; i < adverts.size(); i++) {
            DummyAdvert temp = new DummyAdvert(adverts.get(i));
            toSend.add(temp);
        }
        return toSend;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PATCH)
    public Advert updateAdvert(@RequestBody Advert newAdvert, @PathVariable("id") ObjectId id) {
        Advert advert = advertsRepository.findById(id);
        advert.update(newAdvert.getName(), newAdvert.getCategory(), newAdvert.getInfo(), newAdvert.getPhoto(),
                newAdvert.getLocation(), newAdvert.getPrice(), newAdvert.getNumb_of_hours());
        advertsRepository.save(advert);
        return advert;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteAdvert(@PathVariable ObjectId id) {
        advertsRepository.delete(advertsRepository.findById(id));
    }
}
