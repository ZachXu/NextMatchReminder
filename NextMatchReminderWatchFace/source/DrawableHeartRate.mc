using Toybox.WatchUi as Ui;
using Toybox.Graphics;
using Toybox.System;

class DrawableHeartRate extends Ui.Drawable {
	
	hidden var x, y;

    function initialize(params) {
        Drawable.initialize(params);

        x = params.get(:x);
        y = params.get(:y);
    }
    
    function draw(dc) {
    	var heartRateIt = getHeartRateIterator();
    
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
		    	dc.drawBitmap(x, y, iconheart);
		    	dc.setColor(Graphics.COLOR_WHITE, Graphics.COLOR_TRANSPARENT);
		    	dc.drawText(x + 30, y, Graphics.FONT_SYSTEM_TINY, curHr, Graphics.TEXT_JUSTIFY_LEFT);
		    	dc.setColor(Graphics.COLOR_BLACK, Graphics.COLOR_BLACK);
    		}
    	}
    }
    
    
    function getHeartRateIterator() {
    	if (Toybox has :ActivityMonitor
    	&& Toybox.ActivityMonitor has :getHeartRateHistory)
    	{
    		return Toybox.ActivityMonitor.getHeartRateHistory(1, true);
    	}
    	
    	return null;
	}
    
}