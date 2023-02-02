import java.util.List;

public class Application {

     public static void main(String[] args) {
         SquadSet a = new SquadSet(List.of(1,2,3));
         SquadSet b = new SquadSet(List.of(1,3));

         System.out.println(a.sum(b));
     }
}
