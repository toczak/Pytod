package Beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class SearchBean {
    private String searchUsername;
    private List listUserBySearch;

    public SearchBean() {
    }

    public SearchBean(String searchUsername) {
        this.searchUsername = searchUsername;
    }

    public String getSearchUsername() {
        return searchUsername;
    }

    public void setSearchUsername(String searchUsername) {
        this.searchUsername = searchUsername;
    }

    public List getListUserBySearch() {
        return listUserBySearch;
    }

    public void setListUserBySearch(ArrayList listUserBySearch) {
        this.listUserBySearch = listUserBySearch;
    }

    public String readUserListFromDb() {
        return DatabaseOperation.getUserListBySearch(searchUsername);
    }
}
