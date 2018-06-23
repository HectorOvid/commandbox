package box;

import mapper.IndirectMapByClass;
import util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class AbstractBox<StateS> {
    private List<InOutTask<?, Object, StateS>> inouttasks = new ArrayList<>();
    private List<InTask<?, StateS>> onlyInTasks = new ArrayList<>();

    private StateS m_state = getSupplierOfInitialState().get();

    protected abstract Supplier<StateS> getSupplierOfInitialState();
    protected StateS getCurrentState() {
        return m_state;
    }
    protected void setUpdatedCurrentState(StateS newState) {
        m_state = newState;
    }

    @SuppressWarnings("unchecked")
    public <I, O> AbstractBox<StateS> queue(InOutTask<I, O, StateS> tsk) {
        inouttasks.add((InOutTask<?, Object, StateS>) tsk);
        return this;
    }

    public <I> AbstractBox<StateS> queue(InTask<I, StateS> intask) {
        onlyInTasks.add(intask);
        return this;
    }

    // TODO wrap the indirect map into BoxOutput
    // TODO add blackboxstate ?
    public IndirectMapByClass executeAll() {
        IndirectMapByClass out = new IndirectMapByClass();

        // TODO do the mixed typed task IN THE GIVEN ORDER !

        for (InTask<?, StateS> t: onlyInTasks) {
            System.out.println("Executing next IN-task:  " + t.getClass().getSimpleName());
            StateS nextState = t.execute(getCurrentState());
            setUpdatedCurrentState(nextState);
        }

        for (InOutTask<?,Object, StateS> t : inouttasks) {
            Pair<Object, StateS> oneOutput = t.executeWithOut(getCurrentState());

            System.out.println("Executing next IO-task:  " + t.getClass().getSimpleName() + " with output " + oneOutput.left.toString());
            System.out.println("       task out class:"+ oneOutput.getClass().getSimpleName());

            out.addObject(t.getClass(),
                    oneOutput.left,
                    t.getOutputClass());

            setUpdatedCurrentState(oneOutput.right);
        }

        // TODO clear after task sequence

        return out;
    }
}
