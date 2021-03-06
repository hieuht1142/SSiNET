package custom.corra;

import common.Tuple;

import java.util.*;

public class BigCORRARoutingAlgorithm extends CORRARoutingAlgorithm {

    public BigCORRARoutingAlgorithm(CORRAGraph graph) {
        super();
        this.graph = graph;

        tables = new HashMap<>();
        for (int u : graph.switches()) {
            tables.put(u, new CORRATable());
        }
    }

    @Override
    public int next(int source, int current, int destination) {
        if (graph.isHostVertex(current)) {
            return graph.adj(current).get(0);
        } if (graph.adj(current).contains(destination)) {
            return destination;
        }
        int desSwitch = graph.isHostVertex(destination) ? graph.adj(destination).get(0) : destination;

        CORRATable table = tables.get(current);
        // Initial corra
        if (table.neighborTable.isEmpty()) {
            this.updateSelfTable(current);
        }

        int nextNeighbor = table.getNextNeighborNode(desSwitch);
        if (nextNeighbor > -1) {
            return nextNeighbor;
        }

        // Receive bridges from neighborhood if does not
        if (!table.isReceiveBr) {
            // Receive Br from u's neighbors
            this.getBrFromNeighbor(current);
            table.isReceiveBr = true;
        }

        Tuple<Integer, Integer> nextBr =  this.getNextBrNode(current, desSwitch);
        nextNeighbor = nextBr.a;

//        StdOut.printf("%d %d %d\n", current, desSwitch, nextNeighbor);
        if (nextNeighbor > -1) {
            if (this.type == 0) this.type = nextBr.b;
            return nextNeighbor;
        }

        return this.findShortestPath(current, destination);
    }
}
