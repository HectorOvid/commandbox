package zsample;

import box.AbstractBox;

import java.util.function.Supplier;

public class TheBox extends AbstractBox<IntermediateBoxState> {
    private IntermediateBoxState m_boxState = new IntermediateBoxState();

    @Override
    protected Supplier<IntermediateBoxState> getSupplierOfInitialState() {
        return IntermediateBoxState::new;
    }

    @Override
    protected void setUpdatedCurrentState(IntermediateBoxState boxState) {
        System.out.println("                           ---> upadated state = "+boxState.getTaskCount());
        m_boxState = boxState;
    }
}
