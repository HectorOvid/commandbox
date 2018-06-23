import zsample.IntermediateBoxState;
import zsample.TheBox;
import mapper.IndirectMapByClass;
import box.InOutTask;
import zsample.MultiplyGreeting;
import zsample.PrintLoudly;

import java.util.List;

public class TaskMain {
    public static void main(String[] args) {
        InOutTask<Integer, ?, IntermediateBoxState> tsk = new MultiplyGreeting("BuenasDias");
        MultiplyGreeting saytsk = new MultiplyGreeting("Hi");

        TheBox box = new TheBox();

        IndirectMapByClass fullOut = box
                .queue(new PrintLoudly().withInput(1.2345))
                .queue(tsk.withInput(1))
                .queue(saytsk.withInput(3))
                .executeAll();

        System.out.println(fullOut.toString());

        List<String> saidOutpuuts = fullOut.get(MultiplyGreeting.class);
        System.out.println(saidOutpuuts);
        System.out.println("Class of output MultiplyGreeting is " + fullOut.getLast(MultiplyGreeting.class).getClass().getSimpleName());
    }
}
