package com.example.demo.klsCrawler;
import com.example.demo.sighter.Sight;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;

public class KeelungSightsCrawler
{
    ArrayList<String> zoneList = new ArrayList<>();
    ArrayList<String> attractionList = new ArrayList<>();
    ArrayList<String> attractionUrl = new ArrayList<>();
    ArrayList<Sight> sightList = new ArrayList<>();
    public Sight[] getItems(String targetZone)
    {
        try{
            String nextLink = "";
            Boolean check = false;
            Document document1 = Jsoup.connect("https://www.travelking.com.tw/tourguide/taiwan/keelungcity/").get();
            Elements item1 = document1.getElementsByClass("introduction");//"text"
            Elements tmpLink = item1.attr("class","box_ss");
            Elements zoneLink = tmpLink.select("a[href]");
            for(Element link : zoneLink)
            {
                zoneList.add(link.text());
                if(link.text().equals(targetZone))
                {
                    nextLink = link.attr("abs:href");
                    check = true;
                }
            }
            if(check != true)
            {
                System.out.printf("Can't find the %s!%n",targetZone);
                Sight emptyZone = new Sight();
                String empty = "No information~";
                emptyZone.setsightName(empty);
                emptyZone.setzone(targetZone);
                emptyZone.setcategory(empty);
                emptyZone.setphotoUrl("null");
                emptyZone.setdescription(empty);
                emptyZone.setaddress(empty);
                sightList.add(emptyZone);
                Sight[] sightArray = new Sight[1];
                sightArray[0] = sightList.get(0);
                return sightArray;
            }

            Document document2 = Jsoup.connect(nextLink).get();
            Elements item2 = document2.getElementsByClass("box");
            Elements attractionLink = item2.select("a[href]");
            for(Element link : attractionLink)
            {
                attractionList.add(link.text());
                attractionUrl.add(link.attr("abs:href"));
            }

            for(int i = 0;i < attractionUrl.size();i++)
            {
                String attrUrl = attractionUrl.get(i);
                Sight attr = new Sight();

                Document document3 = Jsoup.connect(attrUrl).get();
                //name
                Elements tmpName = document3.getElementsByClass("h1");
                String attrName = tmpName.text();
                attr.setsightName(attrName);
                //zone
                attr.setzone(targetZone);
                //category
                Elements tmpCategory = document3.getElementsByClass("point_type");
                String attrCategory = tmpCategory.attr("property","rdfs:label").text().substring(6);
                attr.setcategory(attrCategory);
                //photoUrl
                Element tmpPhotoUrl = document3.getElementsByClass("gpic").first();
                if(tmpPhotoUrl!=null)
                {
                    Element tmpAttrPhotoUrl = tmpPhotoUrl.select("img[src]").first();
                    Attributes node = tmpAttrPhotoUrl.attributes();
                    Iterator<Attribute> iterator = node.iterator();
                    while (iterator.hasNext())
                    {
                        Attribute attribute = iterator.next();
                        String key = attribute.getKey();
                        //find data-src
                        if(key.equals("data-src"))
                        {
                            String attrPhotoUrl = attribute.getValue();
                            attr.setphotoUrl(attrPhotoUrl);
                            break;
                        }
                    }
                }

                //description
                Elements tmpDescription = document3.getElementsByClass("text");
                String attrDescription = tmpDescription.attr("property","dc:description").text();
                attr.setdescription(attrDescription);
                //address
                Elements tmpAddress = document3.getElementsByClass("address");
                String attrAddress = tmpAddress.attr("property","vcard:street-address").text().substring(4);
                attr.setaddress(attrAddress);

                sightList.add(attr);
            }
            //Turn Array
            int sightNum = sightList.size();
            Sight[] sightArray = new Sight[sightNum];
            for(int i = 0;i < sightNum;i++)
            {
                sightArray[i] = sightList.get(i);
            }
            return sightArray;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}

