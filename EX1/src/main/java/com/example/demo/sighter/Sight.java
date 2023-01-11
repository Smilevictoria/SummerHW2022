package com.example.demo.sighter;

import org.springframework.stereotype.Component;

//@Component
public class Sight
{
    private String sightName;
    private String zone;
    private String category;
    private String photoUrl;
    private String description;
    private String address;

    //get
    public String getsightName()
    {
        return sightName;
    }
    public String getzone()
    {
        return zone;
    }
    public String getcategory()
    {
        return category;
    }
    public String getphotoUrl()
    {
        return photoUrl;
    }
    public String getdescription()
    {
        return description;
    }
    public String getaddress()
    {
        return address;
    }

    //set
    public void setsightName(String sName)
    {
        sightName = sName;
    }
    public void setzone(String zon)
    {
        zone = zon;
    }
    public void setcategory(String categ)
    {
        category = categ;
    }
    public void setphotoUrl(String pUrl)
    {
        photoUrl = pUrl;
    }
    public void setdescription(String desc)
    {
        description = desc;
    }
    public void setaddress(String addr)
    {
        address = addr;
    }

    @Override
    public String toString()
    {
        return "SightName: "+sightName+"\n"+
                "Zone: "+zone+"\n"+
                "Category: "+ category+"\n"+
                "PhotoURL: "+photoUrl+"\n"+
                "Description: "+description+"\n"+
                "Address: "+address+"\n\n";
    }
}

