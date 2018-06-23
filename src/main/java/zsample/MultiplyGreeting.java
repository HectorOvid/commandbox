package zsample;

import box.InOutTask;
import util.Pair;

public class MultiplyGreeting implements InOutTask<Integer, String, IntermediateBoxState> {

    private Integer m_in = 1;
    private String m_out = "";
    private final String m_phrase;


    public MultiplyGreeting(String phrase) {
        m_phrase = phrase;
    }

    @Override
    public MultiplyGreeting withInput(Integer integ) {
        m_in = integ;
        return this;
    }

    @Override
    public Pair<String, IntermediateBoxState> executeWithOut(IntermediateBoxState state) {
        return new Pair<>(thisOnesExecute(m_in), state.addTaskCount());
    }

    @Override
    public Class<String> getOutputClass() {
        return String.class;
    }

    private String thisOnesExecute(Integer max) {
        for (int i = 0; i < max; ++i) {
            m_out += m_phrase;
        }
        String result = m_out;
        m_out = "";
        return result;
    }
}
