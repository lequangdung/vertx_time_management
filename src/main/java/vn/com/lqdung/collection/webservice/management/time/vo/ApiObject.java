package vn.com.lqdung.collection.webservice.management.time.vo;
/**
 *	@Author lqdung
 *	Nov 30, 2015
 */

import java.io.Serializable;

import vn.com.lqdung.collection.webservice.management.time.api.RestMethod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiObject implements Serializable{

    private static final long serialVersionUID = -7695244577233065095L;
    
    private String apiUrl;
    private String apiDescription;
    
    private RestMethod restMethod;
    
    private String apiKey;
    
    public String getApiKey() {
        return apiKey;
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public RestMethod getRestMethod() {
        return restMethod;
    }
    
    public void setRestMethod(RestMethod restMethod) {
        this.restMethod = restMethod;
    }
    
    public String getApiDescription() {
        return apiDescription;
    }
    
    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }
    
    public String getApiUrl() {
        return apiUrl;
    }
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((apiUrl == null) ? 0 : apiUrl.hashCode());
        result = prime * result + ((restMethod == null) ? 0 : restMethod.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ApiObject other = (ApiObject) obj;
        if (apiUrl == null) {
            if (other.apiUrl != null)
                return false;
        } else if (!apiUrl.equals(other.apiUrl))
            return false;
        if (restMethod != other.restMethod)
            return false;
        return true;
    }

    

}

