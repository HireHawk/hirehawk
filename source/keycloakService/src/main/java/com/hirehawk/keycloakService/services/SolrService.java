package com.hirehawk.keycloakService.services;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SolrService {
	public static void indexUser(String id, String name, String surname, String email, String username, String phone) {
	    String url = "http://176.37.65.30:8213/userSearch/indexUser?";
	    String params = "id=" + id;
	    if(name!=null) params+="&name=" + name;
	    if(surname!=null)params+="&surname=" + surname ;
	    if(username!=null)params+="&username=" + username;
	    if(email!=null)params+="&email=" + email;
	    if(phone!=null)params+="&phone=" + phone;
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

	public static void deleteUser(String id) {
	    String url = "http://176.37.65.30:8213/userSearch/deleteUser?";
	    String params = "id=" + id;
	    url += params;
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
