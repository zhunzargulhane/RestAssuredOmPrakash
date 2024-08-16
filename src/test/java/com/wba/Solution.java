package com.wba;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Pair implements Comparable<Pair>{
    char character;
    int occurrence;
    public Pair(char character,int occurrence){
        this.character= character;
        this.occurrence=occurrence;
    }
    public char getCharacter(){ return character;}
    public int getOccurrence(){return occurrence;}
    public int compareTo(Pair pair){
        return pair.getOccurrence()-this.getOccurrence();
    }
}
class Solution {
    public String reorganizeString(String s) {
        StringBuilder sb = new StringBuilder();
        HashMap<Character,Integer> hashMap  = new HashMap();
        PriorityQueue<Pair> pq =  new PriorityQueue();
        for(int i=0;i<s.length();i++){
            hashMap.put(s.charAt(i),hashMap.getOrDefault(s.charAt(i),0)+1);
        }
        Set<Map.Entry<Character,Integer>> entries = hashMap.entrySet();
        for(Map.Entry<Character,Integer> entry:entries){
            pq.offer(new Pair(entry.getKey(),entry.getValue()));
        }
        while(!pq.isEmpty()){
            if(pq.size()==1){
                if(pq.peek().getOccurrence()>1){
                    return "";
                }else if(sb.charAt(sb.length()-1)!=pq.peek().getCharacter()){
                    sb.append(pq.poll().getCharacter());
                    return sb.toString();
                }else return "";
            }else{
                Pair pair1 = pq.poll();
                Pair pair2 = pq.poll();
                sb.append(pair1.getCharacter());
                sb.append(pair2.getCharacter());
                int freq1 = pair1.getOccurrence();
                int freq2 = pair2.getOccurrence();
                if(freq1-1>0) pq.offer(new Pair(pair1.getCharacter(),freq1-1));
                if(freq2-1>0) pq.offer(new Pair(pair2.getCharacter(),freq2-1));
            }
        }
        return sb.toString();
    }
}
