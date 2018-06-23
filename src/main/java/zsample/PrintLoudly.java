package zsample;

import box.InTask;

public class PrintLoudly implements InTask<Double, IntermediateBoxState> {
    private String m_text = "";

    @Override
    public IntermediateBoxState execute(IntermediateBoxState state) {
        System.out.println("### " + louder(m_text) + " !!!");
        return state.addTaskCount();
    }

    @Override
    public PrintLoudly withInput(Double in) {
        m_text = String.format(" duble_is_%.2f ", in);
        return this;
    }

    private String louder(String m_text) {
        return m_text.toUpperCase();
    }
}
