package network.states.unidirectionalway;

import config.Constant;
import events.CLeavingEXBEvent;
import events.FLeavingSwitchEvent;
import infrastructure.entity.Node;
import infrastructure.event.Event;
import infrastructure.state.State;
import network.elements.ExitBuffer;
import network.elements.Packet;
import network.elements.UnidirectionalWay;
import network.entities.SourceNode;
import network.entities.Switch;

public class W0 extends State {
	//�	State W0: the way has no packet and it is able to transfer one.

    public W0(UnidirectionalWay unidirectionalWay){
        this.element = unidirectionalWay;
    }

    @Override
    public void act(){
        UnidirectionalWay unidirectionalWay = (UnidirectionalWay)element;
        ExitBuffer exitBuffer = unidirectionalWay.getFromNode().physicalLayer.exitBuffers
                .get(unidirectionalWay.getToNode().getId());
        Packet packet = exitBuffer.getPeekPacket();
        if(packet != null){
            if(!(exitBuffer.hasEventOfPacket(packet))){
//                Node nextNode = unidirectionalWay.getToNode();
                if(exitBuffer.getNode() instanceof SourceNode){
                    long time = (long)exitBuffer.physicalLayer.simulator.time();
                    Event event = new CLeavingEXBEvent(time, time, exitBuffer, packet);
                    exitBuffer.insertEvents(event); //chen them su kien moi vao
                }
                else if(exitBuffer.getNode() instanceof Switch){
                	long time = (long)exitBuffer.physicalLayer.simulator.time();
                    Event event = new FLeavingSwitchEvent(time, time + Constant.SWITCH_CYCLE, exitBuffer, packet);
                    exitBuffer.insertEvents(event); //chen them su kien moi vao

                }
            }
        }
    }
}
