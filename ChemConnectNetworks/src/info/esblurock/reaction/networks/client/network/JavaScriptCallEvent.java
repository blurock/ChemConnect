package info.esblurock.reaction.networks.client.network;

import com.google.gwt.user.client.Window;

public class JavaScriptCallEvent {
	static public void writeOut(String text) {
		Window.alert("Text:" + text);
	}
	
	public static native void exportStaticMethod() /*-{
    $wnd.writeout =
       $entry(@info.esblurock.reaction.networks.client.network.JavaScriptCallEvent::writeOut(*));
 }-*/;	

}
