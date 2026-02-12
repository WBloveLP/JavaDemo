import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    static {
        System.out.println("Main ...");
    }


    public static void main(String[] args) {
//        System.out.println(ConstA.I);
        System.out.println(ConstA.J);
//        Class.forName("");
    }

    public static void main1(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

        List<String> list = new ArrayList<>();
        list.add("abc");
        list.add("cba");
        list.add("acb");
        String[] s = list.toArray(new String[0]);

        List<String> list1 = Arrays.asList("");
    }
}