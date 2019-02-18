using Toybox.Communications;
using Toybox.System;
using Toybox.Lang;


module NextMatchReminderConnect {
	
	(:NMRConnect)
	module NMRConnect{
		
		var resultHandler;
		
		function makeNextMatchRequest(pResultHandler){
			
			resultHandler = pResultHandler;
		
			var url = "http://206.217.143.66:8080/nextmatchinfo";
			
			var params = {
				"team" => "SH"
			};
			
			var options = {
				:method => Communications.HTTP_REQUEST_METHOD_GET,
				:headers => {
						"Content-Type" => Communications.REQUEST_CONTENT_TYPE_JSON}
			};
			
			Communications.makeWebRequest(url, params, options, new Lang.Method(NMRConnect, :onReceive));		
		}
		
		//
		function onReceive(responseCode, data)
		{
			if (responseCode == 200
			&& resultHandler != null) {
            	resultHandler.invoke(data);
            }
            else
            {
            	System.println(responseCode);
            }
		}
	}

}
