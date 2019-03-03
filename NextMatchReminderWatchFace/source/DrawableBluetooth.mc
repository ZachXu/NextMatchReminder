using Toybox.WatchUi as Ui;
using Toybox.System;
using Toybox.Lang;

class DrawableBluetooth extends Ui.Drawable {
	
	hidden var x, y;

    function initialize(params) {
        Drawable.initialize(params);

        x = params.get(:x);
        y = params.get(:y);
    }

    function draw(dc) {
    	var devSetting = System.getDeviceSettings();
    	var phoneConnected = devSetting.phoneConnected;
    	
    	var isConnected = false;
    	
    	if (phoneConnected)
    	{
    		if (Toybox.System.DeviceSettings has :connectionInfo)
    		{
    			var bleConnectInfo = devSetting.connectionInfo[:bluetooth];
    			
    			isConnected = System.CONNECTION_STATE_CONNECTED == bleConnectInfo.state;
    		}
    		else
    		{
    			isConnected = true;
    		}
    	}
    	
    	if (isConnected)
    	{
    		dc.setColor(Graphics.COLOR_BLUE, Graphics.COLOR_TRANSPARENT);
			var iconBLE = WatchUi.loadResource(Rez.Drawables.BluetoothIcon);
			dc.drawBitmap(x, y, iconBLE);
			dc.setColor(Graphics.COLOR_BLACK, Graphics.COLOR_BLACK);
    	}
    }
}