package box;

public interface InTask<InputI, StateS>  // impl AnyTask / Inputted<InputI> / Outputted<OutputO>
{
    // TODO Optional<StateS> or own class to handle tasks without state?
    StateS execute(StateS state);

    InTask<InputI, StateS> withInput(InputI in);
}
