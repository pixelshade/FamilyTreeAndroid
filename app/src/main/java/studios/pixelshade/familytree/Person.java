package studios.pixelshade.familytree;

import com.orm.SugarRecord;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by pixelshade on 30/07/15.
 */
public class Person extends SugarRecord<Person> {
    public String id;
    public String name;
    public String imageName;
    public String info;
    public boolean male;
    public Date born;
    public Date death;
    public LinkedList<Person> children;

    public Person(){

    }

    public Person(String name) {
        this.name = name;
    }

}
