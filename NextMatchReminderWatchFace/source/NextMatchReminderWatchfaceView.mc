using Toybox.WatchUi;
using Toybox.Graphics;
using Toybox.System;
using Toybox.Lang;
using Toybox.Time;
using Toybox.Time.Gregorian;
using Toybox.Application.Storage;


class NextMatchReminderWatchfaceView extends WatchUi.WatchFace {

	private const FORMAT_MONTH_SHORT = "MM";
	private const FORMAT_MONTH_MEDIUM = "MMM";
	
	var matchInfo;

    function initialize() {
        WatchFace.initialize();
    }
    
    // Load your resources here
    function onLayout(dc) {
        setLayout(Rez.Layouts.WatchFaceLayout(dc));
    }

    // Called when this View is brought to the foreground. Restore
    // the state of this View and prepare it to be shown. This includes
    // loading resources into memory.
    function onShow() {
    }

    // Update the view
    function onUpdate(dc) {
    
    	var today = Time.today();
    	var todayInfoSmall = Gregorian.info(today, Time.FORMAT_SHORT);
    	var todayInfo = Gregorian.info(today, Time.FORMAT_MEDIUM);
    	
    	var monthformat = WatchUi.loadResource(Rez.Strings.Default_Format_Month);
    	var monthinfo = null;
    	
    	if (FORMAT_MONTH_SHORT.equals(monthformat))
    	{
    		monthinfo = Lang.format("$1$$2$", [
    		 	todayInfoSmall.month.format("%02d"),
    		 	WatchUi.loadResource(Rez.Strings.Month)
    		 ]); 
    	}
    	else
    	{
    		monthinfo = todayInfo.month;
    	}
    	
    	var dateString = Lang.format("$1$ $2$$3$ $4$", [
    		todayInfo.year,
    		monthinfo,
    		todayInfo.day.format("%02d"),
    		todayInfo.day_of_week
    	]);
    	var lbldate = View.findDrawableById("LBL_DATE_WEEKDAY");
    	lbldate.setText(dateString);
        
        // Get and show the current time
        var clockTime = System.getClockTime();
        var timeString = Lang.format("$1$:$2$", [clockTime.hour, clockTime.min.format("%02d")]);
        var lbltime = View.findDrawableById("LBL_TIME");
        lbltime.setText(timeString);
		
		showMatchInfoData(dc);

        // Call the parent onUpdate function to redraw the layout
        View.onUpdate(dc);
    }
    
    //
    function showMatchInfoData(dc)
    {
    	if (Toybox.Application has :Storage)
    	{
    		matchInfo = Storage.getValue(KEY_MATCHINFO);
    	}
    	
    	if (matchInfo == null)
    	{
    		matchInfo = NextMatchService.queryNextMatchInfo();
    	}
    	
    	if (matchInfo != null && matchInfo instanceof Dictionary)
    	{
    		var formatMatchRound =
    			Lang.format(
    				WatchUi.loadResource(Rez.Strings.Format_MatchRound), 
    				[matchInfo["matchRound"]]);
    	
    		var lblMatchBasicInfo = View.findDrawableById("LBL_MATCHBASICINFO");
    		lblMatchBasicInfo.setText(Lang.format(
    			"$1$ $2$ $3$", 
    			[WatchUi.loadResource(Rez.Strings.NEXT_MATCH),
    			matchInfo["categoryName"],
    			formatMatchRound]));
    			
    		var lblMatchTeam = View.findDrawableById("LBL_MATCHTEAM");
    		lblMatchTeam.setText(Lang.format(
    			"$1$ VS $2$", 
    			[matchInfo["home"], matchInfo["guest"]]));
    		
    		var lblMatchTime = View.findDrawableById("LBL_MATCH_DAY");
    		lblMatchTime.setText(matchInfo["matchTime"]);
    	}
    	
    }
    
    // Called when this View is removed from the screen. Save the
    // state of this View here. This includes freeing resources from
    // memory.
    function onHide() {
    }

    // The user has just looked at their watch. Timers and animations may be started here.
    function onExitSleep() {
    }

    // Terminate any active timers and prepare for slow updates.
    function onEnterSleep() {
    }

}
