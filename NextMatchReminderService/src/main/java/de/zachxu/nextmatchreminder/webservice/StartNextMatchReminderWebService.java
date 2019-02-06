package de.zachxu.nextmatchreminder.webservice;

import java.util.Date;
import java.util.stream.Collectors;

import de.zachxu.nextmatchreminder.webservice.db.daoservice.MatchCountryDAOService;
import de.zachxu.nextmatchreminder.webservice.db.daoservice.MatchInfoDAOService;
import de.zachxu.nextmatchreminder.webservice.db.daoservice.TeamInfoDAOService;

/**
 * Hello world!
 *
 */
public class StartNextMatchReminderWebService 
{
    public static void main(String[] args)
    {
    	for (String arg : args)
    	{
    		String[] paramValue = arg.split("=");
    		
    		if (paramValue.length != 2)
    		{
    			throw new RuntimeException("error argumentation:" + arg);
    		}
    		else
    		{
    			NMRProperty.setProperty(paramValue[0], paramValue[1]);
    		}
    	}
    	
    	MatchInfoDAOService.getInstance().getNextMatchInfo("sh", new Date());
    }
}
