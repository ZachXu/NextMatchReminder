using Toybox.Application;
using Toybox.Background;
using Toybox.Time;

const KEY_MATCHINFO="MATCHINFO";

(:background)
class NextMatchReminderWatchfaceApp extends Application.AppBase {

	var mView;
	

    function initialize() {
        AppBase.initialize();
    }

    // onStart() is called on application start up
    function onStart(state) {
    }

    // onStop() is called when your application is exiting
    function onStop(state) {
    }

    // Return the initial view of your application here
    function getInitialView() {
    	mView = new NextMatchReminderWatchfaceView();
    	
    	if(Toybox.System has :ServiceDelegate) {
    		Background.registerForTemporalEvent(new Time.Duration(5 * 60));
    	} 
    	
        return [ mView ];
    }
    
    //
    function onBackgroundData(data) {
    	if (data != null)
    	{
    		Application.Storage.setValue(KEY_MATCHINFO, data);
    	}
    }
    
    //Return background service instance
    function getServiceDelegate(){
        return [new NextMatchReminderWatchfaceBackgroundService()];
    }

}