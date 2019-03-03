using Toybox.WatchUi as Ui;
using Toybox.Graphics;
using Toybox.System;
using Toybox.Lang;

class DrawableBattery extends Ui.Drawable {
	
	hidden var x, y, weight, height, fontyoffset;
	hidden var center;

    function initialize(params) {
        Drawable.initialize(params);
        
        x = params.get(:x);
        y = params.get(:y);
        weight = params.get(:weight);
    	height = params.get(:height); 
    	fontyoffset = params.get(:fontyoffset);
    	center = params.get(:center);
    }
    
    function draw(dc) {
    	var systemStats = System.getSystemStats();
    	var currentBattery = systemStats.battery;
    	
    	if (center)
    	{
			x = dc.getWidth()/2 - weight/2;
    		y = dc.getHeight() - height - 5;    	
    	}
    	
    	//draw bolt if battery is charing
    	if (Toybox.System.Stats has :charging)
    	{
    		if (systemStats.charging)
	    	{
	    		var bw = 20;
	    		var bh = 20;
	    		var bx = x - bw;
	    		
	    		dc.setColor(Graphics.COLOR_WHITE, Graphics.COLOR_TRANSPARENT);
	    		dc.fillPolygon([[bx + 9, y], [bx + 14, y], [bx + 9, y + bh/2], [bx + 4, y + bh/2]]);
				dc.fillPolygon([[bx + 9, y + bh/4], [bx + 16, y + bh/4], [bx + 9, y + bh]]);    		
    		}
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
    	dc.drawText(dc.getWidth()/2, y + fontyoffset, Graphics.FONT_SMALL, Lang.format("$1$%", [currentBattery.toLong()]), Graphics.TEXT_JUSTIFY_CENTER);
    	
    	//draw battery top
    	var size = 6;
    	x = x + weight;
    	y = y + height/2 - size/2;
    	dc.setColor(Graphics.COLOR_WHITE, Graphics.COLOR_WHITE);
    	dc.drawRectangle(x, y, size, size);
    	dc.fillRectangle(x, y, size, size);
    	
    	dc.setColor(Graphics.COLOR_BLACK, Graphics.COLOR_BLACK);
    }
 }