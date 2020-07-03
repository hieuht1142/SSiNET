package weightedloadexperiment.pairstrategies;

import java.util.Arrays;
import java.util.List;

import custom.fattree.FatTreeGraph;
import custom.fattree.FatTreeRoutingAlgorithm;

public class ForcePair extends InterPodIncoming {

	private int modulo = 0;
	public ForcePair(FatTreeRoutingAlgorithm routing, FatTreeGraph G) {
		super(routing, G);
		this.modulo = 0;
	}
	
	public ForcePair(FatTreeRoutingAlgorithm routing, FatTreeGraph G, int modulo) {
		super(routing, G);
		this.modulo = modulo;
	}
	
	@Override
	public void checkValid()
    {
		if(modulo != 5)
		{
			super.checkValid();
		}
		else {
			List<Integer> sources = getSources();
			List<Integer> destinations = getDestinations();
			int realCore = 0;
			for(int i = 0; i < sources.size(); i++)
	        {
	            realCore = getRealCoreSwitch(sources.get(i), destinations.get(i));
	            System.out.println("From " + sources.get(i) + " through " +
	                    getCoreSwitch(sources.get(i), destinations.get(i))
	                    + "/" + realCore
	                    + " to "
	                    + destinations.get(i)
	            );
	        }
		}
    }
	public void pairHosts()
	{
		List<Integer> sources = getSources();
        List<Integer> destinations = getDestinations();
        
        if(this.modulo != 0)
        {
        	Integer[] pairs = pairHostsByModulo(this.modulo);
        	sources.addAll(Arrays.asList(pairs));
        	//destinations.addAll(Arrays.asList(pairs[1]));
        }
        else {
	        sources.add(1);     //destinations.add(11);
	        sources.add(2);     //destinations.add(16);
	        sources.add(3);     //destinations.add(25);
	        sources.add(0);     //destinations.add(18);
	        
	        sources.add(9);     //destinations.add(19);
	        sources.add(10);     //destinations.add(24);
	        sources.add(11);     //destinations.add(26);
	        sources.add(18);     //destinations.add(27);
	        
	        sources.add(8);     //destinations.add(0);
	        sources.add(17);     //destinations.add(1);
	        sources.add(26);     //destinations.add(2);
	        sources.add(19);     //destinations.add(3);
	        
	        sources.add(16);     //destinations.add(8);
	        sources.add(25);     //destinations.add(9);
	        sources.add(27);     //destinations.add(10);
	        sources.add(24);     //destinations.add(17);
        }
        
        destinations.add(11);
        destinations.add(16);
        destinations.add(25);
        destinations.add(18);
        
        destinations.add(19);
        destinations.add(24);
        destinations.add(26);
        destinations.add(27);
        
        destinations.add(0);
        destinations.add(1);
        destinations.add(2);
        destinations.add(3);
        
        destinations.add(8);
        destinations.add(9);
        destinations.add(10);
        destinations.add(17);
        
		this.setSources(sources);
        this.setDestinations(destinations);
	}
	
	private Integer[] pairHostsByModulo(int x)
	{
		Integer[] results = new Integer[16];
		if(x == 1)
		{
			results = new Integer[]{2, 3, 0, 1, 10, 11, 8, 19, 9, 18, 27, 16, 17, 26, 24, 25}; 
		}
		
		if(x == 2)
		{
			results = new Integer[]{3, 0, 1, 2, 11, 8, 9, 16, 10, 19, 24, 17, 18, 27, 25, 26}; 
		}
		
		if(x == 3)
		{
			results = new Integer[]{0, 1, 2, 3, 8, 9, 10, 17, 11, 16, 25, 18, 19, 24, 26, 25}; 
		}
		
		if(x == 4)
		{
			/*
			 * destinations.add(11); destinations.add(16); destinations.add(25);
			 * destinations.add(18);
			 * 
			 * destinations.add(19); destinations.add(24); destinations.add(26);
			 * destinations.add(27);
			 * 
			 * destinations.add(0); destinations.add(1); destinations.add(2);
			 * destinations.add(3);
			 * 
			 * destinations.add(8); destinations.add(9); destinations.add(10);
			 * destinations.add(17);
			 */
			results = new Integer[]{1, 2, 3, 26, 
									9, 10, 11, 18, 
									8, 17, 16, 19, 
									0, 25, 27, 24}; 
		}
		
		if(x == 5)
		{
			results = new Integer[16];
			for(int i = 0; i < results.length; i++)
			{
				results[i] = 27;
			}
			//results[8] = 1;
		}
		
		if(x == 6)
		{
			results = new Integer[]{1, 26, 8, 10, 0, 3, 19, 16, 11, 9, 27, 17, 2, 25, 18, 24}; 
		}
		return results;
	}

}