package vn.com.lqdung.collection.webservice.management.time.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import vn.com.lqdung.collection.webservice.management.time.api.AccessLevel;

/**
 *	@Author lqdung
 *	Nov 30, 2015
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Application implements Serializable{

    private static final long serialVersionUID = -4123047432651193929L;
    
    private String applicationName;
    
    private AccessLevel accessLevel;
    
    private Set<ApiObject> apies = new HashSet<ApiObject>();
    
    public Set<ApiObject> getApies() {
        return apies;
    }
    
    public void setApies(Set<ApiObject> apies) {
        this.apies = apies;
    }
    
    public AccessLevel getAccessLevel() {
        return accessLevel;
    }
    
    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }
    
    public String getApplicationName() {
        return applicationName;
    }
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((applicationName == null) ? 0 : applicationName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if(obj instanceof String){
            String application = String.valueOf(obj);
            return applicationName != null && applicationName.equals(application);
        }
        if (getClass() != obj.getClass())
            return false;
        Application other = (Application) obj;
        if (applicationName == null) {
            if (other.applicationName != null)
                return false;
        } else if (!applicationName.equals(other.applicationName))
            return false;
        return true;
    }

}

