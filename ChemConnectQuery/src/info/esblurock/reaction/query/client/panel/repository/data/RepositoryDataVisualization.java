package info.esblurock.reaction.query.client.panel.repository.data;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.client.ui.MaterialToast;
import info.esblurock.reaction.query.client.panel.repository.RepositoryFileItemTextField;
import info.esblurock.reaction.data.upload.types.ChemkinMechanismFileUpload;
import info.esblurock.reaction.data.upload.types.NASAPolynomialFileUpload;
import info.esblurock.reaction.data.upload.types.ReactMolCorrespondencesFileUpload;
import info.esblurock.reaction.data.upload.types.SDFMoleculesFileUpload;
import info.esblurock.reaction.data.upload.types.ThergasMoleculeFileUpload;
import info.esblurock.reaction.data.upload.types.TransportFileUpload;

public enum RepositoryDataVisualization {
	Chemkin {

		@Override
		public ArrayList<Widget> getDataSetVisualizationItems(String datasetkey) {
			ArrayList<Widget> items = new ArrayList<Widget>();
			RepositoryFileItemTextField item1 = new RepositoryFileItemTextField(datasetkey,
					ChemkinMechanismFileUpload.class.getName());
			RepositoryFileItemTextField item2 = new RepositoryFileItemTextField(datasetkey,
					NASAPolynomialFileUpload.class.getName());
			RepositoryFileItemTextField item3 = new RepositoryFileItemTextField(datasetkey,
					TransportFileUpload.class.getName());
			items.add(item1);
			items.add(item2);
			items.add(item3);
			return items;
		}

	}, ThergasMolecules {

		@Override
		public ArrayList<Widget> getDataSetVisualizationItems(String datasetkey) {
			ArrayList<Widget> items = new ArrayList<Widget>();
			RepositoryFileItemTextField item1 = new RepositoryFileItemTextField(datasetkey,
					ThergasMoleculeFileUpload.class.getName());
			items.add(item1);
			return items;
		}
		
	}, Reaction2DMolecules {

		@Override
		public ArrayList<Widget> getDataSetVisualizationItems(String datasetkey) {
			MaterialToast.fireToast("Reaction2DMolecules: " + datasetkey);
			ArrayList<Widget> items = new ArrayList<Widget>();

			RepositoryFileItemTextField item1 = new RepositoryFileItemTextField(datasetkey,
					SDFMoleculesFileUpload.class.getName());
			items.add(item1);

			RepositoryFileItemTextField item2 = new RepositoryFileItemTextField(datasetkey,
					ReactMolCorrespondencesFileUpload.class.getName());
			items.add(item2);
			return items;
		}
	}, ReSpecThExperimentalData {

		@Override
		public ArrayList<Widget> getDataSetVisualizationItems(String datasetkey) {
			Window.alert("ReSpecThExperimentalData visualization not implemented yet");
			ArrayList<Widget> items = new ArrayList<Widget>();
			return items;
		}
		
	};

	public abstract ArrayList<Widget> getDataSetVisualizationItems(String datasetkey);

}
