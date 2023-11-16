import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        int n = 5;
//        int[] lost = {1,2,3};
//        int[] reserve = {2,3,4};

        int[] lost = {2,4};
        int[] reserve = {1,3,5};

       solution(n,lost,reserve);

    }




    public static void solution(int n, int[] lost, int[] reserve){
        Arrays.sort(lost);
        Arrays.sort(reserve);
        Set<Integer> lostSet = Arrays.stream(lost).boxed().collect(Collectors.toSet());
        Set<Integer> reserveSet = Arrays.stream(reserve).boxed().collect(Collectors.toSet());
        Set<Integer> lostSetTemp = new HashSet<>(lostSet);
        Set<Integer> reserveSetTemp = new HashSet<>(reserveSet);
        lostSet.removeIf(reserveSetTemp::contains);
        reserveSet.removeIf(lostSetTemp::contains);

        int count = 0;

        for(int lostP : lostSet){
            for(int reserveP : reserveSet){
                if(lostP == reserveP-1 || lostP == reserveP+1){
                    count++;
                    reserveSet.remove(reserveP);
                    break;
                }
            }
        }

        int result = n - lostSet.size() + count;
        System.out.println(result);

    }

}