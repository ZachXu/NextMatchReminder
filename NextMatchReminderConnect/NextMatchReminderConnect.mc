using Toybox.Communications;

module NextMatchReminderConnect {
	
	(:NMRConnect)
	module NMRConnect{
		
		var resultHandler;
		
		function makeNextMatchRequest(pResultHandler){
			
			resultHandler = pResultHandler;
		
			var url = "http://127.0.0.1:8080/nextmatchinfo";
			
			var params = {
				"team" => "SH"
			};
			
			var options = {
				:method => Communications.HTTP_REQUEST_METHOD_GET,
				:headers => {
						"Content-Type" => Communications.REQUEST_CONTENT_TYPE_JSON},
				:responseType => Communications.HTTP_RESPONSE_CONTENT_TYPE_JSON
			};
			
			Communications.makeWebRequest(url, params, options, method(:onReceive));		
		}
		
		function onReceive(responseCode, data)
		{
			if (responseCode == 200
			&& resultHandler != null) {
            resultHandler.invoke(data);
            }
		}
	}

}
