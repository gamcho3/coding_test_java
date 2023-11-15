import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        final String[] participant = {"leo", "kiki", "eden"};
        final String[] completion = {"eden", "kiki"};

        String result = solution(participant,completion);
        System.out.println(result);
    }

    public static String solution(String[] participant, String[] completion){
        HashMap<String,Integer> map = new HashMap<>();
        for (String s : participant) map.put(s, map.getOrDefault(s, 0) + 1);
        for(String item : completion) map.put(item,map.get(item)-1);

        String result = "";

        for(String key : map.keySet()){
            if(map.get(key) == 1){
                result = key;
            }
        }

        return result;
    }
}