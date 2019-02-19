using Toybox.Background;
using Toybox.System;
using Toybox.Sensor;
using Toybox.Communications;

(:background)
class NextMatchReminderWatchfaceBackgroundService extends Toybox.System.ServiceDelegate
{
	var responsedata;

	//initialize
	function initialize(){
		System.ServiceDelegate.initialize();
	}
	
	//
	function onTemporalEvent(){
	
		var url = "http://206.217.143.66:8080/nextmatchinfo";
			
			var params = {
				"team" => "SH"
			};
			
			var options = {
				:method => Communications.HTTP_REQUEST_METHOD_GET,
				:headers => {
						"Content-Type" => Communications.REQUEST_CONTENT_TYPE_JSON}
			};
			
			Communications.makeWebRequest(url, params, options, method(:onReceive));
	}
	
	
	function onReceive(responseCode, data){
		if (responseCode == 200){
		responsedata = data;
		
		Background.exit(responsedata);
		
		}else{
		System.println(responseCode);
		}
	}
}