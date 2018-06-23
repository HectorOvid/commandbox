package box;

import util.Pair;

public interface InOutTask<InputI, OutputO, StateS>  // impl AnyTask / Inputted<InputI> / Outputted<OutputO>
{
    // TODO Optional<StateS> or own class to handle tasks without state?
    Pair<OutputO, StateS> executeWithOut(StateS state);

    InOutTask<InputI, OutputO, StateS> withInput(InputI in);

    Class<OutputO> getOutputClass();
}
