package info.esblurock.reaction.query.client.panel.query;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialPanel;
import info.esblurock.reaction.client.async.ReactionSearchService;
import info.esblurock.reaction.client.async.ReactionSearchServiceAsync;
import info.esblurock.reaction.client.panel.data.BaseDataPresentation;
import info.esblurock.reaction.client.panel.data.DataPresentation;
import info.esblurock.reaction.data.DatabaseObject;

public class SearchPanel extends Composite implements HasText {
	private static SearchPanelUiBinder uiBinder = GWT.create(SearchPanelUiBinder.class);
	interface SearchPanelUiBinder extends UiBinder<Widget, SearchPanel> {
	}

	@UiField
	MaterialLink link;
	@UiField
	MaterialLink searchText;
	@UiField
	MaterialLink expandnext;
	@UiField
	MaterialLink objectinfo;
	@UiField
	HTMLPanel resultpanel;
	@UiField
	MaterialLink pathtext;
	@UiField
	HTMLPanel objectinfomodal;
	
	boolean subjectB;
	boolean predicateB;
	boolean objectstringB;
	boolean objectobjectB;
	boolean searchtextB;
	DatabaseObject objectStructure;
	
	QueryPath linkpath;
	MaterialPanel toppanel;
	String originalText;
	
	public SearchPanel(MaterialPanel toppanel) {
		initWidget(uiBinder.createAndBindUi(this));
		this.toppanel = toppanel;
		init();
	}
	public SearchPanel(String firstName,MaterialPanel toppanel) {
		initWidget(uiBinder.createAndBindUi(this));
		searchText.setText(firstName);
		this.toppanel = toppanel;
		init();
	}
	protected void init() {
		subjectB = false;
		predicateB = false;
		objectstringB = false;
		objectobjectB = false;
		searchtextB = false;
		
		expandnext.setVisible(true);
		expandnext.setEnabled(true);
		objectinfo.setVisible(false);
		objectinfo.setEnabled(false);
		
		objectStructure = null;
	}

	public void setUpRDFSubject() {
		subjectB = true;
		link.setIconType(IconType.CHECK);
		expandnext.setIconType(IconType.EXPAND_MORE);
		expandnext.setVisible(true);
		expandnext.setEnabled(true);
		objectinfo.setVisible(false);
		objectinfo.setEnabled(false);
	}
	public void setUpRDFPredicate() {
		predicateB = true;
		link.setIconType(IconType.NAVIGATE_NEXT);
		expandnext.setVisible(false);
		expandnext.setEnabled(false);
		objectinfo.setVisible(false);
		objectinfo.setEnabled(false);
	}
	public void setUpRDFObject() {
		objectstringB = true;
		link.setIconType(IconType.CHECK);
		expandnext.setIconType(IconType.EXPAND_MORE);
		expandnext.setVisible(true);
		expandnext.setEnabled(true);
		objectinfo.setVisible(false);
		objectinfo.setEnabled(false);
	}
	public void setUpObject(DatabaseObject object) {
		objectobjectB = true;
		objectinfo.setIconType(IconType.INFO_OUTLINE);
		expandnext.setVisible(false);
		expandnext.setEnabled(false);
		objectinfo.setVisible(true);
		objectinfo.setEnabled(true);
		objectStructure = object;
	}
	public void setSelected(String text, String originalText, QueryPath path) {
		linkpath = new QueryPath(path,QueryPathElement.SEARCHSTRING,text);
		searchText.setText(text);
		pathtext.setText(linkpath.toString());
		this.originalText = originalText;
	}
	
	@UiHandler("expandnext")
	void infoClicked(ClickEvent e) {
		String text = originalText;
		QueryPath path = new QueryPath(linkpath,QueryPathElement.SEARCHSTRING,text);
		SearchPanel panel = new SearchPanel(text,toppanel);
		toppanel.add(panel);
		BasicObjectSearchAsTreeCallback callback = new BasicObjectSearchAsTreeCallback(path,panel);
		ReactionSearchServiceAsync async = ReactionSearchService.Util.getInstance();
		async.searchedRegisteredQueries(text,text,callback);
	}
	@UiHandler("objectinfo")
	void objectInfoClick(ClickEvent e) {
		if(objectStructure != null) {
			String classname = objectStructure.getClass().getName();
			int pos = classname.lastIndexOf('.');
			String shortname = classname.substring(pos+1);
			DataPresentation presentation = DataPresentation.valueOf(shortname);
			BaseDataPresentation display = presentation.asDisplayObject(objectStructure);
			objectinfomodal.clear();
			//HTMLPanel panel = new HTMLPanel(shortname);
			//panel.add(display);
			objectinfomodal.add(display);
			display.openModal();
		}		
	}
	public void addResultPanel(Widget scroll) {
		resultpanel.add(scroll);
	}
	public void setText(String text) {
		searchText.setText(text);
	}

	public String getText() {
		return searchText.getText();
	}

}
