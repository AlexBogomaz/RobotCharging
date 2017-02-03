import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Alex on 15.08.2016.
 */
public class Detail {
    private int number;
    protected final AtomicBoolean status = new AtomicBoolean();
    Detail (int i)
    {
        this.number = i;
    }

    @Override
    public String toString() {

        return "["+this.number+"] take = "+this.status;
    }

}
