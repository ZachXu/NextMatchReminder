using Toybox.WatchUi as Ui;
using Toybox.System as Sys;
using Toybox.Time;
using Toybox.Time.Gregorian;
using Toybox.Lang;

class ShenhuaCalendarWidgetView extends Ui.View {

	var matchplans;
	
	var teams;

    function initialize() {
    	        
        matchplans = MatchPlan.MATCHPLANS2018; //WatchUi.loadResource(Rez.JsonData.JSON_MATCHDATA);
        teams = Teams.TEAMS; //WatchUi.loadResource(Rez.JsonData.JSON_TEAMS);
    
        View.initialize();
    }

    // Load your resources here
    function onLayout(dc) {
        setLayout(Rez.Layouts.MainLayout(dc));
    }

    // Called when this View is brought to the foreground. Restore
    // the state of this View and prepare it to be shown. This includes
    // loading resources into memory.
    function onShow() {
    
    }

    // Update the view
    function onUpdate(dc) {
       // Call the parent onUpdate function to redraw the layout
       
       var currentmoment = getCurrentMoment();
       
       var matchplan = null;
       
       var matchmomentinfo = null;
       
       var matchweekinfo = null;
       
       var days = null;
       
       for (var i = 0; i < matchplans.size(); i++)
       {
          var obj = matchplans[i];
          
          var matchdate = obj[ShenhuaConstants.MATCHDATE];
        
          var matchdatemoment = Gregorian.moment({:year => matchdate[ShenhuaConstants.YEAR], 
        										:month => matchdate[ShenhuaConstants.MONTH],
        										:day => matchdate[ShenhuaConstants.DAY]});
        										
          if (currentmoment.lessThan(matchdatemoment) || currentmoment.compare(matchdatemoment) == 0)
          {
          	matchplan = obj;
          	
          	matchmomentinfo = Gregorian.info(matchdatemoment, Time.FORMAT_SHORT);
          	
          	matchweekinfo = Gregorian.info(matchdatemoment, Time.FORMAT_MEDIUM);

	        days = (currentmoment.subtract(matchdatemoment).value() / Gregorian.SECONDS_PER_DAY).toLong();  	
			          
			break;          
          }
       }
       
       if (matchplan != null)
       {
       		View.findDrawableById("LBL_HOME").setText(teams[matchplan[ShenhuaConstants.HOME]]);
       		View.findDrawableById("LBL_GUEST").setText(teams[matchplan[ShenhuaConstants.GUEST]]);
       		
       		View.findDrawableById("LBL_MATCH_DAY").setText(
       		  Lang.format("$1$-$2$-$3$, $4$", 
       		  [matchmomentinfo.year,
       		  matchmomentinfo.month,
       		  matchmomentinfo.day,
       		  matchweekinfo.day_of_week]));
       		  
       		var remaindays = Lang.format("$1$ $2$ $3$", [WatchUi.loadResource(Rez.Strings.REMAIN), days, WatchUi.loadResource(Rez.Strings.DAYS)]);
       		View.findDrawableById("LBL_REMAIN_DAYS").setText(remaindays);
       }
       
        View.onUpdate(dc);
    }

    // Called when this View is removed from the screen. Save the
    // state of this View here. This includes freeing resources from
    // memory.
    function onHide() {
    }
    
    
    //get current moment without hour, minute information
    function getCurrentMoment(){
    	var currentinfo =  Gregorian.info(Time.now(), Time.FORMAT_MEDIUM);
    	
    	return Gregorian.moment({:year => currentinfo.year,
    							 :month => currentinfo.month,
    							 :day => currentinfo.day});
    
    }

}
