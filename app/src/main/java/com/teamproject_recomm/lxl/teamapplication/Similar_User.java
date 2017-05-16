package com.teamproject_recomm.lxl.teamapplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxl on 5/2/2017.
 */

public class Similar_User  {

    //Cossim cs = new Cossim();
    HashMap<String, Double> map = new HashMap<String, Double>();
    public Similar_User(HashMap<String, Double> inputMap){
        this.map=inputMap;
    }


    public String Similar_User(){


        String sim_user = null;

        double maxValueInMap=(Collections.max(map.values()));
        // This will return max value in the Hashmap
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                sim_user = entry.getKey();
                // Print the key with max value
            }
        }
        return sim_user;
    }

    String[] userNew = {"name1","name2","name3" };
    String[] userSim = {"name1","name2","name3","name4","name5","name6","name7",};

    public List<String> Guess_your_fav(String[]newUser, String[]similarUser){
        //String [] yourFav = null;
        List<String> a1 =
                Arrays.asList(newUser);
        List<String> a2 =
                Arrays.asList(similarUser);
        List<String> common=new ArrayList<>(a2);
        common.retainAll(a1);

        List<String> diff=new ArrayList<>();
        for(String elements:a2){
            if(!common.contains(elements)){
                diff.add(elements);
            }
        }
        return diff;

    }


}
