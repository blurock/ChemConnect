package info.esblurock.reaction.xmlparse.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.addins.client.fileuploader.MaterialFileUploader;
import gwt.material.design.addins.client.fileuploader.base.UploadFile;
import gwt.material.design.addins.client.fileuploader.events.DragOverEvent;
import gwt.material.design.addins.client.fileuploader.events.SuccessEvent;
import gwt.material.design.addins.client.fileuploader.events.TotalUploadProgressEvent;
import gwt.material.design.client.ui.MaterialButton;
import gwt.material.design.client.ui.MaterialCollapsible;
import gwt.material.design.client.ui.MaterialImage;
import gwt.material.design.client.ui.MaterialLabel;
import gwt.material.design.client.ui.MaterialLink;
import gwt.material.design.client.ui.MaterialProgress;
import gwt.material.design.client.ui.MaterialTextArea;
import gwt.material.design.client.ui.MaterialTextBox;
import gwt.material.design.client.ui.animate.MaterialAnimator;
import gwt.material.design.client.ui.animate.Transition;
import info.esblurock.reaction.client.async.TextToDatabase;
import info.esblurock.reaction.client.async.TextToDatabaseAsync;
import info.esblurock.reaction.client.ui.view.XMLParseDocumentView;
import info.esblurock.reaction.data.chemical.respect.ReSpecTHXMLFileBase;
import info.esblurock.reaction.data.chemical.respect.ReSpecThXMLExperiment;
import info.esblurock.reaction.data.chemical.respect.ReSpecThXMLKExperiment;
import info.esblurock.reaction.xmlparse.client.UploadXMLFileCallback;
import info.esblurock.reaction.xmlparse.client.xmlfiles.XMLDataSource;

public class XMLParseDocumentImpl extends Composite implements XMLParseDocumentView {

	private static XMLParseDocumentImplUiBinder uiBinder = GWT.create(XMLParseDocumentImplUiBinder.class);

	interface XMLParseDocumentImplUiBinder extends UiBinder<Widget, XMLParseDocumentImpl> {
	}

	String name;
	Presenter listener;

	@UiField
	MaterialLink inputFiles;
	@UiField
	MaterialTextBox dataSetName;
	@UiField
	MaterialTextBox htmlText;
	@UiField
	MaterialTextArea textArea;
	@UiField
	MaterialLink sourceFiles;
	@UiField
	MaterialCollapsible setOfSourceFiles;
	@UiField
	MaterialLink dataSets;
	@UiField
	MaterialCollapsible setOfDataSets;
	@UiField
	MaterialFileUploader cardUploader;
	@UiField
	MaterialImage imgPreview;
	@UiField
	MaterialProgress progress;
	@UiField
	MaterialLabel lblName, lblSize;
	@UiField
	MaterialButton uploadButton;
	@UiField
	MaterialCollapsible indirect;
	@UiField
	MaterialCollapsible direct;
	@UiField
	MaterialCollapsible unclassified;
	@UiField
	MaterialLink indirectlabel;	
	@UiField
	MaterialLink directlabel;	
	
	int fileCount;
	
	
	public XMLParseDocumentImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		name = "XMLParse";
		init();
	}

	public XMLParseDocumentImpl(String token) {
		initWidget(uiBinder.createAndBindUi(this));
		name = token;
		init();
	}

	class SuccessHandler implements SuccessEvent.SuccessHandler<UploadFile> {
		XMLParseDocumentImpl top;
		public SuccessHandler(XMLParseDocumentImpl top) {
			this.top = top;
		}
		@Override
		public void onSuccess(SuccessEvent<UploadFile> event) {
			String filename = event.getTarget().getName();
			fileCount++;
			String message = "Upload: " + fileCount + " files";
			lblName.setText(message);
			lblSize.setText(event.getTarget().getType());
			UploadXMLFileCallback uploadCallback = new UploadXMLFileCallback(top);
			uploadCallback.setFilename(filename);
			TextToDatabaseAsync async = TextToDatabase.Util.getInstance();
			async.fileFromUploadFileTransactionSession(filename, uploadCallback);
		}
		
	}
	
	void init() {
		inputFiles.setText("Input files");
		sourceFiles.setText("File Sources");
		dataSetName.setPlaceholder("Data Set Name");
		htmlText.setPlaceholder("HTML address");
		directlabel.setText("Direct");
		indirectlabel.setText("Indirect");
		
		fileCount = 0;
		// Added the progress to card uploader
		cardUploader.addTotalUploadProgressHandler(new TotalUploadProgressEvent.TotalUploadProgressHandler() {
			@Override
			public void onTotalUploadProgress(TotalUploadProgressEvent event) {
				progress.setPercent(event.getProgress());
			}
		});
		cardUploader.addSuccessHandler(new SuccessHandler(this));
		cardUploader.addDragOverHandler(new DragOverEvent.DragOverHandler() {
			@Override
			public void onDragOver(DragOverEvent event) {
				MaterialAnimator.animate(Transition.RUBBERBAND, cardUploader, 0);
				resetUpload();
			}
		});
	}

	void resetUpload() {
		lblName.setText("Upload: ");
		fileCount = 0;		
	}
	
	@UiHandler("uploadButton")
	void onUpload(ClickEvent event) {
		resetUpload();
	}
	public void setText(String text) {
		name = text;
	}

	public String getText() {
		return name;
	}

	@Override
	public void setName(String token) {
		name = token;
	}

	@Override
	public void setPresenter(Presenter listener) {
		this.listener = listener;
	}

	public void addDirectDataSource(XMLDataSource display) {
		ReSpecThXMLKExperiment parsed = (ReSpecThXMLKExperiment) display.getParsedInfo();
		String reaction = parsed.getReaction();
		addToCategory(reaction, display,direct);
	}
	public void addIndirectDataSource(XMLDataSource display) {
		ReSpecThXMLExperiment parsed = (ReSpecThXMLExperiment) display.getParsedInfo();
		String experiment = parsed.getExperimentType();
		addToCategory(experiment, display,indirect);
	}

	private void addToCategory(String displayname, XMLDataSource display, MaterialCollapsible category) {
		int count = 0;
		int max = category.getWidgetCount();
		boolean notdone = max != 0;
		boolean added = false;
		while(notdone) {
			ReSpectExperimentCategory item = (ReSpectExperimentCategory) category.getWidget(count);
			String name = item.getText();
			if(name.equalsIgnoreCase(displayname)) {
				item.addExperiment(display);
				added = true;
				notdone = false;
			} else {
				count++;
				if(count >= max) {
					notdone = false;
				}
			}
		}
		if(!added) {
			ReSpectExperimentCategory item = new ReSpectExperimentCategory(displayname);
			item.addExperiment(display);
			category.add(item);
		}
		
	}

	
	public void addDataSource(XMLDataSource display) {
		ReSpecTHXMLFileBase parsed = display.getParsedInfo();
		if(parsed == null) {
			unclassified.add(display);
		} else {
			if(parsed.getXmlExperimentCategory().matches(ReSpecThXMLKExperiment.kexperimentS)) {
				addDirectDataSource(display);
			} else if(parsed.getXmlExperimentCategory().matches(ReSpecThXMLExperiment.experimentS)) {
				addIndirectDataSource(display);
			} else {
				unclassified.add(display);
			}
		}
	}
}
