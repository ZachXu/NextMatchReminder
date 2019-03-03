using Toybox.Time;
using Toybox.Time.Gregorian;
using Toybox.System;

module NextMatchService{

	var teaminfo = Teams.TEAMS;

	function queryNextMatchInfo(){
		var retmatchinfo = null; 
	
		var matchplans = MatchPlan.MATCHPLANS;
		
		var currentmoment = Time.now();
		
		var nextmatchinfo = null;
		
		for (var i = 0; i < matchplans.size(); i++)
		{
			var matchinfo = matchplans[i];
			
			var matchdate = parseDate(matchinfo[NextMatchConstant.MATCHTIME]);
			
			if (currentmoment.lessThan(matchdate) || currentmoment.compare(matchdate) == 0)
			{
				nextmatchinfo = matchinfo;
				break;
			}
		}
		
		if (nextmatchinfo != null)
		{
			retmatchinfo = {
			 NextMatchConstant.HOME => teaminfo[nextmatchinfo[NextMatchConstant.HOME]],
			 NextMatchConstant.GUEST => teaminfo[nextmatchinfo[NextMatchConstant.GUEST]],
			 NextMatchConstant.CATEGORYNAME => nextmatchinfo[NextMatchConstant.CATEGORYNAME],
			 NextMatchConstant.MATCHROUND => nextmatchinfo[NextMatchConstant.MATCHROUND],
			 NextMatchConstant.MATCHTIME => nextmatchinfo[NextMatchConstant.MATCHTIME]
			};
		}
		
		return retmatchinfo;
	}
	
	
	function parseDate(dateTimeString)
	{
		var splitpos = dateTimeString.find(" ");
		
		var dateString = dateTimeString.substring(0, splitpos);
		var timeString = dateTimeString.substring(splitpos + 1, dateTimeString.length());
		
		var yearpos = dateString.find("-");
		
		var year = dateString.substring(0, yearpos).toNumber();
		
		var monthString = dateString.substring(yearpos + 1, dateString.length());
		
		var monthpos = monthString.find("-");
		
		var month = monthString.substring(0, monthpos).toNumber();
		var day = monthString.substring(monthpos + 1, monthString.length()).toNumber();
		
		var hourpos = timeString.find(":");
		
		var hour = timeString.substring(0, hourpos).toNumber();
		var min = timeString.substring(hourpos + 1, timeString.length()).toNumber();
		
		var retDate = Gregorian.moment({
			:year => year,
			:month => month,
			:day => day,
			:hour => hour,
			:minute => min});
		
		return retDate;	
	}

}