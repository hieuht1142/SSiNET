package simulator;

import simulator.DiscreteEventSimulator;

/**
 * Created by Dandoh on 6/27/17.
 */
public abstract class Event extends umontreal.ssj.simevents.Event{
    public int id;
    protected DiscreteEventSimulator sim;

    public Event(DiscreteEventSimulator sim, double time) {
        super(sim);
        this.sim = sim;
        this.eventTime = time;
        this.id = ++sim.numEvent;
    }

    public Event(DiscreteEventSimulator sim, double time, double priority) {
        this(sim, time);
        this.priority = priority;
    }
}
