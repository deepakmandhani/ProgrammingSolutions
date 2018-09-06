package com.droidrank.sample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by deepak.mandhani on 14/02/18.
 */

public class Obfuscation {

    HashMap<String, String> stringStringHashMap = new HashMap<>();

    /**
     *
     * @param str -
    android.support.v4.text.LayoutManager -> android.support.v4.d.p:
    int getLayoutDirectionFromLocale() -> a
    int getLayoutDirectionFromFirstChar() -> b
    int getLayoutDirectionFromLocator() -> c
    android.support.v4.text.SupportManager -> android.support.v4.d.q:
    int getSupportDirection() -> a
    int getSupportLocation(int) -> b
    int getName() -> c
    android.support.v7.block.Moderator -> android.support.v7.d.q:
    void showDialog() -> a
    void showName() -> b
    void showDialog(String) -> a
    void showDialog(String, String) ->a
     */
    void generateMinified(String str) {
        Scanner scanner = new Scanner(str);
        String className = "";
        String classHashValue = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if(line.contains("->")) {
                String word[] = line.split("->");
                if(line.contains("(") && line.contains(")") && !className.isEmpty() && !classHashValue.isEmpty()){
                    String funName[] = word[0].split(" ",2);
                    stringStringHashMap.put(className+"."+funName[1].trim(),classHashValue+"."+word[1].trim());

                } else {
                    className = word[0].trim();
                    classHashValue = word[1].trim();
                    stringStringHashMap.put(className, classHashValue);
                }

            }
        }
    }

    /**
     *
     * @param input-   android.support.v4.text.LayoutManager.getLayoutDirectionFromLocale(),
     *                 android.support.v7.block.Moderator.showDialog(String, String)
     * @return         android.support.v4.d.p.a()
                       android.support.v7.d.q.a()
     */
    ArrayList<String> getMinifiedNames(String input) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim().replace(",","");
            if(stringStringHashMap.containsKey(line)){
                stringArrayList.add(stringStringHashMap.get(line));
            }
        }
        return stringArrayList;
    }
}