import java.util.List;

public class SquadApplication {

     public static void main(String[] args) {
         SquadSet a = new SquadSet(List.of(1,2,3));
         SquadSet b = new SquadSet(List.of(1,3));

         System.out.println(a.sum(b));
         System.out.println(a.complement(b));
         System.out.println(a.intersect(b));
         System.out.println(a.getElements());
         for (int i =0;i<a.resultAll().length;i++) {
             System.out.println(a.resultAll()[i]);
         }
     }
}
