package project2022.ex0;

public class Main
{
    public static void main(String[] args)
    {
        KeelungSightsCrawler crawler = new KeelungSightsCrawler();
        Sight [] sights = crawler.getItems("中正區");
        for (Sight s: sights)
        {
            System.out.println(s);
        }
    }
}