package com.hirehawk.basic_service.controllers;

import com.hirehawk.basic_service.testmongo.Advert;
import com.hirehawk.basic_service.testmongo.AdvertRepository;
import com.hirehawk.basic_service.testmongo.DummyAdvert;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.HttpResponse;
import org.bson.types.ObjectId;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        advert.setDate(new Date());
        advertsRepository.save(advert);
        Advert added = advertsRepository.findTopByOrderByDateDesc();
        DummyAdvert toSend = new DummyAdvert(added);
        addToSolr(toSend, "http://176.37.65.30:8213/advertSearch/indexAdvert?");

        return toSend;
    }

    public void addToSolr(DummyAdvert advert, String url) {
        {
            //String url = "http://176.37.65.30:8213/advertSearch/indexAdvert?";
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
            System.out.println(url);
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


    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public DummyAdvert getAdvertById(@PathVariable("id") ObjectId id) {
        Advert temp = advertsRepository.findById(id);
        DummyAdvert toSend = new DummyAdvert(temp);
        return toSend;
    }

    @RequestMapping(value = "/getChosen", method = RequestMethod.GET)
    public List<DummyAdvert> getChosenAdverts(@RequestBody String neededIds) {
        List<DummyAdvert> toSend = new ArrayList<>();
        if(neededIds!=null) {
            neededIds = neededIds.replace(" ","");
            String[] id = neededIds.split(",");
            for (int i = 0; i < id.length; i++) {
                Advert advert = advertsRepository.findById(new ObjectId(id[i]));
                DummyAdvert temp = new DummyAdvert(advert);
                toSend.add(temp);
            }
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
    public DummyAdvert updateAdvert(@RequestBody Advert newAdvert, @PathVariable("id") ObjectId id) {
        Advert advert = advertsRepository.findById(id);
        advert.update(newAdvert.getName(), newAdvert.getCategory(), newAdvert.getInfo(), newAdvert.getPhoto(),
                newAdvert.getLocation(), newAdvert.getPrice(), newAdvert.getNumb_of_hours());
        advertsRepository.save(advert);
        DummyAdvert toSend = new DummyAdvert(advert);
        addToSolr(toSend, "http://176.37.65.30:8213/advertSearch/updateAdvert?");
        return toSend;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteAdvert(@PathVariable ObjectId id) {
        String idToSend = advertsRepository.findById(id).getId().toString();
        advertsRepository.delete(advertsRepository.findById(id));
        deleteInSolr(idToSend);
    }

    public void deleteInSolr(String id){
        String url = "http://176.37.65.30:8213/advertSearch/deleteAdvert?id="+id;
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
