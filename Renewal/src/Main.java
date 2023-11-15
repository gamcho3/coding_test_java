import javax.sound.midi.SysexMessage;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2,3,4};

        solution(orders,course);
    }

    public static void solution(String[] orders, int[] course){
       List<Set<String>> i =  Arrays.stream(orders)
               .map(String::chars)
               .map(charStream -> charStream.mapToObj(menu -> String.valueOf((char) menu))
                       .collect(Collectors.toSet()))
               .collect(Collectors.toList());

       Map<Integer, List<Course>> courses = new HashMap<>();
       for(int length : course){
           List<Course> list = new ArrayList<>();
           list.add(new Course("",0));
           courses.put(length,list);
       }

    getCourses('A',new HashSet<>(),i,courses);
       var result = courses.values()
            .stream()
            .filter(list->list.get(0).occurrences > 0)
            // list<List<Course>>를 하나의 List로 합침
            .flatMap(List::stream)
            .map(c->c.course).sorted();
       System.out.println(Arrays.toString(result.toArray()));
    }

    private static void getCourses(char nextMenu, Set<String> selectedMenus, List<Set<String>> orderList, Map<Integer, List<Course>> courses ){


      int occurrences = (int) orderList.stream()
              .filter(order -> order.containsAll(selectedMenus)).count();

      // 다음 글자가 포함된 Set의 개수가 2미만이면 코스가 생성되지 않으므로 리턴
      if(occurrences < 2) return;
      int size = selectedMenus.size();
      if(courses.containsKey(size)){
          List<Course> courseList = courses.get(size);
          // 선택된 메뉴를 Join한후 객체 생성
          Course course = new Course(selectedMenus.stream().sorted().collect(Collectors.joining("")),occurrences);
          Course original = courseList.get(0);
          if(original.occurrences < occurrences){
              courseList.clear();
              courseList.add(course);
          }else if(original.occurrences == occurrences){
              courseList.add(course);
          }
      }
      if(size>=10) return;
      for(char menuChar = nextMenu; menuChar <= 'Z';menuChar++){
          String menu = String.valueOf(menuChar);
          selectedMenus.add(menu);
          getCourses((char) (menuChar+1),selectedMenus,orderList,courses);
          selectedMenus.remove(menu);
      }



    }

    private static class Course{
        public final String course;
        public final int occurrences;

        public Course(String course, int occurrences){
            this.course = course;
            this.occurrences = occurrences;
        }

        @Override
        public String toString() {
            return "Course{" +
                    "course='" + course + '\'' +
                    ", occurrences=" + occurrences +
                    '}';
        }
    }
}



