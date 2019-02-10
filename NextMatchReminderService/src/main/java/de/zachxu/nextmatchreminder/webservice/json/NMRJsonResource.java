package de.zachxu.nextmatchreminder.webservice.json;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import de.zachxu.nextmatchreminder.webservice.db.daoservice.MatchInfoDAOService;
import de.zachxu.nextmatchreminder.webservice.db.data.MatchInfo;

@Path("/")
@Consumes("application/json")
public class NMRJsonResource {

	@Path("nextmatchinfo")
	@GET
	@Produces("application/json")
	public NMRJsonMatch getNextMatch(@QueryParam("team") String team)
	{
		NMRJsonMatch retVal = new NMRJsonMatch();
		
		MatchInfo nextMatchInfo = MatchInfoDAOService.getInstance().getNextMatchInfo(team, new Date());
		
		if (!nextMatchInfo.isNull())
		{
			retVal = new NMRJsonMatch(nextMatchInfo);
		}
		
		return retVal;
	}
	
}
