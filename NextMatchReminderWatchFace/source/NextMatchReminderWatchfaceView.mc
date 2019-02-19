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
	
	var heartRateIt;
	var bleConnectInfo;
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
		        
        showHeartRate(dc);
        
        showBLEConnectionInfo(dc);
        
        showBatteryInfo(dc);
    }
    
    //set heart rate
    function showHeartRate(dc){
    	
    	heartRateIt = getHeartRateIterator();
    
    	if (heartRateIt != null)
    	{
    		var first = heartRateIt.next();
		    var curHr = null;
		      if ((first != null) && (first.heartRate != 255)) {
		        curHr = first.heartRate;
		      }
    	
    		if (curHr != null)
    		{
    			dc.setColor(Graphics.COLOR_RED, Graphics.COLOR_TRANSPARENT);
		    	var iconheart = WatchUi.loadResource(Rez.Drawables.HeartIcon);
		    	dc.drawBitmap(90, 50, iconheart);
		    	dc.setColor(Graphics.COLOR_WHITE, Graphics.COLOR_TRANSPARENT);
		    	dc.drawText(120, 50, Graphics.FONT_SYSTEM_TINY, curHr, Graphics.TEXT_JUSTIFY_LEFT);
		    	dc.setColor(Graphics.COLOR_BLACK, Graphics.COLOR_BLACK);
    		}
    	}
    
    }
    
    //
    function getHeartRateIterator() {
    	if (Toybox has :ActivityMonitor
    	&& Toybox.ActivityMonitor has :getHeartRateHistory)
    	{
    		return Toybox.ActivityMonitor.getHeartRateHistory(1, true);
    	}
    	
    	return null;
	}
    
    //
    function showBLEConnectionInfo(dc){
    	var devSetting = System.getDeviceSettings();
    	var phoneConnected = devSetting.phoneConnected;
    	
    	if (phoneConnected)
    	{
			bleConnectInfo = devSetting.connectionInfo[:bluetooth];
			
			if (System.CONNECTION_STATE_CONNECTED == bleConnectInfo.state)
			{
				dc.setColor(Graphics.COLOR_BLUE, Graphics.COLOR_TRANSPARENT);
				var iconBLE = WatchUi.loadResource(Rez.Drawables.BluetoothIcon);
				dc.drawBitmap(60, 50, iconBLE);
				dc.setColor(Graphics.COLOR_BLACK, Graphics.COLOR_BLACK);
			}	
    	}
    }
    
    //show battery info
    function showBatteryInfo(dc){
    	var systemStats = System.getSystemStats();
    	var currentBattery = systemStats.battery;
    	
    	var weight = 60;
    	var height = 20;
    	var x = dc.getWidth()/2 - weight/2;
    	var y = dc.getHeight() - height - 5;
    	
    	//draw bolt if battery is charing
    	if (systemStats.charging)
    	{
    		var bw = 20;
    		var bh = 20;
    		var bx = x - bw;
    		
    		dc.setColor(Graphics.COLOR_WHITE, Graphics.COLOR_TRANSPARENT);
    		dc.fillPolygon([[bx + 9, y], [bx + 14, y], [bx + 9, y + bh/2], [bx + 4, y + bh/2]]);
			dc.fillPolygon([[bx + 9, y + bh/4], [bx + 16, y + bh/4], [bx + 9, y + bh]]);    		
    	}
    	
    	//draw battery body
    	dc.setColor(Graphics.COLOR_WHITE, Graphics.COLOR_WHITE);
    	dc.drawRectangle(x, y, weight, height);
    	
    	if (currentBattery >= 20)
    	{
    		dc.setColor(Graphics.COLOR_DK_GREEN, Graphics.COLOR_DK_GREEN);
    	}
    	else
    	{
    		dc.setColor(Graphics.COLOR_DK_RED, Graphics.COLOR_DK_RED);
    	}
    	
    	var currentWidth = (currentBattery / 100f * weight).toLong(); 
    	dc.fillRectangle(x + 1, y + 1, currentWidth, height - 2);
    	
    	dc.setColor(Graphics.COLOR_WHITE, Graphics.COLOR_TRANSPARENT);
    	dc.drawText(dc.getWidth()/2, y - 7, Graphics.FONT_SMALL, Lang.format("$1$%", [currentBattery.toLong()]), Graphics.TEXT_JUSTIFY_CENTER);
    	
    	//draw battery top
    	var size = 6;
    	x = x + weight;
    	y = y + height/2 - size/2;
    	dc.setColor(Graphics.COLOR_WHITE, Graphics.COLOR_WHITE);
    	dc.drawRectangle(x, y, size, size);
    	dc.fillRectangle(x, y, size, size);
    	
    	dc.setColor(Graphics.COLOR_BLACK, Graphics.COLOR_BLACK);
    }
    
    //
    function showMatchInfoData(dc)
    {
    	matchInfo = Storage.getValue(KEY_MATCHINFO);
    	
    	if (matchInfo != null && matchInfo instanceof Dictionary)
    	{
    		var lblMatchCategory = View.findDrawableById("LBL_MATCHCATEGORY");
    		lblMatchCategory.setText(matchInfo["categoryName"]);
    		
    		var lblMatchRound = View.findDrawableById("LBL_MATCHROUND");
    		var formatMatchRound = WatchUi.loadResource(Rez.Strings.Format_MatchRound);
    		lblMatchRound.setText(
    			Lang.format(formatMatchRound, [matchInfo["matchRound"]]));
    		
    		var lblHome = View.findDrawableById("LBL_HOME");
    		lblHome.setText(matchInfo["home"]);
    		
    		var lblGuest = View.findDrawableById("LBL_GUEST");
    		lblGuest.setText(matchInfo["guest"]);
    		
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
